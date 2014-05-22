package com.ctb.organizeme.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_content")
public class Content {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private ContentType type;

	private Language language;

	private String title;

	private LocationType locationType;

	private String location;

	public Content() {
	}

	public Content(ContentType type, Language language, String title,
			LocationType locationType, String location) {
		this.type = type;
		this.language = language;
		this.title = title;
		this.locationType = locationType;
		this.location = location;
	}

	public Long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Content [id=" + id + ", type=" + type + ", language="
				+ language + ", title=" + title + ", locationType="
				+ locationType + ", location=" + location + "]";
	}

}
