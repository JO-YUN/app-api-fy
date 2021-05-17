package com.mscs.app.common.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Page implements Serializable{
	
	private static final long serialVersionUID = 7828822531951472L;

	private static int DEFAULT_PAGE_SIZE = 10;

	/** the begin index of the records by the current query */
	private int beginIndex;
	
	private int endIndex;
	/** the number of every page */
	private int pageSize = DEFAULT_PAGE_SIZE;
	
	private int pageNo = 1;
	
	private int totalCount = 13;
	
	private int totalPage =2;
	
	private int maxPage = 10;
	
	private int beginPage ;
	
	private int endPage ;
	
	private List<?> data ;
	
	private Map<?,List> tows;
	
	
	
	
	public List<?> getData() {
		return data;
	}



	public void setData(List<?> data) {
		this.data = data;
	}



	public Map<?, List> getTows() {
		return tows;
	}
	


	public void setTows(Map<?, List> tows) {
		this.tows = tows;
	}
	


	public static int getDEFAULT_PAGE_SIZE() {
		return DEFAULT_PAGE_SIZE;
	}
	

	public static void setDEFAULT_PAGE_SIZE(int dEFAULT_PAGE_SIZE) {
		DEFAULT_PAGE_SIZE = dEFAULT_PAGE_SIZE;
	}
	

	public Page() {
		super();
		initializeIndex(pageNo,pageSize);
	}
	
	

	private void initializeIndex(int pageNo, int pageSize) {

		beginIndex = (pageNo - 1) * pageSize+1;
		endIndex = pageNo * pageSize;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		initializePage();
	}
	
	public void initializePage(){

		if (totalCount % pageSize == 0) {
			totalPage = (int)(totalCount / pageSize);
		} else {
			totalPage = (int)(totalCount / pageSize) + 1;
		}

		if (totalPage <= maxPage) {
			this.beginPage = 1;
			endPage = totalPage;

		} else {

			if (pageNo <= maxPage / 2) {
				beginPage = 1;
				endPage = beginPage + (maxPage - 1);
			} else if (pageNo > maxPage / 2
					&& pageNo < totalPage - (maxPage / 2 - 1)) {
				beginPage = pageNo - (maxPage / 2 - 1);
				endPage = beginPage + (maxPage - 1);
			} else {
				beginPage = totalPage - (maxPage - 1);
				endPage = totalPage;
			}

		}
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		
		initializeIndex(pageNo,pageSize);
		
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		
		initializeIndex(pageNo,pageSize);
		
		this.pageNo = pageNo;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	
}