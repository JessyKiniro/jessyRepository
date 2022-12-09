# SpringAOP

## AOP

在不修改源代码的基础上，将增强的逻辑以一种无侵入的方式插入到特定业务方法的执行前、抛异常或执行后等时刻，对源代码功能进行增强，这种将原业务方法分成不同切面的编程方式又叫面向切面编程

## AOP 原理

AOP 底层使用动态代理，我们感觉是调用了原先对象的方法，实际上是直接调用了动态对象增强后的方法

1. 被增强类有实现接口，那么使用 JDK 动态代理
   ![Cookie描述](notes/jdk动态代理.png)

2. 被增强类没有实现接口，那么使用 CGLIB 动态代理
   ![Cookie描述](notes/cglib.png)

## AOP 术语

AOP 面向切面编程，指面向在方法执行的各个阶段。

- Joinpoint(连接点)：方法开始时、正常运行完毕时、方法异常时等特殊时间点
- Pointcut(切入点)：从所有连接点中选出的要加强的连接点
- Advice(增强逻辑)：指的时候要添加的代码逻辑

###

- target(目标对象)：要被增强的对象
- proxy(代理对象)：用来代理目标对象的增强对象
- Aspect(切面)：切入点+增强逻辑

有以下几种分类：

1. 前置通知：方法执行前
2. 后置通知：方法执行后，方法中途发生异常，不会执行该通知
3. 异常通知：方法抛出异常
4. 最终通知：方法执行后，是一种特殊的后置通知，方法中途发生异常也会执行
5. 环绕通知：在方法的前后增强通知

## Spring 中 AOP 的实现方式

在 SpringAOP 实现方式中，支持两类实现方式

- 第一类：使用 XML 配置(已经没有人用这种方式)

- 第二类：使用纯注解配置

1. 创建被增强的类

```JAVA

@Component
public class User {
    public Integer add(int i) {
        System.out.println("add...");
        return 10;

    }
}

```

2. 创建切面类，其实就是一个配置 AOP 的类

```JAVA

@Component
@Aspect //生成代理对象
public class UserProxy {

    //前置通知
    @Before(value = "execution(* cn.jyy.User.add(..))")
    public void before(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();

        for (Object o:args){
            System.out.println(o);
        }

        System.out.println("前置通知");

    }

    //后置通知
    @AfterReturning(value = "execution(* cn.jyy.User.add(..))")
    public void afterReturning(){
        System.out.println("后置通知");
    }

    //最终通知
    @After(value="execution(* cn.jyy.User.add(..))")
    public void after(){
        System.out.println("最终通知");
    }

    //异常通知
    @AfterThrowing(value = "execution(* cn.jyy.User.add(..))")
    public void afterThrow(){
        System.out.println("异常通知");

    }

    @Around(value="execution(* cn.jyy.User.add(..))")
    public Integer around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("环绕之前...");

        //获取 目标类
        Object target = proceedingJoinPoint.getTarget();
        String s = target.toString();

        System.out.println("target: "+s);

        //增强方法的参数
        Object[] args = proceedingJoinPoint.getArgs();

        for (Object o:args){
            System.out.println(o);
        }


        //MethodSignature 封装方法的信息
        MethodSignature ms = (MethodSignature) proceedingJoinPoint.getSignature();

        //方法名
        Method method = ms.getMethod();
        String name = method.getName();

        System.out.println("method: "+name);

        //获取方法上的注解
        Transactional annotation = method.getAnnotation(Transactional.class);
        System.out.println("annotation"+annotation);


        //被增强的方法执行
       int i= (int) proceedingJoinPoint.proceed();
       System.out.println("i "+i);



        System.out.println("环绕之后...");
        return 20;
    }
}
```

切入点表达式：用来匹配哪些方法是切入点 \*表示任意数量的字符的意思

- 匹配全部方法

> execution(\*..\*.\*(..))

- User 类的 add 方法的执行

> execution(\* cn.jyy.User.add(..)) 注意空格间隙！

- 在 cn.jyy 包里的任意方法的执行

> execution(\* cn.jyy.\*.\*(..))

(..):表示方法的参数可以是任意类型且个数任意

3. 配置类(SpringConfig...)开启 AOP 代理

```JAVA
@Configuration
@ComponentScan(basePackages = {"cn.jyy"})
@EnableAspectJAutoProxy(proxyTargetClass=true)  //开启AOP支持，重要
public class SpringConfig {

}
```

4. 创建测试类

```JAVA

public class AOPTest {

    @Test
    public void test1(){
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(SpringConfig.class);

        User user=context.getBean("user",User.class);

        user.add(5);

    }
}

```

细节：当有多个切面类，默认按类名排序，也可以通过@order(数字)这个注解；奥定义执行顺序，括号中的数字越小越先执行

## 环绕通知中的 ProceedingJoinPoint

ProceedingJoinPoint 是 JointPoint 的子类，封装了切面的信息

proceedingJoinPoint 对象的主要方法

1. 获取切入点所在目标对象

```java
 Object targetObj=proceedingJoinPoint.getTarget();
```

2. 获取方法和方法名

```java
MethodSignature ms=(MethodSignature)proceddingJoinPoint.getgetSignature();
Method method = ms.getMethod();
String name = method.getName();
```

3. 获取方法上的参数

```JAVA
Object[] args = proceedingJoinPoint.getArgs();

```

4. 获取方法上的注解

```JAVA
Transactional ts=  method.getAnnotation(Transactional.class);
```

5. 执行目标对象方法并获取返回值

```JAVA
返回值类型  变量=（返回值类型）joinPoint.proceed();
```

关于环绕通知的两个细节

细节 1：我们在环绕通知里可以拦截目标对象的方法
细节 2：也可以环绕通知里自定义增强后的返回值

## Spring 中 AOP 的代理选择

1. 当被代理对象实现任何接口时，Spring 会选择 JDK 动态代理
2. 当被代理对象没有任何实现接口时，Spring 会选择 CGLIB

可以通过下列方式强制选择 CGLIB

```java
@EnableAspectJAutoProxy(proxyTargetClass = true)
```
