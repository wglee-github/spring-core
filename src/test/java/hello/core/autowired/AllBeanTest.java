package hello.core.autowired;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;

public class AllBeanTest {

	
	@Test
	void findAllBean() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
		
		DiscountService discountService = ac.getBean(DiscountService.class);
		
		Member member = new Member(1L, "member", Grade.VIP);
		int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");
		
		assertThat(discountPrice).isEqualTo(1000);
		
		int ratePrice = discountService.discount(member, 20000, "rateDiscountPolicy");
		assertThat(ratePrice).isEqualTo(2000);
	}
	
	/**
	 * 
	 * 내가 원하는 Bean 조회 하여 다형성 활용하기
	 * service의 구현체가 여러개 인 경우 조건에 따라 구현체를 선택적으로 조회할 수 있다.
	 * 
	 * 다만 호출하는 구현체의 bean 이름을 텍스트로 넘겨야 하는 리스크가 있다.
	 */
	static class DiscountService{
		private final Map<String, DiscountPolicy> policyMap;
		private final List<DiscountPolicy> policies;
		
		/**
		 * DI 주입 시  DiscountPolicy 타입과 일치하는 모든 빈 조회하여 주입해준다.
		 */
		@Autowired
		public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
			this.policyMap = policyMap;
			this.policies = policies;
			System.out.println("policyMap = " + policyMap);
			System.out.println("policies = " + policies);
		}

		/**
		 * 
		 * @param member
		 * @param price
		 * @param discountCode
		 * @return
		 */
		public int discount(Member member, int price, String discountCode) {
			DiscountPolicy discountPolicy = policyMap.get(discountCode);
			return discountPolicy.discount(member, price);
		}
	}
}
