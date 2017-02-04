package com.wide.config;



import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.wide.interceptor.LoginInterceptor;
import com.wide.common.model._MappingKit;
import com.wide.route.ExamRoutes;
import com.wide.route.ExerciseRoutes;
import com.wide.route.ResourceRoutes;
import com.wide.route.StatisticsRoutes;
import com.wide.route.SysRoutes;
import com.wide.util.JspSkipHandler;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.ext.plugin.shiro.ShiroInterceptor;
import com.jfinal.ext.plugin.shiro.ShiroPlugin;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.render.ViewType;

/**
 * API引导式配置
 */
public class _BaseConfig extends JFinalConfig {
	/**
	 * 供Shiro插件使用。
	 */
	Routes routes;
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		// 加载少量必要配置，随后可用PropKit.get(...)获取值
		PropKit.use("sys.properties");
		me.setDevMode(PropKit.getBoolean("devMode", false));
		//默认为freemarker模版 现调整为jsp
		//me.setViewType(ViewType.VELOCITY);
		me.setViewType(ViewType.JSP);
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		this.routes = me;
		me.add(new SysRoutes());
		me.add(new ExamRoutes()); 
		me.add(new ResourceRoutes());
		me.add(new ExerciseRoutes());
		me.add(new StatisticsRoutes());
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件
		/**
		C3p0Plugin C3p0Plugin = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
		me.add(C3p0Plugin);
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(C3p0Plugin);
		me.add(arp);
		*/
		// 配置DruidPlugin
		DruidPlugin druiddb = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password"));
		druiddb.addFilter(new StatFilter());
		WallFilter wall = new WallFilter();
		wall.setDbType("mysql");
		druiddb.addFilter(wall);
		me.add(druiddb);
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druiddb);
		arp.setContainerFactory(new PropertyNameContainerFactory()); //CaseInsensitiveContainerFactory()
		me.add(arp);
		// 所有配置在 MappingKit 中搞定(可以配置多个)
	    _MappingKit.mapping(arp);
	    
	  //加载Shiro插件
	  //me.add(new ShiroPlugin(routes));
	  ShiroPlugin shiroPlugin = new ShiroPlugin(this.routes);
	  shiroPlugin.setLoginUrl("/index");
	  shiroPlugin.setSuccessUrl("/user/addInfo");
	  shiroPlugin.setUnauthorizedUrl("/errorPage");
	  me.add(shiroPlugin);
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		me.add(new SessionInViewInterceptor(true));
		me.add(new LoginInterceptor());
		me.add(new ShiroInterceptor());
		//me.add(new CaptchaFormAuthenticationInterceptor());
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("basepath"));
		DruidStatViewHandler dvh =  new DruidStatViewHandler("/druid");
		me.add(dvh);
		me.add(new JspSkipHandler());
	}
	
}
