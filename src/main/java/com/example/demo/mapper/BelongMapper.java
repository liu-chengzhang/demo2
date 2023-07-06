package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BelongMapper {
    @Insert("insert into belongtb values(#{AccountId},last_insert_id())")
    boolean insertbelong(@Param("AccountId")String AccountId);
}
