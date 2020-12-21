# custom-spring-boot-starter

## 概述

本项目旨在记录Web应用程序请求日志信息和操作信息。

## 安装依赖

1. 添加`custom-spring-boot-stater`依赖`pom.xml`文件
    ```java
    <dependency>
      <groupId>cn.com.coding4fun</groupId>
      <artifactId>custom-spring-boot-logging</artifactId>
      <version>0.0.1-SNAPSHOT</version>
    </dependency>
    ```
    
2. 修改`spring-boot`版本方法，默认使用`2.4.0`（可选）

   ```java
   <properties>
     <spring-boot.version>2.3.1.RELEASE</spring-boot.version>
   </properties>
   ```
   
3. 在`application.yml`配置日志输出情况

    ```
    coding4fun:
      logging:
        enable: true
        name: LEARN
        format: false
    ```

## 使用方法

在Controller类上面添加`@LoggingModule`来说明日志模块名称

在Controller类中的方法上面添加`@LoggingOperation`来说明日志模块

> 注意
>
> 默认是使用`SLF4J`通用日志接口打印日志，若是要替换打印日志的方法需要继承`AbstractLoggingManager`重写`finish()`和`afterThrow()`方法即可，这个两个方法中返回本次请求信息、操作信息、系统名称和方法返回值或异常信息。并将这个重写的类注册为spring bean即可替换默认行为。
>
> 默认情况下请求信息记录的操作人用户名为`anonymous`。若需要通过用户登录信息获取真实用户名，请重写`getUsername()`方法即可。



