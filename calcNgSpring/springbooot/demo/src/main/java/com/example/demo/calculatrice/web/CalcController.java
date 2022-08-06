package com.example.demo.calculatrice.web;

import com.example.demo.calculatrice.model.Operators;
import com.example.demo.calculatrice.service.Calc;
import org.springframework.web.bind.annotation.*;

@RestController
public class CalcController {
    Calc c = new Calc();

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/adddigit/{val}")
    public String takeDigit(@PathVariable int val) {

        return c.addDigit(val);

    }

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/setoperator/{op}")
    public String takeOP(@PathVariable String op) {
        switch (op) {
            case "+":
                return c.setOperator(Operators.ADD);
            case "-":
                return c.setOperator(Operators.MINUS);
            case "*":
                return c.setOperator(Operators.MULTIPLE);
            case "/":
                return c.setOperator(Operators.DIVIDE);
            default:
                return "Error boomer";
        }


    }

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/equal")
    public String displayEQ() {
        return c.equals();
    }

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/clear")
    public String clearIT() {
        return c.clear();
    }
}
