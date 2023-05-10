package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.service.MemberService;

public class SingletonTest {

	@Test
	@DisplayName("스프링 없는 순수한 DI 컨테이너")
	void pureContainer() {
		AppConfig appConfig = new AppConfig();
		// 1. 조회 : 호출할 때 마다 객체 생성
		MemberService memberService1 = appConfig.memberService();

		// 2. 조회 : 호출할 때 마다 객체 생성
		MemberService memberService2 = appConfig.memberService();
		
		System.out.println("memberService1 = " + memberService1);
		System.out.println("memberService2 = " + memberService2);
		
		Assertions.assertThat(memberService1).isNotSameAs(memberService2);
	}
	
	@Test
	@DisplayName("싱글톤패턴을 적용한 객체 사용")
	void singletoneServiceTest() {
		// 기본 생성자를 private으로 선언했기 때문에 new 인스턴스 불가하여 컴파일 오류가 발생한다.
//		new SingletonService();
		
		SingletonService singletonService1 = SingletonService.getInstance();
		SingletonService singletonService2 = SingletonService.getInstance();
		
		System.out.println("singletonService1 = " + singletonService1);
		System.out.println("singletonService2 = " + singletonService2);
		
		Assertions.assertThat(singletonService1).isSameAs(singletonService2);
	}
	
	
	/**
	 * 싱글톤 컨테이너
스프링 컨테이너는 싱글톤 패턴의 문제점을 해결하면서, 객체 인스턴스를 싱글톤(1개만 생성)으로
관리한다.
지금까지 우리가 학습한 스프링 빈이 바로 싱글톤으로 관리되는 빈이다.
싱글톤 컨테이너
스프링 컨테이너는 싱글턴 패턴을 적용하지 않아도, 객체 인스턴스를 싱글톤으로 관리한다.
이전에 설명한 컨테이너 생성 과정을 자세히 보자. 컨테이너는 객체를 하나만 생성해서 관리한다.
스프링 컨테이너는 싱글톤 컨테이너 역할을 한다. 이렇게 싱글톤 객체를 생성하고 관리하는 기능을 싱글톤
레지스트리라 한다.
스프링 컨테이너의 이런 기능 덕분에 싱글턴 패턴의 모든 단점을 해결하면서 객체를 싱글톤으로 유지할 수
있다.
싱글톤 패턴을 위한 지저분한 코드가 들어가지 않아도 된다.
DIP, OCP, 테스트, private 생성자로 부터 자유롭게 싱글톤을 사용할 수 있다
	 */
	@Test
	@DisplayName("스프링 컨테이너와 싱글통")
	void springContainer() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		
		MemberService memberService1 = ac.getBean("memberService",MemberService.class);
		MemberService memberService2 = ac.getBean("memberService",MemberService.class);
		
		System.out.println("memberService1 = " + memberService1);
		System.out.println("memberService2 = " + memberService2);
		
		Assertions.assertThat(memberService1).isSameAs(memberService2);
	}
	
	
}
