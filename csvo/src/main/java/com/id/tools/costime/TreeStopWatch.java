package com.id.tools.costime;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 可重入的计时器 支持树状结构日志打印,一次打印统计, 非线程安全
 * 支持
 *      start()
 *              start()
 *                      start()stop()
 *                      start()stop()
 *               stop()
 *      stop()
 * 结构的调用
 * 需要保证对称性
 */
public class TreeStopWatch {
	public static void main(String[] args) {
/*		TreeStopWatch tsw = new TreeStopWatch();
		tsw.start("1");
			tsw.start("1-1");
				tsw.start("1-1-1");
				tsw.stop();
				tsw.start("1-1-2");
				tsw.stop();
				tsw.start("1-1-3");
				tsw.stop();
				tsw.start("1-1-4");
				tsw.stop();
			tsw.stop();			
		tsw.stop();
		System.out.println(tsw.formatResult());*/
	}
	
    private static String BR = "\n";

    /**
     * 任务列表
     */
    private ArrayList<LinkedList<TaskInfo>> taskList = new ArrayList<LinkedList<TaskInfo>>(5);
    
    {
    	taskList.add(new LinkedList<TaskInfo>());
    	taskList.add(new LinkedList<TaskInfo>());
    	taskList.add(new LinkedList<TaskInfo>());
    	taskList.add(new LinkedList<TaskInfo>());
    	taskList.add(new LinkedList<TaskInfo>());
    }
    
    /**
     * 请求cmd名称
     */
    private String cmd = "unset";
    
    /**
     * uuid
     */
//    private String uuidString = CosttimeUtil.UUID_DEFAULT_VALUE;

    /**
     * 执行的任务级别 默认0 根节点   taskLevel<5 最多支持5层结构
     */
    private int taskLevel = -1;

    /**
     * 当前任务名称
     */
    private String currentTaskName;

    /**
     * 总任务数(调用stop)
     */
    private int taskCount = 0;
    
    /**
     * ice总耗时
     */
    private long allIceCost = 0;
    
    /**
     * inceptor总耗时
     */
    private long inceptorCost = 0;
    
    /**
	 * @return the cmd
	 */
	public String getCmd() {
		return cmd;
	}

	/**
	 * @param cmd the cmd to set
	 */
	public void setCmd(String cmd) {
//		uuidString = ThreadLocalConstant.REQUEST_UUID.get();
//		uuidString = MDC.get(UuidHolder.MDC_UUID_KEY);
		this.cmd = cmd;
	}

	/**
     * 任务启动
     * @param taskName 任务名称
     * @throws IllegalStateException
     */
    public void start(String taskName){
        taskLevel++;
        if (taskLevel==5) {
            throw new IllegalStateException("任务结构最多支持5层!");
        }
        taskList.get(taskLevel).addLast(new TaskInfo(taskName));
        this.currentTaskName = taskName;
    }

    /**
     * 结束上次启动的任务
     * @throws IllegalStateException
     */
    public void stop(){
        if (taskLevel==-1) {
            throw new IllegalStateException("任务调用非对称,请先调用start()!");
        }
        taskList.get(taskLevel).getLast().stop();
        ++this.taskCount;
        this.currentTaskName = null;
        taskLevel--;
    }

    /**
     * 返回按树结构格式化的统计信息
     * @return 统计计时信息
     */
    public String formatResult(){
        if (taskLevel!=-1) {
            throw new IllegalStateException("任务调用未结束,不能统计!");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BR).append("-------------------------------------------[CMD:").append(this.cmd).append(" UUID:").append("").append(" BEGIN]------------------------------------------").append(BR);
        //获取inceptorCost
        inceptorCost = taskList.get(0).get(0).getTaskTime();
        taskList.get(0).remove(0);
        //计算allIceCost
        for(int i=0;i<5;i++){
            for(TaskInfo taskInfo:taskList.get(i)){
            	allIceCost=allIceCost+taskInfo.getTaskTime();
                stringBuilder.append(format(i)).append(String.format("%s [cost time:%dms]",taskInfo.taskName,taskInfo.getTaskTime())).append(BR);
            }
        }
        DecimalFormat df = new DecimalFormat("#.##");
        double db = Double.parseDouble(df.format((double)(inceptorCost-allIceCost)/inceptorCost));  
        stringBuilder.append(String.format("本次请求总耗时:%dms; 后台ICE总耗时:%dms; 接入系统耗时比:%s", inceptorCost,allIceCost,db)).append(BR);
        stringBuilder.append("--------------------------------------------[CMD:").append(this.cmd).append(" UUID:").append("").append(" END]-------------------------------------------");
        return stringBuilder.toString();
    }
    
    public String printSingleResult(){
    	return String.format("%s [cost time:%dms]",taskList.get(0).get(0).taskName,taskList.get(0).get(0).getTaskTime());
    }
    
    private String format(int level){
        switch (level){
            case 0:
                return "--";
            case 1:
                return "----";
            case 2:
                return "------";
            case 3:
                return "--------";
            case 4:
                return "----------";
            default:
                return "";
        }
    }

    /**
     * 任务信息
     */
    public static final class TaskInfo {
        //任务名称
        final String taskName;
        //任务开始时间
        final long beginTime;
        //任务结束时间
        long endTime;

        TaskInfo(String taskName) {
            this.taskName = taskName;
            this.beginTime = System.currentTimeMillis();
        }

        void stop(){
            this.endTime = System.currentTimeMillis();
        }

        long getTaskTime(){
            return this.endTime-this.beginTime;
        }
    }
}
