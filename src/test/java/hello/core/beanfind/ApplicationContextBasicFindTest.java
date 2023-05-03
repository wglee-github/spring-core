package hello.core.beanfind;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.service.MemberService;
import hello.core.member.service.MemberServiceImpl;

/**
 * 
 * 스프링 컨테이너에서 스프링 빈을 찾는 가장 기본적인 조회 방법
	ac.getBean(빈이름, 타입)
	ac.getBean(타입)

 * 조회 대상 스프링 빈이 없으면 예외 발생
	NoSuchBeanDefinitionException: No bean named 'xxxxx' available

 */
public class ApplicationContextBasicFindTest {

	@Test
	@DisplayName("빈 이름으로 조회")
	void findBeanByName() {
		// given
		// AppConfig.class 를 읽어서 스프링 bean으로 등록
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		
		// when
		// bean 이름으로 bean 객체 조회
		Object bean = ac.getBean("memberService");
		
		// then
		System.out.println("bean = " + bean);
		Assertions.assertThat(bean).isInstanceOf(MemberServiceImpl.class);
	}
	
	@Test
	@DisplayName("빈 이름과 타입으로 조회")
	void findBeanByNameAndType() {
		// given
		// AppConfig.class 를 읽어서 스프링 bean으로 등록
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		
		// when
		// type은 리턴 타입
		Object bean = ac.getBean("memberService", MemberService.class);
		
		// then
		System.out.println("bean = " + bean);
		Assertions.assertThat(bean).isInstanceOf(MemberServiceImpl.class);
	}
	
	@Test
	@DisplayName("빈 타입으로 조회")
	void findBeanByType() {
		// given
		// AppConfig.class 를 읽어서 스프링 bean으로 등록
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		
		// when
		// type은 리턴 타입
		Object bean = ac.getBean(MemberService.class);
		
		// then
		System.out.println("bean = " + bean);
		Assertions.assertThat(bean).isInstanceOf(MemberServiceImpl.class);
	}
	
	@Test
	@DisplayName("빈 타입을 구체 타입으로 조회")
	void findBeanByType2() {
		// given
		// AppConfig.class 를 읽어서 스프링 bean으로 등록
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		
		// when
		// type을 구체타입으로 조회해도 된다.
		Object bean = ac.getBean(MemberServiceImpl.class);
		
		// then
		System.out.println("bean = " + bean);
		Assertions.assertThat(bean).isInstanceOf(MemberServiceImpl.class);
	}
	
	@Test
	@DisplayName("빈 이름으로 조회 실패")
	void findBeanByNameFail() {
		// given
		// AppConfig.class 를 읽어서 스프링 bean으로 등록
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		
		// when
		// then
		org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
				() -> ac.getBean("xxxx", MemberService.class));
	}
}
