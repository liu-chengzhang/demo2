package com.example.demo.mapper;

import com.example.demo.entity.Count;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ClockInMapper {
    @Insert("Insert into clockintb values(null,#{ClockInTime},#{ClockInDate},#{Content})")
    public boolean clockin(@Param("ClockInTime") Integer ClockInTime, @Param("ClockInDate") LocalDateTime ClockInDate, @Param("Content") String Content);
    @Select("Select count(*) as clockincount,sum(ClockInTime) as clockintime from clockintb")
    Count getAllClockInCount();
}
