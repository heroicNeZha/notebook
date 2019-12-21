# Java In Linux

### 配置java环境

1. 下载JDK
   1. oracle官网上下载jdk
   2. 解压JDK包到文件夹，我的解压路径是~/Dev/
2. 配置环境
    1. gedit ~/.bashrc 打开文件
    2. 文件末尾添加信息
        ```bash
        export JAVA_HOME=/home/lizw/Dev/jdk-13
        export JRE_HOME=${JAVA_HOME}/jre
        export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib:${CLASSPATH}
        export PATH=${JAVA_HOME}/bin:${JRE_HOME}/bin:${PATH}
        ```
    3. 加载配置
        ```bash
        source ~/.bashrc
        ```
3. 测试
    ```
    java -vision
    ```