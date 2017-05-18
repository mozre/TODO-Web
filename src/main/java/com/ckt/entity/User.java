package com.ckt.entity;
/**
 * Created by mozre on 2017/5/18.
 */

public class User {

    private Integer mem_id;

    private String mem_name;

    private String mem_password;

    private String mem_phone_num;

    private Integer men_level;


    @Override
    public String toString() {
        return "User{" +
                "mem_id=" + mem_id +
                ", mem_name='" + mem_name + '\'' +
                ", mem_password='" + mem_password + '\'' +
                ", mem_phone_num=" + mem_phone_num +
                ", men_level=" + men_level +
                '}';
    }

    public User() {
    }


    public Integer getMem_id() {
        return mem_id;
    }

    public String getMem_name() {
        return mem_name;
    }

    public String getMem_password() {
        return mem_password;
    }

    public String getMem_phone_num() {
        return mem_phone_num;
    }

    public Integer getMen_level() {
        return men_level;
    }

    public void setMen_level(Integer men_level) {
        this.men_level = men_level;
    }

    public void setMem_id(Integer mem_id) {
        this.mem_id = mem_id;
    }

    public void setMem_name(String mem_name) {
        this.mem_name = mem_name;
    }

    public void setMem_password(String mem_password) {
        this.mem_password = mem_password;
    }

    public void setMem_phone_num(String mem_phone_num) {
        this.mem_phone_num = mem_phone_num;
    }


    public User(Integer mem_id, String mem_name, String mem_password, String mem_phone_num, Integer men_level) {
        this.mem_id = mem_id;
        this.mem_name = mem_name;
        this.mem_password = mem_password;
        this.mem_phone_num = mem_phone_num;
        this.men_level = men_level;
    }
}