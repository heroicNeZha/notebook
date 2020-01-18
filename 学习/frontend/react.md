# React
### 介绍
1. 可以被逐步采用,可以按需引入或多或少的React特性.
### vs开发react
1. 确保拥有最新版本的nodejs
    ```bash
    #安装node.js
    curl -sL https://deb.nodesource.com/setup_13.x | sudo -E bash -
    sudo apt-get install -y nodejs
    ```
2. 运行
    ```js
    npx create-react-app my-app
    ```
### 组件
1. React组件使用一个名为render()方法,接收输入数据并返回需要展示的内容.
   1. this.props 访问被传入的数据.
   2. this.state 维护内部状态.当内部状态改变时,组建会再次调用render方法.
    ```jsx
    class Timer extends React.Component {  
        constructor(props) {
            super(props);
            this.state = { seconds: 0 };
        }
        tick() {
            this.setState(state => ({
                seconds: state.seconds + 1
            }));
        }
        componentDidMount() {
            this.interval = setInterval(() => this.tick(), 1000);
        }

        componentWillUnmount() {
            clearInterval(this.interval);
        }
        render(){
            return(
                <div>Seconds:{this.state.seconds}</div>
            );
        }
    }
    ReactDOM.render(
        <Timer/>,
        document.getElementById('timer-example')
        );
    ```
