package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface ClockInMapper {
    @Insert("Insert into clockintb values(#{ClockInId},#{ClockInTime},#{ClockInDate},#{Content})")
    public boolean clockin(@Param("ClockInId") Integer ClockInId, @Param("ClockInTime") Integer ClockInTime,@Param("ClockInDate") Date ClockInDate,@Param("Content") String Content);
}
