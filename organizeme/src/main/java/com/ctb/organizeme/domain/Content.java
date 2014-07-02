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

import com.ctb.organizeme.support.user.domain.User;

@Entity
@Table(name = "tb_content")
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

	public Long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public ContentType getType() {
		return type;
	}

	public void setType(ContentType type) {
		this.type = type;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocationType getLocationType() {
		return locationType;
	}

	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Progress getProgress() {
		return progress;
	}

	public void setProgress(Progress progress) {
		this.progress = progress;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Date getDeletedTime() {
		return deletedTime;
	}

	public void setDeletedTime(Date deletedTime) {
		this.deletedTime = deletedTime;
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

	@Override
	public String toString() {
		return "Content [id=" + id + ", category=" + category + ", type="
				+ type + ", language=" + language + ", title=" + title
				+ ", locationType=" + locationType + ", location=" + location
				+ ", author=" + author + ", progress=" + progress
				+ ", createdTime=" + createdTime + ", modifiedTime="
				+ modifiedTime + ", deletedTime=" + deletedTime + "]";
	}

}
