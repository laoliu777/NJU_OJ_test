package ojtest;
/*
时间与收益
Given a set of n jobs where each job i has a deadline and profit associated to it. Each job takes 1 unit of time to complete and only one job can be scheduled at a time. We earn the profit if and only if the job is completed by its deadline. The task is to find the maximum profit and the number of jobs done.
Input
The first line of input contains an integer T denoting the number of test cases.Each test case consist of an integer N denoting the number of jobs and the next line consist of Job id, Deadline and the Profit associated to that Job.
Constraints:1<=T<=100，1<=N<=100，1<=Deadline<=100，1<=Profit<=500
Output
Output the number of jobs done and the maximum profit.
Sample Input 1
2
4
1 4 20 2 1 10 3 1 40 4 1 30
5
1 2 100 2 1 19 3 2 27 4 1 25 5 1 15
Sample Output 1
2 60
2 127
给定一组n个职位，其中每个职位i都有一个截止日期和与之相关的利润。 每个作业需要1个时间单位才能完成，一次只能安排一个作业。
当且仅当工作在截止日期之前完成时，我们才能赚取利润。 任务是找到最大的利润和完成的工作数量。
第一组：有4个工作，Job1在4h前完成，利润20，Job2在1h前完成，利润10，。。。
第1h先挑最赚的3完成，然后第2h只有1了，完成后，共2个工作，利润和60
 */

import java.util.Scanner;

public class Main5_3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int tnum=Integer.parseInt(in.nextLine());
        for(int tcase=0;tcase<tnum;tcase++){
            if(tcase>0)
                System.out.println();
            int n=Integer.parseInt(in.nextLine());
            String []str=in.nextLine().split(" ");
            //deadline是截止时间，profit利润
            int[] deadline=new int[n];
            int[] profit=new int[n];
            for(int j=0;j<n;j++){
                deadline[j]= Integer.parseInt(str[j*3+1]);
                profit[j]= Integer.parseInt(str[j*3+2]);
            }
            String res = maxDP(deadline,profit);
            System.out.print(res);
        }
        in.close();
    }

    private static String maxDP(int[] deadline, int[] profit) {
        int n=deadline.length;
        for(int i=0;i<n-1;i++) {
            for (int j = i+1; j < n; j++) {
                if ((profit[i] < profit[j]) || (profit[i] == profit[j] && deadline[i] < deadline[j])) {
                    int temp1 = deadline[j];
                    deadline[j] = deadline[i];
                    deadline[i] = temp1;

                    int temp2=profit[j];
                    profit[j] = profit[i];
                    profit[i] = temp2;
                }
            }
        }
        int totalProfit = 0;
        int count = 0;
        boolean[] isSpent = new boolean[n];//判断每个位置是不是占用了
        for (int i = 0; i < n; i++) {
            for (int j = Math.min(n, deadline[i]) - 1; j >= 0; j--) {
                if (isSpent[j] == false) {
                    isSpent[j] = true;
                    totalProfit += profit[i];
                    count++;
                    break;
                }
            }
        }

        return count+" "+totalProfit;
    }

}

