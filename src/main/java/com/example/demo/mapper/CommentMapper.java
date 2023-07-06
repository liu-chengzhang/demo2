package com.example.demo.mapper;

import com.example.demo.entity.Comment;
import com.example.demo.entity.CommentCard;
import com.example.demo.entity.Post;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert("insert into commentsdtb values(null,#{PID},#{AccountId},#{Content},#{CMtime})")
    int comment(@Param("AccountId")String AccountId, @Param("PID")int PID, @Param("Content")String Content,@Param("CMtime") LocalDateTime CMtime);
    @Delete("delete from commentsdtb where CMId=#{CMId}")
    int deleteComment(@Param("CMId")int CMId);
    @Select("select * from commentsdtb inner join accounttb on accounttb.AccountId=commentsdtb.AccountId where commentsdtb.PID=#{PID} order by CMtime desc")
    List<CommentCard> querycomment(@Param("PID")int PID);
    @Select("select * from commentsdtb  inner join accounttb on accounttb.AccountId=commentsdtb.AccountId where commentsdtb.CMId=#{CMId}")
    CommentCard querycommentbyCMId(@Param("CMId")int CMId);
    @Select("select * from commentsdtb  inner join accounttb on accounttb.AccountId=commentsdtb.AccountId order by CMtime desc")
    List<CommentCard> getallcomment();
}

