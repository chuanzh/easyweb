package cn.chuanz.webframe.bean;

public class HtmlPage {

	private boolean isSuccess;
	private Object content;
	
	public HtmlPage() {
		// TODO Auto-generated constructor stub
	}
	
	public HtmlPage(boolean isSuccess, Object content) {
		this.isSuccess = isSuccess;
		this.content = content;
	}
	
	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

}
