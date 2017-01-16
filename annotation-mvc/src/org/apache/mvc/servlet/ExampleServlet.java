package org.apache.mvc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.mvc.bean.ActionBean;
import org.apache.mvc.scanner.Scanner;
import org.apache.mvc.util.ParamUtil;

public class ExampleServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(ExampleServlet.class);
	/**
	 * Constructor of the object.
	 */
	public ExampleServlet() {
		super();
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
          doPost(request, response);
	}

	 
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, ActionBean> beanMap = (Map<String, ActionBean>)getServletContext().getAttribute(Config.MVC_CONTENT);
		ActionBean actionBean = beanMap.get(request.getServletPath());
		if(actionBean == null){
			super.doPost(request, response);
			return;
		}
		 
		 Object[] params = ParamUtil.getParamEntity(actionBean.getParams(),request,response,request.getSession());
		 Object result = "";
		 try {
			Class clazz = Class.forName(actionBean.getClassPath());
			Method method = clazz.getMethod(actionBean.getMethod(), actionBean.getParams());//得到哪个方法，参数是
			method.invoke(clazz.newInstance(), );//执行哪个对象的的方法,参数是什么
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		if(actionBean.isResponseBody()){
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		}else if(result instanceof String){
			String url = (String) result;if (url.startsWith(Config.REDIRECT_PREFIX)) {
                response.sendRedirect(url.substring(Config.REDIRECT_PREFIX.length()));
            } else {
                request.getRequestDispatcher(Config.PAGE_PREFIX + url + Config.PAGE_SUFFIX).forward(request, response);
            }
        } else {
            logger.error("-----> 返回类型错误");

        }

	}
	public void init() throws ServletException {
		try {
			getServletContext().setAttribute(Config.MVC_CONTENT, Scanner.scannerPackage(Config.SCANNER_PATH, new HashMap<>()));
			log.info("--mvc初始化成功!--");
		} catch (Exception e) {
			log.info("--mvc初始化失败--");
		}
	}

}
