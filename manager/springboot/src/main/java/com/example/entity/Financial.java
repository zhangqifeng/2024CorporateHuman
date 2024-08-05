package com.example.entity;

import java.io.Serializable;

public class Financial implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    private Integer departmentId;
    private String time;
    private Double price;
    private String name;

    private String departmentName;

    public Financial() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Financial(Integer id, Integer departmentId, String time, Double price, String name, String departmentName) {
        this.id = id;
        this.departmentId = departmentId;
        this.time = time;
        this.price = price;
        this.name = name;
        this.departmentName = departmentName;
    }
}