package ojtest;
/*
如何花最少的钱购买蔬菜:
拉胡尔想购买蔬菜，主要是茄子，胡萝卜和番茄。 一行中有N个不同的蔬菜销售商。 每个蔬菜销售商出售所有三种蔬菜，但价格不同。
拉胡尔（Rahul）痴迷于最佳消费，因此决定不从附近的商店购买相同的蔬菜。
此外，Rahul将从一家商店购买一种蔬菜（仅1公斤）。 拉胡尔希望使用这种策略花费最少的钱购买蔬菜。 帮助拉胡尔确定他将花费的最低费用。
Sample Input 1  输入n行，每行3个数
1
3
1 50 50
50 50 50
1 50 50
Sample Output 1
52
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main4_4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int tnum=Integer.parseInt(in.nextLine());
        for(int tcase=0;tcase<tnum;tcase++){
            if(tcase>0)
                System.out.println();
            int n=Integer.parseInt(in.nextLine());
            int arr[][]=new int[n][3];
            for(int i=0;i<n;++i) {
                String[] line = in.nextLine().split(" ");
                arr[i] = Arrays.stream(line).mapToInt(Integer::parseInt).toArray();
            }
            System.out.print(minCost(arr,n));
        }
        in.close();
    }

    public static int minCost(int arr[][],int n)
    {
        int dp[][]=new int[n][3];
        for(int i=0;i<3;++i)
            dp[0][i]=arr[0][i];
        for(int i=1;i<n;++i)
            for(int j=0;j<3;++j)
                dp[i][j]=Math.min(getMin(dp[i-1],0,j-1),getMin(dp[i-1],j+1,2))+arr[i][j];
        int res=Integer.MAX_VALUE;
        for(int i=0;i<3;++i)
            if(dp[n-1][i]<res)
                res=dp[n-1][i];
        return res;
    }

    //找到数组从l开始到h里面的最小值
    public static int getMin(int arr[],int l,int h)
    {
        int res=Integer.MAX_VALUE;
        for(int i=l;i<=h;++i)
            if(arr[i]<res)
                res=arr[i];
        return res;
    }

}
