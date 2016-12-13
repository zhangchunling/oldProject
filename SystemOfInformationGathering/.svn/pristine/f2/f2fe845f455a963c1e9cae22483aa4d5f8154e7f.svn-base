package com.zrgk.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zrgk.permission.model.Employee;


/**
 * 过滤器： 实现非法登陆（想进行相关操作，如果没登录，页面直接跳转到登录页面）
 * @author lc
 * 2015-05-25
 */
public class MyFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		//销毁
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//1. HttpServletRequest是ServletRequest类的子类
		//把ServletRequest向下强转成HttpServletRequest以方便使用 
		HttpServletRequest req=(HttpServletRequest)request;
		req.setCharacterEncoding("utf-8");//处理request请求的编码格式
		
		//2.获取请求的地址:用于后面直接跳转到希望的页面
		String path = req.getRequestURI();
		//这个得到的是工程名后面跟的一个要访问的地址
		//访问查询页面时path为：/jsp_0525servletMonitor_FilterAndJavaScript/listAllUsers
		//访问商品页面时path为：/jsp_0525servletMonitor_FilterAndJavaScript/product.jsp
		
		String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path;
		//拼接成完整地址
		/*System.out.println(path);
		System.out.println(basePath);*/
		
		//3.实现 用户非法登陆
		HttpSession session=req.getSession();//创建得到session
		Employee user=(Employee) session.getAttribute("user");
		//得到用户登陆后session里存的用户信息
		
		//4.用户没登录，想要去执行那些只有登录后才能执行的操作，那么会直接跳到登录页面去		
		if(user==null){
			
			session.setAttribute("basePath", basePath);//将整个链接存入session,作判断用
			String b="http://localhost:8080/SystemOfInformationGathering/employeePackagee/login_login.action";
			if(b.equals(basePath)){
				chain.doFilter(request, response);//过滤器放行到servlet的方法
			}else{
			//如果user是空就跳转到登陆页面去
			req.getRequestDispatcher("/login.jsp").forward(req, response);
			//forward里可以不跟req和resp，跟大类也可以
			}
		//5.放行。如果session里有值时则放行，使用户可进行只有登录状态才能做的操作
		}else{	
			
			chain.doFilter(request, response);//过滤器放行到servlet的方法
			
		}
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		//初始化
	}

}
