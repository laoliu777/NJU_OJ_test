package ojtest;
/*
硬币最小数量
Given the list of coins of distinct denominations and total amount of money. Output the minimum number of coins required to make up that amount. Output -1 if that money cannot be made up using given coins. You may assume that there are infinite numbers of coins of each type.
Input
The first line contains 'T' denoting the number of test cases. Then follows description of test cases. Each cases begins with the two space separated integers 'n' and 'amount' denoting the total number of distinct coins and total amount of money respectively. The second line contains N space-separated integers A1, A2, ..., AN denoting the values of coins.
Constraints:1<=T<=30，1<=n<=100，1<=Ai<=1000，1<=amount<=100000
Output
Print the minimum number of coins required to make up that amount or return -1 if it is impossible to make that amount using given coins.
Sample Input 1
2
3 11
1 2 5
2 7
2 6
Sample Output 1
3
-1
贪心算法求最少硬币个数，比如第一组：3种硬币，凑出11元，最少3个
 */


import java.util.Arrays;
import java.util.Scanner;

public class Main5_4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int tnum=Integer.parseInt(in.nextLine());
        for(int tcase=0;tcase<tnum;tcase++){
            if(tcase>0)
                System.out.println();
            String []str1=in.nextLine().split(" ");
            int n=Integer.parseInt(str1[0]);
            int total=Integer.parseInt(str1[1]);
            String []str2=in.nextLine().split(" ");
            int[] coins=new int[n];
            for(int j=0;j<n;j++){
                coins[j]= Integer.parseInt(str2[j]);
            }
            //写一个从大到小排序！
            int res = getMinNeedOfCoins(coins,total);
            System.out.print(res);
        }
        in.close();
    }

    // 得到所需最少硬币数
    public static int getMinNeedOfCoins(int[] coins, int finalTarget) {
        int[] dp = new int[finalTarget + 1];
        for (int target = 1; target <= finalTarget; target++) {
            int coinsNeeded = Integer.MAX_VALUE;
            for (int i = 0; i < coins.length; i++) {
                if (coins[i] <= target && dp[target - coins[i]] != -1) {
                    // 当前面额比目标金额少 且 使用该面额后的钱数是可以兑换的
                    coinsNeeded = Math.min(coinsNeeded, dp[target-coins[i]] + 1);    // 原来兑换所需个数 vs 用当前面额所需个数
                }
            }
            // 判断可否兑换
            if (coinsNeeded == Integer.MAX_VALUE) {
                dp[target] = -1;
            } else {
                dp[target] = coinsNeeded;
            }
        }
        return dp[finalTarget];
    }
}
