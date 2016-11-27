package cn.chuanz.webframe;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.chuanz.filter.HttpFilter;
import cn.chuanz.util.FuncStatic;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
 

public class FreemarkerBuilder implements HtmlBuilder{
	private static Logger logger = Logger.getLogger ( FreemarkerBuilder.class.getName () );
	private static Configuration cfg = new Configuration();
	private Template temp = null;
	private Map<String,Object> map = new HashMap<String,Object>();
	private String node = null;
	public static void init(String viewFolder , boolean ifCache,String[] macros){
		String viewpath = "../../../"+viewFolder.replace(".", "/")+"/";
		cfg.setClassForTemplateLoading(FreemarkerBuilder.class,viewpath);
		cfg.setDefaultEncoding(HttpFilter.ENCODE);
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		cfg.setNumberFormat("#.######");
		if(macros != null){
			for(String macro : macros){
				cfg.addAutoInclude(macro);
			}
		}
		if(ifCache){
			cfg.setCacheStorage(new freemarker.cache.MruCacheStorage(20, 250));
		}
	}
	 
	@Override
	public void setValue(String name, Object value) {
		map.put(name, value);
	}

	@Override
	public void setValue(Map<String, Object> value) {
		if(value != null){
			for(String key : value.keySet()){
				this.map.put(key, value.get(key));
			}
		}
	}

	@Override
	public void setTemplatePath(String path) {
		try {
			this.temp = cfg.getTemplate(path);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
		}
	}

	private String html() {
		StringWriter sw = new StringWriter();
		try {
			this.temp.process(map, sw);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
		}
		return sw.toString();
	}

	public String html(String node) {
		return FuncStatic.searchString(html(), FuncStatic.format("<!--{0} start-->([\\s\\S]*?)<!--{0} end-->", node));
	}

	@Override
	public void html(Writer writer) {
		try {
			if(this.node == null){
				this.temp.process(map, writer);
			}else{
				writer.write(this.html(node));
			}
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
		}
	}

	@Override
	public void setNode(String node) {
		this.node = node;
	}
	
	@Override
	public String toString(){
		if(this.node == null){
			return this.html();
		}
		else{
			return this.html(node);
		}
	}
}
