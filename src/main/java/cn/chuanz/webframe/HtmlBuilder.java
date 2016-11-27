package cn.chuanz.webframe;

import java.io.Writer;
import java.util.Map;


public interface HtmlBuilder {
	
	public void setNode(String node);
	
	public void setValue(String name, Object value);

	public void setValue(Map<String, Object> value);

	public void setTemplatePath(String path);

	public void html(Writer writer);
	
	public String toString();
}
