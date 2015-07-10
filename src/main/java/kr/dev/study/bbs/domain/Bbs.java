package kr.dev.study.bbs.domain;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="bbs")
public class Bbs {
	
	//id will be used for storing MongoDB _id
	@Id
	private String id;

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

	public Bbs(String id, String subject, String content, String writer) {
		this.id = id;
		this.subject = subject;
		this.content = content;
		this.writer = writer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	@Override
	public String toString() {
		return "Bbs [" + ToStringBuilder.reflectionToString(this) + "]";
	}
}
