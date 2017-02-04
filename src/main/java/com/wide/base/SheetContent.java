package com.wide.base;

import java.io.Serializable;

/**
 * excel结构封装
 */
public class SheetContent implements Serializable{
	private int index = 0;
	private int rowStart = 1;  //开始行
	private int colStart = 1;  //开始列
	
	private String[] column = null; //映射列(属性)名称集合
	
	public SheetContent(){
		
	}

	
	public int getColStart() {
		return colStart;
	}

	
	public void setColStart(int colStart) {
		this.colStart = colStart;
	}

	
	public String[] getColumn() {
		return column;
	}

	
	public void setColumn(String[] column) {
		this.column = column;
	}

	
	public int getIndex() {
		return index;
	}

	
	public void setIndex(int index) {
		this.index = index;
	}

	
	public int getRowStart() {
		return rowStart;
	}

	
	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}	
	
}

