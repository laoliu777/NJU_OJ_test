package ojtest;
/*
格子里的整数
描述
给定一个大小为n的正方形网格，其中每个单元格都包含整数成本，代表通过该单元格所要经过的成本，我们需要找到一条从左上角单元格到右下角单元格的路径，由此产生的总成本最小。
注：假定输入矩阵中不存在负成本周期。
输入
The first line of input will contain number of test cases T. Then T test cases follow . Each test case contains 2 lines. The first line of each test case contains an integer n denoting the size of the grid. Next line of each test contains a single line containing N*N space separated integers depecting cost of respective cell from (0,0) to (n,n).
Constraints:1<=T<=50，1<= n<= 50
输出
For each test case output a single integer depecting the minimum cost to reach the destination.
输入样例 1
2
5
31 100 65 12 18 10 13 47 157 6 100 113 174 11 33 88 124 41 20 140 99 32 111 41 20
2
42 93 7 14
输出样例 1
327
63
*/
import java.util.Scanner;

public class Main5_6 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int times=Integer.parseInt(sc.nextLine());
        while(times>0) {
            int n=Integer.parseInt(sc.nextLine());
            String[] sarr=sc.nextLine().split(" ");
            int[][] arr=new int[n][n];
            for(int i=0;i<sarr.length;i++) {
                arr[i/n][i%n]=Integer.parseInt(sarr[i]);

            }
            handle(arr);
            times--;
        }
    }
    static int min=Integer.MAX_VALUE;
    public static void handle(int[][] arr) {
        int[][] direction= {{0,1},  //往右
                {1,0}   //往下
        };

        int[] start= {0,0};
        int[] stop= {arr.length-1,arr.length-1};
        int value=arr[0][0];
        dfs(arr,direction,start,stop,value);

        System.out.println(min);
        min=Integer.MAX_VALUE;

    }

    public static void dfs(int[][] arr,int[][] direction,int[] start,int[] stop,int value) {

        if(start[0]==stop[0]&&start[1]==stop[1]) {
            //对value进行处理
            if(value<min) {
                min=value;
            }
        }
        else {
            for(int i=0;i<direction.length;i++) {
                int[] dir=direction[i];

                if(start[0]+dir[0]<arr[0].length&&start[1]+dir[1]<arr.length) {
                    start[0]=start[0]+dir[0];
                    start[1]=start[1]+dir[1];
                    value+=arr[start[0]][start[1]];
                    dfs(arr, direction, start, stop, value);
                    value-=arr[start[0]][start[1]];
                    start[0]=start[0]-dir[0];
                    start[1]=start[1]-dir[1];
                }
            }
        }
    }
}
