package com.great.javabean;

import org.springframework.stereotype.Component;

/**
 * ClassName: SystemLog <br/>
 * Description: <br/>
 * date: 2020/3/25 21:50<br/>
 *
 * @author lenovo<br />
 * @since JDK 1.8
 */
@Component
public class SystemLog {

    private Integer log_id;
    private String log_operator;
    private String log_operator_role;
    private String log_operator_ip;
    private String log_type;
    private String log_content;
    private String log_result;
    private String log_date;

    public SystemLog() {
    }

    public SystemLog(Integer log_id, String log_operator, String log_operator_role, String log_operator_ip, String log_type, String log_content, String log_result, String log_date) {
        this.log_id = log_id;
        this.log_operator = log_operator;
        this.log_operator_role = log_operator_role;
        this.log_operator_ip = log_operator_ip;
        this.log_type = log_type;
        this.log_content = log_content;
        this.log_result = log_result;
        this.log_date = log_date;
    }

    public Integer getLog_id() {
        return log_id;
    }

    public void setLog_id(Integer log_id) {
        this.log_id = log_id;
    }

    public String getLog_operator() {
        return log_operator;
    }

    public void setLog_operator(String log_operator) {
        this.log_operator = log_operator;
    }

    public String getLog_operator_role() {
        return log_operator_role;
    }

    public void setLog_operator_role(String log_operator_role) {
        this.log_operator_role = log_operator_role;
    }

    public String getLog_operator_ip() {
        return log_operator_ip;
    }

    public void setLog_operator_ip(String log_operator_ip) {
        this.log_operator_ip = log_operator_ip;
    }

    public String getLog_type() {
        return log_type;
    }

    public void setLog_type(String log_type) {
        this.log_type = log_type;
    }

    public String getLog_content() {
        return log_content;
    }

    public void setLog_content(String log_content) {
        this.log_content = log_content;
    }

    public String getLog_result() {
        return log_result;
    }

    public void setLog_result(String log_result) {
        this.log_result = log_result;
    }

    public String getLog_date() {
        return log_date;
    }

    public void setLog_date(String log_date) {
        this.log_date = log_date;
    }

    @Override
    public String toString() {
        return "SystemLog{" +
                "log_id=" + log_id +
                ", log_operator='" + log_operator + '\'' +
                ", log_operator_role='" + log_operator_role + '\'' +
                ", log_operator_ip='" + log_operator_ip + '\'' +
                ", log_type='" + log_type + '\'' +
                ", log_content='" + log_content + '\'' +
                ", log_result='" + log_result + '\'' +
                ", log_date='" + log_date + '\'' +
                '}';
    }
}
