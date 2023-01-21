package com.example.BlogApp.BlogRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BlogApp.BlogEntity.Post;




public interface PostRepo extends JpaRepository<Post,Integer>  {

	

}
