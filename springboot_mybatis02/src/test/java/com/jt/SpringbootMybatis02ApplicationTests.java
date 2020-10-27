package com.jt;

import com.jt.dao.UserDao;
import com.jt.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootMybatis02ApplicationTests {
	@Autowired
	private UserDao userDao;
	@Test
	void testFind() {
		List<User>userList=userDao.findAll();
		System.out.println(userList);
	}

}
