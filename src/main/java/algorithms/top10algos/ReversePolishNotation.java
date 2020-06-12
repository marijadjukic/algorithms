package algorithms.top10algos;

import java.util.Stack;

public class ReversePolishNotation {

    public int evaluateReversePolishNotation(String[] tokens) {
        String operators = "+-*/";

        if (tokens.length == 0) {
            return 0;
        } else if (tokens.length == 1) {
            if (tokens[0].equals("")) {
                return 0;
            }
            return Integer.valueOf(tokens[0]);
        } else {
            if(operators.contains(tokens[0]) || operators.contains(tokens[1])) {
                return 0;
            }
        }

        Stack<Integer> stack = new Stack<>();
        for(String token : tokens) {
            if(operators.contains(token)) {
                Integer operand2 = stack.pop();
                Integer operand1 = stack.pop();
                if (token.equals("+")) {
                    stack.push(operand1 + operand2);
                } else if (token.equals("-")) {
                    stack.push(operand1 - operand2);
                } else if (token.equals("*")) {
                    stack.push(operand1 * operand2);
                } else {
                    stack.push(operand1 / operand2);
                }
            } else {
                Integer operand = token == "" ? 0 : Integer.valueOf(token);
                stack.push(operand);
            }
        }
        return stack.pop().intValue();
    }

}
