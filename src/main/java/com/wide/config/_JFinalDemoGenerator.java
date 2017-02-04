package com.wide.config;

import javax.sql.DataSource;

import com.jfinal.kit.*;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.c3p0.C3p0Plugin;

/**
 * 在数据库表有任何变动时，运行一下 main 方法，极速响应变化进行代码重构
 */
public class _JFinalDemoGenerator {
	
	public static DataSource getDataSource() {
		PropKit.use("sys.properties");
		C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
		c3p0Plugin.start();
		return c3p0Plugin.getDataSource();
	}
	//Bean生成器
	public static void main(String[] args) {
		// base model 所使用的包名
		String baseModelPackageName = "com.wide.common.model.base";
		// base model 文件保存路径
		String baseModelOutputDir = PathKit.getWebRootPath() + "/src/main/java/com/wide/common/model/base";
		
		// model 所使用的包名 (MappingKit 默认使用的包名)
		String modelPackageName = "com.wide.common.model";
		// model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
		String modelOutputDir = baseModelOutputDir + "/..";
		
		// base model 所使用的包名
		@SuppressWarnings("unused")
		String viewModelPackageName = "com.wide.common.viewmodel";
		// base model 文件保存路径
		@SuppressWarnings("unused")
		String viewModelOutputDir = PathKit.getWebRootPath() + "/src/main/java/com/wide/common/viewmodel";
		
		
		// model创建生成器
		Generator gernerator = new Generator(getDataSource(), baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);// 添加不需要生成的表名
		gernerator.addExcludedTable("adv");
		// 设置是否在 Model 中生成 dao 对象
		gernerator.setGenerateDaoInModel(true);
		// 设置是否生成字典文件
		gernerator.setGenerateDataDictionary(false);
		// 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
		gernerator.setRemovedTableNamePrefixes("sys");
		// 生成
		gernerator.generate();
		/**
		//viewmodel生成器
		Generator viewgernerator = new Generator(getDataSource(), viewModelPackageName, viewModelOutputDir);
		// 添加不需要生成的表名
		viewgernerator.addExcludedTable("adv");
		// 设置是否在 Model 中生成 dao 对象
		viewgernerator.setGenerateDaoInModel(false);
		// 设置是否生成字典文件
		viewgernerator.setGenerateDataDictionary(false);
		// 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
		viewgernerator.setRemovedTableNamePrefixes("sys");
		// 生成
		viewgernerator.generate();
		*/
		
	}
}




