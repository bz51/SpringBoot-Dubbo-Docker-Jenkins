package com.gaoxi.entity.user;

/**
 * @Author 大闲人柴毛毛
 * @Date 2017/10/30 下午6:13
 */
public class CommentEntity {

    private String id;
    private String comment;
    private String articleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}
