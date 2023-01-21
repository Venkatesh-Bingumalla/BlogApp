package com.example.BlogApp.BlogServiceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.BlogApp.BlogEntity.Post;
import com.example.BlogApp.BlogEntity.PostResponse;
import com.example.BlogApp.BlogRepository.PostRepo;
import com.example.BlogApp.BlogServiceimpl.PostException.ResourceNotFoundException;





@Service
public class PostService {
	
	@Autowired
	PostRepo prepo;
	
	private ModelMapper mapper;
//	PostService(ModelMapper mapper){
//		this.mapper=mapper;
//	}
	public PostResponse getAll(int pageNo, int pageSize, String sortBy)
	{
		PageRequest pg =PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
		Page<Post> lpost=prepo.findAll(pg);
		List<Post> pagePosts=lpost.getContent();
		
		List<Post> content =pagePosts.stream().map(post->mapPost(post)).collect(Collectors.toList());
		PostResponse ps=new PostResponse();
		ps.setContent(content);
		ps.setPageNum(lpost.getNumber());
		ps.setPageSize(lpost.getSize());
		ps.setTotalele(lpost.getTotalElements());
		ps.setTotalpages(lpost.getTotalPages());
		ps.setLast(lpost.isLast());
		return ps;
	}
	public Post createPosts(Post p1) {
		Post mypost=mapPost(p1);
		Post response=prepo.save(mypost);
		Post postres=mapPost(response);
		return postres;
		
	}
	private Post mapPost(Post p1) {
		Post mypost=new Post();
		mypost.setId(p1.getId());
		mypost.setContent(p1.getContent());
		mypost.setDescription(p1.getDescription());
		mypost.setTitle(p1.getTitle());
		//mypost.setComments(p1.getComments());
		return mypost;
	}
	public Post getPostById(int id) {
		
		Post getBy=prepo.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));
		
		System.out.println(getBy.getComments().size());
		
		Post mypost=new Post();
		mypost.setId(getBy.getId());
		mypost.setContent(getBy.getContent());
		mypost.setComments(getBy.getComments());
		mypost.setDescription(getBy.getDescription());
		mypost.setTitle(getBy.getTitle());
	//	System.out.println(mypost.toString());
		
		
		//System.out.println("Vjhgvhgcyfddcy");
		//Post p=mapper.map(getBy, mypost);
		return mypost;
		//return mapPost(getBy);
	}
	
	public Post updatePost(Post pupdate,int id) {
		Post actualPost=prepo.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));
		//actualPost.setId(pupdate.getId());
		actualPost.setContent(pupdate.getContent());
		actualPost.setDescription(pupdate.getDescription());
		actualPost.setTitle(pupdate.getTitle());
		Post updatedPost=prepo.save(actualPost);		
		return mapPost(updatedPost);
	}
	
	public Post delPost(int id) {
		Post mypost=prepo.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));
		prepo.delete(mypost);
		return mypost;
	}
	
	
}
