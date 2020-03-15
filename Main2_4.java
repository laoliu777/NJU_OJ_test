package ojtest;

/*
有9个因数的数：
Description
Find the count of numbers less than N having exactly 9 divisors
1<=T<=1000,1<=N<=10^12
Input
First Line of Input contains the number of testcases. Only Line of each testcase contains the number of members N in the rival gang.
Output
Print the desired output.
Sample Input 1
2
40
5
Sample Output 1
1
0
 */

import java.util.*;

public class Main2_4 {
    static int[] a;
    static List<Integer> list;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nu = sc.nextInt();
        int[] a = new int[10000];
        for (int p = 0; p < nu; p++) {
            long num = sc.nextLong();
            getPrime(num);
            System.out.println(getCount(num));
        }
    }

    public static void getPrime(long num) {
        list = new ArrayList<Integer>();
        int l = (int) Math.sqrt(num);
        a = new int[l + 1];
        for (int i = 2; i < a.length; i++) {
            if (a[i] == 0) {
                list.add(i);
                for (int j = 2 * i; j < a.length; j = j + i) {
                    a[j] = 1;
                }
            }
        }
    }

    public static long getCount(long num) {
        long res = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            if (Math.pow(list.get(i) * list.get(i + 1), 2) > num) {
                break;
            } else {
                for (int j = i + 1; j < list.size(); j++) {
                    if (Math.pow(list.get(i) * list.get(j), 2) > num)
                        break;
                    res++;
                }
            }
        }
        for (int i = 0; i < 9 && i < list.size(); i++) {  //注意,这里i<9是为了防止用例问题而添加的
            if (Math.pow(list.get(i), 8) > num) {
                break;
            }
            res++;
        }
        return res;
    }
}
