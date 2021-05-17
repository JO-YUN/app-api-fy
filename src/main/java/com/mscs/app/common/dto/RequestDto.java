package com.mscs.app.common.dto;


/**
 * 
 * @author hechunyang
 *
 */
public class RequestDto extends BaseDto {

	private static final long serialVersionUID = -7601115342263565329L;
	// 版本号
	private String ver;
	// 当前页
	private int pageIndex = 1;
	// 每页记录数
	private int pageSize = 5;

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 分页查询开始记录
	 * 
	 * @return
	 */
	public int getStartRecord() {
		return (pageIndex - 1) * pageSize;
	}
}
