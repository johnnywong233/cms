## 错误1
```
Error:java: Compilation failed: internal java compiler error
```
解决方法：
build, execution & deployment选项卡设置java compiler，确保和本地安装的JDK版本一致。
[参考](http://blog.csdn.net/u011275152/article/details/45242201)

## 错误2
程序包org.slf4j不存在
原因在于引入了Lombok的注解@Slf4j，然后想要使用其log.info("");功能
暂时不知道是不是因为版本的原因。换用1.16.10还是不行。

## 报错3
执行```mvn install```报错
```
Caused by: java.lang.ClassCastException: com.github.pagehelper.PageHelper cannot be cast to org.apache.ibatis.plugin.Interceptor
```
解决方法：
```
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper</artifactId>
    <!--<version>5.1.2</version>-->
    <version>4.1.6</version>
</dependency>
```

## 报错4
执行命令```mvn install```报错：
```
Caused by: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near '' at line 1
```
错误原因：
单元测试方法有问题；
解决方法：
[参考](http://blog.csdn.net/you23hai45/article/details/45103723)

## 报错5
执行命令```mvn install```报错：
```
Caused by: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry '10' for key 'PRIMARY'
```
意思是在数据库已经有数据，可是我用一个没有用过的id还是报这个错误。为什么？

## 报错6
Tomcat配置“好”之后，启动应用，下面的output日志显示
```
[2017-12-09 12:23:14,900] Artifact cms:war: Artifact is deployed successfully
```
但是访问http://localhost:8080/index.jsp一直报错404，api也不能请求！！
各种百度，实在是找不到解决方法。
并且其他的spring mvc 这一套web应用也是这样的一直报错404；
暂且搁置一晚上，思考原因；
没有办法：终极方法就是重新安装Tomcat，Windows开发平台，卸载之前安装在C盘的Tomcat，重新安装在D盘，没有问题！

## 报错7
```
java.lang.ClassNotFoundException: org.apache.jsp.index_jsp

```
在pom文件导入 jstl 以及 standard 两个jar包即可。

## 报错8
```
父工程不要添加dependencyManagement信息，或者就添加完整的子module需要的所有dependency，否则子module的jar包找不到，编译不通过。
```
note：注册的一套用户名/密码：root/1Qaz2w