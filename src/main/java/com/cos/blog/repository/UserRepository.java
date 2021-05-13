package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

//DAO 
//자동으로 BEAN 등록이 된다. 
//@Repository 생략이 가능 
public interface UserRepository extends JpaRepository<User,Integer> {
  //JPA Naming 쿼리
 //SELECT * FROM user WHERE username = ? AND paaword = ? 
  User findByUsernameAndPassword(String username, String password);
  
//  @Query(value="SELECT * FROM user WHERE username = ? AND paaword = ? ", nativeQuery = true)
//  User login(String username, String password);
}
