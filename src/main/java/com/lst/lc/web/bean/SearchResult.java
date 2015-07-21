package com.lst.lc.web.bean;

import java.util.List;

public class SearchResult<T> {
	
	private String type;
	private List<T> results;
	public SearchResult() {
		super();
	}
	public SearchResult(String type, List<T> results) {
		super();
		this.type = type;
		this.results = results;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<T> getResults() {
		return results;
	}
	public void setResults(List<T> results) {
		this.results = results;
	}
	
}
