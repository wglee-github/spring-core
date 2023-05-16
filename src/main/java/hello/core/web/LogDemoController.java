package hello.core.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.core.common.MyLogger;
import hello.core.web.service.LogDemoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

	private final LogDemoService logDemoService;
	private final MyLogger myLogger;
	
	@RequestMapping("log-demo")
	@ResponseBody
	public String logDemo(HttpServletRequest request) throws InterruptedException {
		String requestURL = request.getRequestURL().toString();
		myLogger.setRequestURL(requestURL);
		
		myLogger.log("controller test");
		
		Thread.sleep(1000);
		
		logDemoService.logic("testId");
		
		return "OK";
	}
}
