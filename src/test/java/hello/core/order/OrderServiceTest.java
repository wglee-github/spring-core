package hello.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.service.MemberServcieImpl;
import hello.core.member.service.MemberService;

class OrderServiceTest {

	MemberService memberService = new MemberServcieImpl();
	OrderService orderService = new OrderServiceImpl();
	
	@Test
	void createOrder() {
		
		// givne
		Long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP);
		memberService.join(member);
		
		
		// when
		Order order = orderService.createOrder(memberId, "itemA", 10000);
		
		
		// then
		Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
		Assertions.assertThat(order.calculatePrice()).isEqualTo(9000);
		
		
	}

}
