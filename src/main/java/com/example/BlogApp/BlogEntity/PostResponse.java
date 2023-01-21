package com.example.BlogApp.BlogEntity;

import java.util.List;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
	
	private List<Post> content;
	private int pageNum;
	private int pageSize;
	private long totalele;
	private int totalpages;
	private boolean last ;
	public List<Post> getContent() {
		return content;
	}
	public void setContent(List<Post> content) {
		this.content = content;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalele() {
		return totalele;
	}
	public void setTotalele(long totalele) {
		this.totalele = totalele;
	}
	public int getTotalpages() {
		return totalpages;
	}
	public void setTotalpages(int totalpages) {
		this.totalpages = totalpages;
	}
	public boolean isLast() {
		return last;
	}
	public void setLast(boolean last) {
		this.last = last;
	}
	
	
	
	

}
