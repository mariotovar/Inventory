package com.mx.base.navegation;

import java.util.List;

import com.mx.base.abstractions.CatalogModel;
import com.mx.base.abstractions.Paginable;

public class CtrlPage {

	private int endIndex;
	private int startIndex;
	private int numberOfRows;
	private int numberOfPage;
	private int numberOfPages;
	private List<? extends Paginable> listRows;
	public static final int DISPLAY_ROWS = 10;
	
	public CtrlPage(int numberOfPage, int numberOfRows){
		this.numberOfRows = numberOfRows;
		this.numberOfPage = numberOfPage;
		this.startIndex = ((numberOfPage - 1) * DISPLAY_ROWS) + 1;	
		this.endIndex = numberOfPage * DISPLAY_ROWS;
		this.endIndex = (this.endIndex < numberOfRows) ? this.endIndex : numberOfRows;
		this.numberOfPages = (int) Math.ceil( (double)numberOfRows / DISPLAY_ROWS );
	}

	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getNumberOfRows() {
		return numberOfRows;
	}
	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}
	public int getNumberOfPage() {
		return numberOfPage;
	}
	public void setNumberOfPage(int numberOfPage) {
		this.numberOfPage = numberOfPage;
	}
	public int getNumberOfPages() {
		return numberOfPages;
	}
	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
	public List<? extends Paginable> getListRows() {
		return listRows;
	}
	public void setListRows(List<? extends Paginable> listRows) {
		this.listRows = listRows;
	}

}
