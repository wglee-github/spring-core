package hello.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class PrototypeTest {

	/**
	 * prototype 빈 스코프
	 * 스프링 컨테이너가 시작된 후 실제 빈 호출 시 @PostConstruct 선언된 메소드를 매번 호출
	   빈이 한번 생성 후 추가 생성되지 않는다는 의미
	 */
	@Test
	void prototypeBeanFind() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
		System.out.println("find ProtoTypeBean1");
		PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
		System.out.println("find ProtoTypeBean2");
		PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
		
		System.out.println("prototypeBean1 = " + prototypeBean1);
		System.out.println("prototypeBean2 = " + prototypeBean2);
		
		assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
	}
	
	@Scope("prototype")
	static class PrototypeBean{
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
