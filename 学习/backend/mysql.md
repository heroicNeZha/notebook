# MySQL

[牛客sql刷题](https://www.nowcoder.com/ta/sql?page=0)

## 技巧

1. 在大表中distinct效率要比group by低.
2. left join 比 inner join 多返回一部分数据,所以慢. 
3. 判断存在效率排行
   1. exists <= in <= join not
   2. exists <= not in <= left join
   3. not exists <= not in <= left join
   4. in是把外表和内表作hash连接,而exists是对外表做loop循环,每次循环再对内表查询.所以内表小时用IN,外表小用exsits.

## 安装mysql

1. `sudo apt install mysql-server mysql-client`
2. 安装时可能不提示你输入密码，此时root用户采用的是auth认证方式，必须通过sudo登录。如果想使用密码（mysql_native_password方式）需要输入如下语句：

    ```sql
    ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '密码';
    FLUSH PRIVILEGES;
    ```

3. 建议使用workbrench可视化。

## Innodb详解

### MySQL体系结构和存储引擎

1. MySql是单进程多线程的,跟Oracle的多进程架构不同(Oracle在windows版本也是但进程多线程架构的).MySQL数据库实例在系统上的表现就是一个进程.

### InnoDB和MyISAM的区别

|                |     InnoDB     |   MyISAM   |
| :------------- | :------------: | :--------: |
| 事务           |       T        |            |
| 锁             |    行锁设计    |   不支持   |
| 全文索引       |  1.2版本以后   |     T      |
| 外键           |       T        |            |
| B-Tree索引     |       T        |     T      |
| Hash索引       |       T        |            |
| 聚集索引       |       T        |            |
| 缓存           | 数据索引都缓存 | 只缓存索引 |
| MVCC快照读     |       T        |            |
| 存储空间与内存 |       高       |     低     |