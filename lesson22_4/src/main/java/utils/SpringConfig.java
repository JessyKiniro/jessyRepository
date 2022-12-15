package utils;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"cn.jyy"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringConfig {

}
