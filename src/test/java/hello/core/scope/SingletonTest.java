package hello.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class SingletonTest {

	/**
	 * singleton 빈 스코프 
	 * 스프링 컨테이너가 시작된 직후 @PostConstruct 선언된 메소드를 한번만 호출
	   빈이 한번 생성 후 추가 생성되지 않는다는 의미
	 * 스프링 컨테이너가 종료되는 시점에 @PreDestroy 선언된 메소드 호출 
	 */
	@Test
	void singletonBeanFind() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
		System.out.println("findSingletone1");
		SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
		System.out.println("findSingletone2");
		SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
		
		System.out.println("singletonBean1 = " + singletonBean1);
		System.out.println("singletonBean2 = " + singletonBean2);
		
		assertThat(singletonBean1).isSameAs(singletonBean2);
		
		ac.close();
	}
	
	@Scope("singleton")
	static class SingletonBean{
		
		@PostConstruct
		public void init() {
			System.out.println("SingletonBean.init");
		}
		
		@PreDestroy
		public void destory() {
			System.out.println("SingletonBean.destory");
		}
		
	}
}
