package com.example.demo.service;
import com.example.demo.entity.Rank;
import com.example.demo.entity.accounttb;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.mapper.LogMapper;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    public UserMapper mapper;
    @Autowired
    public LogMapper logMapper;
    public List<accounttb> findalluser(){
        return mapper.findalluser();
    }
    public List<Rank> getrank(){
        return  mapper.getrank();
    }
    @Override
    public boolean login(String AccountId, String Password, HttpServletRequest request){
        if(mapper.login(AccountId,Password)==null)return false;
        else{
            logMapper.writelog(AccountId, LocalDateTime.now(),request.getRemoteAddr());
            return true;
        }
    }

    @Override
    public boolean register(String AccountId, String Password,String EMail) {
        mapper.register(AccountId,Password,EMail);
        return true;
    }
    @Override
    public accounttb Userinfo(String id){
        return mapper.Userinfo(id);
    };
    @Override
    public boolean forgetpassword(String AccountId,String Password){
        return mapper.forgetpassword(AccountId,Password);
    }
}
