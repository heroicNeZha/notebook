# MyBatis 学习笔记

MyBatis 可以简化JDBC操作，实现数据的持久化  
ORM 对象关系映射  
MyBatis是ORM概念的实现  
ORM 可以让开发人员像操作对象一样操作数据库表

## 配置MyBatis以及初级CRUD

### 配置Mybatis

1. 导入Jar包 Maven：Mysql、MyBatis

2. 数据库添加表，DAO添加实体

3. mybatis配置文件mybatis-config.xml，配置**数据库信息**和**需要加载的文件**
    ```xml
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE configuration
            PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <configuration>
        <environments default="development">
            <environment id="development">
                <transactionManager type="JDBC"/>
                <dataSource type="POOLED">
                    <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                    <property name="url"
                            value="jdbc:mysql://localhost/mybatis_start?characterEncoding=utf8&amp;serverTimezone=GMT"/>
                    <property name="username" value="root"/>
                    <property name="password" value="123456"/>
                </dataSource>
            </environment>
        </environments>
        <mappers>
            <mapper resource="mapper/personMapper.xml"/>
        </mappers>
    </configuration>
    ```
    1. 可以通过修改default和build的参数指定数据库
    2. 数据源类型  
         - UNPOOLED：传统JDBC模式，需要打开关闭数据库
         - POOLED：使用数据库连接池
         - JNDI：从tomcat中获取一个内置的数据库连接池
    3. 事务提交方式
         - JDBC：利用JDBC方式处理事务（commit rollback close）
         - MANAGED：将事务交由其他组件去托管（spring jobss），默认会关闭连接

### 通过配置文件方式实现CRUD

1. 添加映射文件mapper.xml：增删改查标签
    ```xml
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper
            PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

    <mapper namespace="mapper.personMapper">
        <select id="queryPersonById" resultType="edu.ustc.SpringStart.person.POJO.Person" parameterType="int">
            select *
            from person
            where id = #{id}
        </select>

        <insert id="addPerson" parameterType="edu.ustc.SpringStart.person.POJO.Person">
            insert into person(name, age)
            values (#{name}, #{age})
        </insert>

        <update id="updatePersonById" parameterType="edu.ustc.SpringStart.person.POJO.Person">
            update person
            set name = #{name},
                age  = #{age}
            where id = #{id}
        </update>

        <insert id="deletePersonById" parameterType="int">
            delete
            FROM person
            where id = #{id}
        </insert>
    </mapper>
    ```
    1. 语法上只能有**一个**输入输出，但可以是集合和对象
    2. 如果输入/输出是简单类型，则可以使用任意占位符；如果是对象类型，则只能是对象的属性（严格区分大小写）
    3. 无论返回单值还是多个值，resultType都是一个

2. DAO通过Resources加载配置文件，并连接到数据库
    ```java
    @Override
    public Person queryPersonByName(String name) throws Exception {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        String statement = "mapper.personMapper.queryPersonByName";
        Person person = session.selectOne(statement, name);
        return person;
    }
    ```
    1. 如果使用的事务方式是JDBC，需要手动commit提交

---

## 动态代理方式CRUD（官方推荐）

MyBatis接口开发  

### 原则：约定优于配置

>1. 配置方式：abc.xml  
>2. 硬编码方式：abc.setName()  
>3. 约定方式：默认值就是name

### 具体实现的步骤

1. 基础环境：mybatis、mysql-jdbc、conf.xml、mapper.xml
2. 约定的目标：省略掉statement，即根据约定直接可以定位出SQL
3. 约定：
   1. 方法名和mapper.xml文件中标签的id值相同
   2. 方法的输入参数和mapper.xml文件中标签的parameterType类型一致
   3. 方法的返回值与mapper.xml文件中标签的resultType类型一致
    ``` java
    public interface PersonMapper {
        Person queryPersonByName(String name);
        Person queryPersonById(int id);
        void addPerson(Person person);
        void updatePersonById(Person person);
        void deletePersonById(int id);
        List<Person> queryAll();
    }
    ```
    4. 除了以上约定，要实现接口中的方法和mapper.xml中xml标签一一对应，还需要**将mapper.xml的namespace的值设置为接口的全类名**
    ```xml
    <mapper namespace="edu.ustc.SpringStart.person.mapper.PersonMapper">
        ...
    </mapper>
    ```
4. 匹配的过程
   1. 根据接口名找到mapper.xml文件
   2. 根据接口的方法名找到mapper.xml文件中的SQL标签（方法名=SQL标签的id值）
   3. 以上两点可以保证：当我们调用接口中的方法时，程序能唯一的自动定位到某一个Mapper.xml文件中的sql标签
5. 习惯：SQL映射文件（mapper.xml）和接口放在同一个包
6. DAO通过getMappers方法获得接口
    ``` java
    @Override
    public Person queryPersonByName(String name) throws Exception {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = factory.openSession();
        PersonMapper personMapper = session.getMapper(PersonMapper.class);
        Person person = personMapper.queryPersonByName(name);
        return person;
    }
    ```
## 优化

### 属性文件

1. 可以将配置信息单独放入db.properties文件中
    ```properties
    driver=com.mysql.cj.jdbc.Driver
    url=jdbc:mysql://localhost/mybatis_start?characterEncoding=utf8&serverTimezone=GMT
    username=root
    password=123456
    ```
2. 在configuration标签中动态引入属性文件
    ```xml
    <properties resource="db.properties"/>
    ```
3. 引入后使用${key}

### Mybatis 全局参数

1. 全局参数的设置
    ```xml
    <!-- settings是 MyBatis 中极为重要的调整设置，它们会改变 MyBatis 的运行时行为。 -->
    <settings>
        <!-- 该配置影响的所有映射器中配置的缓存的全局开关。默认值true -->
        <setting name="cacheEnabled" value="true"/>
        <!--延迟加载的全局开关。当开启时，所有关联对象都会延迟加载。 特定关联关系中可通过设置fetchType属性来覆盖该项的开关状态。默认值false  -->
        <setting name="lazyLoadingEnabled" value="true"/>
        <!-- 是否允许单一语句返回多结果集（需要兼容驱动）。 默认值true -->
        <setting name="multipleResultSetsEnabled" value="true"/>
        <!-- 使用列标签代替列名。不同的驱动在这方面会有不同的表现， 具体可参考相关驱动文档或通过测试这两种不同的模式来观察所用驱动的结果。默认值true -->
        <setting name="useColumnLabel" value="true"/>
        <!-- 允许 JDBC 支持自动生成主键，需要驱动兼容。 如果设置为 true 则这个设置强制使用自动生成主键，尽管一些驱动不能兼容但仍可正常工作（比如 Derby）。 默认值false  -->
        <setting name="useGeneratedKeys" value="false"/>
        <!--  指定 MyBatis 应如何自动映射列到字段或属性。 NONE 表示取消自动映射；PARTIAL 只会自动映射没有定义嵌套结果集映射的结果集。 FULL 会自动映射任意复杂的结果集（无论是否嵌套）。 -->
        <!-- 默认值PARTIAL -->
        <setting name="autoMappingBehavior" value="PARTIAL"/>

        <setting name="autoMappingUnknownColumnBehavior" value="WARNING"/>
        <!--  配置默认的执行器。SIMPLE 就是普通的执行器；REUSE 执行器会重用预处理语句（prepared statements）； BATCH 执行器将重用语句并执行批量更新。默认SIMPLE  -->
        <setting name="defaultExecutorType" value="SIMPLE"/>
        <!-- 设置超时时间，它决定驱动等待数据库响应的秒数。 -->
        <setting name="defaultStatementTimeout" value="25"/>

        <setting name="defaultFetchSize" value="100"/>
        <!-- 允许在嵌套语句中使用分页（RowBounds）默认值False -->
        <setting name="safeRowBoundsEnabled" value="false"/>
        <!-- 是否开启自动驼峰命名规则（camel case）映射，即从经典数据库列名 A_COLUMN 到经典 Java 属性名 aColumn 的类似映射。  默认false -->
        <setting name="mapUnderscoreToCamelCase" value="false"/>
        <!-- MyBatis 利用本地缓存机制（Local Cache）防止循环引用（circular references）和加速重复嵌套查询。
               默认值为 SESSION，这种情况下会缓存一个会话中执行的所有查询。
              若设置值为 STATEMENT，本地会话仅用在语句执行上，对相同 SqlSession 的不同调用将不会共享数据。  -->
        <setting name="localCacheScope" value="SESSION"/>
        <!-- 当没有为参数提供特定的 JDBC 类型时，为空值指定 JDBC 类型。 某些驱动需要指定列的 JDBC 类型，多数情况直接用一般类型即可，比如 NULL、VARCHAR 或 OTHER。  -->
        <setting name="jdbcTypeForNull" value="OTHER"/>
        <!--   指定哪个对象的方法触发一次延迟加载。  -->
        <setting name="lazyLoadTriggerMethods" value="equals,clone,hashCode,toString"/>
    </settings>
    ```
    
2. 全部全局参数如图

    ![mybatis全局参数](/Users/lzw/workspace/notebook/学习/Java++/assets/mybatis全局参数.png)

    ![mybatis全局参数2](/Users/lzw/workspace/notebook/学习/Java++/assets/mybatis全局参数2.png)

### 别名

1. 设置单个别名，忽略大小写
2. 批量设置别名,会自动将该包中的所有类批量定义别名
    ```xml
    <!-- 设置单个/多个别名-->
    <typeAliases>
        <!-- 设置单个别名-->
        <typeAlias type="edu.ustc.SpringStart.person.POJO.Person" alias="person"></typeAlias>
        <!-- 批量定义别名-->
        <package name="edu.ustc.SpringStart.person.POJO"></package>
    </typeAliases>
    ```
3. 除了自定义别名外，Mybatis还内置了一些常见类的别名  
    |    别名    | 映射的类型 |
    | :--------: | :--------: |
    |   _byte    |    byte    |
    |   _long    |    long    |
    |   _short   |   short    |
    |    _int    |    int     |
    |  _integer  |    int     |
    |  _double   |   double   |
    |   _float   |   float    |
    |  _boolean  |  boolean   |
    |   string   |   String   |
    |    byte    |    Byte    |
    |    long    |    Long    |
    |   short    |   Short    |
    |    int     |  Integer   |
    |  integer   |  Integer   |
    |   double   |   Double   |
    |   float    |   Float    |
    |    date    |    Date    |
    |  decimal   | BigDecimal |
    | bigdecimal | BigDecimal |

### 类型处理器（类型转换器）

1. Mybatis自带一些类型处理器  
    int - number
2. 自定义Mybatis类型处理器  
    SET: java -> 数据库（JDBC类型)  
    GET: 数据库（JDBC类型) -> java

    | 实体类Person(sex) | 表person(sex) |
    | :---------------: | :-----------: |
    |      boolean       |    Integer     |
    |     true：男      |     1：男     |
    |     false：女     |     0：女     |
3. 自定义类型转换器步骤：
   1. 创建转换器两种选择：
      1. 实现TypeHandler接口
      2. 继承此接口实现类BaseTypeHandler
        ```java
        public class BooleanAndIntConverler extends BaseTypeHandler<Boolean> {
            /*
            * ps:PreparedStatement对象
            * index: PreparedStatement对象操作参数的位置
            * parameter：java值
            * jdbcType：jdbc操作数据库的值
            */
            @Override
            public void setNonNullParameter(PreparedStatement preparedStatement, int index, Boolean parameter, JdbcType jdbcType) throws SQLException {
                if (parameter) {
                    preparedStatement.setInt(index, 1);
                } else {
                    preparedStatement.setInt(index, 0);
                }
            }

            @Override
            public Boolean getNullableResult(ResultSet resultSet, String columnIndex) throws SQLException {
                return resultSet.getInt(columnIndex) == 1;
            }

            @Override
            public Boolean getNullableResult(ResultSet resultSet, int index) throws SQLException {
                return resultSet.getInt(index) == 1;
            }

            @Override
            public Boolean getNullableResult(CallableStatement callableStatement, int index) throws SQLException {
                return callableStatement.getInt(index) == 1;
            }
        }
        ```
   2. 在configuration中引用转换器
        ```xml
        <typeHandlers>
            <typeHandler handler="edu.ustc.SpringStart.person.converter.BoolenAndIntConverler" javaType="Boolean" jdbcType="INTEGER"/>
        </typeHandlers>
        ```
      1. jdbcType在别名表中可以查到
   3. mapper文件中
      1. 如果类中属性和表中字段类型能够合理识别（string-varchar），则可以使用resultType，否则（boolean-int）使用resultMap
      2. 使用类中属性名和表中字段名能够合理识别（id-id），则可以使用resultType，否则（name-pName）使用resultMap
            ```xml
            <resultMap id="personResult" type="person">
                <!-- 分为主键和非主键-->
                <id property="id" column="id"/>
                <result property="name" column="name"/>
                <result property="age" column="age"/>
                <result property="sex" column="sex" javaType="Boolean" jdbcType="INTEGER"/>
            </resultMap>

            <select id="queryPersonById" resultMap="personResult" parameterType="int">
                select *
                from person
                where id = #{id}
            </select>
            
            <insert id="addPerson" parameterType="person">
                insert into person(name, age ,sex)
                values (#{name}, #{age},#{sex,javaType=boolen,jdbcType=INTEGER})
            </insert>

            ```
## 输入参数parameterType

### 两种取值符号

1. 不同之处
    1. 简单类型（8个基本类型+String): \#{任意值}、\${value}  
        对象类型: \#{属性名}、\${属性名} 
    2. \#{} 自动加单引号  
        \${} 原样输出，但适合**动态排序**（动态字段）
    3. \#{} 防止SQL注入  
        \${} 不防止注入
2. 相同之处
    
    1. 都可以获取对象的值（嵌套对象类型）
3. 模糊查询
   1. 方式一，提前处理数据
   ```java
    name ="%"+name+"%";
    Person person = personMapper.queryPersonByName(name);
   ```
   1. 方式二，修改mapper
   ```xml
    <select id="queryPersonByName" resultMap="personResult" parameterType="string">
        select *
        from person
        where name like '%${name}%'
    </select>
   ```
4. 嵌套对象类型
   
   1. 可级联调用

### 输入参数为HashMap

用map中key的值匹配占位符\#{key}，\${key}，如果匹配成功，就用key的value替换占位符

### mybatis 调用存储过程

1. 创建存储过程
    ```sql
    DELIMITER $$ //临时将结束符设为$$
    CREATE PROCEDURE `queryPersonByIdWithProcedure`(in begin_age int,in end_age int,out p_count int)
    BEGIN
	    select Count(*) into p_count from person where person.age >begin_age and person.age < end_age;
    END$$
    DELIMITER ;
    ```
1. 通过调用[存储过程]实现查询，statementType="CALLABLE"
2. 存储过程的输入参数，在mybatis用Map来传递（hashMap）
3. 修改mapper
    ```xml
    <select id="queryPersonByAge" parameterType="HashMap" statementType="CALLABLE">
        {
            CALL queryPersonByIdWithProcedure(#{start_age,jdbcType=INTEGER,mode=IN},#{end_age,jdbcType=INTEGER,mode=IN},#{p_count,jdbcType=INTEGER,mode=OUT})
        }
    </select>
    ```
4. 通过HashMap的get方法拿到输出参数

## 输出参数resultType

1. 简单类型（8个基本类型+String)
2. 对象类型
3. HashMap
   1. 别名作为Map的key
   2. 返回值为HashMap时，只能查询一条
4. 名字不一致也可以使用resultType+HashMap
    
    >注意：发现某一个字段结果始终为默认值，可能是字段和属性没匹配上

## 动态SQL

### SQL标签

1. if
   1. 属性，严格区分大小写
   2. \<where>\</where>标签自动添加where，并自动处理第一个and
    ```xml
    <select id="queryPersonByPerson" resultMap="personResult" parameterType="person">
        select * from person
        <where>
            <if test="age!=null and id!=0">
                and id = #{id}
            </if>
            <if test="name!=null and name != '' ">
                and name like '%${name}%'
            </if>
            <if test="age!=null and age != 0 ">
                and age = #{age}
            </if>
            <if test="sex!=null and sex != '' ">
                and sex = #{sex,javaType=boolean,jdbcType=INTEGER}
            </if>
        </where>
    </select>
    ```
2. choose（when，otherwise）
   1. 只想选择其中一项的时候
    ```xml
    <select id="findActiveBlogLike" resultType="Blog">
        SELECT * FROM BLOG WHERE 1=1
        <choose>
            <when test="title != null">
                AND title like #{title}
            </when>
            <when test="author != null and author.name != null">
                AND author_name like #{author.name}
            </when>
            <otherwise>
                AND featured = 1
            </otherwise>
        </choose>
    </select>
    ```
3. trim，where，set
   1. 和where元素等价的trim语句如下
        ```xml
        <trim prefix="WHERE" prefixOverrides="AND |OR ">
        ...
        </trim>
        ```
    2. 更新语句类似的是set元素
        ```xml
        <update id="updateAuthorIfNecessary">
            update Author
            <set>
                <if test="username != null">username=#{username},</if>
                <if test="password != null">password=#{password},</if>
                <if test="email != null">email=#{email},</if>
                <if test="bio != null">bio=#{bio}</if>
            </set>
            where id=#{id}
        </update>
        ```
4. foreach
   1. 遍历集合，通常构建IN语句的时候
   2. open是开始的前缀，close是结束的后缀，通过item迭代，separator分隔符
        ```xml
        <select id="selectPostIn" resultType="domain.blog.Post">
            SELECT *
            FROM POST P
            WHERE ID in
            <foreach item="item" index="index" collection="list"
                    open="(" separator="," close=")">
                #{item}
            </foreach>
        </select>
        ```
   3. 如果传进来的是简单类型数组，mapper.xml必须用array代替，集合必须用list代替。如果是对象数组，parameterType必须是Object[]，collection是array。
### SQL片段

1. 相似代码在java中是方法，在数据库中是存储过程，在Mybatis是SQL片段
2. 使用方法，先声明SQL片段`
    ```xml
    <sql id="selectAll">
        select * from person
    </sql>
    ```
    然后在标签中通过include引用
    ```xml
    <include refid="selectAll"/>
    ```
    如果SQL片段和引用处不在一个文件，需要在refid加上命名空间

## 关联查询

### 一对一

1. 业务扩展类

### 一对多

MyBatis：多对一、多对多本质就是一对多的变化

## 逆向工程

表、类、接口、mapper 密切相关，因此可由任意一项生成其他三项。

### 操作步骤

1. mybatis-generator-core.jar、mybatis.jar、jdbc.jar
2. 逆向工程配置文件generator.xml
3. 执行逆向工程mybatisgennerator.java

### 使用方法

1. example.criteria:查询条件
2. 对于like模糊查询，逆向工程需要自己加%
3. example 默认第一个条件，两个条件用example.or（criteria）