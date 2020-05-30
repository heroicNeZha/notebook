# 分布式

## 简介

Seata 是阿里开源的分布式事务解决方案, 有高性能和微服务架构易用性特点.

Seata 主要有三个基本组件:
1. 事务协调者(TC): 维护全局和分支事务的状态.
2. 事务管理者(TM): 定义全局事务的范围
3. 资源管理者(RM)

## 生命周期

一个典型的 Seata 管理分布式事务的生命周期如下:
1. TM让TC开启一个全局事务. 

## 通信技术

主要分为以下几种:
1. **RPC**: 一般是C/S方式, 同步, 跨语言跨平台, 面向过程.
2. **CORBA**(Common Object Request Broker Architecture): CORBA从概念上扩展了RPC. 面向对象的, 企业级的(面向对象中间件还有DCOM).
3. **RMI**(Remote Method Invocation): 面向对象方式的 Java RPC.
4. **Web Service**: 基于Web, C/S或B/S, 跨系统跨平台跨网络. 多为同步调用、实时性要求较高.
5. **MOM**(Message Oriented Middleware): 面向消息中间件, 主要使用与消息通道、消息总线、消息路由和发布/订阅场景. 目前主流的有**JMS**(Java Message Service)、**AMQP**(Advanced Message Queuing Protocol)和**STOMP**(Streaming Text Oriented Messaging Protocol)等. 其中JMS是Java平台上的面向接口的消息规范, 是一套API标准, 并有考虑异构系统. AMQP是一个面向协议的, 跟语言平台无关的消息传递应用层协议规范. STOMP是流文本定向消息协议, 是一种为MOM设计的简单文本协议, 在使用websocket通信时可以使用该协议来中继中间件功能. 

MOM消息中间件主要解决应用耦合、异步消息、流量削峰等问题. 实现高性能、高可用、可伸缩和最终一致性架构, 是大型分布式系统不可缺少的中间件. 目前在生产环境中, 使用较多的消息队列有 ActiveMQ、RabbitMQ、ZeroMQ、Kafka、RocketMQ 等, 本文介绍其中的一种 ActiveMQ 消息中间件来展开消息中间件使用过程. 