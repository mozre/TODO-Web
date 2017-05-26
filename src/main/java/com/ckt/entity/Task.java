package com.ckt.entity;

/**
 * Created by admin on 2017/5/25.
 */
public class Task {

    private String taskId;
    //任务标题
    private String taskTitle;
    //任务内容
    private String taskContent;
    //任务创建人
    private String createUerId;
    //任务执行人
    private String execUserId;
    //任务类别
    private int taskType;
    //任务优先级
    private int taskPriority;
    //任务状态
    private int taskStatus;
    //任务开始时间
    private long taskStartTime;
    //任务预计花费时间EventTask
    private float taskPredictTime;
    //任务提醒时间
    private long taskRemindTime;
    //任务实际花费时间
    private float taskRealSpendTime;
    //任务所属计划
    private String planId;
    //任务最新更新时间
    private long taskUpdateTime;


    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public String getCreateUerId() {
        return createUerId;
    }

    public void setCreateUerId(String createUerId) {
        this.createUerId = createUerId;
    }

    public String getExecUserId() {
        return execUserId;
    }

    public void setExecUserId(String execUserId) {
        this.execUserId = execUserId;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public int getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(int taskPriority) {
        this.taskPriority = taskPriority;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public long getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskStartTime(long taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public float getTaskPredictTime() {
        return taskPredictTime;
    }

    public void setTaskPredictTime(float taskPredictTime) {
        this.taskPredictTime = taskPredictTime;
    }

    public long getTaskRemindTime() {
        return taskRemindTime;
    }

    public void setTaskRemindTime(long taskRemindTime) {
        this.taskRemindTime = taskRemindTime;
    }

    public float getTaskRealSpendTime() {
        return taskRealSpendTime;
    }

    public void setTaskRealSpendTime(float taskRealSpendTime) {
        this.taskRealSpendTime = taskRealSpendTime;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public long getTaskUpdateTime() {
        return taskUpdateTime;
    }

    public void setTaskUpdateTime(long taskUpdateTime) {
        this.taskUpdateTime = taskUpdateTime;
    }
}
