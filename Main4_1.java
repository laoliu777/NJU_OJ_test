package ojtest;
/*
数组查询
给定一个数组，任务是完成查找最大和子数组的函数，在这个函数中，可以删除最多一个元素来获得最大和。
Given an array, the task is to complete the function which finds the maximum sum subarray, where you may remove at most one element to get the maximum sum.
Sample Input 1
1
5
1 2 3 -4 5
Sample Output 1
11
 */
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main4_1 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int tnum= Integer.parseInt(in.nextLine());
        for(int tcase=0;tcase<tnum;tcase++){
            if(tcase>0) System.out.println();
            int length=Integer.parseInt(in.nextLine());
            String[] str=new String[length];
            int[] arr=new int[length];
            str=in.nextLine().split(" ");
            for(int j=0;j<length;j++){
                arr[j]= Integer.parseInt(str[j]);
            }
            //开始解答
            //int res=maxSubArraySum(arr);
            int res=maxSumSubarrayRemovingOneEle(arr,length);
            System.out.print(res);
        }
        in.close();
    }

    /*
    先看我们怎么求连续子数组的最大和的？
    Kadane算法又被称为扫描法，为动态规划（dynamic programming）的一个典型应用。
    我们用DP来解决最大子数组和问题：对于数组a，用ci标记子数组a[0..i]的最大和，那么则有ci=max{ai,ci−1+ai}，子数组最大和即为max{ci}。
    Kadane算法比上面DP更进一步，不需要用一个数组来记录中间子数组和。通过观察容易得到：
    若ci−1≤0，则ci=ai。用e表示以当前为结束的子数组的最大和，以替代数组c；那么e=max{ai,e+ai}
    换种理解方法：
    Kadane算法的简单思想是查找数组的所有正的连续段（这里使用max_ending_here）。
    并跟踪所有正段之间的最大和连续段（使用max_so_far）。
    每次我们得到一个正和，将它与max_so_far比较，如果它大于max_so_far，则更新max_so_far。
    然后求可以删一个的话，考虑法2，
    法1行不通！
     */
    public static int maxSubArraySum(int a[]) {
        int max_so_far = a[0];
        int max_ending_here = 0;
        int min=0;
        int size = a.length;

        for (int i = 0; i < size; i++) {
            max_ending_here = max_ending_here + a[i];
            if(a[i]<min)
                min=a[i];
            if (max_ending_here < 0) {
                max_ending_here = 0;
                min = 0;
            }
            else if (max_so_far < max_ending_here-min) {
                max_so_far = max_ending_here-min;
            }
        }
        return max_so_far;
    }

    //法2
    public static int maxSumSubarrayRemovingOneEle(int arr[], int n) {
        //fw表示从前往后遍历的连续数组的最大和
        int fw[] = new int[n];
        //bw表示从后往前遍历的连续数组的最大和
        int bw[] = new int[n];
        //先求出fw的每一位的从前面到该位的连续最大和
        int cur_max = arr[0], max_so_far = arr[0];
        fw[0] = arr[0];
        for (int i = 1; i < n; i++) {
            cur_max = Math.max(arr[i], cur_max + arr[i]);
            max_so_far = Math.max(max_so_far, cur_max);
            fw[i] = cur_max;
        }
        //再求出bw从尾部开始每位的最大值
        //先把cur_max，max_so_far，bw[n - 1]都变为arr[n - 1]
        cur_max = arr[n - 1];
        max_so_far = arr[n - 1];
        bw[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            cur_max = Math.max(arr[i], cur_max + arr[i]);
            max_so_far = Math.max(max_so_far, cur_max);
            bw[i] = cur_max;
        }
        //得到从前 以及 从后 两边的每位置连续最大和，之后就比较 去掉第i位后的fw[i-1]+bw[i+1] 和 不删的已知最大值max_so_far
        int fans = max_so_far;
        for (int i = 1; i < n - 1; i++)
            fans = Math.max(fans, fw[i - 1] + bw[i + 1]);

        return fans;
    }


}
