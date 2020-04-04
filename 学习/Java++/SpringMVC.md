1. Spring MVC 处理流程
   1. Spring MVC框架围绕DispatcherServlet这个核心展开,它负责截获请求并将其分派给相应的处理器处理
   2. SpringMVC通过一个Servlet接收所有的请求，并将具体工作委托给其他组件进行处理
   3. 整个过程始于客户端发出一个HTTP请求，Web应用服务器接收到这个请求，如果匹配*DispatcherServlet*的请求映射路径（在web.xml中指定），Web容器将该请求转交给DispatcherServlet处理。
   4. 当DispatcherServlet根据*HandlerMapping*得到对应当前请求的Handler后，通过HandlerAdapter对Handler进行封装，再以统一的适配器接口调用Handler。 *HandlerAdapter*是Spring MVC的框架级接口，顾名思义，HandlerAdapter是一个适配器，它用统一的接口对各种Handler方法进行调用。
   5. 处理器完成业务逻辑的处理后将运回一个*ModelAndView*给DispatcherServlet，ModelAndView包含了视图逻辑名和模型数据信息。
   6. ModelAndView中包含的是“逻辑视图名”而非真正的视图对象，DispatcherServlet借由*ViewResolver*完成逻辑视图名到真实视图对象的解析工作。
   7. 当得到真实的视图对象View后，DispatcherServlet就使用这个View对象对ModelAndView中的模型数据进行视图渲染。
   8. 最终客户端得到的响应消息

2. springmvc流程 ：

URL
--------前端控制器DispatcherServlet
---------HandlerMapping处理器映射器
-------调用HandlerAdapter处理器适配器 --------具体的处理器(Controller)得到视图
-------将视图传给视图解析器
响应用户

1. springmvc和struts2的区别：

struts需要配置，不支持注解开发

struts（基于类）的是通过filer进行拦截，而springmvc（基于方法）是用servlet拦截的，

springmvc与ajax的集成；spring MVC加 @ResponseBody就会换成Json数据，而struts2需要手动转换为json

设计思想:struts2 更符合OOP编程思想，spring MVC 则是再servlet上扩展功能。