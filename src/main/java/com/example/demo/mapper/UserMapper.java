package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Rank;
import com.example.demo.entity.accounttb;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<accounttb> {
    @Select("Select * from accounttb where AccountId=#{AccountId} and Password=#{Password}")
    accounttb login(@Param("AccountId")Integer AccountId,@Param("Password") String Password);
    @Insert("Insert into accounttb values(#{AccountId},null,null,#{Password},null)")
    accounttb register(@Param("AccountId")Integer AccountId,@Param("Password") String Password);
    @Update("Update accounttb set NickName=#{NickName},EMail=#{EMail} where AccountId=#{AccountId}")
    int updateinfo(@Param("AccountId")Integer AccountId,@Param("NickName")String NickName,@Param("EMail")String EMail);
    @Update("Update accounttb set Avatar=#{Avatar} where AccountId=#{AccountId}")
    int updateAvator(@Param("Avatar")String Avatar,@Param("AccountId")Integer AccountId);
    @Select("Select * from accounttb")
    List<accounttb> finduser();
    @Select("select * from accounttb where AccountId=#{AccountId}")
    accounttb Userinfo(Integer AccountId);
    @Delete("Delete from accounttb where AccountId=#{AccountId}")
    boolean DeleteByID(Integer AccountId);
    @Select("SELECT accounttb.AccountId,accounttb.NickName, SUM(clockintb.ClockInTime) AS total_time" +
            "FROM accounttb" +
            "INNER JOIN belongtb ON accounttb.AccountId = belongtb.AccountId" +
            "INNER JOIN clockintb ON belongtb.ClockInId = clockintb.ClockInId" +
            "GROUP BY accounttb.AccountId" +
            "ORDER BY total_time DESC" +
            "LIMIT 10;")
    List<Rank> getrank();
}
