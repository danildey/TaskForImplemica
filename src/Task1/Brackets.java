package Task1;

import java.util.Scanner;

public class Brackets {


    public Brackets() {

    }
    public Brackets(int n) {
        System.out.println("result :\n"+getNumberOfPossibleCombinations(n));
        generateBrackets(n,0,0,new char[n*2]);
    }

    // This method based on catalan numbers
    public int getNumberOfPossibleCombinations(int n) {
        int res = 0;

        // Base case
        if (n <= 1) {
            return 1;
        }
        for (int i = 0; i < n; i++) {
            res += getNumberOfPossibleCombinations(i)
                    * getNumberOfPossibleCombinations(n - i - 1);
        }
        return res;
    }



    public void generateBrackets(int openStock, int closeStock, int index, char[] arr) {
        while (closeStock >= 0) {
            if (openStock > 0) {
                arr[index] = '(';
                generateBrackets(openStock-1, closeStock+1, index+1, arr);
            }
            if (closeStock-- > 0) {
                arr[index++] = ')';
                if (index == arr.length) {
                    System.out.println(arr);
                }
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("Enter the number of the calculation : ");
        Scanner scan = new Scanner(System.in);
        int input= scan.nextInt();

        scan.close();
        if(input>0){
            Brackets brackets = new Brackets(input);
        }else{
            System.out.println("Not the correct number");
        }

    }
}
