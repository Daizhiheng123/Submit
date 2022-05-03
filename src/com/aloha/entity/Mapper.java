package com.aloha.entity;

/**
 * @author Aloha 2022-03-29 17:11
 */
public class Mapper {
    private Integer taskId;
    private Integer userId;
    private String name;

    public Mapper(Integer taskId, Integer userId, String name) {
        this.taskId = taskId;
        this.userId = userId;
        this.name = name;
    }

    public Mapper() {
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Mapper{" +
                "taskId=" + taskId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                '}';
    }
}
