package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface ClockInMapper {
    @Insert("Insert into clockintb values(#{ClockInId},#{ClockInTime},#{ClockInDate},#{Content})")
    public boolean clockin(Integer ClockInId, Integer ClockInTime, Date ClockInDate,String Content);
}
