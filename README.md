# java SPI 机制
### SPI： 
* Service Provider Interface
* Java 提供的一套用来被第三方实现或者扩展的 Api，它可以用来启动框架扩展和替换组件

### 架构
* 接口实现+策略模式+配置文件实现动态加载

### 约定
1. 规定在 classPath 的 META-INF/services/ 下，创建该接口的全名称文件
2. 在该文件中，写入该接口实现类全称（路径+文件名），多个实现类的话，分行写。
3. 用的时候，使用 java.util.ServiceLoader 的 load(Interface.class)，获取到实现类，就可以使用了。

### 注意
* 接口实现类必须有一个不带参数的构造方法

### 初始化过程
#### 调用链
* ServiceLoader.load(Interface.class)-->new ServiceLoader<>(service, loader)-->(初始化 ClassLoader , AccessControlContext，providers 清空数据，LazyIterator 包装 service,loader )-->LazyIterator.hasNextService（）-->LazyIterator.nextService()-->Class.forName(cn, false, loader)-->c.newInstance()-->providers.put(cn, p)--> 返回实现类的实例
* LazyIterator，实现懒加载的迭代器，只有遍历的时候，调用了 hasNextService() 才会加载，调用 nextService() 才会实例化并放入到 provider 中缓存，然后返回实例

### 优缺点

#### 优点
* 解耦

#### 缺点
* 存在多个实现类的话，无法根据某个参数或者标志位获取实例，只能通过遍历获取，没有实现所谓的懒加载

> 源码：https://github.com/jingboli/javaSPI    
>
> 参考：https://www.jianshu.com/p/46b42f7f593c    
> 参考：https://juejin.im/post/5af952fdf265da0b9e652de3