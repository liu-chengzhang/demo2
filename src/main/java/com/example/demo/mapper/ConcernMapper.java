package com.example.demo.mapper;
import com.example.demo.entity.ConcernCard;
import com.example.demo.entity.Post;
import com.example.demo.entity.accounttb;
import org.apache.ibatis.annotations.*;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ConcernMapper {
    @Insert("insert into concerntb values(#{Account1Id},#{Account2Id},#{concerntime})")
    public int concern(@Param("Account1Id") String Account1Id,@Param("Account2Id") String Account2Id,@Param("concerntime") LocalDateTime concerntime);
    @Delete("delete from concerntb where Account1Id=#{Account1Id} and Account2Id=#{Account2Id}")
    public int deleteconcern(@Param("Account1Id") String Account1Id,@Param("Account2Id") String Account2Id);
    @Select("select * from concerntb inner join accounttb on concerntb.Account1Id=accounttb.AccountId where concerntb.Account2Id=#{AccountId} order by concerntime desc")
    public List<ConcernCard> querymyconcern(@Param("AccountId") String AccountId);
    @Select("select * from concerntb inner join post on concerntb.Account2Id=post.AccountId where concerntb.Account1Id=#{AccountId} order by PID desc")
    public List<Post> queryconcernpost(@Param("AccountId")String AccountId);
}
