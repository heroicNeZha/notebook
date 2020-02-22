/*
 * @lc app=leetcode.cn id=155 lang=java
 *
 * [155] 鏈€灏忔爤
 */

// @lc code=start
class MinStack {
    // 两个栈
    // 存差值
    private class Item {
        int value;
        Item pre;

        public Item(int v, Item p) {
            this.value = v;
            this.pre = p;
        }
    }

    Item head;
    Item peek;
    int min;

    /** initialize your data structure here. */
    public MinStack() {
        head = null;
        peek = head;
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        Item item = new Item(x, peek);
        if (x < min)
            min = x;
        peek = item;
    }

    public void pop() {
        if (min == peek.value) {
            min = Integer.MAX_VALUE;
            Item pointer = peek.pre;
            while (pointer != null) {
                if (pointer.value < min)
                    min = pointer.value;
                pointer = pointer.pre;
            }
        }
        peek = peek.pre;
    }

    public int top() {
        return peek.value;
    }

    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such: MinStack obj =
 * new MinStack(); obj.push(x); obj.pop(); int param_3 = obj.top(); int param_4
 * = obj.getMin();
 */
// @lc code=end
