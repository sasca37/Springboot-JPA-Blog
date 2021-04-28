package com.cos.blog.test;


import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//데이터를 리턴해주는 컨트롤러 
@RestController
public class DummyControllerTest {
	
	@Autowired //의존성 주입 (DI)
	private UserRepository userRepository;
	
	
	//save 함수는 id를 전달하지 않으면 insert를 해주고 
	//id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고 
	// 데이터가 없으면 insert를 한다.
	
	@Transactional  //save를 호출하지 않아도 db값 설정가능
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		//@RequestBody : json 데이터를 요청 -> M.C가 변환
		System.out.println("id " +id);
		System.out.println("password: " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정 실패.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
//		userRepository.save(user);
		//더티 체킹
		return user;
	}
	
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	//한 페이지당 2건의 데이터를 리턴 받는 메서드 
	@GetMapping("/dummy/user")
	public List<User> pagingList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) Pageable pageable) {
		Page<User> pagingUser= userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	//{id} 주소로 파라미터를 전달 받을 수 있음. 
	//http:~~~/user/3 
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		//user가 널일 경우를 대비해 findById는 Optional로 객체를 감싸서 가져오니, 
		// 개발자가 null 여부를 판단해서 return해야한다. 
		
		//람다식
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당 사용자는 없습니다.");
//		});
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 사용자가 없습니다.");
			}
			
		});
		//요청 : 웹브라우저
		//user 객체 : 자바 오브젝트 -> json으로 변환해줘야함 (오브젝트는 웹이 이해못함)
		//스프링부트 = MessageConverter라는 애가 응답시에 자동작동 
		//만약에 자바 오브젝트를 리턴 시 M.C가 Jackson 라이브러리를 호출해서 
		//user 오브젝트를 json으로 변환해서 브라우저에게 던져줌 
		
		return user;
	}
	
	// http://localhost:8000/blog/dummy/join (요청)
	//  http의 body에 username, password, email 데이터를 가지고 (요청) 
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("username" +user.getUsername());
		System.out.println("password" +user.getPassword());
		System.out.println("email" +user.getEmail());   
		System.out.println("id " + user.getId());
		System.out.println("role " + user.getRole());
		System.out.println("createDate " + user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입완료";
	}
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			return "해당 id는 db에 없읍";
		}
		
		
		return "삭제 되었습니다. id: "+id ;
	}

}
