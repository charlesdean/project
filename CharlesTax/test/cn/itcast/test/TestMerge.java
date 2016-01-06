package cn.itcast.test;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.test.dao.TestDao;
import cn.itcast.test.entity.Person;
import cn.itcast.test.service.TestService;

public class TestMerge {
	
	@Resource
	TestDao dao ;
	
	ClassPathXmlApplicationContext ctx =  new ClassPathXmlApplicationContext("applicationContext.xml");;
	@Test
	public void testSpringInit() throws Exception {
		
		 ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
	}
	@Test
	public void testSpring() throws Exception {
		

		 TestService service = (TestService) ctx.getBean("testService");
		 service.say();
	}
	@Test
	public void testhibernate() throws Exception {
	
		SessionFactory factory = (SessionFactory) ctx.getBean("sessionFactory");
		Session session = factory.openSession();
		session.getTransaction().begin();
		session.save(new Person("»À‘±1"));
		session.getTransaction().commit();
		session.close();
		
	}
	@Test
	public void testTransactionSave() throws Exception {
		 TestService service = (TestService) ctx.getBean("testService");
	//	 service.save(new Person("∫«∫«1"));
		
	System.out.println(service.findPerson("402881e9521552d801521552db930000").getName());
	}
}
