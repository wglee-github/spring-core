package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

	
	@Test
	@DisplayName("싱글톤 객체의 상태를 유지할 경우 발생하는 문제점")
	void statefulServiceSingleton() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
		StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
		StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);
		
		statefulService1.order("orderA", 10000);
		statefulService2.order("orderB", 20000);
		
		System.out.println("statefulService1 price = " + statefulService1.getPrice());
	}
	
	static class TestConfig{
		
		@Bean
		public StatefulService statefulService() {
			return new StatefulService();
		}
	}
}
