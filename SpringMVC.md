# SpringMVC

## SpringMVC 框架概述

在后端项目架构中，系统标准的三层架构包括：表现层、业务层、持久层

![SpringMVC框架](notes/springmvc.png)

- 表现层：也就是 web 层，负责接收客户端请求，向客户端响应结果，双向使用 http 协议

- 持久层：直连数据库，增删改查

- 业务层：负责处理负责业务逻辑

表现层的设计一般都使用 MVC 模式

MVC 全名是 Model View Controller，是模型(model)-视图(view)-控制性(controller)的缩写，是一种用于设计创建 web 应用程序表现层的模式。MVC 中各个部分各司其职

- Model(模型)：通常指的就是我们数据模式。一般情况下用于封装数据，前端传来的信息
- View(视图)：通常指的就是我们的 JSP 或者 html。作用一般就是阐述数据。通常视图是依据数据模式创建
- $\color{red}controller(控制器)$：是应用程序中处理用户交互的部分

SpringMVC 的优点

1. 封装代码，维护成本低，耦合性低
   在 MVC 模式中，三个层各施其职，所以如果一旦哪一层的需求发生了变化，就只需要更改相应的层中的代码而不会影响到其它层中的代码。
2. 有利于开发中的分工，提高开发效率
   在 MVC 模式中，由于按层把系统分开，那么就能更好的实现开发中的分工。网页设计人员可以进行开发视图层中的 JSP，对业务熟悉的开发人员可开发业务层，而其它开发人员可开发控制层。
3. 和 Spring 其他框架无缝集成，是其它 Web 框架所不具备的。
4. 功能强大的数据验证、格式化、绑定机制。

> SpringMVC 底层就是 Servlet，SpringMVC 就是对 Servlet 进行深层次的封装

## 入门案例

```JAVA
@Controller
@RequestMapping("/user")
public class HelloController {


    @RequestMapping(path="hello")
    @ResponseBody
    public String sayHello(){
        System.out.println("Hello StringMVC");
        return "success_springboot";
    }


}
```

## SpringMVC 基本原理

![容器](notes/tomcatspringboot.png)

### 核心组件

$\color{red}DipatcherServlet$:请求转发控制器(处理请求 Web 请求的大管家，有且只有一个)

SpringMVC 的核心就是 DispatcherServlet，DispatcherServlet 实质也是永和 HttpServlet。DispatcherServlet 负责将请求分发，所有的请求都经过它来统一分发。

$\color{red}Controller()$:实际处理请求的组件

$\color{red}HandlerMapping$: 处理请求路径与实际处理请求的控制器的映射关系

在容器初始化时会建立所有的 url 和 controller 的控制映射关系，保存到 Map<url,controller>中。tomcat 启动时会通知 Spring 初始化容器（加载 bean 的定义信息和初始化所有对的单例 bean），然后 springmvc 会遍历容器中的 bean，获取每一个 Controller 中的所有方法访问的 url，然后将 url 和 Controller 保存在每一个 Map 中

这样就可以根据 request 快速定位到 Controller，因为最终处理 request 是 Controller 中的方法，Map 中只保留了 url 和 Controller 中的对应关系，所以要根据 request 的 url 进一步确认 Controller 中的 method，这一步的工作原理就是拼接 Controller 的 url(Contoller 上的@RequestMapping 的值)，与 request 的 url 进行匹配，找到匹配的那个方法。

ModelAndView:处理完请求后，封装了响应结果和视图信息的对象
ViewResolver:视图解析器，根据视图名得到具体负责显示的视图组件

### 处理流程

![springmvc处理流程](notes/springmvcprocess.png)

用户向服务器发送请求，请求到达 DispatcherServlet，请求 URL 进行解析，得到请求资源标知符(URI),然后根据该 URL,调用 HandlerMapping 获得该 Handler 配置的所有相关对象
