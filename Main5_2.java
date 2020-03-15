package ojtest;
/*
时间分隔
Given arrival and departure times of all trains that reach a railway station. Your task is to find the minimum number of platforms required for the railway station so that no train waits.
Note: Consider that all the trains arrive on the same day and leave on the same day. Also, arrival and departure times must not be same for a train.
Input
The first line of input contains T, the number of test cases. For each test case, first line will contain an integer N, the number of trains. Next two lines will consist of N space separated time intervals denoting arrival and departure times respectively.
Note: Time intervals are in the 24-hourformat(hhmm), preceding zeros are insignificant. 200 means 2:00.
Consider the example for better understanding of input.
Constraints:1 <= T <= 100，1 <= N <= 1000，1 <= A[i] < D[i] <= 2359
Output
For each test case, print the minimum number of platforms required for the trains to arrive and depart safely.
Sample Input 1
1
6
900  940 950  1100 1500 1800
910 1200 1120 1130 1900 2000
Sample Output 1
3
给定到达火车站的所有火车的到达和离开时间。 您的任务是找到火车站所需的最少平台数量，以便没有火车等待。
注意：请注意，所有火车都在同一天到达，并在同一天出发。 另外，火车的到达和离开时间不得相同。

 */

import java.util.Arrays;
import java.util.Scanner;

public class Main5_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int tnum=Integer.parseInt(in.nextLine());
        for(int tcase=0;tcase<tnum;tcase++){
            if(tcase>0)
                System.out.println();
            int n=Integer.parseInt(in.nextLine());
            String []str1=in.nextLine().split(" ");
            String []str2=in.nextLine().split(" ");
            //A是达到时间，B是离开时间
            int[] A=new int[n];
            int[] B=new int[n];
            for(int j=0;j<n;j++){
                A[j]= Integer.parseInt(str1[j]);
                B[j]= Integer.parseInt(str2[j]);
            }
            int res = minPlatform(A,B);
            System.out.print(res);
        }
        in.close();
    }

    private static int minPlatform(int[] A, int[] B) {
        int n=A.length;
        int[] AB=new int[n*2];
        for(int j=0;j<n;j++){
            AB[j]= A[j];
            AB[j+n]= B[j];
        }
        Arrays.sort(AB);
        int cur=0;//记录当前这时间点需要的车站数
        int max=0;//记录全程需要的
        for(int i=0;i<n*2;i++){
            cur=0;
            for(int j=0;j<n;j++){
                if(AB[i]>=A[j]&&AB[i]<=B[j])
                    cur++;
            }
            if(cur>max)
                max=cur;
        }
        return max;
    }
}
