package com.gaoxi.user.dao;

import com.gaoxi.entity.user.ArticleEntity;
import com.gaoxi.entity.user.CommentEntity;
import com.gaoxi.entity.user.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author 大闲人柴毛毛
 * @Date 2017/10/30 下午6:04
 */
@Mapper
public interface DemoDAO {

    /**
     * 根据userId查询文章列表
     * @param userId 用户id
     * @return 文章列表
     */
    @Select("select * from test_article")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "title",  column = "title"),
            @Result(property = "content",  column = "content"),
            @Result(property = "userEntity",  column = "user_id", one = @One(select = "com.gaoxi.user.dao.DemoDAO.findUserById")),
            @Result(property = "userEntity",  column = "user_id", many = @Many(select = "com.gaoxi.user.dao.DemoDAO.findCommentsByArticleId"))
    })
    List<ArticleEntity> findArticlesByUserId(String userId);

    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    @Select("select * from test_user where id=#{userId}")
    UserEntity findUserById(@Param("userId") String userId);

    /**
     * 根据文章id查询评论
     * @param articleId 文章id
     * @return
     */
    @Select("select * from test_comment where article_id=#{articleId}")
    List<CommentEntity> findCommentsByArticleId(@Param("articleId") String articleId);

}
