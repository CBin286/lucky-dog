package com.great.javabean;

import java.util.List;

/**
 * ClassName: Tree <br/>
 * Description: <br/>
 * date: 2020/3/19 21:44<br/>
 *
 * @author lenovo<br />
 * @since JDK 1.8
 */
public class Tree {
    private String title;
    private Integer id;
    private List<Tree> children;
    private Integer fid;
    private String url;
    public Tree() {
    }


    public Tree(String title, Integer id, List<Tree> children, Integer fid, String url) {
        this.title = title;
        this.id = id;
        this.children = children;
        this.fid = fid;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", children=" + children +
                ", fid=" + fid +
                ", url='" + url + '\'' +
                '}';
    }
}

