package com.example.demo.controller;

import com.example.demo.entity.Comment;
import com.example.demo.entity.CommentCard;
import com.example.demo.entity.Post;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
@RestController
@CrossOrigin
public class CommentController {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    PostMapper postMapper;
    @RequestMapping("/comment")
    public int comment(@RequestParam("AccountId")String AccountId, @RequestParam("PID") int PID,@RequestParam("Content")String Content){
        postMapper.Comment(PID);
        return commentMapper.comment(AccountId,PID,Content,LocalDateTime.now());
    }
    @RequestMapping("/comment/delete")
    public int deletecomment(int CMId){
        CommentCard comment=commentMapper.querycommentbyCMId(CMId);
        postMapper.DeleteComment(comment.getPID());
        return commentMapper.deleteComment(CMId);
    }
    @RequestMapping("/post/comment")
    public List<CommentCard> queryComment(@RequestParam("PID")int PID){
        return commentMapper.querycomment(PID);
    }
    @RequestMapping("/admin/getallcomments")
    public List<CommentCard> getallcomments(){
        return commentMapper.getallcomment();
    }

}
