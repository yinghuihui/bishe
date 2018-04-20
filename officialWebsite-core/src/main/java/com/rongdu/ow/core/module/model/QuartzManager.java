package com.rongdu.ow.core.module.model;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 类名：QuartzManager <br/>
 * 功能：<br/>
 * 详细：Quartz增、删、改工具类 <br/>
 * 作者： Tliu <br/>
 * 日期：2015-7-17 <br/>
 */
public class QuartzManager {
 
	private static final Logger logger = LoggerFactory.getLogger(QuartzManager.class);
 
	public static String JOB_GROUP_NAME = "EXTJWEB_JOBGROUP_NAME";
	
    private static Scheduler scheduler = null;
     
    public static final String DATA_KEY = "STATE_DATA";
     
    public QuartzManager (){
        try {
            scheduler = new StdSchedulerFactory().getScheduler();
            logger.info("初始化调度器 ");
        } catch (SchedulerException ex) {
            logger.error("初始化调度器=> [失败]:" + ex.getLocalizedMessage());
        }
    }   
    public void addJob(String name, String group, Class<? extends Job> clazz,String cronExpression) {                 
        try {       
            //构造任务
            JobDetail job = newJob(clazz)
                    .withIdentity(name, group)                  
                    .build();
             
            //构造任务触发器
            Trigger trg = newTrigger()
                    .withIdentity(name, group)
                    .withSchedule(cronSchedule(cronExpression))
                    .build();       
             
            //将作业添加到调度器
            scheduler.scheduleJob(job, trg);
            logger.info("创建作业=> [作业名称：" + name + " 作业组：" + group + "] ");
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.error("创建作业=> [作业名称：" + name + " 作业组：" + group + "]=> [失败]");
        }
    }
     
    public void removeJob(String name, String group){
        try {
            TriggerKey tk = TriggerKey.triggerKey(name, group);
            scheduler.pauseTrigger(tk);//停止触发器  
            scheduler.unscheduleJob(tk);//移除触发器
            JobKey jobKey = JobKey.jobKey(name, group);
            scheduler.deleteJob(jobKey);//删除作业
            logger.info("删除作业=> [作业名称：" + name + " 作业组：" + group + "] ");
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.error("删除作业=> [作业名称：" + name + " 作业组：" + group + "]=> [失败]");
        }
    }
     
    public void pauseJob(String name, String group){
        try {
            JobKey jobKey = JobKey.jobKey(name, group);
            scheduler.pauseJob(jobKey);
            logger.info("暂停作业=> [作业名称：" + name + " 作业组：" + group + "] ");
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.error("暂停作业=> [作业名称：" + name + " 作业组：" + group + "]=> [失败]");
        }
    }
     
    public void resumeJob(String name, String group){
        try {
            JobKey jobKey = JobKey.jobKey(name, group);         
            scheduler.resumeJob(jobKey);
            logger.info("恢复作业=> [作业名称：" + name + " 作业组：" + group + "] ");
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.error("恢复作业=> [作业名称：" + name + " 作业组：" + group + "]=> [失败]");
        }       
    }
     
    public void modifyTime(String name, String group, String cronExpression){       
        try {
            TriggerKey tk = TriggerKey.triggerKey(name, group);
            //构造任务触发器
            Trigger trg = newTrigger().withIdentity(name, group).withSchedule(cronSchedule(cronExpression)).build();       
            scheduler.rescheduleJob(tk, trg);
            logger.info("修改作业触发时间=> [作业名称：" + name + " 作业组：" + group + "] ");
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.error("修改作业触发时间=> [作业名称：" + name + " 作业组：" + group + "]=> [失败]");
        }
    }
 
    public void start() {
        try {
            scheduler.start();
            logger.info("启动调度器 ");
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.error("启动调度器=> [失败]");
        }
    }
 
    public void shutdown() {
        try {
            scheduler.shutdown();
            logger.info("停止调度器 ");
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.error("停止调度器=> [失败]");
        }
    }
    
	public void startJobNow(String jobName) {
		try {
			JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
			scheduler.triggerJob(jobKey);
		} catch (SchedulerException e) {
			logger.error(e.getMessage(), e);
		}

	}    
    
}