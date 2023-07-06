package com.example.demo.mapper;

import com.example.demo.entity.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {
        @Insert("INSERT INTO post(PID,AccountId, Content, Type, CommentNum, CollectNum, GoodNum, RelTime, Title, Brief, Cover) " +
                "VALUES(null,#{AccountId}, #{Content}, #{Type}, 0, 0, 0, #{RelTime}, #{Title}, #{Brief}, #{CoverURL})")
        int insertPost(Post post);
        @Select("Select * from post inner join accounttb on post.AccountId=accounttb.AccountId order by RelTime desc")
        List<Post> getallpost();
        @Delete("Delete from post where PID=#{PID}")
        int deletepostbyId(int PID);
        @Select("Select * from post inner join accounttb on post.AccountId=accounttb.AccountId where PID=#{PID}")
        Post getpostbyId(int PID);
        @Select("Select CoverURL from post where PID=#{PID}")
        String getCoverURLbyId(int PID);
        @Select("Select * from post inner join accounttb on post.AccountId=accounttb.AccountId and post.AccountId=#{AccountId} order by RelTime desc")
        List<Post> getpostbyAccountId(String AccountId);
        @Update("Update post set GoodNum=GoodNum+1 where PID=#{PID}")
        int Good(int PID);
        @Update("Update post set GoodNum=GoodNum-1 where PID=#{PID}")
        int CancelGood(int PID);
        @Update("Update post set CollectNum=CollectNum+1 where PID=#{PID}")
        int Collect(int PID);
        @Update("Update post set CollectNum=CollectNum-1 where PID=#{PID}")
        int CancelCollect(int PID);
        @Update("Update post set CommentNum=CommentNum+1 where PID=#{PID}")
        int Comment(int PID);
        @Update("Update post set CommentNum=CommentNum-1 where PID=#{PID}")
        int DeleteComment(int PID);
        @Select("SELECT *\n" +
                "FROM post\n" +
                "INNER JOIN accounttb ON post.AccountId = accounttb.AccountId\n" +
                "WHERE post.Title LIKE #{Key}\n" +
                "OR post.Brief LIKE #{Key}\n" +
                "OR post.Content LIKE #{Key}\n" +
                "OR accounttb.NickName LIKE #{Key} " +
                "ORDER BY((CASE WHEN post.Title LIKE #{Key} THEN 2 ELSE 0 END) + (CASE WHEN post.Content LIKE #{Key} THEN 1 ELSE 0 END)+(CASE WHEN post.Brief Like #{Key} THEN 2 ELSE 0 END)+(CASE WHEN accounttb.NickName Like #{Key} THEN 2 ELSE 0 END)) DESC;")
        List<Post> QueryPost(@Param("Key") String Key);


}
