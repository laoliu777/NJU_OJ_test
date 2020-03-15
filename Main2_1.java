package ojtest;

/*
数学公式：
求A^B%C:
(A*B)%N=(A%N * B%N)%N
本题变为(A^(N/2)*A^(N/2))
Sample Input 1
3
3 2 4
10 9 6
450 768 517
Sample Output 1
1
4
34
*/


import java.math.BigInteger;
import java.util.Scanner;

public class Main2_1 {
    static int power(int x, int y, int p) {
        // Initialize result
        int res = 1;

        for (int i = 0; i < y; i++) {
            res *= x;
            res %= p;
        }
        return res;
    }

    // Driver Program to test above functions
    public static void main(String args[])
    {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        while (N-- > 0) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int p = sc.nextInt();
            System.out.println(power(x, y, p));
        }
    }

}
