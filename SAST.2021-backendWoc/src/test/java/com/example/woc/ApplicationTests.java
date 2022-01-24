package com.example.woc;

import com.example.woc.entity.Account;
import com.example.woc.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	void contextLoads() {
		Account account = new Account();
		account.setId(2);
		account.setUsername("xry");
		account.setPassword("123");
		account.setEmail("123@qq.com");
		userService.add(account);
	}

}
