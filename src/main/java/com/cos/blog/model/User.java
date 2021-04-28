package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity //User 클래스가 MYSQL에 자동으로 테이블이 생성이 된다.

//@DynamicInsert //null이면 제외 
public class User {
	//JPA 는 ORM -> 모든 언어 object를 테이블로 매핑해주는 기술 
	@Id //Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 db의 넘버링 전략을 따라간다.
	private int id; //시퀸스  auto_increment
	
	//unique =true : 중복 없앰
	@Column(nullable = false, length =30, unique=true)
	private String username;  //아이디
	
	@Column(nullable = false, length =100) //해쉬를 사용한 암호화 사용위한 100 
	private String password; 
	
	@Column(nullable = false, length =50)
	private String email;
	
	
	//@ColumnDefault("'user'") //문자임을 알리기위한 " '   ' "
	@Enumerated(EnumType.STRING)
	private RoleType role; // 정확히는 Enum 사용하는게 좋음 String 타입은 오타를 낼 수 있으므로 . 권환 차이 분배 (admin user~ )
	
	@CreationTimestamp //시간 자동입력
	private Timestamp createDate;
}
