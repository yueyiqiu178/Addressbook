package site.yueyiqiu.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterFilter implements Filter{
	
	String encoding=null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		this.encoding=filterConfig.getInitParameter("encoding");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		System.out.println("EnterFilter!!!");
		System.out.println("®É¶¡:"+new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(new Date()));
		
		if(this.encoding!=null){
			request.setCharacterEncoding(encoding);
			response.setContentType("text/html; charset="+this.encoding);
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
		this.encoding=null;
		
	}
	
}
