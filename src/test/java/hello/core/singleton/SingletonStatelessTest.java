package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class SingletonStatelessTest {

	
	@Test
	@DisplayName("로컬변수 사용")
	void singletonStatelessLocalVar() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
		SingletonStateless singletonStateless1 = ac.getBean("singletonStateless", SingletonStateless.class);
		SingletonStateless singletonStateless2 = ac.getBean("singletonStateless", SingletonStateless.class);
		
		int price1 = singletonStateless1.orderLocalVar("주문A", 10000);
		int price2 = singletonStateless2.orderLocalVar("주문B", 20000);
		
		System.out.println("singletonStateless1 orderLocalVar = " + price1);
		System.out.println("singletonStateless2 orderLocalVar = " + price2);
	}
	
	@Test
	@DisplayName("파라미터 변수 사용")
	void singletonStatelessParam() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
		SingletonStateless singletonStateless1 = ac.getBean("singletonStateless", SingletonStateless.class);
		SingletonStateless singletonStateless2 = ac.getBean("singletonStateless", SingletonStateless.class);
		
		int price1 = singletonStateless1.orderParam("주문A", 10000);
		int price2 = singletonStateless2.orderParam("주문B", 20000);
		
		System.out.println("singletonStateless1 orderParam = " + price1);
		System.out.println("singletonStateless2 orderParam = " + price2);
	}
	
	@Test
	@DisplayName("ThreadLocal 사용")
	void singletonStatelessThreadLocal() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
		SingletonStateless singletonStateless1 = ac.getBean("singletonStateless", SingletonStateless.class);
		SingletonStateless singletonStateless2 = ac.getBean("singletonStateless", SingletonStateless.class);
		
		singletonStateless1.orderThreadLocal("주문A", 10000);
		singletonStateless2.orderThreadLocal("주문B", 20000);
		
		System.out.println("singletonStateless1 orderThreadLocal = " + singletonStateless1.getPriceThreadLocal());
		System.out.println("singletonStateless2 orderThreadLocal = " + singletonStateless2.getPriceThreadLocal());
	}
	
	static class TestConfig{
		
		@Bean
		public SingletonStateless singletonStateless() {
			return new SingletonStateless();
		}
	}
	
}
