package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌 (ioc를 해준다는거. 즉 메모리에 대신띄워줌)
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional //전체가 성공해야 commit 실패시 rollback
	public void 회원가입(User user) {
		
			userRepository.save(user);
		
	}
}
