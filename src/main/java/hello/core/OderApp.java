package hello.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.service.MemberServiceImpl;
import hello.core.member.service.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OderApp {

	
	public static void main(String[] args) {
		스프링컨테이너();
	}
	
	static void 스프링컨테이너() {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		MemberService memberService = context.getBean("memberService", MemberService.class);
		OrderService orderService = context.getBean("orderService", OrderService.class);
		
		Long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP);
		memberService.join(member);
		
		Order order = orderService.createOrder(memberId, "itemA", 20000);
		
		System.out.println(" createOrder :" + order);
		System.out.println(" createOrder :" + order.calculatePrice());
	}
	
	static void 구현체외부주입() {
		AppConfig appConfig = new AppConfig();
		OrderService orderService = appConfig.orderService();
		MemberService memberService =  appConfig.memberService();
		
		Long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP);
		memberService.join(member);
		
		Order order = orderService.createOrder(memberId, "itemA", 20000);
		
		System.out.println(" createOrder :" + order);
		System.out.println(" createOrder :" + order.calculatePrice());
	}
	
	static void 구현체직접생성() {
		MemberService memberService = new MemberServiceImpl();
		OrderService orderService = new OrderServiceImpl();
		
		Long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP);
		memberService.join(member);
		
		Order order = orderService.createOrder(memberId, "itemA", 10000);
		
		System.out.println(" createOrder :" + order);
		System.out.println(" createOrder :" + order.calculatePrice());
	}
}
