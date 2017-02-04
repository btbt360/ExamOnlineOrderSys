 # JfinalYBaseV1.0
 
 这是学习Jfinal架构的第一个框架,自己搭建并且已经实施过一个项目,这里并不是完整的架构。
 里面用了一套BootStrop样式来玩,并且集成了Ckfinder和Ckeditor,希望对大家学习有帮助。
 数据库文件和相关的配置maven包在JfinalYBaseV1.0/src/main/webapp/common/lib/目录下。请自行查看！有问题可以留言！
 正在搭建2.0版本下个版本将上线主流的架构和jfinal结合，敬请期待！
 
 
 安装时会遇到的问题：
 
 一、maven仓库中有些包没有，所以需要手动加入到maven仓库中，具体包都已经放入D:目录下方法如下：
 
 需要将/Users/wide/Documents/workspace/lib/改为你存放的lib包存放的目录，然后在命令窗口中输入如下代码
 
 mvn install:install-file -Dfile=/Users/wide/Documents/workspace/lib/ckeditor-java-core-3.5.3.jar 
 -DgroupId=ckeditor-DartifactId=ckeditor-java-core -Dversion=3.5.3 -Dpackaging=jar

 mvn install:install-file -Dfile=/Users/wide/Documents/workspace/lib/ckfinder-2.3.jar 
 -DgroupId=ckfinder -DartifactId=ckfinder -Dversion=2.3 -Dpackaging=jar

 mvn install:install-file -Dfile=/Users/wide/Documents/workspace/lib/ckfinderplugin-fileeditor-2.3.jar 
 -DgroupId=ckfinderplugin -DartifactId=ckfinderplugin-fileeditor -Dversion=2.3 -Dpackaging=jar

 mvn install:install-file -Dfile=/Users/wide/Documents/workspace/lib/ckfinderplugin-imageresize-2.3.jar 
 -DgroupId=ckfinderplugin -DartifactId=ckfinderplugin-imageresize -Dversion=2.3 -Dpackaging=jar

 mvn install:install-file -Dfile=/Users/wide/Documents/workspace/lib/jfinal-shiro-2.0.0.jar -DgroupId=jfinal 
 -DartifactId=jfinal-shiro -Dversion=2.0.0 -Dpackaging=jar
 
 二、导入Eclipse中后执行三步：
 
 右击项目 Maven->Update Project。
 Project->Clean Project。
 右击项目 Run As->Maven building。
 
 三、文件位置

 数据库配置文件JfinalYBaseV1.0/src/main/resources/sys.properties
 ckeditor文件位置JfinalYBaseV1.0/src/main/webapp/ckeditor/ 在此处的config.js中可以配置ckeditor相关属性
 ckfinder文件位置JfinalYBaseV1.0/src/main/webapp/ckfinder/ 在此处的config.js中可以配置ckfinder相关属性
 ckfinder存放文件放置配置文件 JfinalYBaseV1.0/src/main/webapp/WEB-INF/ckfinder.xml
 ckfinder上传的文件位置JfinalYBaseV1.0/src/main/webapp/userfiles/files/
 前台UI放置在JfinalYBaseV1.0/src/main/webapp/static/
 页面放置 JfinalYBaseV1.0/src/main/webapp/pages/

 四、项目运用到的技术
 
 1.jfinal
 2.bootstrop 样式框架
 3.shiro 安全验证
 4.treetable 
 5.datatables
 6.ztree
 7.ckeditor和ckfinder
 其他是一些零零碎碎的东西，仅供参考！
