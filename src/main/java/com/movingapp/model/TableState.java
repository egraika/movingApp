package com.movingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TableState {	
	
	private Sort sort;
	private Search search;
	private Pagination pagination;
	
	public Sort getSort(){
		return sort;
	}
	
	public void setSort(Sort sort){
		this.sort = sort;
	}
	
	public Search getSearch(){
		return search;
	}
	
	public void setSearch(Search search){
		this.search = search;
	}
	
	public Pagination getPagination(){
		return pagination;
	}
	
	public void setPagination(Pagination pagination){
		this.pagination = pagination;
	}
}
