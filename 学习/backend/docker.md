# Docker

## Docker基础

### Docker架构
1. Docker使用c/s架构,使用远程API来创建和管理Docker容器.
2. Docker 容器(Container)通过 Docker 镜像(Images)来创建.镜像->容器相当于类->对象.  
    |          概念          |                               意义                               |
    | :--------------------: | :--------------------------------------------------------------: |
    |  Docker 镜像(Images)   |                   用于创建 Docker 容器的模板。                   |
    | Docker 容器(Container) |                 容器是独立运行的一个或一组应用。                 |
    | Docker 客户端(Client)  | 通过命令行或者其他工具使用 Docker API 与 Docker 的守护进程通信。 |
    |   Docker 主机(Host)    |      一个物理或者虚拟的机器用于执行 Docker 守护进程和容器。      |
    | Docker 仓库(Registry)  |    Docker 仓库用来保存镜像，可以理解为代码控制中的代码仓库。     |
    |       Docker Hub       |                   提供了庞大的镜像集合供使用.                    |
    |     Docker Machine     |                 一个简化Docker安装的命令行工具.                  |

### Ubuntu 19.10 安装 Docker
1. `sudo apt install docker.io`
2. `sudo service docker start`
   
### docker 运行
1. `sudo docker run ubuntu:15.10 /bin/echo "helloworld"`  
   1. docker 使用的是unix socket而不是tcp端口,而unix socket是属于root用户的.
   2. 如果本地没有ubuntu的镜像库,会自动从镜像仓库Docker Hub上面下载公共镜像
2. `sudo docker run -i -t ubuntu:15.10 /bin/bash`
   1. 通过`-i -t`参数使镜像具有交互能力
   2. `-t`:在新容器内指定一个伪终端或终端
   3. `-i`:允许你对容器内的标准输入 (STDIN) 进行交互
3. `sudo docker run -d ubuntu:15.10 /bin/sh -c "while true; do echo hello world; sleep 1; done" `
   1. 通过`-d`后台运行docker容器.
   2. 输出结果为容器id,可以通过`sudo docker ps`查看正在运行的docker容器
   3. 通过`docker logs <容器id>`可以查看运行结果,`-f`标准化输出
   4. 通过`docker stop <容器id>`可以停止容器

### docker 容器的使用
1. `docker run -d -P training/webapp python app.py`
   1. 通过docker 构建一个web应用
   2. 通过`-P`将容器内部使用的网络端口映射到我们使用的主机上
   3. 也可以`-P 5000:5000`控制端口映射
   4. 可以通过`docker port`命令查看网络端口
   5. `docker inspect` 查看底层配置信息

### docker 镜像管理