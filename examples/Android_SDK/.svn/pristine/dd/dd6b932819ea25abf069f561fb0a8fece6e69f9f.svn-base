package com.ihealth.communication.task;

import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import android.content.Context;

public class iHDTaskQueue {

	private BlockingQueue<iHDtask> mIHDTaskQueue;
	private Worker mWorker;
	
	public iHDTaskQueue(Context context) {
		mIHDTaskQueue = new LinkedBlockingQueue<iHDtask>();
		mWorker = new Worker(mIHDTaskQueue);
		mWorker.run();
	}
	
	public void destory(){
		if(mIHDTaskQueue != null){
			mIHDTaskQueue.clear();
			mIHDTaskQueue = null;
		}
	}

	public void add(AbstractTask task, String action, Object... objs){
		iHDtask tasks = new iHDtask(task, action, objs);
		mIHDTaskQueue.add(tasks);
	}
	
	class Worker implements Runnable{

		private final Object mPauseLock;
		private final Object mStopLock;
		
		private boolean mPauseFlag;
		private boolean mStopFlag;
		
		private BlockingQueue<iHDtask> taskQueue;
		
		public Worker(BlockingQueue<iHDtask> queue){
			this.taskQueue = queue;
			
			mPauseLock = new Object();
			mPauseFlag = false;
			
			mStopLock = new Object();
			mStopFlag = false;
		}
		
		public void stopWorker(){
			synchronized (mStopLock) {
				mStopFlag = true;
			}
		}
		
		public void resumeWorker(){
			synchronized (mPauseLock) {
				mPauseFlag = false;
				mPauseLock.notifyAll();
			}
		}
		
		private void pauseWorker() {
			synchronized (mPauseLock) {
				if (mPauseFlag) {
					try {
						mPauseLock.wait();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		@Override
		public void run() {
			
			while(!mStopFlag){
				if(taskQueue.size() > 0){
					exec(taskQueue.poll());
					pauseWorker();
				}
			}
		}
		
		private void exec(iHDtask tasks){
			Class<?> className = null;
	        try {
	        	className = Class.forName("AbstractTask");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        Method method = null;
	        try{
	        	String methodName = tasks.getmTaskMethod();
	        	Object[] paraName = tasks.getmTaskPara();
	        	
	        	if(paraName == null){
	        		method = className.getMethod(methodName);
	        		method.invoke(className.newInstance());
	        	}else{
	        		Class<?>[] types = getParaTypes(paraName);
	        		method = className.getMethod(methodName, types);
	        		method.invoke(className.newInstance(), paraName);
	        	}
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
		}
		
		private Class<?>[] getParaTypes(Object[] paraName){
			int len = paraName.length;
			Class<?>[] typs = new Class[len];
			int index = 0;
			for (Object object : paraName) {
				if(object instanceof Byte){
					typs[index] = byte.class;
					index += 1;
				}else if(object instanceof Byte[]){
					typs[index] = byte[].class;
					index += 1;
				}else if(object instanceof Integer){
					typs[index] = int.class;
					index += 1;
				}else if(object instanceof Integer[]){
					typs[index] = int[].class;
					index += 1;
				}else if(object instanceof Long){
					typs[index] = long.class;
					index += 1;
				}else if(object instanceof Long[]){
					typs[index] = long[].class;
					index += 1;
				}else if(object instanceof Float){
					typs[index] = float.class;
					index += 1;
				}else if(object instanceof Float[]){
					typs[index] = float[].class;
					index += 1;
				}else if(object instanceof Double){
					typs[index] = double.class;
					index += 1;
				}else if(object instanceof Double[]){
					typs[index] = double[].class;
					index += 1;
				}else if(object instanceof Boolean){
					typs[index] = boolean.class;
					index += 1;
				}else if(object instanceof Boolean[]){
					typs[index] = boolean[].class;
					index += 1;
				}else if(object instanceof String){
					typs[index] = String.class;
					index += 1;
				}else if(object instanceof String[]){
					typs[index] = String[].class;
					index += 1;
				}
			}
			return typs;
		} 
		
	}
	
}
