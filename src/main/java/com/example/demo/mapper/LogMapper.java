package com.example.demo.mapper;

import com.example.demo.entity.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface LogMapper {
    @Insert("insert into log values(#{AccountId},#{LogTime},#{IP},null,null)")
    void writelog(@Param("AccountId") String AccountId, @Param("LogTime") LocalDateTime LogTime,@Param("IP") String IP);
    @Select("select * from log order by LogId desc")
    List<Log> getalllog();
}
