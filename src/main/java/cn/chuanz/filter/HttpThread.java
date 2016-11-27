package cn.chuanz.filter;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.chuanz.util.FuncStatic;
import cn.chuanz.webframe.bean.HtmlPage;

public class HttpThread implements Runnable{
	private static Logger logger = Logger.getLogger ( HttpThread.class.getName () );
	private HttpServletRequest request = null;
	private HttpServletResponse response  = null;
	private HttpFilter filter = null;
	private HtmlPage htmlPage = null;
	
	public HtmlPage getHtmlPage() {
		return this.htmlPage;
	}
	
	@Override
	public void run() {
		htmlPage = this.process(this.request, this.response);
	}
	
	private HtmlPage process(HttpServletRequest request, HttpServletResponse response) {
		String nowURI = request.getRequestURI();
		Object o = null;
		try {
			String className = nowURI.substring(filter.alias().length(), nowURI.indexOf(".")).toLowerCase();
			try {
				Class c = HttpFilter.actionList.get(className);
				o = c.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("not found action class: "+nowURI);
				return new HtmlPage(false,"<h1>404, NOT FIND PAGE!</h1>");
			}
			
			String funcName = "func";
			if (!FuncStatic.checkIsEmpty(request.getParameter("method"))) {
				funcName = request.getParameter("method");
			}
			
			filter.beforeDoControl(o,request,response);
			
			Method m = null;
			m = o.getClass().getMethod(funcName);
			Object restr = m.invoke(o);
			if (restr != null) {
				return new HtmlPage(true,restr);
			}
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
		} finally {
			try {
				filter.releaseResource(o);
			} catch (Exception e) {
				logger.error(FuncStatic.errorTrace(e));
			}
		}
		
		return new HtmlPage(false,"<h1>page maybe has inter error!</h1>");
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpFilter getFilter() {
		return filter;
	}

	public void setFilter(HttpFilter filter) {
		this.filter = filter;
	}

}
