package com.ctb.organizeme.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ctb.organizeme.support.user.domain.User;

import lombok.Data;

@Entity
@Table(name = "tb_collection")
@Data
public class Collection {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private String description;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Content> contents;

	@ManyToOne
	private User author;

	public Collection() {
	}

	public Collection(String name, String description, List<Content> contents,
			User author) {
		this.name = name;
		this.description = description;
		this.contents = contents;
		this.author = author;
	}

}
