package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * 
 * basePackages : 탐색할 패키지의 시작 위치를 지정한다. 이 패키지를 포함해서 하위 패키지를 모두 탐색한다.
 * basePackages = {"hello.core", "hello.service"} 이렇게 여러 시작 위치를 지정할 수도 있다.
 * basePackageClasses : 지정한 클래스의 패키지를 탐색 시작 위치로 지정한다.
 * 
 * 만약 지정하지 않으면 @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다
 */
@Configuration
@ComponentScan(
//		basePackages = "hello.core.member",  
//		basePackageClasses = AutoAppConfig.class,
		excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
		)
public class AutoAppConfig {

}
