package ru.neoflex;

public class NumberFactorial {
    private int[] number;

    public NumberFactorial(int[] number) {
        this.number = number;
    }

    public NumberFactorial(NumberFactorial n) {
        this.number = n.getNumber();
    }

    public NumberFactorial(int n) {
        int k = n;
        int count = n == 0 ? 1 : 0;
        while (k > 0) {
            k = k / 10;
            count++;
        }
        int[] numbers = new int[count];
        for (int i = count - 1; i >= 0; i--) {
            numbers[i] = n % 10;
            n /= 10;
        }
        setNumber(numbers);
    }

    public int[] getNumber() {
        return number;
    }

    public void setNumber(int[] number) {
        this.number = number;
    }

    public NumberFactorial multiply(NumberFactorial n) {
        int[] big;
        int[] little;
        if (getNumber().length > n.getNumber().length) {
            big = getNumber();
            little = n.getNumber();
        } else {
            big = n.getNumber();
            little = getNumber();
        }
        NumberFactorial resultNumberFactorial = new NumberFactorial(0);
        for (int i = little.length - 1; i >= 0; i--) {
            int i1 = big.length + little.length - 1 - i;
            int[] result = new int[i1];
            for (int j = big.length - 1; j >= 0; j--) {
                result[j] = little[i] * big[j];
            }
            for (int j = i1 - 1; j > big.length - 1; j--) {
                result[j] = 0;
            }
            resultNumberFactorial = resultNumberFactorial.sum(new NumberFactorial(result));

        }
        return resultNumberFactorial;
    }

    public NumberFactorial sum(NumberFactorial n) {
        int[] big;
        int[] little;
        if (getNumber().length > n.getNumber().length) {
            big = getNumber();
            little = n.getNumber();
        } else {
            big = n.getNumber();
            little = getNumber();
        }
        boolean flag = false;
        for (int i = big.length - 1, j = little.length - 1; i >= big.length - little.length && j >= 0; i--) {
            big[i] += little[j--];
            if (big[0] > 9) flag = true;
        }
        int[] result;
        int count;
        if (flag) {
            result = new int[big.length + 1];
            count = big.length + 1;
        } else {
            result = new int[big.length];
            count = big.length;
        }
        for (int i = count - 1, j = big.length - 1; i >= 0; i--) {
            if (j >= 0) {
                result[i] = big[j--];
            } else {
                result[i] = 0;
            }

        }
        for (int i = count - 1; i > 0; i--) {
            if (result[i] < 10) result[i] = result[i];
            else {
                result[i - 1] += result[i] / 10;
                result[i] = result[i] % 10;
            }
        }
        return new NumberFactorial(result);

    }

    public NumberFactorial difference(NumberFactorial n) {
        int[] nNumbers = n.getNumber();
        int[] result = new int[number.length];
   /* for (int i = 0; i < number.length; i++) {
      result[i] = 0;
    }*/
        for (int i = number.length - 1, j = nNumbers.length - 1; i >= 0; i--) {
            if (j < 0) {
                result[i] = number[i];
            } else {
                result[i] = number[i] - nNumbers[j--];
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
            int count = 0;
            int i = 0;
            while (i < number.length && result[i] == 0) {
                count++;
                i++;
            }
            resultWithoutZero = new int[number.length - count];
            for (int j = 0; j < number.length - count; j++) {
                resultWithoutZero[j] = result[j + count];
            }
        }
        return new NumberFactorial(resultWithoutZero);
    }

    public boolean equals(NumberFactorial n) {
        if (this.number.length != n.getNumber().length) return false;
        else {
            boolean flag = true;
            int i = 0;
            while (i < this.number.length && flag) {
                flag = this.number[i] == n.getNumber()[i];
                i++;
            }
            return flag;
        }
    }

    public void showNumber() {
        for (int i = 0; i < number.length; i++) {
            System.out.print(number[i]);
        }
        System.out.println();
    }


}
