# Vue

### Vue环境介绍
1. node.js和npm可以通过版本管理工具nvm来控制,可以用cnpm、别名和淘宝镜像源来加速下载.
2. sudo npm install -g @vue/cli
3. vue create my-app
4. code my-app
5. 安装vetur
6. chrome 安装插件 vuejs devtools
   
### 认识vue
1. 第一个vue应用
    ```html
    {{msg}}
    <script>
        new Vue({
            el: '#div1',
            data: {
                msg: 'hello vue'
            }
        })
    </script>
    ```
2. 模板语言
   1. v-html="template" 会崩溃??
   2. v-bind:href="url" 绑定属性,可以用:代替
   3. v-on:click="count()" 绑定事件,可以用@代替
    ```html
    <div id="div1" v-html="template">
        {{msg}}
        {{countNum}}<br>
        <a :href="url">百度</a>
        <button @click="count()">click</button>
    </div>
    <script>
        new Vue({
            el: '#div1',
            data: {
                url: 'http://baidu.com',
                msg: 'hello vue <br>',
                countNum: 0
            },
            methods: {
                count: function () {
                    this.countNum++
                }
            }
        })
    </script>
    ```
### 基本概念
### 组件
### 常用API
### 部署