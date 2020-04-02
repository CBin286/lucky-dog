package com.great.javabean;

import org.springframework.stereotype.Component;

/**
 * ClassName: User <br/>
 * Description: <br/>
 * date: 2020/3/24 22:13<br/>
 *
 * @author lenovo<br />
 * @since JDK 1.8
 */
@Component
public class User {
    private Integer user_id;
    private String user_account;
    private String user_pwd;
    private Integer user_sex;
    private Integer user_age;
    private String user_date;
    private String user_education;
    private String user_occupation;
    private Integer user_state;
    private Long user_phone;
    private String user_integral;
    private String user_emil;
    private Integer user_up;
    private Integer user_down;

    public User() {
    }

    public User(Integer user_id, String user_account, String user_pwd, Integer user_sex, Integer user_age, String user_date, String user_education, String user_occupation, Integer user_state, Long user_phone, String user_integral, String user_emil, Integer user_up, Integer user_down) {
        this.user_id = user_id;
        this.user_account = user_account;
        this.user_pwd = user_pwd;
        this.user_sex = user_sex;
        this.user_age = user_age;
        this.user_date = user_date;
        this.user_education = user_education;
        this.user_occupation = user_occupation;
        this.user_state = user_state;
        this.user_phone = user_phone;
        this.user_integral = user_integral;
        this.user_emil = user_emil;
        this.user_up = user_up;
        this.user_down = user_down;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_account='" + user_account + '\'' +
                ", user_pwd='" + user_pwd + '\'' +
                ", user_sex=" + user_sex +
                ", user_age=" + user_age +
                ", user_date='" + user_date + '\'' +
                ", user_education='" + user_education + '\'' +
                ", user_occupation='" + user_occupation + '\'' +
                ", user_state=" + user_state +
                ", user_phone=" + user_phone +
                ", user_integral='" + user_integral + '\'' +
                ", user_emil='" + user_emil + '\'' +
                ", user_up=" + user_up +
                ", user_down=" + user_down +
                '}';
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public Integer getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(Integer user_sex) {
        this.user_sex = user_sex;
    }

    public Integer getUser_age() {
        return user_age;
    }

    public void setUser_age(Integer user_age) {
        this.user_age = user_age;
    }

    public String getUser_date() {
        return user_date;
    }

    public void setUser_date(String user_date) {
        this.user_date = user_date;
    }

    public String getUser_education() {
        return user_education;
    }

    public void setUser_education(String user_education) {
        this.user_education = user_education;
    }

    public String getUser_occupation() {
        return user_occupation;
    }

    public void setUser_occupation(String user_occupation) {
        this.user_occupation = user_occupation;
    }

    public Integer getUser_state() {
        return user_state;
    }

    public void setUser_state(Integer user_state) {
        this.user_state = user_state;
    }

    public Long getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(Long user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_integral() {
        return user_integral;
    }

    public void setUser_integral(String user_integral) {
        this.user_integral = user_integral;
    }

    public String getUser_emil() {
        return user_emil;
    }

    public void setUser_emil(String user_emil) {
        this.user_emil = user_emil;
    }

    public Integer getUser_up() {
        return user_up;
    }

    public void setUser_up(Integer user_up) {
        this.user_up = user_up;
    }

    public Integer getUser_down() {
        return user_down;
    }

    public void setUser_down(Integer user_down) {
        this.user_down = user_down;
    }
}
