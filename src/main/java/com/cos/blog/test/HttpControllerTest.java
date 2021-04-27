package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 -> 응답 (Data)

@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest: ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m= Member.builder().username("sar").password("124").email("tt").build();
		System.out.println(TAG+"getter : "+m.getId());
		m.setId(5000);
		System.out.println(TAG+"getter : "+m.getId());
		return "lombok test ";
	}
	
	//인터넷 브라우저 요청은 무조건 get 요청밖에 할 수가 없다.
	//http:localhost:8080/http/get
	//select
	@GetMapping("/http/get")
	//public String getTest(@RequestParam int id, @RequestParam String username) {
	public String getTest(Member m) {
		
		
		return "get 요청"+m.getId()+"/"+m.getUsername()+"/"+m.getPassword()+"/"+m.getEmail();
	}
	//insert
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) {
		return "post 요청"+m.getId()+"/"+m.getUsername()+"/"+m.getPassword()+"/"+m.getEmail();
	}
	//update
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청"+m.getId()+"/"+m.getUsername()+"/"+m.getPassword()+"/"+m.getEmail();
	}
	
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
