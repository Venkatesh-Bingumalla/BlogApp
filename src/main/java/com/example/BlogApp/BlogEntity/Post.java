package com.example.BlogApp.BlogEntity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Table;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter

public class Post {
	
	@jakarta.persistence.Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int Id;
	@Column
	private String title;
	@Column
	private String description;
	@Column
	private String content;
	
	@OneToMany(mappedBy="post",cascade=CascadeType.ALL,orphanRemoval=true)
	private Set<Comments> comments= new HashSet<>();
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Set<Comments> getComments() {
		return comments;
	}
	public void setComments(Set<Comments> comments) {
		if(comments == null) {
			this.comments=new HashSet<>();
			
		}
		else {
			this.comments.addAll(comments);
		}
		
	}
	@Override
	public String toString() {
		return "Post [Id=" + Id + ", title=" + title + ", description=" + description + ", content=" + content
				+ ", comments=" + comments + ", getId()=" + getId() + ", getTitle()=" + getTitle()
				+ ", getDescription()=" + getDescription() + ", getContent()=" + getContent() + ", getComments()="
				+ getComments() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	

}


