package org.apache.mvc.bean;

import java.util.Arrays;

import org.springframework.annotation.RequestMethod;

/**
 * Action��Ӧ��bean
 * 
 * @author yxm
 * @date 2017-1-15
 */
public class ActionBean {
	private String path;// ����·��
	private RequestMethod[] types;// ��������(post��get)
	private Class[] params;// ������������
	private String classPath;// ������·��
	private String method;// ���󷽷�����
	private boolean isResponseBody;// �Ƿ�������Ϊ��Ӧ�������

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public RequestMethod[] getTypes() {
		return types;
	}

	public void setTypes(RequestMethod[] types) {
		this.types = types;
	}

	public Class[] getParams() {
		return params;
	}

	public void setParams(Class[] params) {
		this.params = params;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public boolean isResponseBody() {
		return isResponseBody;
	}

	public void setResponseBody(boolean isResponseBody) {
		this.isResponseBody = isResponseBody;
	}

	@Override
	public String toString() {
		return "ActionBean [path=" + path + ", types=" + Arrays.toString(types)
				+ ", params=" + Arrays.toString(params) + ", classPath="
				+ classPath + ", method=" + method + ", isResponseBody="
				+ isResponseBody + "]";
	}

}
