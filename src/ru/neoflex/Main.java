package ru.neoflex;

public class Main {
    public static void main(String[] args) {
        factorial(new NumberFactorial(1000)).showNumber();
    }

    public static NumberFactorial factorial(NumberFactorial n) {
        return n.equals(new NumberFactorial(0)) ?
                new NumberFactorial(1) :
                n.multiply(factorial
                        (new NumberFactorial(n.difference(new NumberFactorial(1)))));
    }
}
