package ru.neoflex;

public class NumberFactorial {

    private static final int TEN = 10;
    private int[] number;

    public NumberFactorial(int[] number) {
        this.number = number;
    }

    public NumberFactorial(NumberFactorial numberFactorial) {
        this.number = numberFactorial.getNumber();
    }

    public NumberFactorial(int inputNumber) {
        int length = getLength(inputNumber);
        int[] numbers = new int[length];
        for (int i = length - 1; i >= 0; i--) {
            numbers[i] = inputNumber % 10;
            inputNumber /= 10;
        }
        setNumber(numbers);
    }

    private int getLength(int inputNumber) {
        if (inputNumber == 0) {
            return 1;
        } else {
            int numberLength = 0;
            while (inputNumber > 0) {
                inputNumber = inputNumber / TEN;
                numberLength++;
            }
            return numberLength;
        }
    }

    public int[] getNumber() {
        return number;
    }

    public void setNumber(int[] number) {
        this.number = number;
    }

    public NumberFactorial multiply(NumberFactorial inputNumber) {
        int[] big=getBiggest(number,inputNumber.getNumber());
        int[] little=getLess(number,inputNumber.getNumber());

        NumberFactorial resultNumberFactorial = new NumberFactorial(0);
        for (int i = little.length - 1; i >= 0; i--) {
            int lengthTemp = big.length + little.length - 1 - i;
            int[] tempResult = new int[lengthTemp];

            for (int j = big.length - 1; j >= 0; j--) {
                tempResult[j] = little[i] * big[j];
            }
            for (int j = lengthTemp - 1; j > big.length - 1; j--) {
                tempResult[j] = 0;
            }
            resultNumberFactorial = resultNumberFactorial.sum(new NumberFactorial(tempResult));

        }
        return resultNumberFactorial;
    }

    private int[] getBiggest(int[] number1, int[] number2){
        if (number1.length > number2.length) {
            return number1;
        } else {
            return number2;
        }
    }

    private int[] getLess(int[] number1, int[] number2){
        if (number1.length > number2.length) {
            return number2;
        } else {
            return number1;
        }
    }

    public NumberFactorial sum(NumberFactorial inputNumber) {
        int[] big=getBiggest(number,inputNumber.getNumber());
        int[] little=getLess(number,inputNumber.getNumber());

        boolean increaseCapacity = false;
        for (int i = big.length - 1, j = little.length - 1; i >= big.length - little.length && j >= 0; i--) {
            big[i] += little[j--];
            if (big[0] >= 10) {
                increaseCapacity = true;
            }
        }

        int[] result;
        if (increaseCapacity) {
            result = new int[big.length + 1];
        } else {
            result = new int[big.length];
        }

        for (int i = result.length - 1, j = big.length - 1; i >= 0; i--) {
            if (j >= 0) {
                result[i] = big[j--];
            } else {
                result[i] = 0;
            }
        }
        for (int i = result.length - 1; i > 0; i--) {
            if (result[i] >= 10) {
                result[i - 1] += result[i] / 10;
                result[i] = result[i] % 10;
            }
        }
        return new NumberFactorial(result);
    }

    public NumberFactorial difference(NumberFactorial numberFactorial) {
        int[] inputNumber = numberFactorial.getNumber();
        int[] result = new int[number.length];

        for (int i = number.length - 1, j = inputNumber.length - 1; i >= 0; i--) {
            if (j < 0) {
                result[i] = number[i];
            } else {
                result[i] = number[i] - inputNumber[j--];
            }
        }
        
        for (int i = number.length - 1; i >= 0; i--) {
            if (result[i] < 0 && i != 0) {
                result[i - 1]--;
                result[i] += 10;
            }
        }

        int[] resultWithoutZero;
        if (result[0] == 0 && result.length == 1) {
            resultWithoutZero = result;
        } else {
            int lengthWithoutZero = 0;
            int i = 0;
            while (i < number.length && result[i] == 0) {
                lengthWithoutZero++;
                i++;
            }

            resultWithoutZero = new int[number.length - lengthWithoutZero];
            for (int j = 0; j < number.length - lengthWithoutZero; j++) {
                resultWithoutZero[j] = result[j + lengthWithoutZero];
            }
        }
        return new NumberFactorial(resultWithoutZero);
    }

    public boolean equals(NumberFactorial n) {
        if (this.number.length != n.getNumber().length) return false;
        else {
            boolean digitsEquals = true;
            int i = 0;
            while (i < this.number.length && digitsEquals) {
                digitsEquals = this.number[i] == n.getNumber()[i];
                i++;
            }
            return digitsEquals;
        }
    }

    public void showNumber() {
        for (int i = 0; i < number.length; i++) {
            System.out.print(number[i]);
        }
        System.out.println();
    }

}
