package com.codewithrajan.blog.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Integer postId;
	
	@Column(name = "title", length = 100, nullable = false)
	private String title;
	
	@Column(name = "content", length = 10000)
	private String content;
	
	@Column(name = "image_name")
	private String imageName;
	
	@Column(name = "added_date")
	private Date addedDate;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	// foreign key will be created in Comment table
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	private List<Comment> comments;
	
}
