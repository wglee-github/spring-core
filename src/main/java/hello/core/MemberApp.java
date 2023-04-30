package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.service.MemberServcieImpl;
import hello.core.member.service.MemberService;

public class MemberApp {
	
	public static void main(String[] args) {
		MemberService memberService = new MemberServcieImpl();
		
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
