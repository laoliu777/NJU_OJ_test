package ojtest;
/*
订单问题：
Rahul和Ankit是皇家餐厅仅有的两位服务员。
今天，餐厅收到了N份订单。不同的服务员给的小费可能会不同，如果Rahul点了第i个菜，他会给Ai卢比，如果Ankit点了这个菜，小费是Bi卢比。
为了使小费总额最大化，他们决定把订单分配给他们自己。一个订单只会由一个人处理。
此外，由于时间限制，Rahul不能接受超过X个订单，Ankit不能接受超过Y个订单。
可以保证X + Y大于或等于N，这意味着所有的订单都可以由Rahul或Ankit处理。在处理完所有的订单后，找出小费总额的最大值。
Input
• The first line contains one integer, number of test cases.
• The second line contains three integers N, X, Y.
• The third line contains N integers. The ith integer represents Ai.
• The fourth line contains N integers. The ith integer represents Bi.
Sample Input 1
1
5 3 3
1 2 3 4 5
5 4 3 2 1
Sample Output 1
21
上面最终选的是5 4 3 4 5，和为21
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main4_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int tnum=Integer.parseInt(in.nextLine());
        for(int tcase=0;tcase<tnum;tcase++){
            if(tcase>0)
                System.out.println();
            String []str1=in.nextLine().split(" ");
            int n=Integer.parseInt(str1[0]);
            int x=Integer.parseInt(str1[1]);
            int y=Integer.parseInt(str1[2]);
            String []str2=in.nextLine().split(" ");
            String []str3=in.nextLine().split(" ");
            int[] A=new int[n];
            int[] B=new int[n];
            for(int j=0;j<n;j++){
                A[j]= Integer.parseInt(str2[j]);
                B[j]= Integer.parseInt(str3[j]);
            }
            int res = MaxArr(A,B,x,y);
            System.out.print(res);
        }
        in.close();
    }

    private static int MaxArr(int[] A, int[] B, int X, int Y) {
        int N=A.length;
        //核心思想：通过二维数组来记录每取i个A和j个B的元素时，得到的和最大是多少？
        //动态规划： 当i+j没满总数时，看原本（取i-1个A和j个B时）再加入A的最新元素，和B比谁大？  d(i,j)= max(d(i-1,j)+A(i+j-1),d(i,j-1)+B(i+j-1))
        //            当i+j满了时，说明数量已分配完毕，正好取满了n个元素，够了，不能再加元素了，直接比较前面d(i-1,j)和d(i,j-1)谁大即可
        int dp[][]=new int[X+1][Y+1];
        for(int i=1;i<=X;i++)
            dp[i][0]= A[i-1] + dp[i-1][0];
        for(int i=1;i<=Y;i++)
            dp[0][i]= B[i-1] + dp[0][i-1];

        for(int i=1;i<=X;i++)
        {
            for(int j=1;j<=Y;j++)
            {
                if(i+j <= N)
                {
                    int op1= A[i+j-1] + dp[i-1][j]; //取i-1个A和j个B时的最大值，加上此时指针指向的A元素
                    int op2= B[i+j-1] + dp[i][j-1]; //取i个A和j-1个B时的最大值，加上此时指针指向的B元素
                    dp[i][j] = Math.max( op1 , op2 );
                }
                else
                    dp[i][j] = Math.max( dp[i-1][j] , dp[i][j-1] );
            }
        }
        return dp[X][Y];
    }

}
