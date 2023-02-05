import java.util.Stack;

public class calc {
    private StringBuilder buffer = new StringBuilder();
    private Stack<Operators> operators = new Stack<>();
    private Stack<Integer> digits = new Stack<>();
    private boolean wasOperator = false;

    public String getBuffer() {
        if (buffer.length() != 0) {
            return buffer.toString();
        }
        if (!digits.isEmpty()) {
            return "" + digits.peek();
        }
        return "";
    }

    public String addDigit(int digit) {
        if (wasOperator) {
            clearBuffer();
        }
        wasOperator = false;
        buffer.append(digit);

        return getBuffer();
    }

    public String setOperator(Operators operateur) {
        if (buffer.length() != 0) {
            if (wasOperator) {
                operators.pop();
            } else {
                wasOperator = true;
                digits.push(Integer.parseInt(buffer.toString()));
            }

            while(!operators.isEmpty()&& operators.peek().getPrecedence() > operateur.getPrecedence()) {
                    reduce();
                }

            operators.push(operateur);

            //clearBuffer();
        }else if(digits.peek()!=null){
            wasOperator = true;
            buffer.append(digits.peek());
            while(!operators.isEmpty()&& operators.peek().getPrecedence() > operateur.getPrecedence()) {
                reduce();
            }
            operators.push(operateur);
        }

        return getBuffer();
    }

    public String clear() {
        clearBuffer();
        wasOperator = false;
        digits.clear();
        operators.clear();
        return getBuffer();
    }

    public String equals() {
        if (buffer.length() != 0) {
            digits.push(Integer.parseInt(buffer.toString()));
            clearBuffer();
        }
        wasOperator = false;
        while ((digits.size()>1)) {
            reduce();
        }


        return getBuffer();
    }
    private void reduce() {
        while (!operators.isEmpty()) {
            Operators o = operators.pop();
            int b = digits.pop();
            if (digits.isEmpty()) {
                digits.push(b);
                break;
            }
            int a = digits.pop();
            switch (o) {
                case ADD: {
                    digits.push(a + b);
                    break;
                }
                case MINUS: {
                    digits.push(a - b);
                    break;
                }
                case MULTIPLE: {
                    digits.push(a * b);
                    break;
                }
                case DIVIDE: {
                    digits.push(a / b);
                    break;
                }
            }
        }
    }



    private void clearBuffer() {
        buffer.delete(0, buffer.length());
    }
}





