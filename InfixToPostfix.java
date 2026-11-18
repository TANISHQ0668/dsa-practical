import java.util.Stack;

public class InfixToPostfix {

    // Assign precedence to operators
    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    // Convert infix expression to postfix
    static String convertToPostfix(String exp) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char c : exp.toCharArray()) {
            // 1. If operand (letter or digit), add directly to output
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            }
            // 2. If '(', push to stack
            else if (c == '(') {
                stack.push(c);
            }
            // 3. If ')', pop until '(' is found
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop(); // Remove '(' from stack
            }
            // 4. If operator, pop higher/equal precedence operators first
            else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        // Pop all remaining operators from stack
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String infix1 = "A+B*C";
        String infix2 = "(A+B)*(C-D)";

        System.out.println("Infix: " + infix1 + "  -->  Postfix: " + convertToPostfix(infix1));
        System.out.println("Infix: " + infix2 + "  -->  Postfix: " + convertToPostfix(infix2));
    }
}