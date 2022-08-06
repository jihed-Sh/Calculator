
class Stack<T>  {
    private storage: T[] = [];
  
    constructor(private capacity: number = Infinity) {}
  
    push(item: T): void {
      if (this.size() === this.capacity) {
        throw Error("Stack has reached max capacity, you cannot add more items");
      }
      this.storage.push(item);
    }
  
    pop(): T | undefined {
      return this.storage.pop();
    }
  
    peek(): T | undefined {
      return this.storage[this.size() - 1];
    }
  
    size(): number {
      return this.storage.length;
    }
    isEmpty(): boolean {
        if(this.size()>0)
          return true;
          
      
        return false
      
    }
    clear(): void {
     while(!this.isEmpty()){
       this.pop();
     }
    }
  }




class Calc {
    
     buffer:string="";
      wasOperator:boolean=false;
     digits = new Stack<number>();
    operators= new Stack<number>();//changable to opertators enums
  
  
    getBuffer(): string {
        if (this.buffer.length != 0) {
            return this.buffer.toString();
        }
        if (!this.digits.isEmpty()) {
            return "" + this.digits.peek();
        }
        return "";
    }
     clearBuffer() : void {
         this.buffer="";
        
    }

     addDigit(digit:number):String{
         
        if (this.wasOperator) {
            this.clearBuffer();
        }
        this.wasOperator = false;
        this.buffer=this.buffer+digit;
        
        document.getElementById("interface")!.nodeValue = this.buffer;
        return this.getBuffer();
    }
    

    setOperator( op:number): String{

        if (this.buffer.length!=0) {
            if (this.wasOperator) {
                this.operators.pop();//changable to opertators enums
            } 
            else 
            {
                this.wasOperator = true;
            }
            this.digits.push(parseInt(this.buffer)); 
                while (this.operators.size()>0&&(this.getPre(this.operators.peek()!)>this.getPre(op))) { //changable to opertators enums
                    this.reduce();
                }
            this.operators.push(op);//changable to opertators enums
            
            
            this.clearBuffer();
        }
        return this.getBuffer();
    }
    
    
    
    clear() : String{
        this.clearBuffer();
        this.wasOperator = false;
        this.digits.clear();
        this.operators.clear();
        document.getElementById("interface")!.nodeValue=this.buffer;//changable to opertators enums
        return this.getBuffer();
    }
     reduce() : void {
        while (this.operators.size()>0) {
            var o:number = this.operators.pop()!;
            var b:number = this.digits.pop()!;
            if(this.digits.size()==0){
                this.digits.push(b);
            }
            var a:number = this.digits.pop()!;
            switch (o) {
                case 100: {//changable to opertators enums
                    this.digits.push(a + b);
                    break;
                }
                case 300: {//changable to opertators enums
                    this.digits.push(a / b);
                    break;
                }
                case 200: {//changable to opertators enums
                    this.digits.push(a - b);
                    break;
                }
                case 400: {//changable to opertators enums
                    this.digits.push(a * b);
                    break;
                }
            }
        }
        
    }
     equals(): string {
        if (this.buffer.length != 0) {
            this.digits.push(parseInt(this.buffer.toString()));
            this.clearBuffer();
        }
        this.wasOperator = false;
        
        while (this.digits.size()>1) {
            this.reduce();
            
        }

        document.getElementById("interface")!.nodeValue = this.buffer;//change it to peek in js
        return this.getBuffer();
    }
    getPre(X:number){
        switch(X){
            case 100:
            case 200:
                return 1;
            case 300:
            case 400:
                return 2;
                default:return 0;
        }
    }
    
        
   
    
    
    
   
     
  
}
var obj=new Calc();
