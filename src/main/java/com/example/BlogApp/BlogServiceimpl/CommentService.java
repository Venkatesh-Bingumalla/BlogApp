package com.example.BlogApp.BlogServiceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.BlogApp.BlogEntity.Comments;
import com.example.BlogApp.BlogEntity.Post;
import com.example.BlogApp.BlogRepository.CommentRepo;
import com.example.BlogApp.BlogRepository.PostRepo;
import com.example.BlogApp.BlogServiceimpl.PostException.BlogAPIException;
import com.example.BlogApp.BlogServiceimpl.PostException.ResourceNotFoundException;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepo crepo;
	
	@Autowired
	private PostRepo prepo;
	
	public Comments createComments(int postId,Comments com) {
		
		Comments coms=mapComment(com);
		
		Post po = prepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
		
		coms.setPost(po);
		
		Comments myComment = crepo.save(coms);
		
		return mapComment(myComment);
	}
	
	private Comments mapComment(Comments c1) {
		Comments mycomment=new Comments();
		mycomment.setId(c1.getId());
		mycomment.setCname(c1.getCname());
		mycomment.setBody(c1.getBody());
		mycomment.setEmail(c1.getEmail());
		//mycomment.setPost(c1.getPost());
		return mycomment;
		
	}

	public List<Comments> getCommentsByPostId(int pid) {
		
		
		
		List<Comments> lcom=crepo.getCommentsByPostId(pid);
		
		return lcom.stream().map(comment->mapComment(comment)).collect(Collectors.toList());
	}

	public Comments getCommentsById(int pid, long id) {
		
		Post po = prepo.findById(pid).orElseThrow(()->new ResourceNotFoundException("Post","Id",pid));
		
		Comments c=crepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Comments","id",(int) id));
		System.out.println("************************************");
		if(c.getPost().getId()!=(po.getId()))
		{
		
			throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment Does not belongs to post");
		}
		System.out.println(c.getPost().getId());
		return mapComment(c);
	}

	public void delCommentsById(int pid, long id) {
		Post po = prepo.findById(pid).orElseThrow(()->new ResourceNotFoundException("Post","Id",pid));
		
		Comments c=crepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Comments","id",(int) id));
		System.out.println("************************************");
		if(c.getPost().getId()!=(po.getId()))
		{
		
			throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment Does not belongs to post");
		}
		crepo.deleteById(id);
		
		
	}



	public Object updateCommentsById(int pid, long id, Comments coms) {
		Post po = prepo.findById(pid).orElseThrow(()->new ResourceNotFoundException("Post","Id",pid));
		
		Comments c=crepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Comments","id",(int) id));
		System.out.println("************************************");
		if(c.getPost().getId()!=(po.getId()))
		{
		
			throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment Does not belongs to post");
		}	
		c.setCname(coms.getCname());
		c.setBody(coms.getBody());
		c.setEmail(coms.getEmail());
		Comments cs=crepo.save(c);
		return cs;
		
	}

}
