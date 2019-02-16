package cn.henu.search.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


/**
 * 配置全局异常处理器，注意这个只能配置一个
 * @author syw
 *
 */
public class GlocalExceptionResolver implements HandlerExceptionResolver {

	private static final Logger logger=LoggerFactory.getLogger(GlocalExceptionResolver.class);
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		
		// 1.打印控制台
		ex.printStackTrace();
		//2.写日志
		logger.debug("测试系统输出的日志");
		logger.info("系统出现异常");
		logger.error("系统发生异常",ex);//级别最高,debug<info<warning<error
		//3.发邮件，发短信，发邮件使用jmail工具包
		//4.显示错误界面
		ModelAndView view = new ModelAndView();
		view.setViewName("error/exception");
		return view;
	}

}
