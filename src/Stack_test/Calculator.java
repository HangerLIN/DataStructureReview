package Stack_test;
public class Calculator {
    public static void main(String[] args) {
        Stack<Integer> numStack = new Stack<Integer>();
        Stack<Character> operatorStack = new Stack<Character>();

        String expression = "7 * 21 * 2 - 52 + 1 - 5 + 3 - 4";
        int index = 0;
        /*
        如果想遍历含有空格的字符串，可以使用 split() 方法将字符串按照空格分割成字符串数组，然后遍历数组中的每个元素。例如，可以按照以下方式遍历字符串 expression：
String expression = "7 * 21 * 2- 52 + 1 - 5 + 3 - 4";
         */
        String[] tokens = expression.split(" ");
        for (int i = 0; i < tokens.length; i++) {
            String temp = tokens[i];
            //Number
            if ((int)temp.charAt(0) > 48 && (int)temp.charAt(0) < 57){
                int number = Integer.valueOf(temp);
                numStack.push(number);
            }else{
                //operator
                    //if empty => just into stack
                if (operatorStack.isEmpty()){
                    operatorStack.push(temp.charAt(0));
                }else {
                    //优先级高：入栈
                    if (operatorStack.Priority(String.valueOf(operatorStack.peek())) > operatorStack.Priority(temp)){
                        operatorStack.push(temp.charAt(0));
                    }
                    //优先级低，从里面跳出两个值来做计算
                    else {
                        int num1 = numStack.pop();
                        int num2 = numStack.pop();

                        char operation = operatorStack.pop();
                        int result = numStack.cal(num1,num2, String.valueOf(operation));

                        numStack.push(result);
                        operatorStack.push(temp.charAt(0));
                    }
                }
            }
        }

//        已经扫描完了
        while (numStack.size() >= 2){
            int num1 = numStack.pop();
            int num2 = numStack.pop();

            numStack.push(Stack.cal(num1,num2, String.valueOf(operatorStack.pop())));
        }

        System.out.println("the result is:" + numStack.pop());
    }
}
