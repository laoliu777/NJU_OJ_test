package ojtest;
/*
子数组的取值范围：
给定数组arr和整数num，求arr的连续子数组中满足：其最大值减去最小值的结果大于num的个数。请实现一个时间复杂度为O(length(arr))的算法。
Sample Input 1
1
3 6 4 3 2
2
Sample Output 1
6
 */
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class MainT1_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a=Integer.parseInt(in.nextLine());
        for(int i=0;i<a;i++){
            if(i>0) System.out.println();
            String []str=in.nextLine().split(" ");
            int []arr=new int[str.length];
            for(int j=0;j<str.length;j++){
                arr[j]=Integer.parseInt(str[j]);
            }
            int num=Integer.parseInt(in.nextLine());
            int result = partarray1(arr,num);
            System.out.print(result);
        }
        in.close();
    }

    //这个做法的问题是可能两端的两数不一定就是最大值和最小值！
    public static int partarray(int [] arr , int num)
    {
        int res=0;
        for(int i=0;i<arr.length-1;i++)
            for(int j=i+1;j<arr.length;j++)
                if (Math.abs(arr[i] - arr[j])>num){
                    res += arr.length-j;
                    break;
                }
        return res;
    }

    public static int partarray1(int[] arr,int num){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int res = 0;
        int i = 0;
        int j = 0;
        //两个双端队列，比如qmax开头记录最大的值，后面接着次大的值，每次一有新的从后面进去，遇一个小的就替换掉，直到遇到比它大的
        Deque<Integer> qmax = new LinkedList<>();
        Deque<Integer> qmin = new LinkedList<>();
        //int qmax=0;
        //int qmin=0;

        while(i < arr.length){
            while(j < arr.length){
                //每次窗口移动完成之后，开始重新确定窗口里的最大数最小数的队列
                //维护窗口最大值
                while(!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[j]){//最尾端的数小于当前数
                    qmax.pollLast();
                }
                //把前面小于当前数arr[j]的都删了，然后把他插尾部
                qmax.addLast(j);
                /*
                if(arr[qmax]<=arr[j])
                    qmax=j;
                 */
                //维护窗口最小值
                while(!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[j]){
                    qmin.pollLast();
                }
                qmin.addLast(j);
                 /*
                if(arr[qmin]>=arr[j])
                    qmin=j;
                  */
                 //最大值减最小值大于num
                if(arr[qmax.peekFirst()] - arr[qmin.peekFirst()] > num){
                //if(arr[qmax] - arr[qmin] <= num){
                    break;
                }
                //要是没有大于，没有break，那么j往后走一个
                j++;
            }
            //
            //res += j-i;
            //到第j个没问题的话，后面的肯定也满足
            res += arr.length-j;

            //由于窗口里第一个数i要变动，往后走，所以如果有i是最大值的话，得把最大数的那个队列清空，重新来一遍
            if(qmax.peekFirst() == i){
                qmax.poll();
            }
            if(qmin.peekFirst() == i){
                qmin.poll();
            }
            i++;
        }
        return res;
    }
}
