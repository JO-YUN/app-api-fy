package com.mscs.app.common.dto.resp;

/**
 * @author hechunyang
 * @date 2018年3月9日
 * @remark TODO
 */
public class RespUserAppCountDto {

	private int totalNum;

	/**
	 * 學生數
	 */
	private int studentNum;

	/**
	 * 教师数
	 */
	private int teacherNum;

	/**
	 * 學生使用app數目
	 */
	private int studentAppNum;

	/**
	 * 教師使用app數目
	 */
	private int teacherAppNum;

	public int getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}

	public int getTeacherNum() {
		return teacherNum;
	}

	public void setTeacherNum(int teacherNum) {
		this.teacherNum = teacherNum;
	}

	public int getStudentAppNum() {
		return studentAppNum;
	}

	public void setStudentAppNum(int studentAppNum) {
		this.studentAppNum = studentAppNum;
	}

	public int getTeacherAppNum() {
		return teacherAppNum;
	}

	public void setTeacherAppNum(int teacherAppNum) {
		this.teacherAppNum = teacherAppNum;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

}
