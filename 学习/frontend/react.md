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
1. 在上面的代码中,Timer是一个React组建类,或者是一个React组建类型.
2. React组件使用一个名为render()方法,接收输入数据并返回需要展示的层次结构.
   1. this.props 访问被传入的数据.
   2. this.state 维护内部状态.更改时调用this.setState({value:'1'}).当内部状态改变时,组件会再次调用render方法.
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
3. JSX在编译时,会将<div />自动编译成React.createElment('div');
4. 对于如下代码,调用顺序为button.onclick()->Square.onclick()->handleClick(i)
    ```JSX
    class Square extends React.Component {
        render() {
            return (
                <button
                    className="square"
                    onClick={() => this.props.onClick()} >
                </button>
            );
        }
    }

    class Board extends React.Component {
        renderSquare(i) {
            //将()=>this.handleClick(i)传递给Square
            return <Square onClick={() => this.handleClick(i)} />;
        }
        render() {
            return (
                <div>
                    {this.renderSquare(0)}
                </div>
            );
        }
    }
    ```
5. 调用slice方法创建数组副本,维持不变性.
6. 对于只包含一个render方法的组建,可以将其实现为函数
    ```JSX
    function Square(props) {
        return ();
    }
    ```
