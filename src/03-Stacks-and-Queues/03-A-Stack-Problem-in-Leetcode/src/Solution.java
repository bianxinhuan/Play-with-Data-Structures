import java.util.Stack;

/**
 * LeetCode中国中的题目：有效的括号，https://leetcode-cn.com/problems/valid-parentheses/
 *
 * @author bianxinhuan
 * @date 2019-04-18 22:21:23
 */
public class Solution {

    //    给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
    //
    //    有效字符串需满足：
    //
    //    左括号必须用相同类型的右括号闭合。
    //    左括号必须以正确的顺序闭合。
    //    注意空字符串可被认为是有效字符串。
    //
    //    示例 1:
    //
    //    输入: "()"
    //    输出: true
    //    示例 2:
    //
    //    输入: "()[]{}"
    //    输出: true
    //    示例 3:
    //
    //    输入: "(]"
    //    输出: false
    //    示例 4:
    //
    //    输入: "([)]"
    //    输出: false
    //    示例 5:
    //
    //    输入: "{[]}"
    //    输出: true

    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            // 如果字符是左括号则入栈
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                // 如果是右括号，栈为空，则匹配失败
                if (stack.isEmpty()) {
                    return false;
                }

                // 取出栈顶字符和当前字符匹配
                char topChar = stack.pop();

                // 如果栈顶的字符和当前字符不能匹配成一对括号，则匹配失败
                if (c == ')' && topChar != '(') {
                    return false;
                }
                if (c == ']' && topChar != '[') {
                    return false;
                }
                if (c == '}' && topChar != '{') {
                    return false;
                }
            }
        }

        // 如果匹配完栈内还有元素，则表示括号没有闭合，匹配失败；反之成功
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isValid("()"));
        System.out.println(new Solution().isValid("()[]{}"));
        System.out.println(new Solution().isValid("(]"));
    }
}
