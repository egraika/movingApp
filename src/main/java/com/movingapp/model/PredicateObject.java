package com.movingapp.model;

public class PredicateObject {

	private String globalSearch;
	private String statusSearch;
	private String locationSearch;
	private String userTypeSearch;

	public String getStatusSearch() {
		return statusSearch;
	}

	public void setStatusSearch(String statusSearch) {
		this.statusSearch = statusSearch;
	}

	public String getGlobalSearch() {
		return globalSearch;
	}

	public void setGlobalSearch(String globalSearch) {
		this.globalSearch = globalSearch;
	}

	public String getLocationSearch() {
		return locationSearch;
	}

	public void setLocationSearch(String locationSearch) {
		this.locationSearch = locationSearch;
	}

	public String getUserTypeSearch() {
		return userTypeSearch;
	}

	public void setUserTypeSearch(String userTypeSearch) {
		this.userTypeSearch = userTypeSearch;
	}
}
