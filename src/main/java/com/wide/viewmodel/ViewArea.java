package com.wide.viewmodel;

import java.util.List;

import com.wide.common.model.Area;

public class ViewArea {
	private Area area;
	private List<Area> listareas;
	private Double sort;
	

	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}

	public List<Area> getListareas() {
		return listareas;
	}
	public void setListareas(List<Area> listareas) {
		this.listareas = listareas;
	}
	public Double getsort()
	{
		return sort;
	}
	public void setsort(Double sort){
		this.sort=sort;
	}
}
