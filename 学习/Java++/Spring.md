# Spring学习笔记

## 配置Maven环境

### 创建Spring项目

GroupId定义项目属于哪个组(edu.ustc.SpringStart)  
ArtifactId定义当前maven项目在组中的唯一id(SpringStart)


### 建立相应目录

- src
	- java
	- resources
	- webapp  

需要将java设置为Sources目录才可以创建包和java文件  
需要将resources设置为Resources目录标注资源文件


### 配置tomcat

1. 下载tomcat服务器到本地
2. +本地的tomcat服务器
3. 修改Application server为Tomcat路径
4. 修改name、访问路径、端口
5. Deployment添加Artifact选择war-exploded

### 配置jetty
1. +maven 依赖
    ```xml
	<plugins> <!--servlet容器 jetty插件-->
		<plugin>
			<groupId>org.eclipse.jetty</groupId>
			<artifactId>jetty-maven-plugin</artifactId>
            <version>9.3.10.v20160621</version>
		</plugin>
	</plugins>
    ```
2. run/debug configurations中
3. +maven配置
4. +command line为jetty:run

---

## Spring 入门

### IOC底层原理

目的是为了解耦合

创建xml配置文件，配置要创建的对象类
```xml
<bean id="userService" class="edu.ustc.UserService"/>
```
创建工厂类，使用dom4j解析配置文件+反射
```java
public static UserService getService() {
        // 使用dom4j解析配置文件+反射
        String classValue = "class属性值";
        // 使用反射创建类对象
        Class clazz = Class.forName(classValue);
        UserService service = clazz.newInstance();
        return service;
}
```

### IOC操作

1. 创建类与方法
2. 创建Spring配置文件，配置创建类
	1. Spring核心配置文件名称和位置不是固定的，建议用ApplicationContext.xml作为配置文件名字
	2. 引入schema约束
        ```xml
        <beans xmlns="http://www.Springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://www.Springframework.org/schema/beans http://www.Springframework.org/schema/beans/Spring-beans.xsd">
            
            <bean id="user" class="edu.ustc.SpringStart.pojo.User"/>
        </beans>
        ```
3. 写代码测试对象创建
    ```java
    @Test
    public void testUser(){
        //加载Spring配置文件，根据创建对象
        ApplicationContext factory = new ClassPathXmlApplicationContext("Spring/applicationContext.xml");
        //得到配置创建的方法
        User user = (User)factory.getBean("user");
        System.out.println(user);
        user.call();
    }
    ```
---

## Spring 的bean管理（xml方式）

### Bean实例化的方式

1. 在Spring里面通过配置文件创建对象
2. bean实例化三种方式实现
   1. 使用类的无参数构造创建  
        ```xml
        <bean id="user" class="edu.ustc.SpringStart.bean.User"/>
        ```
        类里面没有无参数的构造，出现如下异常。
        ```
        org.Springframework.beans.factory.BeanCreationException
        ```
   2. 使用静态工厂创建  
        创建静态方法，返回类对象。
        ```xml
        <bean id="bean" class="edu.ustc.SpringStart.bean.BeanFactory" factory-method="getBean"/>
        ```
   3. 使用实例工厂创建 
        ```xml
        <bean id="bean2Factory" class="edu.ustc.SpringStart.bean.Bean2Factory"/>  
        <bean id="bean2" factory-bean="bean2Factory" factory-method="getBean2"/>
        ```

### Bean标签的常用属性

1. id 名称  
    属性值不能包含特殊符号，根据id值得到配置对象
2. class 创建对象所在类的全路径
3. name 与id一样（抛弃）  
    可以包含特殊符号
4. scope Bean的作用范围
    singleton|prototype|request|session|globalSession

###  属性注入

创建对象时候，向类里面设置属性值  

属性注入的三种方法（java）
   1. 使用set方法注入
   2. 有参数构造注入
   3. 使用接口注入

在Spring框架中支持前两种方式
   1. set方法注入
        ```xml
        <bean id="user3" class="edu.ustc.SpringStart.bean.User">
            <property name="username" value="小李"/>
        </bean>
        ```
   2. 有参数构造注入  
        ```xml
        <bean id="user2" class="edu.ustc.SpringStart.bean.User">
            <constructor-arg name="username" value="小王"/>
        </bean>
        ```

### 注入对象属性属性
1. 创建service和dao类
   1. 在service得到dao对象
2. 具体实现过程
   1. 在service里面把dao作为类型属性
   2. 生成dao类型属性的set方法
   3. 配置文件中注入关系
        ```xml
        <bean id="userDao" class="edu.ustc.SpringStart.dao.UserDao"/>
        <bean id="userService" class="edu.ustc.SpringStart.service.UserService">
            <property name="userDao" ref="userDao"/>
        </bean>
        ```

### p名称空间注入
1. 引入p标签
   ```xml
   xmlns:p="http://www.Springframework.org/schema/p"
   ```
2. 构造bean注入
   ```xml
   <bean id="userService" class="edu.ustc.SpringStart.service.UserService" p:userDao-ref="userDao"/>
   ```

### Spring注入复杂数据
1. 数组
2. list
3. map
4. properties
    ```xml
    <bean id="userDao" class="edu.ustc.SpringStart.dao.UserDao">
        <!--        数组-->
        <property name="arrs">
            <list>
                <value>小王</value>
                <value>小李</value>
            </list>
        </property>
        <!--        list-->
        <property name="list">
            <list>
                <value>大王</value>
                <value>大李</value>
            </list>
        </property>
        <!--        map-->
        <property name="map">
            <map>
                <entry key="aa" value="王"/>
                <entry key="bb" value="李"/>
            </map>
        </property>
        <!--        properties-->
        <property name="properties">
            <props>
                <prop key="cc">王</prop>
                <prop key="dd">李</prop>
            </props>
        </property>
    </bean>
    ```

### IOC与DI区别
1. IOC：控制反转，把对象创建交给Spring完成
2. DI：依赖注入，向类里面的属性中设置值
3. 关系：依赖注入不能单独存在，需要在IOC基础之上完成操作

---

## Spring整合web项目原理

1. 加载Spring核心配置文件  
   需要new对象，功能可以实现，效率很低
2. 实现思想：把加载配置文件和创建对象过程，在服务器启动时完成
3. 实现原理
   1. ServletContext对象
   2. 监听器
   3. 具体使用  
   在服务器启动的时候，为每个项目创建一个ServletContext对象
   在ServletContext对象创建的时候，使用监听器可以具体到servletContext对象什么时候创建  
   使用监听器听到ServletContext对象创建的时候  
   加载Spring配置文件，把配置文件配置对象创建  
   把创建出来的对象放到ServletContext域对象里面（setAttribute方法）  
   获取对象的时候，到ServletContext域得到（getAttribute方法）

---

## Spring 的bean管理（注解）

注解：代码中的特殊标记，使用注解可以完成功能  
写法：@注解名称（属性名称-属性值）  
注解使用在类、方法、属性上

开启注解扫描  
到包里面扫描类、方法、属性上面是否有注解
```xml
<context:component-scan base-package="edu.ustc.SpringStart.dao"/>
```
扫描属性上的注解
```xml
<context:annotation-config></context:annotation-config>
```

### 使用注解创建对象

1. 使用注解
    ```java
    @Component(value = "user")
    public class UserDao
    ```
2. 创建对象有四个注解，Spring中提供@component的三个衍生注解
   - @Controller ：WEB层
   - @Service    ：业务层
   - @Repository ：持久层

    目前这四个注解功能一样，只是为了标注类本身的用途

3. 创建对象单实例多实例
    ```java
    @Scope(value = "prototype")
    ```

### 使用注解注入属性

1. 根据类名自动装配
    ```java
    @Autowired
    private UserDao userDao;
    ```
2. 第二个注解根据bean名字装配
    ```java
    @Resource(name = "userDao")
    private UserDao userDao;
    ```


### xml和注解方式混合使用

使用配置文件方式实现创建对象  
使用注解方式实现注入属性

---

## AOP

### AOP概述

AOP：面向切面（方面）编程，扩展功能不修改源代码实现  
采用横向抽取机制，取代了传统纵向继承体系重复性代码
AOP(Aspect-Oriented Programming:面向切面编程)能够将那些与业务无关，却为业务模块所共同调用的逻辑或责任（例如事务处理、日志管理、权限控制等）封装起来，便于减少系统的重复代码，降低模块间的耦合度，并有利于未来的可拓展性和可维护性。

### AOP底层原理

Spring AOP使用动态代理方式实现（运行时织入）  
1. 有接口情况，使用JDK Proxy动态代理方式实现
2. 没有接口情况，使用Cglib动态实现


### AOP操作相关术语

1. Joinpoint 连接点：被拦截到的点，Spring中这些点指的是方法（可以被增强的方法）
2. Pointcut 切入点：指我们对哪些Joinpoint进行拦截的定义（实际增强的方法）
3. Advice 通知（增强）：指拦截到Joinpoint之后要做的事情（增强的逻辑）
   1. 前置通知：在方法之前执行
   2. 后置通知：在方法之后执行
   3. 异常通知：方法出现异常时执行
   4. 最终通知：在后置之后执行
   5. 环绕通知：方法之前和之后执行  
4. Aspect 切面：是切入点和通知的结合（把增强应用到切入点的过程）
5. Introduction 引介：一种特殊的通知，在不修改类代码的前提下，引介可以在运行期为类动态的添加一些方法或字段
6. Target 目标对象：代理的目标对象（要增强的类）
7. Weaving 织入：是把增强应用到目标的过程，把Advice应用到Target的过程
8. Proxy 代理：一个类被AOP增强后，就产生一个结果代理类

---

## Spring的AOP操作

1. 在Spring里面进行AOP操作，使用AspectJ实现
   1. AspectJ不是Spring一部分，和Spring一起进行AOP操作
   2. Spring2.0以后新增对AspectJ切点表达式支持
2. 使用AspectJ实现AOP有两种方式
   1. 基于AspectJ的xml配置文件方式
   2. 基于AspectJ的注解方式

### 基于Aspect的xml配置文件方式

1. 导入相关jar包（Maven）spring-aop、aspectjweaving
2. 创建核心配置文件并导入AOP约束
3. 使用表达式配置切入点   
    execution（<访问修饰符>?<方法名>(<参数>)<异常>）
   1. execution(* edu.ustc.SpringStart.service.MyPersonService.beforeCall(..))
   2. execution(* edu.ustc.SpringStart.service.MyPersonService.*(..))
   3. execution(* *.*(..))
   4. execution(* save*(..))
4. 具体配置
   ```xml
    <bean id="myPersonService" class="edu.ustc.SpringStart.service.MyPersonService"/>
    <bean id="personService" class="edu.ustc.SpringStart.service.PersonService"/>

    <aop:config>
        <aop:pointcut id="pointcut1" expression="execution(* edu.ustc.SpringStart.service.PersonService.call(..))"/>
        <aop:aspect ref="myPersonService">
            <aop:before method="beforeCall" pointcut-ref="pointcut1"/>
            <aop:around method="aroundCall" pointcut-ref="pointcut1"/>
        </aop:aspect>
    </aop:config>
   ```
   将MyPersonService对象的beforeCall（Advice）方法注入到PersonService（Target）的call（Pointcut）方法中

### 基于AspectJ的注解方式

1. 创建对象
2. 在Spring核心配置文件开启aop扫描
    ```xml
    <aop:aspectj-autoproxy/>
    ```
3. 


---

## log4j介绍

1. 通过log4j可以看到程序运行中更详细的信息
2. 使用
   1. 导入jar包（Maven）
   2. 复制log4j的配置文件到src下log4j.properties
3. 设置日志级别INFO\DEBUG
   ```
   log4j.rootLogger=DEBUG,A,R
   ```

---

## Spring整合web项目

每次访问controller的时候，都会加载spring配置文件

解决方案：
在服务器启动的时候，创建对象加载配置文件  
底层使用监听器、ServletContext对象

spring不需要自己写代码实现，已封装监听器，只需要配置就行
```xml
<listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:spring/applicationContext.xml</param-value>
</context-param>
```
---

## Spring AOP

需求:大量用户反映系统响应明显变慢,请核心组成员查明哪些功能模块慢,找出原因并优化.

# 面试

1. Spring FactoryBean和BeanFactory 区别  
BeanFactory是个bean 工厂，是一个工厂类(接口)， 它负责生产和管理bean的一个工厂
是ioc 容器最底层的接口，是个ioc容器，是spring用来管理和装配普通bean的ioc容器（这些bean成为普通bean）。
FactoryBean是个bean，在IOC容器的基础上给Bean的实现加上了一个简单工厂模式和装饰模式，是一个可以生产对象和装饰对象的工厂bean，由spring管理后，
生产的对象是由getObject()方法决定的（从容器中获取到的对象不是“FactoryBeanTest”对象）。

2. IOC  
IOC是为了解决强依赖问题,就是把原本需要new出来的对象,交给Spring框架来管理,这样就实现了解耦合的作用.
IOC容器是Spring用来实现IOC的载体，IOC容器实际上就是一个Map(key, value)，Map中存放的是各种对象。
这样可以很大程度上简化应用的开发，把应用从复杂的依赖关系中解放出来。IOC容器就像是一个工厂，当需要创建一个对象，只需要配置好配置文件/注解即可，不用考虑对象是如何被创建出来的，大大增加了项目的可维护性且降低了开发难度。
 
3. AOP  
OOP是一个自上而下纵向继承的编程思想,而AOP是一个横切的编程思想,采用横向抽取机制.
扩展功能不修改源代码实现
AOP(Aspect-Oriented Programming:面向切面编程)能够将那些与业务无关，却为业务模块所共同调用的逻辑或责任（例如事务处理、日志管理、权限控制等）封装起来，便于减少系统的重复代码，降低模块间的耦合度，并有利于未来的可拓展性和可维护性。

4. Spring AOP使用动态代理方式实现（运行时织入）  
   1. 有接口情况，使用JDK Proxy动态代理方式实现
   2. 没有接口情况，使用Cglib动态实现
 
5. Spring AOP / AspectJ AOP 的区别？  
Spring AOP属于运行时增强，而AspectJ是编译时增强。
Spring AOP基于代理（Proxying），而AspectJ基于字节码操作（Bytecode Manipulation）。
AspectJ相比于Spring AOP功能更加强大，但是Spring AOP相对来说更简单。如果切面比较少，那么两者性能差异不大。但是，当切面太多的话，最好选择AspectJ，它比SpringAOP快很多。

6. Spring 事务传播机制
   |           名称            |                                               解释                                               |
   | :-----------------------: | :----------------------------------------------------------------------------------------------: |
   |   PROPAGATION_REQUIRED    |  支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择，也是Spring默认的事务的传播。  |
   |   PROPAGATION_SUPPORTS    |                       支持当前事务，如果当前没有事务，就以非事务方式执行。                       |
   |   PROPAGATION_MANDATORY   |                           支持当前事务，如果当前没有事务，就抛出异常。                           |
   | PROPAGATION_REQUIRES_NEW  |                           新建事务，如果当前存在事务，把当前事务挂起。                           |
   | PROPAGATION_NOT_SUPPORTED |                    以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。                    |
   |     PROPAGATION_NEVER     |                         以非事务方式执行，如果当前存在事务，则抛出异常。                         |
   |    PROPAGATION_NESTED     | 如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则进行与PROPAGATION_REQUIRED类似的操作。 |
1. Maven生命周期
Maven 有三个标准的生命周期：

clean：项目清理的处理
default(或 build)：项目部署的处理
site：项目站点文档创建的处理

验证 validate	验证项目是否正确且所有必须信息是可用的
编译 compile	源代码编译在此阶段完成
测试 Test	    使用适当的单元测试框架（例如JUnit）运行测试。
包装 package	创建JAR/WAR包如在 pom.xml 中定义提及的包
检查 verify		对集成测试的结果进行检查，以保证质量达标
安装 install	安装打包的项目到本地仓库，以供其他项目使用
部署 deploy 	拷贝最终的工程包到远程仓库中，以共享给其他开发人员和工程
