package com.example.BlogApp.BlogController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BlogApp.BlogEntity.Post;
import com.example.BlogApp.BlogEntity.PostResponse;
import com.example.BlogApp.BlogServiceimpl.PostService;




@RestController
public class postController {
	@Autowired
	PostService pserv;
	
	@GetMapping("/getAll")
	public PostResponse getAll(
			@RequestParam(value="pageNo",defaultValue="0",required=false) int pageNo,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="sortBy",defaultValue="id",required=false) String sortBy
	) {
		
		return pserv.getAll(pageNo,pageSize,sortBy);
	}
	
	@PostMapping("/createPost")
	public ResponseEntity<Post> createPost(@RequestBody Post p1) {
		
		return new ResponseEntity<>(pserv.createPosts(p1),HttpStatus.CREATED);
	}
	
	@GetMapping("/getById/{id}")
	public Post getByPostId(@PathVariable int id){
		return pserv.getPostById(id);
		
	}
//	public void getByPostId(@PathVariable int id){
//		pserv.getPostById(id);
//		
//	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Post> updateMyPost(@RequestBody Post pupdate ,@PathVariable int id) {
		return new ResponseEntity<>(pserv.updatePost(pupdate,id),HttpStatus.OK);
		
	}
	
	@DeleteMapping("/del/{id}")
	public ResponseEntity<Post> deletePost(@PathVariable int id){
		return new ResponseEntity<>(pserv.delPost(id),HttpStatus.OK);
		
	}
	
	

}
