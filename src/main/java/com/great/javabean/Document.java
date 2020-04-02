package com.great.javabean;

/**
 * ClassName: Document <br/>
 * Description: <br/>
 * date: 2020/4/1 10:28<br/>
 *
 * @author lenovo<br />
 * @since JDK 1.8
 */

import org.springframework.stereotype.Component;

@Component
public class Document {

    private Integer document_id;
    private String document_head;//文件名
    private String document_synopsis;//简介
    private Integer document_type_id;//文档类型
    private Double document_integral;//下载文档需要的积分
    private String document_date;//上传时间
    private Integer document_state;//审核状态
    private Integer document_down_num;//下载次数
    private Integer user_id;//上传用户id
    private String document_type_name;
    private String user_name;

    public Document() {
    }

    public Document(Integer document_id, String document_head, String document_synopsis, Integer document_type_id, Double document_integral, String document_date, Integer document_state, Integer document_down_num, Integer user_id, String document_type_name, String user_name) {
        this.document_id = document_id;
        this.document_head = document_head;
        this.document_synopsis = document_synopsis;
        this.document_type_id = document_type_id;
        this.document_integral = document_integral;
        this.document_date = document_date;
        this.document_state = document_state;
        this.document_down_num = document_down_num;
        this.user_id = user_id;
        this.document_type_name = document_type_name;
        this.user_name = user_name;
    }

    public String getDocument_type_name() {
        return document_type_name;
    }

    public void setDocument_type_name(String document_type_name) {
        this.document_type_name = document_type_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getDocument_id() {
        return document_id;
    }

    public void setDocument_id(Integer document_id) {
        this.document_id = document_id;
    }

    public String getDocument_head() {
        return document_head;
    }

    public void setDocument_head(String document_head) {
        this.document_head = document_head;
    }

    public String getDocument_synopsis() {
        return document_synopsis;
    }

    public void setDocument_synopsis(String document_synopsis) {
        this.document_synopsis = document_synopsis;
    }

    public Integer getDocument_type_id() {
        return document_type_id;
    }

    public void setDocument_type_id(Integer document_type_id) {
        this.document_type_id = document_type_id;
    }

    public Double getDocument_integral() {
        return document_integral;
    }

    public void setDocument_integral(Double document_integral) {
        this.document_integral = document_integral;
    }

    public String getDocument_date() {
        return document_date;
    }

    public void setDocument_date(String document_date) {
        this.document_date = document_date;
    }

    public Integer getDocument_state() {
        return document_state;
    }

    public void setDocument_state(Integer document_state) {
        this.document_state = document_state;
    }

    public Integer getDocument_down_num() {
        return document_down_num;
    }

    public void setDocument_down_num(Integer document_down_num) {
        this.document_down_num = document_down_num;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Document{" +
                "document_id=" + document_id +
                ", document_head='" + document_head + '\'' +
                ", document_synopsis='" + document_synopsis + '\'' +
                ", document_type_id=" + document_type_id +
                ", document_integral=" + document_integral +
                ", document_date='" + document_date + '\'' +
                ", document_state=" + document_state +
                ", document_down_num=" + document_down_num +
                ", user_id=" + user_id +
                ", document_type_name='" + document_type_name + '\'' +
                ", user_name='" + user_name + '\'' +
                '}';
    }
}
