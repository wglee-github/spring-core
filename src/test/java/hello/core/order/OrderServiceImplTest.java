package hello.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.repository.MemoryMemberRepository;

class OrderServiceImplTest {

	/**
	 * 스프링을 사용하지 않고 자바로만 테스트
	 * setter 주입으로 한 경우 직접 의존관계를 넣어줘야 한다. 
	 * 넣어주지 않으면 null 에러 발생.
	 * 
	 * 생성자 주입으로 사용하는 경우 의존성 주입을 해주지 않으면 
	 * 일단 OrderServiceImpl 객체의 인스턴스를 생성할 때 컴파일 오류가 발생한다. 즉, 넣어줄 수 밖에 없다. 
	 */
	@Test
	void creatOrder() {
		
		// member도 가짜 객체를 생성해서 넣어 줄 수 있따.
		MemoryMemberRepository memberRepository = new MemoryMemberRepository();
		memberRepository.save(new Member(1L, "name", Grade.VIP));
		
		OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());
		
		// setter 주입
//		orderService.setMemberRepository(new MemoryMemberRepository());
//		orderService.setDiscountPolicy(new RateDiscountPolicy());

		Order order = orderService.createOrder(1L, "itemA", 10000);
		Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
	}

}
