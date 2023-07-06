package com.example.demo.mapper;

import com.example.demo.entity.Post;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface GoodMapper {
    @Insert("insert into goodtb values(#{AccountId},#{PID},#{GoodTime}")
    int good(@Param("AccountId")String AccountId, @Param("PID")int PID, @Param("GoodTime") LocalDateTime GoodTime);
    @Delete("delete from goodtb where AccountId=#{AccountId} and PID=#{PID}")
    int deletegood(@Param("AccountId")String AccountId,@Param("PID")int PID);
    @Select("select * from post inner join goodtb on post.PID=goodtb.PID where goodtb.AccountId=#{AccountId} order by GoodTime")
    List<Post> querygood(@Param("AccountId")String AccountId);
}
