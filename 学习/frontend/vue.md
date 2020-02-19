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
   1. v-html="msg" 插入html元素
   2. v-bind:href="url" 绑定属性,可以用:代替
   3. v-on:click="count()" 绑定事件,可以用@代替
    ```html
    <div id="div1" v-html="template">
        <div id="vhtml" v-html="msg">
            {{msg}}
        </div>
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
3. 计算属性与侦听器
    1. 计算属性computed`:函数中包含的本实例的任意值变化都会引起属性变化和重新渲染.
    2. 侦听器`watch`:只能侦听一个值的变化
    ```js
    var app = new Vue({
        el: '#div1',
        data: {
            msg: 'hello vue',
            msg2: 'hello msg2'
        },
        watch: {
            msg: function (newVal, oldVal) {
                console.log(newVal + "\n" + oldVal);
            }
        },
        computed: {
            msg1: function () {
                return "computed:" + this.msg + this.msg2;
            }
        }
    })
    ```
4. 条件渲染、列表渲染、Class和Style绑定
   1. 条件渲染
      1. `v-if="count > 0"`
      2. v-else,v-elseif
      3. v-show
        
   2. 列表渲染
      1. `v-for="item in list"`
   3. Class和Style绑定
      1. `styleMsg:{color:'red'}` 对象
      2. `v-bind:style="styleMsg"` 绑定style
      3. 类似text-shadow的属性可以写成'text-shadow'或者textShadow
      4. `:class="['active','a2']"` 绑定class
      5. `:class="[{'one':true,'anothor': count > 3}]"` 条件绑定
        ```html
        <<div id="div1">
        {{msg}}
            <div v-if="count== 0">
                {{count}}==0
            </div>
            <div v-else-if="count < 5" v-bind:style="styleMsg">
                {{count}} &lt; 5
            </div>
            <div v-else>
                {{count}} > 5
            </div>
            <div v-show="count==0">
                {{count}}
            </div>
            <br>
            <div v-for="item in list">
                {{item}} 
            </div>
        </div>
        ```


### vue常用核心技术

1. 认识vue-cli
   1. vue create my-app
   2. 或者通过vue-ui来可视化创建
2. 组建化思想
   1. WHAT组件化是独立的、可复用的、整体化的
   2. WHY为了实现功能模块的复用、高执行效率、开发复杂的SPA
   3. HOW拆分原则：300行原则、复用原则、业务复杂性原则
   4. 组件化的问题：
      1. 组件状态管理(vuex)
      2. 多组件混合使用、多页面、复杂业务(vue-router)
      3. 组件间传参、消息、事件管理(props、emit/on、bus)
3. vue-router
   1. 在router/index.js中写路由信息
    ```javascript
    {
    path: '/about',
    name: 'About',
    component: () => import('../views/About.vue'),
    }
    ```
   2. 在导航添加router-link
    ```javascript
    <router-link to="/about">About</router-link>
    ```
4. vuex
   1. 单向数据流:状态->试图->动作
   2. 多个视图依赖同一状态(菜单导航)
   3. 来自不同视图的行为需要变更统一状态(弹幕状态)
   4. state:状态,通过store.state.name调用
   5. mutations:方法,通过store.commit('func_name')调用