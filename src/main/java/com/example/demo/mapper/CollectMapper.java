package com.example.demo.mapper;

import com.example.demo.entity.Post;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface CollectMapper {
    @Insert("insert into collectiontb values(#{AccountId},#{PID},#{CLtime})")
    int collect(@Param("AccountId")String AccountId, @Param("PID")int PID,@Param("CLtime") LocalDateTime CLtime);
    @Delete("delete from collectiontb where AccountId=#{AccountId} and PID=#{PID}")
    int deleteCollection(@Param("AccountId")String AccountId,@Param("PID")int PID);
    @Select("select * from post inner join collectiontb on post.PID=collectiontb.PID where collectiontb.AccountId=#{AccountId} order by CLtime")
    List<Post> querycollect(@Param("AccountId")String AccountId);
}
