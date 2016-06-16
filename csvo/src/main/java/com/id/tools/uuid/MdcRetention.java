/**
 * <br>项目名: csvo
 * <br>文件名: MdcRetention.java
 * <br>Copyright 2016
 */
package com.id.tools.uuid;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.MDC;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;

/**
 * Provides services to execute {@link Runnable}s and {@link Callable}s with
 * {@link MDC} retention.
 * 
 * <p>
 * In general <code>MDC</code> belongs to a specific {@link Thread}. Somethimes
 * there is need to run code in the same <code>MDC</code>, but on a different
 * thread. This is especially the case with {@link Executor}s that execute code
 * in thread pools.
 * 
 * <p>
 * This class helps transferring the submitter class's <code>MDC</code> to new
 * threads. It does this by wrapping <code>Runnable</code>s and
 * <code>Callable</code>s in {@link MdcRetainingRunnable} and
 * {@link MdcRetainingCallable}. These classes do two things:
 * <ol>
 * <li>Upon creation they store the original <code>MDC</code> of the initiating
 * thread
 * <li>Upon execution of {@link Runnable#run()}/{@link Callable#call()} they set
 * the executor thread's <code>MDC</code> to the stored version, and restore it
 * after the wrapped execution is finished.
 * </ol>
 * 
 * <p>
 * There are two ways to use the static methods provided by the class:
 * <ul>
 * <li>You can use a plain-vanilla {@link Executor}, and wrap your code manually
 * with {@link #wrap(Runnable)} and {@link #wrap(Callable)} before submitting it
 * to the <code>Executor</code>.
 * <li>You can wrap your <code>Executor</code> itself with
 * {@link #wrap(Executor)}, {@link #wrap(ExecutorService)} and
 * {@link #wrap(ScheduledExecutorService)}. Then you can supply plain-vanilla
 * <code>Runnable</code>s and <code>Callable</code>s to the wrapped
 * <code>Executor</code> instance.
 * </ul>
 * 
 * <p>
 * <strong>Note:</strong>The recommended way is to use the <code>wrap()</code> methods, but you can also use the <code>MdcRetaining*</code> classes straight awway if you wish.
 * 
 * @author lorantp
 */
public final class MdcRetention {
	private MdcRetention() {
		// Utility class
	}

	public static Runnable wrap(final Runnable delegate) {
		return new MdcRetainingRunnable() {
			@Override
			protected void runInContext() {
				delegate.run();
			}
		};
	}

	public static <T> Callable<T> wrap(final Callable<T> delegate) {
		return new MdcRetainingCallable<T>() {
			@Override
			protected T callInContext() throws Exception {
				return delegate.call();
			}
		};
	}

	public static Executor wrap(Executor delegate) {
		return new MdcRetainingExecutor(delegate);
	}

	public static ExecutorService wrap(ExecutorService delegate) {
		return new MdcRetainingExecutorService(delegate);
	}

	public static ScheduledExecutorService wrap(ScheduledExecutorService delegate) {
		return new MdcRetainingScheduledExecutorService(delegate);
	}

	private static abstract class MdcRetentionSupport {
		protected final Map<?, ?> originalMdc;

		protected MdcRetentionSupport() {
			Map<?, ?> originalMdc = MDC.getCopyOfContextMap();
			this.originalMdc = originalMdc == null ? Collections.emptyMap() : originalMdc;
		}
	}

	public static abstract class MdcRetainingRunnable extends MdcRetentionSupport implements Runnable {

		@Override
		public final void run() {
			Map<?, ?> currentMdc = MDC.getCopyOfContextMap();
			MDC.setContextMap(originalMdc);
			try {
				runInContext();
			} finally {
				MDC.setContextMap(currentMdc);
			}
		}

		abstract protected void runInContext();
	}

	public static abstract class MdcRetainingCallable<T> extends MdcRetentionSupport implements Callable<T> {

		@Override
		public final T call() throws Exception {
			Map<?, ?> currentMdc = MDC.getCopyOfContextMap();
			MDC.setContextMap(originalMdc);
			try {
				return callInContext();
			} finally {
				MDC.setContextMap(currentMdc);
			}
		}

		abstract protected T callInContext() throws Exception;
	}

	public static class MdcRetainingExecutor implements Executor {

		private final Executor delegate;

		public MdcRetainingExecutor(Executor delegate) {
			this.delegate = delegate;
		}

		@Override
		public void execute(Runnable command) {
			getDelegate().execute(wrap(command));
		}

		protected Executor getDelegate() {
			return delegate;
		}
	}

	public static class MdcRetainingExecutorService extends MdcRetainingExecutor implements ExecutorService {

		public MdcRetainingExecutorService(ExecutorService executorService) {
			super(executorService);
		}

		@Override
		protected ExecutorService getDelegate() {
			return (ExecutorService) super.getDelegate();
		}

		@Override
		public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
			return getDelegate().awaitTermination(timeout, unit);
		}

		@Override
		public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout,
				TimeUnit unit) throws InterruptedException {
			return getDelegate().invokeAll(wrapAll(tasks), timeout, unit);
		}

		@Override
		public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
				throws InterruptedException {
			return getDelegate().invokeAll(wrapAll(tasks));
		}

		@Override
		public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
				throws InterruptedException, ExecutionException, TimeoutException {
			return getDelegate().invokeAny(wrapAll(tasks), timeout, unit);
		}

		@Override
		public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException,
				ExecutionException {
			return getDelegate().invokeAny(wrapAll(tasks));
		}

		@Override
		public boolean isShutdown() {
			return getDelegate().isShutdown();
		}

		@Override
		public boolean isTerminated() {
			return getDelegate().isTerminated();
		}

		@Override
		public void shutdown() {
			getDelegate().shutdown();
		}

		@Override
		public List<Runnable> shutdownNow() {
			return getDelegate().shutdownNow();
		}

		@Override
		public <T> Future<T> submit(Callable<T> task) {
			return getDelegate().submit(wrap(task));
		}

		@Override
		public <T> Future<T> submit(Runnable task, T result) {
			return getDelegate().submit(wrap(task), result);
		}

		@Override
		public Future<?> submit(Runnable task) {
			return getDelegate().submit(wrap(task));
		}

		private static <T> Collection<? extends Callable<T>> wrapAll(
				Collection<? extends Callable<T>> delegates) {
			return Collections2.transform(delegates, new Function<Callable<T>, Callable<T>>() {
				@Override
				public Callable<T> apply(Callable<T> delegate) {
					return wrap(delegate);
				}
			});
		}
	}

	public static class MdcRetainingScheduledExecutorService extends MdcRetainingExecutorService implements
			ScheduledExecutorService {

		public MdcRetainingScheduledExecutorService(ScheduledExecutorService executorService) {
			super(executorService);
		}

		@Override
		protected ScheduledExecutorService getDelegate() {
			return (ScheduledExecutorService) super.getDelegate();
		}

		@Override
		public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
			return getDelegate().schedule(wrap(callable), delay, unit);
		}

		@Override
		public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
			return getDelegate().schedule(wrap(command), delay, unit);
		}

		@Override
		public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period,
				TimeUnit unit) {
			return getDelegate().scheduleAtFixedRate(wrap(command), initialDelay, period, unit);
		}

		@Override
		public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay,
				TimeUnit unit) {
			return getDelegate().scheduleWithFixedDelay(wrap(command), initialDelay, delay, unit);
		}
	}
}
