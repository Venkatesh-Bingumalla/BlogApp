package com.example.BlogApp.BlogController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.BlogApp.BlogEntity.Comments;
import com.example.BlogApp.BlogServiceimpl.CommentService;

@RestController
public class CommentController {
	
	@Autowired
	private CommentService cserv;
	
	@PostMapping("posts/{pid}/create")
	private ResponseEntity<Comments> createComment(@PathVariable int pid,@RequestBody Comments com) {
		
		return new ResponseEntity<>(cserv.createComments(pid,com),HttpStatus.CREATED);
		
	}
	
	@GetMapping("posts/{pid}/getAll")
	public List<Comments> getCommentsByPost(@PathVariable int pid){
		
		return cserv.getCommentsByPostId(pid);
	}
	
	@GetMapping("posts/{pid}/getComByID/{id}")
	public Comments getCommentsById(@PathVariable("pid") int pid,@PathVariable("id") int id){
		
		return cserv.getCommentsById(pid,id);
	}
	
	@DeleteMapping("posts/{pid}/delComByID/{id}")
	public ResponseEntity<String> delCommentsById(@PathVariable int pid,@PathVariable long id){
		cserv.delCommentsById(pid,id);
		return new ResponseEntity<>("Deleted",HttpStatus.OK);
	}
	
	@PutMapping("posts/{pid}/upComByID/{id}")
	public Comments updateCommentsById(@PathVariable int pid,@PathVariable long id ,@RequestBody Comments coms){
		
		return (Comments) cserv.updateCommentsById(pid, id, coms);
	}
	
	
}
