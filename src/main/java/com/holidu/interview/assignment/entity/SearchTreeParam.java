package com.holidu.interview.assignment.entity;

public class SearchTreeParam {

	private double centerPoint_X;
	private double centerPoint_Y;
	private double searchRadius;

	public SearchTreeParam() {
	}
	
	public SearchTreeParam(double centerPoint_X, double centerPoint_Y, double searchRadius) {
		super();
		this.centerPoint_X = centerPoint_X;
		this.centerPoint_Y = centerPoint_Y;
		this.searchRadius = searchRadius;
	}

	public double getCenterPoint_X() {
		return centerPoint_X;
	}

	public void setCenterPoint_X(double centerPoint_X) {
		this.centerPoint_X = centerPoint_X;
	}

	public double getCenterPoint_Y() {
		return centerPoint_Y;
	}

	public void setCenterPoint_Y(double centerPoint_Y) {
		this.centerPoint_Y = centerPoint_Y;
	}

	public double getSearchRadius() {
		return searchRadius;
	}

	public void setSearchRadius(double searchRadius) {
		this.searchRadius = searchRadius;
	}

	@Override
	public String toString() {
		return "SearchTreeParam [centerPoint_X=" + centerPoint_X + ", centerPoint_Y=" + centerPoint_Y
				+ ", searchRadius=" + searchRadius + "]";
	}
}
