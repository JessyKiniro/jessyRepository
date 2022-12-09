# Spring IOC

## Spring

Spring 是轻量级的开源的 JavaEE 框架，以 IOC 和 AOP 为内核，提供了展现层 Spring MVC 和业务层事务管理等众多的企业级应用技术，还能整合开源世界众多著名的第三方框架和类库，已经成为使用最多的 Java EE 企业应用开源框架。

Spring 有两个核心部分：IOC 和 AOP

> IOC：控制反转：把创建对象交给 Spring 进行管理
> AOP:面向切面，不修改源代码进行功能增强

![IoC描述1](notes/IOC.png)

## IOC 概念

IOC 是 Inversion of Control 的简写，译为“控制反转”,而是一种设计思想，是一个重要的面向对象编程法则，能够指导我们设计出松耦合、更优良的程序。

Spring 通过 IoC 容器来管理所有 Java 对象的实例化和初始化，控制对象与对象之间的依赖关系。$\color{red} 我们将由 IoC 容器管理的 Java 对象称为 Spring Bean，它与使用关键字 new 创建的 Java 对象没有任何区别。$

- 传统方式：比如类 A 依赖于类 B，往往会在类 A 中 new 一个 B 的对象，并手动 set 一些必要的属性
- IOC 思想下的开发方式：我们不用自己去 new 对象，而是由 IOC 容器（Spring 框架）去帮助我们实例化对象并且管理它，我们需要使用哪个对象，去问 IOC 容器要即可

> 我们丧失了一个权利（创建、管理对象的权利），得到了一个福利（不用考虑对象的创建、管理等一系列事情）

- 控制：指的是对象创建（实例化、管理）的权利
- 反转：控制权交给外部环境了（springIOC 容器）

## DI 概念

依赖注入(Denpendency Injection)，在面向对象中，对象和对象之间存在一种叫做“依赖”的关系，简单来说，依赖关系就是在一个对象中需要用到另一个对象，即对象中存在一个属性，该属性是另外一个类的对象

```JAVA
public class B{
    String bid;
    A a;
}

```

B 中存在一个 A 类型的对象属性 a。此时我们就可以说 B 对象依赖于对象 a，而依赖注入就是基于这种“依赖关系”而产生的。

控制反转核心思想就是由 Spring 负责对象的创建。在对象创建过程中，Spring 会自动根据依赖关系，将它依赖的对象注入到当前对象中，这就是所谓的“依赖注入”。

## Spring IoC 的原理

###

Spring 启动时读取应用程序提供的 Bean 配置信息，并在 Spring 容器中生成一份相应的 Bean 配置注册表，然后根据这张注册表实例化 Bean，装配好 Bean 之间的依赖关系，为上层应用提供准备就绪的运行环境.

![IoC描述1](notes/springioc.png)

- Bean 缓存池：ConcurrentHashMap
- 实例化 Bean：通过反射调用构造函数创建对象

```JAVA

Class<?> clazz=Class.forName(className);
Object o=clazz.newInstance();

```

### Spring 提供 IoC 容器两种方式

- BeanFactory:IoC 容器基本实现，是 Spring 内部接口，不提供开发人员进行使用
- ApplicationContext：BeanFactory 接口的子接口，提供更多更强大的功能，一般由开发人员进行使用。

ApplicationContext 加载配置文件就只会把配置文件中单例且非懒加载的对象进行创建。

常见实现类:

- ClassPathXmlApplicationContext

> 默认从类路径加载配置文件

- FileSystemXmlApplicationContext

> 默认从文件系统中装配

- $\color{red}AnnotationConfigApplicationContext$

> 直接装载带@Configuration 注解的配置类

$\color{red} ApplicationContext 有个getBean方法，可以根据Bean名字和类型来从容器中获取bean$

### Bean 的创建和使用

#### 创建:

##### 方式 1

类上加注解 写一个配置类开启注解扫描

a. 常用注解：

- $\color{red} @Controller 访问层 --->处理请求$
- $\color{red} @Service 业务逻辑层 $
- $\color{red} @Repository 持久层--->访问数据层 $
- $\color{red} @Component --->除上述三种注解的其它情况 $

b. 新建一个配置类

```JAVA
@Configuration
@ComponentScan(basePackages={"xxxx"})
public class SpringConfig{

}
```

> @Configuration 作为配置类代替 xml 进行配置
> @ComponentScan(basePackages = {"xxxx","xxxxx"}) basePackages 扫描包的路径

被扫描的类如果用于上面四种注解任意一个就会创建一个对象，加到 IOC 容器中，默认创建的 bean 名称是类首字母小写

```JAVA

//不指定bean名称
@Component

public class University {

          to do sthing...

}
//指定bean名称

@Component("university1")

public class University {

          to do sthing...

}
```

##### 方式 2

在配置类中使用@Bean(bean 名称)进行注册，优点是可以创建多个相同类型的但不同名字 bean 对象

```JAVA
@Configuration
@ComponentScan(basePackages={"xxxxx"})
public class SpringConfig{
    @Bean("accountService")
    AccountService create(){
        return new AccountServiceImpl();
    }
}
```

#### 启动容器、获取 bean 对象&属性注入

##### 启动容器

```JAVA
AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(SpringConfig.class); //加载容器
AccountService accountService=context.getBean("accountService",AccountService.class);//获取bean
```

##### 属性注入

一般来说，这里的属性指的是引用类型的属性（field），注入的来源是 IOC 容器

1. @AutoWired:根据属性类型进行自动装配属性;

   - 默认情况下必须要求依赖对象必须存在，如果要允许 null 值，可以设置它的 required 属性为 false；
   - 匹配到同类型的对象不唯一时，会匹配和该属性名称相同的 Bean，如果不唯一，则报错
   - @Autowired 可以和@Qualifier(“ 注入 bean 的名字”)一起用，分别限定类型和名称

2. @Resource：根据名字来自动装配属性 （变量名 ）@Resource（name=""）

   - 一般不指定名字，默认按属性名注入，也可以指定名字，那就按名字注入
   - 一般不指定属性名字，默认按属性名注入，没找到对象，则回退为按类型进行匹配，如果匹配唯一对象则自动装配，否则报错
   - 如果指定了 name，则从上下文中查找名称匹配的 bean 进行装配，找不到则报错

### Bean 的作用域

@Scope("作用域")

- 单例 singleton：在整个应用中只创建一次，默认作用域。只有单例对象的创建和销毁全程都是 spring 进行管理的
- 原型 prototype；每次注入或者通过 Spring 应用上下文获取时，都会创建一个 Bean 实例

### Bean 生命周期

生命周期：从创建到对象销毁的过程

- 实例化 Instantiation：构造器创建 bean 对象
- 属性赋值 Populate：属性 set 相应的值
- 初始化 Initialization：调用实现 InitializingBean 接口的方法等
- 销毁 Destruction

让 Bean 实现一些特定的生命周期接口，这样可以在 Bean 的生命周期的各个阶段进行一些处理

有两类接口

- 容器级别的生命周期(整个容器，所有 bean)
- Bean 级别的生命周期接口

1. 容器级别的生命周期接口

![IoC描述1](notes/interface.png)

> InstantiationAwareBeanPostProcessor 作用于实例化阶段的前后
> BeanPostProcessor 作用于初始化阶段的前后

- InstantiationAwareBeanPostProcessor 实现

```java
@Component
public class MyInstantAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {

        System.out.println(beanName+"实例化之前");
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {

        System.out.println(beanName+"实例化之后");
        return false;
    }
}

```

- BeanPostProcessor 实现

```JAVA
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        System.out.println(beanName+"初始化前");

        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        System.out.println(beanName+"初始化后");
        return null;
    }
}
```

2. Bean 级别的生命周期接口

- BeanNameAware
- BeanClassLoaderAware
- BeanFactoryAware
- ApplicationContextAware

##

- IntializingBean
- DisposableBean

Aware 类型的接口的作用就是让我们能够拿到 Spring 容器中的一些资源。（属于属性赋值阶段，在初始化阶段之前运行）

InitializingBean 有个 afterPropertiesSet()方法，我们可以在此方法自定义一些逻辑。
DisposableBean 类似于 InitializingBean，它有个 destroy()方法 。spring 容器在关闭(context.close())的时候会去调用各个 bean 的 destroy()方法。

## Bean 创建时调用的构造方法

1. 优先调用无参构造方法，get 和 set 方法可以省略
2. 若没有无参构造方法，则会调用有参构造方法，方法参数必须在容器中存在或显示指定；此时若有多个有参够造方法，则会报错

```java
@Component
public class BeanBean {

    public BeanBean(@Value("121") String name ){

    }
//    public BeanBean(@Value("1212") String name,@Value("122") String name2){
//
//    }
}
```
