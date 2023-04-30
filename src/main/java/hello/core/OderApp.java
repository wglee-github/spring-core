package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.service.MemberServcieImpl;
import hello.core.member.service.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OderApp {

	
	public static void main(String[] args) {
		MemberService memberService = new MemberServcieImpl();
		OrderService orderService = new OrderServiceImpl();
		
		Long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP);
		memberService.join(member);
		
		Order order = orderService.createOrder(memberId, "itemA", 10000);
		
		System.out.println(" createOrder :" + order);
		System.out.println(" createOrder :" + order.calculatePrice());
	}
}
