package cn.jy.music.test;


import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import cn.jy.music.pojo.User;
import cn.jy.music.service.impl.UserServiceImpl;

class TestLogin {

	@Test
	void test() {
		@SuppressWarnings("resource")
		ApplicationContext act = new ClassPathXmlApplicationContext("Application.xml");
		UserServiceImpl umi = (UserServiceImpl)act.getBean("userService");
		User param = new User();
		param.setUsername("xk");
		param.setPassword("qqqqqq");
		User result = umi.login(param);
		System.out.println(result+"²âÊÔ½á¹û");
	}
}
