package com.aloha.entity;

/**
 * @author Aloha 2022-03-29 16:09
 */
public class Manager {
    private Integer id;
    private String managerId;
    private String password;

    public Manager() {
    }

    public Manager(Integer id, String managerId, String password) {
        this.id = id;
        this.managerId = managerId;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", managerId='" + managerId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
