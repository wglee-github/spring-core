package hello.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.service.MemberServiceImpl;
import hello.core.member.service.MemberService;

class OrderServiceTest {

	MemberService memberService;
	OrderService orderService;
	
	@BeforeEach
	void beforeEach() {
		AppConfig appConfig = new AppConfig();
		memberService = appConfig.memberService();
		orderService = appConfig.orderService();
	}

	
	@Test
	void 구현체외부주입_createOrder() {
		
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
	
	@Test
	void 구현체직접생성_createOrder() {
		MemberService memberService = new MemberServiceImpl();
		OrderService orderService = new OrderServiceImpl();
		
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
