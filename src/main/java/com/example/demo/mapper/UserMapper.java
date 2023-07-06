package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.ClockIn;
import com.example.demo.entity.Rank;
import com.example.demo.entity.accounttb;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<accounttb> {
    @Select("Select * from accounttb where AccountId=#{AccountId} and Password=#{Password}")
    accounttb login(@Param("AccountId")String AccountId,@Param("Password") String Password);
    @Insert("Insert into accounttb values(#{AccountId},null,#{EMail},#{Password},null)")
    boolean register(@Param("AccountId")String AccountId,@Param("Password") String Password,@Param("EMail")String EMail);
    @Update("Update accounttb set NickName=#{NickName} where AccountId=#{AccountId}")
    int updateNickname(@Param("AccountId")String AccountId,@Param("NickName")String NickName);
    @Update("Update accounttb set Password=#{Password} where AccountId=#{AccountId}")
    int updatePassword(@Param("AccountId")String AccountId,@Param("Password")String Password);
    @Update("Update accounttb set EMail=#{EMail} where AccountId=#{AccountId}")
    int updateEmail(@Param("AccountId")String AccountId,@Param("EMail")String EMail);
    @Update("Update accounttb set Avatar=#{Avatar} where AccountId=#{AccountId}")
    int updateAvator(@Param("Avatar")String Avatar,@Param("AccountId")String AccountId);
    @Select("Select * from accounttb")
    List<accounttb> findalluser();
    @Select("select * from accounttb where AccountId=#{AccountId}")
    accounttb Userinfo(@Param("AccountId") String AccountId);
    @Delete("DELETE FROM team_account where AccountId=#{AccountId};\n"+
            "DELETE FROM concerntb WHERE AccountId=#{AccountId};\n"+
            "DELETE FROM goodtb WHERE AccountId=#{AccountId};\n"+
            "DELETE FROM collectiontb WHERE AccountId=#{AccountId};\n"+
            "DELETE FROM commentsdtb WHERE AccountId=#{AccountId};\n"+
            "DELETE FROM wishtb WHERE AccountId =#{AccountId};\n"+
            "DELETE FROM belongtb WHERE AccountId =#{AccountId};\n" +
            "Delete from accounttb where AccountId=#{AccountId};\n" +
            "Delete from clockintb where AccountId=#{AccountId};")
    boolean DeleteByID(@Param("AccountId") String AccountId);
    @Select("SELECT accounttb.AccountId,accounttb.NickName, SUM(clockintb.ClockInTime) AS totaltime,count(*) as count" +
            "FROM accounttb " +
            "INNER JOIN belongtb ON accounttb.AccountId = belongtb.AccountId " +
            "INNER JOIN clockintb ON belongtb.ClockInId = clockintb.ClockInId " +
            "GROUP BY accounttb.AccountId " +
            "ORDER BY total_time DESC " +
            "LIMIT 10;")
    List<Rank> getrank();
    @Select("select COALESCE(SUM(clockintb.ClockInTime),0) AS totaltime from  belongtb  inner join clockintb on belongtb.ClockInId = clockintb.ClockInId and belongtb.AccountId=#{AccountId}")
    int gettotaltime(@Param("AccountId") String AccountId);
    @Select("Select * from belongtb inner join clockintb on belongtb.ClockInId=clockintb.ClockInId and belongtb.AccountId=#{AccountId} order by ClockInDate desc")
    List<ClockIn> getclockinlog(@Param("AccountId")String AccountId);
    @Select("select count(*) from accounttb")
    int getUserCount();
    @Insert("Insert into accounttb values(#{AccountId},#{NickName},#{EMail},#{Password},null)")
    boolean insertuser(@Param("AccountId") String AccountId, @Param("Password") String Password, @Param("EMail")String EMail, @Param("NickName")String NickName);
    @Update("Update accounttb set Password=#{Password} where AccountId=#{AccountId}")
    boolean forgetpassword(@Param("AccountId")String AccountId,@Param("Password")String Password);
}
