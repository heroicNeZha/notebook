### 安装mysql
1. `sudo apt install mysql-server mysql-client`
2. 安装时可能不提示你输入密码，此时root用户采用的是auth认证方式，必须通过sudo登录。如果想使用密码（mysql_native_password方式）需要输入如下语句：
    ```sql
    ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '密码';
    FLUSH PRIVILEGES;
    ```
3. 建议使用workbrench可视化。