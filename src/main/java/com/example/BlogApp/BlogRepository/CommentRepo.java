package com.example.BlogApp.BlogRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BlogApp.BlogEntity.Comments;

public interface CommentRepo extends JpaRepository<Comments,Long> {

	List<Comments> getCommentsByPostId(int pid);

}
