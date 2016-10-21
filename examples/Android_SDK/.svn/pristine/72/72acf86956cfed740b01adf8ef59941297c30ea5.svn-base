package com.ihealth.communication.utils;


import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.ihealth.communication.utils.Log;

public class DataThreadPoolManager {
	private static final String TAG = "DataThreadPoolManager";
	
	private DataThreadPoolManager(){};
	//单例 静态内部类
	private static class SingletonHolder {
		private static final DataThreadPoolManager INSTANCE = new DataThreadPoolManager();
	}
	
	public static final DataThreadPoolManager getInstance() {
		return SingletonHolder.INSTANCE;
	}
	
	//线程池维护线程的最小数量
	private final static int CORE_POOL_SIZE = 1; 
	
	//线程池维护线程的最大数量
	private final static int MAX_POOL_SIZE = 1;
	
	//线程池维护线程允许的空闲时间
	private final static int KEEP_ALIVE_TIME = 0;
	
	//线程池所使用的缓冲队列的大小
	private final static int WORK_QUEUE_SIZE = 10;
	
	//任务调度周期
	private final static int TASK_QOS_PERIOD = 10;
	
	//消息缓冲队列
	Queue<Runnable> taskQueue = new LinkedList<Runnable>();
	
	/**
	 * 线程池超过界限时，将任务加入缓冲队列
	 */
	final RejectedExecutionHandler handler = new RejectedExecutionHandler() {
		
		@Override
		public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
			// TODO Auto-generated method stub
			taskQueue.offer(runnable);
		}
	};
	
	/**
	 * 将缓冲队列中的任务重新加载到线程池
	 */
	final Runnable accessBufferThread = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if (hasMoreAcquire()) {
				threadPool.execute(taskQueue.poll());
			}
		}
	};
	
	/**
	 * 线程池
	 */
	 final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, 
													TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(WORK_QUEUE_SIZE), 
														this.handler);
	 /**
	  * 消息队列检查
	  */
	 private boolean hasMoreAcquire() {
		 return !taskQueue.isEmpty();
	 }
	 
	 /**
	  * 创建一个调度线程池
	  */
	 final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	 
	 /** 
	  * 通过调度线程周期性的执行缓冲队列中任务 
	  */  
	 final ScheduledFuture<?> taskHandler = scheduler.scheduleAtFixedRate(accessBufferThread, 0, TASK_QOS_PERIOD, TimeUnit.MILLISECONDS);
	 
	 /**
	  * 将任务添加到线程池中执行
	  * @param task 所要执行的任务
	  */
	 public void addExecuteTask(Runnable task) {
		if (task != null) {
			threadPool.execute(task);
			Log.v(TAG, "add a task and execute and active thread num = "+threadPool.getActiveCount());
		} 
	 }
}
