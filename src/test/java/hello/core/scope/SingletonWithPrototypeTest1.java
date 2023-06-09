package hello.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class SingletonWithPrototypeTest1 {

	@Test
	void prototypeFind() {
		
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
	 	
		PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
	 	prototypeBean1.addCount();
	 	int count1 = prototypeBean1.getCount();
	 	assertThat(count1).isEqualTo(1);
	 	
	 	PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
	 	prototypeBean2.addCount();
	 	int count2 = prototypeBean2.getCount();
	 	assertThat(count2).isEqualTo(1);
		
	}
	
	@Test
	void singletonClientUsePrototype() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class, PrototypeBean.class);
		
		SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
		int count1 = singletonBean1.logic();
		assertThat(count1).isEqualTo(1);
		
		SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
		int count2 = singletonBean2.logic();
		assertThat(count2).isEqualTo(2);
		
	}
	
	
	@Scope("singleton")
	static class SingletonBean{
		
		private final PrototypeBean prototypeBean;

		@Autowired
		public SingletonBean(PrototypeBean prototypeBean) {
			this.prototypeBean = prototypeBean;
		}
		
		public int logic() {
			prototypeBean.addCount();
			int count =  prototypeBean.getCount();
			return count;
		}
		
	}
	
	@Scope("prototype")
	static class PrototypeBean {
		
		private int count = 0;
		
		public void addCount() {
			count++;
		}
		
		public int getCount() {
			return count;
		}
		
		@PostConstruct
		public void init() {
			System.out.println("PrototypeBean.init");
		}
		
		@PreDestroy
		public void destroy() {
			System.out.println("PrototypeBean.destroy");
		}
	}
}
