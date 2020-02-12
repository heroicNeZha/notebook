import java.util.Stack;

/*
 * @lc app=leetcode.cn id=20 lang=java
 *
 * [20] 有效的括号
 */

// @lc code=start
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (stack.empty()) {
                stack.push(s.charAt(i));
            } else {
                switch (s.charAt(i)) {
                case '}':
                    if (stack.peek() == '{')
                        stack.pop();
                    else
                        stack.push(s.charAt(i));
                    break;
                case ']':
                    if (stack.peek() == '[')
                        stack.pop();
                    else
                        stack.push(s.charAt(i));
                    break;
                case ')':
                    if (stack.peek() == '(')
                        stack.pop();
                    else
                        stack.push(s.charAt(i));
                    break;
                default:
                    stack.push(s.charAt(i));
                }
            }
        }
        if (stack.empty())
            return true;
        return false;
    }
}
// @lc code=end
