package kr.dev.study.bbs.domain;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

public class Bbs {
	
	private Integer id;

	@NotBlank
	private String subject;

	@NotBlank
	private String content;
	
	@NotBlank
	private String writer;
	
	private Date createDate;
	
	public Bbs() {}
	
	public Bbs(String subject, String content, String writer) {
		this.subject = subject;
		this.content = content;
		this.writer = writer;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
