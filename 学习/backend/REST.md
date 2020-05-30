# REST

## RESTful 在spring中的应用

### ajax PUT和DELETE

有两种方式实现PUT:  
1. 采用AJAX POST+_method:put+filter的方式
   1. 前端ajax代码如下
    ```js
        $.ajax({
            type: "post",
            url: "user/employee/",
            contentType: "application/json",
            data: {_method: 'PUT', user: JSON.stringify(row)},
            dataType: 'JSON',
            success: function (data, status) {
                if (status == "success") {
                    alert('提交数据成功');
                }
            }
        });
    ```
   2. 后端Spring Controller代码如下
    ```java
        @RequestMapping(path = "/employee/", produces = "text/json", method = RequestMethod.PUT)
        public int editEmployee(@RequestBody User user) {
            System.out.println(user.toString());
            return 4;
        }
    ```
   3. 配置filter如下
    ```xml
        <filter>
            <filter-name>HiddenHttpMethodFilter</filter-name>
            <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
        </filter>
        <filter-mapping>
            <filter-name>HiddenHttpMethodFilter</filter-name>
            <!-- 备注，这边的名称必须和配置'springmvc'的servlet名称一样 -->
            <servlet-name>SpringMVC</servlet-name>
        </filter-mapping>
    ```
2. 采用AJAX PUT的方式
   1. 前端采用PUT方法,数据类型设置为JSON
   2. 前端ajax代码如下
    ```js
        $.ajax({
            type: "put",
            url: "user/employee/",
            contentType: "application/json",
            data: JSON.stringify(row),
            dataType: 'JSON',
            success: function (data, status) {
                if (status == "success") {
                    alert('提交数据成功');
                }
            }
        });
    ```
   3. 后端Spring Controller代码如下
    ```java
        @RequestMapping(path = "/employee/", produces = "text/json", method = RequestMethod.PUT)
        public int editEmployee(@RequestBody User user) {
            System.out.println(user.toString());
            return 4;
        }
    ```

注1: 需要注意的是@RequestBody和@RequestParam的区别,前者解析JSON字符串,后者解析JSON对象  
注2: 传递bean问题