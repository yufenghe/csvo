/**
 * <br>项目名: csvo
 * <br>文件名: MdcRetentionTest.java
 * <br>Copyright 2016
 */
package com.id.tools.uuid;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.MDC;

import com.google.common.base.Throwables;
import com.google.common.collect.Lists;

public class MdcRetentionTest {
	private static final String[] TEST_DATA = { "alma", "korte", "szilva", "barack", "cseresznye" };
	private ExecutorService executor;

	@Before
	public void init() {
		// A thread pool with 2 threads all the time
		executor = Executors.newFixedThreadPool(2);
	}

	@Test
	public void testWithNormalExecutor() throws Exception {
		runTests(executor, TEST_DATA);
	}

	@Test
	public void testWithWrappedExecutor() throws Exception {
		runTests(MdcRetention.wrap(executor), TEST_DATA);
	}

	private void runTests(ExecutorService executor, String... cases) throws Exception {
		System.out.println("=============================================");
		System.out.println("Executor: " + executor.getClass().getSimpleName());
		System.out.println("=============================================");
		// Initialize MDC
		MDC.put("data", "original");

		List<Future<?>> futures = Lists.newArrayList();

		for (final String data : cases) {
			// Put new data into the MDC
			MDC.put("data", data);
			Future<?> future = executor.submit(new Runnable() {
				@Override
				public void run() {
					String dataFromMdc = MDC.get("data");

					// Show some info
					System.out.println("---------------------------------------------");
					System.out.println("Thread:   " + Thread.currentThread().getName());
					System.out.println("MDC:      " + dataFromMdc);
					System.out.println("Expected: " + data + (data.equals(dataFromMdc) ? "" : " <--- NOT GOOD!"));

					// Check our assertion
					assertThat("Stale data in MDC on thread " + Thread.currentThread().getName(),
							dataFromMdc, equalTo(data));

					// We wait a bit so that other threads get used as well
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// Ignore
					}
				}
			});
			futures.add(future);
		}

		// Fail if any of the futures have thrown an exception
		for (Future<?> future : futures) {
			try {
				future.get();
			} catch (ExecutionException ex) {
				throw Throwables.propagate(ex.getCause());
			}
		}
	}
}
