# Java高级开发

## JVM揭秘
1. 线程共享：所有线程能访问这块内存数据，随虚拟机或GC创建和销毁
2. 线程独占：每个线程都会有他的独立空间，随线程生命周期而创建和销毁

### 方法区

作用：存储加载的类信息、常量、静态变量、JIT编译的代码等数据  
Hopspot VM以类对象方式存储在方法区

GC：方法区存在垃圾回收、但回收效率低  
回收主要针对常量池的回收和类型的卸载  
当方法区无法满足内存要求时，报OOM

![jvm](/学习/images/java_1.jpg)

### java 代码运行过程

```java
public class Demo1{
    final int NUMBER = 1;
    private static Student stu = new Student();
    
    public void main(String args[]){
        int x = 500;
        int y = 100;
        int z = x/y;

        String envName = "JAVA_HOME";
        stu.age = z;
        String path = System.getenv(envName);
    }
}
```
上面代码的运行过程如下所示.
![jvm](/学习/images/java_2.jpg)


