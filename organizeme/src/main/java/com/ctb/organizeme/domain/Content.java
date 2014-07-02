package com.ctb.organizeme.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Data;

import com.ctb.organizeme.support.user.domain.User;

@Entity
@Table(name = "tb_content")
@Data
public class Content {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Category category;

	private ContentType type;

	private Language language;

	private String title;

	private LocationType locationType;

	private String location;

	@ManyToOne
	private User author;

	private Progress progress;

	private Date createdTime;
	private Date modifiedTime;
	private Date deletedTime;

	public Content() {
	}

	public Content(Category category, ContentType type, Language language,
			String title, LocationType locationType, String location,
			User author, Progress progress) {
		this.category = category;
		this.type = type;
		this.language = language;
		this.title = title;
		this.locationType = locationType;
		this.location = location;
		this.author = author;
		this.progress = progress;
	}

	@PrePersist
	private void onCreate() {
		setCreatedTime(new Date());
	}

	@PreUpdate
	private void onUpdate() {
		setModifiedTime(new Date());
	}

	@PreRemove
	private void onDelete() {
		setDeletedTime(new Date());
	}

}
