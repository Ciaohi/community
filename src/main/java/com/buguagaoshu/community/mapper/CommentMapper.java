package com.buguagaoshu.community.mapper;

import com.buguagaoshu.community.model.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Pu Zhiwei {@literal puzhiweipuzhiwei@foxmail.com}
 * create          2019-08-25 19:11
 */
@Mapper
public interface CommentMapper {
    @Insert("insert into comment(questionId ,parentId, type, commentator, content, likeCount, commentCount, createTime, modifiedTime) " +
            "values(#{questionId}, #{parentId}, #{type}, #{commentator}, #{content}, #{likeCount},  #{commentCount}, #{createTime}, #{modifiedTime})")
    @Options(useGeneratedKeys = true, keyProperty = "commentId")
    int insertComment(Comment comment);


    @Select("select * from comment where commentId=#{commentId} AND status=#{status}")
    Comment selectCommentByCommentId(long commentId, @Param("status") int status);

    /**
     * 查找一级评论列表
     */
    @Select("select * from comment where questionId=#{questionId} AND type=#{type} AND status=#{status} limit #{page}, #{size}")
    List<Comment> getCommentDtoByQuestionId(@Param("questionId") long questionId, @Param("type") int type, @Param("page") long page, @Param("size") long size, @Param("status") int status);


    @Select("select COUNT(1) from comment where questionId=#{questionId} AND type=#{type} AND status=#{status}")
    long getCommentNumber(@Param("questionId") long questionId, @Param("type") int type, @Param("status") int status);


    /**
     * 查找二级评论列表
     */
    @Select("select * from comment where parentId=#{parentId} AND  type=#{type} AND status=#{status}")
    List<Comment> getTwoLevelCommentByParent(@Param("parentId") long parentId, @Param("type") int type, @Param("status") int status);


    @Update("update comment set commentCount=commentCount+#{commentCount} where commentId=#{commentId}")
    int updateCommentCount(Comment comment);


    @Select("select * from comment where questionId=#{questionId} AND status=#{status}")
    List<Comment> getAllComment(@Param("questionId") long questionId, @Param("status") int status);
}
