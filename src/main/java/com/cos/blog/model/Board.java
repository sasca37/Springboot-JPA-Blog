package com.cos.blog.model;


import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length =100)
	private String title;  
	
	@Lob //대용량 데이터쓸 때 사용 
	private String content; //내용은 길 수 가 있으므로 섬머노트 라이브러리 사용 
											//html 태그가 섞여서 디자인이됨.
	
	@ColumnDefault("0")
	private int count; // 조회수   
	
	@ManyToOne  //Board = Many , User = one -> 유저한명이 여러글을 쓸수있다. 연관관계설정 
	@JoinColumn(name="userId") //조인 user   
	private User user; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트 저장할 수 있다. 
	
	@OneToMany
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
}
