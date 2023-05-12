package hello.core.autowired;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.repository.MemoryMemberRepository;
import hello.core.order.OrderServiceImpl;
import jakarta.annotation.Nullable;

public class AutowiredTest {

	
	@Test
	void autowriedOptionTest() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
		
		
//		OrderServiceImpl impl = new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());
		
	}
	
	static class TestBean{
		
		/**
		 *  자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안됨 
		 */
		@Autowired(required = false)
		public void setBean1(Member bean1) {
			System.out.println("bean1 = " + bean1);
		}
		
		/**
		 *  자동 주입할 대상이 없으면 null이 입력된다 
		 */
		@Autowired
		public void setBean2(@Nullable Member bean2) {
			System.out.println("bean2 = " + bean2);
		}
		
		/**
		 * 자동 주입할 대상이 없으면 Optional.empty 가 입력된다 
		 */
		@Autowired
		public void setBean3(Optional<Member> bean3) {
			System.out.println("bean3 = " + bean3);
		}
	}
}
