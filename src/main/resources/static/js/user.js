let index = {
	init: function() {
		/* on 다음 처음껀 이벤트, 다음껀 그다음뭐할지*/ 
		$("#btn_save").on("click", ()=> { //function() {}을 안쓰고 , () =>{}쓴 이유 (this를 바인딩하기위해 사용 )
			this.save();
		});
		$("#btn_login").on("click", ()=> { //function() {}을 안쓰고 , () =>{}쓴 이유 (this를 바인딩하기위해 사용 )
			this.login();
		});
		},
		
		save: function() {
			//alert('user의 save 함수 호출됨');
			let data = {
				//해당값을 변수에 바인딩 
				username: $("#username").val(),
				password: $("#password").val(),
				email: $("#email").val(),
			};			
			//console.log(data);
			
			// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!
			// ajax 호출시 default가 비동기 호출 
			
			$.ajax({
				// 회원가입 수행 요청 
				type:"POST",
				url: "/blog/api/user",
				data: JSON.stringify(data), // http body 데이터 즉 mime 타입 필요(json 문자열) 
				contentType:"application/json; charset=utf-8",
				dataType:"json" //요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (버퍼로 오기때문) 생긴게 json이라면 
											//javascript오브젝트로 변경해줌 
			}).done(function(resp){
				alert("회원가입이 완료되었습니다.");
				console.log(resp);
				location.href="/blog";
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
			
			
		},
		
		login: function() {
			//alert('user의 save 함수 호출됨');
			let data = {
				username: $("#username").val(),
				password: $("#password").val(),
			};			
			
			$.ajax({
				type:"POST",
				url: "/blog/api/user/login",
				data: JSON.stringify(data), // http body 데이터 즉 mime 타입 필요(json 문자열) 
				contentType:"application/json; charset=utf-8",
				dataType:"json" //요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (버퍼로 오기때문) 생긴게 json이라면 
											//javascript오브젝트로 변경해줌 
			}).done(function(resp){
				alert("로그인이 완료되었습니다.");
				location.href="/blog";
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
			
			
		}
}

index.init();