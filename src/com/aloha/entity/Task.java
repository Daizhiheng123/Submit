package com.aloha.entity;

/**
 * @author Aloha 2022-03-29 17:11
 */
public class Task {
    private Integer taskId;
    private Integer managerId;
    private Integer state; //默认是 1,表示待提交；0表示已经截止
    private String describe;

    public Task() {
        this.state = 0;
    }

    public Task(Integer taskId, Integer managerId, Integer state, String describe) {
        this.taskId = taskId;
        this.managerId = managerId;
        this.state = state;
        this.describe = describe;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", managerId=" + managerId +
                ", state=" + state +
                ", describe='" + describe + '\'' +
                '}';
    }
}
