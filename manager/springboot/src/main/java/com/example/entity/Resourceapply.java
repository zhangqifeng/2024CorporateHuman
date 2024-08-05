package com.example.entity;

import java.io.Serializable;

public class Resourceapply implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    private Integer employeeId;
    private Integer departmentId;
    private Integer resourcesId;
    private Integer num;
    private String time;
    private String process;
    private String status;
    private String note;

    private String employeeName;
    private String departmentName;
    private String resourceName;

    public Resourceapply() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(Integer resourcesId) {
        this.resourcesId = resourcesId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Resourceapply(Integer id, Integer employeeId, Integer departmentId, Integer resourcesId, Integer num, String time, String process, String status, String note, String employeeName, String departmentName, String resourceName) {
        this.id = id;
        this.employeeId = employeeId;
        this.departmentId = departmentId;
        this.resourcesId = resourcesId;
        this.num = num;
        this.time = time;
        this.process = process;
        this.status = status;
        this.note = note;
        this.employeeName = employeeName;
        this.departmentName = departmentName;
        this.resourceName = resourceName;
    }
}