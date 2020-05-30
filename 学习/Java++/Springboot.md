# Spring Boot

习惯优于配置

## 第一个Spring Boot程序

- jdk 8
- Spring Boot 2.2.6
- maven 3.6.1

可以通过 [Spring Initializr](https://start.Spring.io/) 快速创建应用, 并选择模块

- application.properties: Spring Boot 核心配置文件
- banner.txt: 彩蛋

## 原理

### pom.xml

- Spring-Boot-dependencies: 核心依赖在父工程中
- 我们在引入SpringBoot依赖的时候, 不需要指定版本, 因为有版本仓库

### 启动器
```xml
<dependency>
    <groupId>org.Springframework.Boot</groupId>
    <artifactId>Spring-Boot-starter-web</artifactId>
</dependency>
```
- Spring-Boot-starter-*是SpringBoot的启动场景 
- 比如 -web 就会自动导入web环境相关所有依赖
- 使用什么功能只需要找到[对应的启动器](https://docs.Spring.io/Spring-Boot/docs/2.2.0.RELEASE/reference/html/using-Spring-Boot.html#using-Boot-dependency-management)

### 主程序

```java
@SpringBootApplication //标注SpringBoot应用
  @SpringBootConfiguration //SpringBoot的配置
    @Configuration: //Spring配置类
      @Compoent: //Spring组件
  @EnableAutoConfiguration: //自动配置
    @AutoConfigurationPackage: //自动配置包
      @Import(AutoConfigurationPackages.Registrar.class): //自动配置`包注册`(元数据)
    @Import(AutoConfigurationImportSelector.class): //导入自动配置选择器
      getAutoConfigurationEntry() //获取自动配置实体
      getCandidateConfigurations() //获取候选配置
        loadFactoryNames() //获取配置名字
          loadSpringFactories() //从META-INF/Spring.factories获取自动配置
          //如果 @ConditionalOnXXX() 成立才导入相应配置
```

Spring Boot 所有自动配置都在启动类中被扫描并加载. `spring.factories`所有自动配置类都在里面, 但不一定生效, 需要判断条件是否成立. 只要导入对应的starter, 有了对应的启动器, 自动装配就会生效, 然后配置成功. 

- 从`META-INF/spring.factories` 获取指定的值
- 将这些自动配置的类导入容器, 自动配置就会生效, 进行自动配置
- 以前需要配置的东西, 现在spring boot帮我们做
- 整合java EE, 解决方案和自动配置的东西都在 `spring-boot-autoconfigure.jar` 中
- 它会把所有需要的组件, 以类名方式返回, 这些组件就会被添加到容器
- 容器中存在大量xxxAutoConfigration的文件(@Bean), 这些类中导入了这个场景所需要的组件, 并自动配置, @Configuration
- 自动配置类免去了手动配置的工作
- javaConfig @Configuration bean

## Spring Boot 配置

application.properties是key=value形式

application.yml是`key: value`形式, 是一种以数据为中心的标记语言. value的类型可以是字面量, 也可以是对象Map和数组. 

支持**松散绑定**

### JSR303 校验

@Validated

```
判空
@Null	被注释的元素必须为 null
@NotNull	被注释的元素必须不为 null
布尔
@AssertTrue	被注释的元素必须为 true
@AssertFalse	被注释的元素必须为 false
长度
@Min(value)	被注释的元素必须是一个数字，其值必须大于等于指定的最小值
@Max(value)	被注释的元素必须是一个数字，其值必须小于等于指定的最大值
@DecimalMin(value)	被注释的元素必须是一个数字，其值必须大于等于指定的最小值
@DecimalMax(value)	被注释的元素必须是一个数字，其值必须小于等于指定的最大值
@Size(max, min)	被注释的元素的大小必须在指定的范围内
@Digits (integer, fraction)	被注释的元素必须是一个数字，其值必须在可接受的范围内
日期
@Past	被注释的元素必须是一个过去的日期
@Future	被注释的元素必须是一个将来的日期
格式
@Pattern(value)	被注释的元素必须符合指定的正则表达式
@Email(message="邮箱格式错误")
```

### 多环境开发

默认配置文件位置优先级如下

1. `file:./config/`
2. `file:./`
3. `classpath:/config/`
4. `classpath:/`

自己选择配置文件

```yml
# 选择application-dev.yml
spring:
  profiles:
    active: dev

---
# 创建配置,命名为test1
spring:
  profiles: test
```

**跳过了自动配置原理再理解**

## Spring Boot Web 开发

要解决的问题:
- 导入静态资源
- 首页
- jsp与模板引擎 Thymeleaf
- 装配扩展Spring MVC
- 增删改查
- 拦截器

### 静态资源

```java
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
  if (!this.resourceProperties.isAddMappings()) {
    logger.debug("Default resource handling disabled");
    return;
  }
  Duration cachePeriod = this.resourceProperties.getCache().getPeriod();
  CacheControl cacheControl = this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl();
  //用webjars 引用静态资源
  if (!registry.hasMappingForPattern("/webjars/**")) {
    customizeResourceHandlerRegistration(registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/")
        .setCachePeriod(getSeconds(cachePeriod)).setCacheControl(cacheControl));
  }
  //通过 spring.mvc.static-path-pattern=/** 修改
  //用 /** 映射resourceProperties的资源路径
  String staticPathPattern = this.mvcProperties.getStaticPathPattern();
  if (!registry.hasMappingForPattern(staticPathPattern)) {
    customizeResourceHandlerRegistration(registry.addResourceHandler(staticPathPattern)
        .addResourceLocations(getResourceLocations(this.resourceProperties.getStaticLocations()))
        .setCachePeriod(getSeconds(cachePeriod)).setCacheControl(cacheControl));
  }
}
```

在spring boot中,我们可以使用以下方式处理资源:
- webjars `/webjars/**`
- resources > static > public `/**`

### 首页



## Spring Boot 整合

SpringBoot 操作数据层：Spring-data jpa jdbc mongodb redis

Spring Data 是和SpringBoot 齐名的项目

说明：在SpringBoot 2.x以后，原来使用的jedis被替换为了lettuce

jedis：采用直连，多线程操作不安全，避免不安全要用jedis pool 连接池，更像BIO模式

lettuce：采用netty，实例可以在多个线程中共享，不存在线程不安全的情况，更像NIO模式。

Redis都需要序列化

### 整合测试

1. 导入依赖
2. 配置连接
3. 测试！

```java
@SpringBootTest
class RedisSpringbootDemoApplicationTests {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("mykey","李泽伟lzw");
        System.out.println(redisTemplate.opsForValue().get("mykey"));
    }

}
```

## 分布式 Dubbo 和 Zookeeper

![image](assets/dubbo-architecture-roadmap.jpg)

RPC

```
// Client端 
//    int l_times_r = Call(ServerAddr, Multiply, lvalue, rvalue)
1. 将这个调用映射为Call ID。这里假设用最简单的字符串当Call ID的方法
2. 将Call ID，lvalue和rvalue序列化。可以直接将它们的值以二进制形式打包
3. 把2中得到的数据包发送给ServerAddr，这需要使用网络传输层
4. 等待服务器返回结果
5. 如果服务器调用成功，那么就将结果反序列化，并赋给l_times_r

// Server端
1. 在本地维护一个Call ID到函数指针的映射call_id_map，可以用std::map<std::string, std::function<>>
2. 等待请求
3. 得到一个请求后，将其数据包反序列化，得到Call ID
4. 通过在call_id_map中查找，得到相应的函数指针
5. 将lvalue和rvalue反序列化后，在本地调用Multiply函数，得到结果
6. 将结果序列化后通过网络返回给Client
```

dubbo

![img](assets/architecture.png)

zookeeper：注册中心

dubbo-admin：监控管理后台-查看注册哪些服务

Dubbo：