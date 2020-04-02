package com.great.javabean;

import org.springframework.stereotype.Component;

/**
 * ClassName: DocumentType <br/>
 * Description: <br/>
 * date: 2020/4/1 10:31<br/>
 *
 * @author lenovo<br />
 * @since JDK 1.8
 */
@Component
public class DocumentType {
    private Integer document_type_id;//文档id
    private String document_type_name;//文档类型
    private Integer document_type_score;//奖励分

    public DocumentType() {
    }

    public DocumentType(Integer document_type_id, String document_type_name, Integer document_type_score) {
        this.document_type_id = document_type_id;
        this.document_type_name = document_type_name;
        this.document_type_score = document_type_score;
    }

    public Integer getDocument_type_id() {
        return document_type_id;
    }

    public void setDocument_type_id(Integer document_type_id) {
        this.document_type_id = document_type_id;
    }

    public String getDocument_type_name() {
        return document_type_name;
    }

    public void setDocument_type_name(String document_type_name) {
        this.document_type_name = document_type_name;
    }

    public Integer getDocument_type_score() {
        return document_type_score;
    }

    public void setDocument_type_score(Integer document_type_score) {
        this.document_type_score = document_type_score;
    }

    @Override
    public String toString() {
        return "DocumentType{" +
                "document_type_id=" + document_type_id +
                ", document_type_name='" + document_type_name + '\'' +
                ", document_type_score=" + document_type_score +
                '}';
    }
}
