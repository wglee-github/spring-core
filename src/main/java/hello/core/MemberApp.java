package hello.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.service.MemberServiceImpl;
import hello.core.member.service.MemberService;

public class MemberApp {
	
	MemberService memberService;
	
	public static void main(String[] args) {
		스프링컨테이너();
	}
	
	/**
	 * 구현체 외부 주입 테스트
	 */
	static void 스프링컨테이너() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
		
		Member member = new Member(1L, "memberA", Grade.VIP);
		memberService.join(member);
		
		Member findMember = memberService.findMember(1L);
		System.out.println("new member : " + member.getName());
		System.out.println("find Member : " + findMember.getName());
		System.out.println("new Member = find Member : " + (member.getClass() == findMember.getClass()));
		
	}
	
	/**
	 * 구현체 외부 주입 테스트
	 */
	static void 구현체외부주입() {
		AppConfig appConfig = new AppConfig();
		MemberService memberService = appConfig.memberService();
		
		Member member = new Member(1L, "memberA", Grade.VIP);
		memberService.join(member);
		
		Member findMember = memberService.findMember(1L);
		System.out.println("new member : " + member.getName());
		System.out.println("find Member : " + findMember.getName());
		System.out.println("new Member = find Member : " + (member.getClass() == findMember.getClass()));
		
	}
	
	/**
	 * MemberServiceImpl을 직접 선언 테스트 
	 */
	static void 구현체직접생성() {
		MemberService memberService = new MemberServiceImpl();
		
		Member member = new Member(1L, "memberA", Grade.VIP);
		memberService.join(member);
		
		Member findMember = memberService.findMember(1L);
		System.out.println("new member : " + member.getName());
		System.out.println("find Member : " + findMember.getName());
		System.out.println("new Member = find Member : " + (member.getClass() == findMember.getClass()));
		
		Member member1 = new Member(null, null, null);
		Member member2 = new Member(null, null, null);
		
		System.out.println(member1);
		System.out.println(member2);
		
		// 주소값이 다르기 때문에 == 비교는 false 가 나온다.
		System.out.println("Member1 == Member2 : " + (member1 == member2));
		// 동일한 class 이기 땜누에 true 가 나온다.
		System.out.println("Member1 class == Member2 class : " + (member1.getClass() == member2.getClass()));
		
	}
	
}
