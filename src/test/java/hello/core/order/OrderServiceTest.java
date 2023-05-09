package hello.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
		// AppConfig : 애노테이션 기반의 자바 설정 클래스로 스프링 컨테이너를 만들었음.
		AppConfig appConfig = new AppConfig();
		memberService = appConfig.memberService();
		orderService = appConfig.orderService();
	}

	
	@Test
	@DisplayName("구현체를 외부에서 주입할 수 있다.")
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
	
	@Test
	@DisplayName("구현체를 직접 생성할 수 있다.")
	void createOrderByDirect() {
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
