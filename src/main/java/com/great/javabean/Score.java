package com.great.javabean;

/**
 * ClassName: Score <br/>
 * Description: <br/>
 * date: 2020/4/1 16:04<br/>
 *
 * @author lenovo<br />
 * @since JDK 1.8
 */
public class Score {

    private Integer score_id;
    private String score_name;
    private Integer score_num;
    private String score_date;
    private Integer user_id;


    public Score() {
    }

    public Score(Integer score_id, String score_name, Integer score_num, String score_date, Integer user_id) {
        this.score_id = score_id;
        this.score_name = score_name;
        this.score_num = score_num;
        this.score_date = score_date;
        this.user_id = user_id;
    }

    public Integer getScore_id() {
        return score_id;
    }

    public void setScore_id(Integer score_id) {
        this.score_id = score_id;
    }

    public String getScore_name() {
        return score_name;
    }

    public void setScore_name(String score_name) {
        this.score_name = score_name;
    }

    public Integer getScore_num() {
        return score_num;
    }

    public void setScore_num(Integer score_num) {
        this.score_num = score_num;
    }

    public String getScore_date() {
        return score_date;
    }

    public void setScore_date(String score_date) {
        this.score_date = score_date;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Score{" +
                "score_id=" + score_id +
                ", score_name='" + score_name + '\'' +
                ", score_num=" + score_num +
                ", score_date='" + score_date + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
