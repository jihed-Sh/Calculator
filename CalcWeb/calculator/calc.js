var Stack = /** @class */ (function () {
    function Stack(capacity) {
        if (capacity === void 0) { capacity = Infinity; }
        this.capacity = capacity;
        this.storage = [];
    }
    Stack.prototype.push = function (item) {
        if (this.size() === this.capacity) {
            throw Error("Stack has reached max capacity, you cannot add more items");
        }
        this.storage.push(item);
    };
    Stack.prototype.pop = function () {
        return this.storage.pop();
    };
    Stack.prototype.peek = function () {
        return this.storage[this.size() - 1];
    };
    Stack.prototype.size = function () {
        return this.storage.length;
    };
    Stack.prototype.isEmpty = function () {
        if (this.size() > 0)
            return true;
        return false;
    };
    Stack.prototype.clear = function () {
        while (!this.isEmpty()) {
            this.pop();
        }
    };
    return Stack;
}());
var Calc = /** @class */ (function () {
    function Calc() {
        this.buffer = "";
        this.wasOperator = false;
        this.digits = new Stack();
        this.operators = new Stack(); //changable to opertators enums
    }
    Calc.prototype.getBuffer = function () {
        if (this.buffer.length != 0) {
            return this.buffer.toString();
        }
        if (!this.digits.isEmpty()) {
            return "" + this.digits.peek();
        }
        return "";
    };
    Calc.prototype.clearBuffer = function () {
        this.buffer = "";
    };
    Calc.prototype.addDigit = function (digit) {
        if (this.wasOperator) {
            this.clearBuffer();
        }
        this.wasOperator = false;
        this.buffer = this.buffer + digit;
        document.getElementById("interface").value = this.buffer;
        return this.getBuffer();
    };
    Calc.prototype.setOperator = function (op) {
        if (this.buffer.length != 0) {
            if (this.wasOperator) {
                this.operators.pop(); //changable to opertators enums
            }
            else {
                this.wasOperator = true;
            }
            this.digits.push(parseInt(this.buffer));
            while (this.operators.size() > 0 && (this.getPre((this.operators.peek())) > this.getPre(op))) { //changable to opertators enums
                this.reduce();
            }
            this.operators.push(op); //changable to opertators enums
            this.clearBuffer();
        }
        return this.getBuffer();
    };
    Calc.prototype.clear = function () {
        this.clearBuffer();
        this.wasOperator = false;
        this.digits.clear();
        this.operators.clear();
        document.getElementById("interface").value = this.buffer; //changable to opertators enums
        return this.getBuffer();
    };
    Calc.prototype.reduce = function () {
        while (this.operators.size() > 0) {
            var o = this.operators.pop();
            var b = this.digits.pop();
            if (this.digits.size() == 0) {
                this.digits.push(b);
            }
            var a = this.digits.pop();
            switch (o) {
                case 100: { //changable to opertators enums
                    this.digits.push(a + b);
                    break;
                }
                case 300: { //changable to opertators enums
                    this.digits.push(a / b);
                    break;
                }
                case 200: { //changable to opertators enums
                    this.digits.push(a - b);
                    break;
                }
                case 400: { //changable to opertators enums
                    this.digits.push(a * b);
                    break;
                }
            }
        }
    };
    Calc.prototype.equals = function () {
        if (this.buffer.length != 0) {
            this.digits.push(parseInt(this.buffer.toString()));
            this.clearBuffer();
        }
        this.wasOperator = false;
        while (this.digits.size() > 1) {
            this.reduce();
        }
        document.getElementById("interface").value = this.digits.peek(); //change it to peek in js
        return this.getBuffer();
    };
    Calc.prototype.getPre = function (X) {
        switch (X) {
            case 100:
            case 200:
                return 1;
            case 300:
            case 400:
                return 2;
        }
    };
    return Calc;
}());
var obj = new Calc();
