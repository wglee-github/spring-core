package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.repository.MemoryMemberRepository;
import hello.core.member.service.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;

public class ConfigurationSingletonTest {

	@Test
	@DisplayName("컨피그레이션이 빈 객체를 싱글톤으로 관리하는지 확인")
	void configurationTest() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		
		MemberServiceImpl memberService =  ac.getBean("memberService", MemberServiceImpl.class);
		OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
		MemoryMemberRepository memberRepository = ac.getBean("memberRepository", MemoryMemberRepository.class);
		
		System.out.println("memberService getclass = " + memberService.getMemberRepository());
		System.out.println("orderService getclass = " + orderService.getMemberRepository());
		System.out.println("orderService getclass = " + memberRepository);
		
		Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
		Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
	}
}
