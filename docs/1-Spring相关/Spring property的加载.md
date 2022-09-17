Spring boot 启动的时候通常是这样的：
```java
@SpringBootApplication
public class SpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplication.class, args);
	}
}
```
最近因为遇到了个 Spring property 加载的问题所以对这块产生了点兴趣，所以看一看属性加载这块到底是怎么做的。

## 1. SpringApplication 是做什么的
bean 可以从如下几中方式获取：
1. AnnotatedBeanDefinitionReader
2. XmlBeanDefinitionReader / GroovyBeanDefinitionReader: 从 xml 或者 groovy 脚本中获取
3. ClassPathBeanDefinitionScanner: 扫描 classpath 获取

`SpringFactoriesLoader` 用于通过 SPI 的方式从 META-INF 文件夹下加载所有 Spring 动态配置的类，然后就可用 `SpringFactoriesLoader.loadFactoryNames` 方法，通过 classLoader 和接口类来获得这个接口的所有实现类的类名了。

`SpringApplicationRunListener` 为 Spring 的应用 Listener，其默认实现为 `EventPublishingRunListener`，它就是通过 SPI 的方式加载的。用于在 Spring 加载过程中的不同时间点接受事件，并进行相应操作。
* `starting`：SpringApplication 调用 `run` 方法的时候马上就会调用这个方法；
* `environmentPrepared`：在 `ApplicationContext` 创建之前、`Environment` 准备好之后就立刻会调用；
* `contextPrepared`：在 `ApplicationContext` 创建并完成准备，但是在加载资源之前调用；
* `contextLoaded`：在 `ApplicationContext` 加载之后，但是在 refresh 之前调用；
* `started`：在 `ApplicationContext` refresh 并 start 之后，但是 `CommandLineRunners` 和 `ApplicationRunners` 开始运行之前
* `ready`：在两个 Runners 都调用完了、`ApplicationContext` refresh 好了之后调用；
* `failed`：在 `run` 方法失败后调用。


### 当前问题
`Environment` 的作用到底是啥？

是 `profiles` 和 `properties` 的抽象。在加载 properties 的时候用到的是 `ConfigurableEnvironment`

context 的各个阶段有啥区别：create、prepare、load、refresh、ready

commandlineRunners、applicationRunners 都是干啥的？