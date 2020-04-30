1. springmvc流程 ：

URL
--------前端控制器DispatcherServlet
---------HandlerMapping处理器映射器
-------调用HandlerAdapter处理器适配器 --------具体的处理器(Controller)得到视图
-------将视图传给视图解析器
响应用户

2. springmvc和struts2的区别：

struts需要配置，不支持注解开发

struts（基于类）的是通过filer进行拦截，而springmvc（基于方法）是用servlet拦截的，

springmvc与ajax的集成；spring MVC加 @ResponseBody就会换成Json数据，而struts2需要手动转换为json

设计思想:struts2 更符合OOP编程思想，spring MVC 则是再servlet上扩展功能。

3. Spring MVC 返回值类型
   1. ModelAndView 页面+返回值
   2. Map<key,value> 可以通过${key}获得值,相当与request.setAttribute方法
   3. String 页面视图名称,但@ResponseBody会直接返回字符串
   4. void,map,model 响应视图页面为访问地址