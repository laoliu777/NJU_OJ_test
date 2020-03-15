package ojtest;

/*
漆狗屋
Description
Dilpreet wants to paint his dog- Buzo's home that has n boards with different lengths[A1, A2,..., An]. He hired k painters for this work and each painter takes 1 unit time to paint 1 unit of the board.The problem is to find the minimum time to get this job done under the constraints that any painter will only paint continuous sections of boards, say board {2, 3, 4} or only board {1} or nothing but not board {2, 4, 5}.
Constraints:1<=T<=100,1<=k<=30,1<=n<=50,1<=A[i]<=500
Input
The first line consists of a single integer T, the number of test cases. For each test case, the first line contains an integer k denoting the number of painters and integer n denoting the number of boards. Next line contains n- space separated integers denoting the size of boards.
Output
For each test case, the output is an integer displaying the minimum time for painting that house.
Sample Input 1
2
2 4
10 10 10 10
2 4
10 20 30 40
Sample Output 1
20
60
找到连续的数组，分割成k个子数组，要使得每个连续子数组的和里面最大的一个最小
比如，10 20 30 40，3：分成60和40，输出60
 */

import java.util.Scanner;

public class Main2_3 {
    public static int minmaxseq(int[] arr,int k){
        int max=0;//记录数组中最大值
        int total=0;//记录总的和
        //那么最小的最大子数组和肯定介于两者之间，于是采用二分法来求解，到底是多少？
        for (int i = 0; i < arr.length; i++){
            total += arr[i];
            if (max < arr[i]){
                max = arr[i];
            }
        }

        int start=max;
        int end=total;
        int mid;
        //终止条件：1，start==end; 2,start+1=end
        while(start+1<end){
            mid=start+(end-start)/2;
            if(neednum(arr,mid)>k){
                start = mid;
            }else{
                end = mid;
            }
        }
        //当start是k个人的时候，不论是start=end还是，此时肯定正好是k个人
        if (neednum(arr, start) <= k){
            return start;
        } else {//当start>k个人时，必然是start+1=end且end是k个人的情况，采用end
            return end;
        }
    }

    //这里记录当最大和是limit，至少需要的人数，
    // 方法就是如果sum + arr[i] > limit就说明需要新加人数，并且将sum重置为0。
    public static int neednum(int[] arr, int limit){
        int numCount = 1;
        int sum = arr[0];
        for (int i = 1; i < arr.length; i++){
            //最慢的那个人完成不了任务，新增人数
            if (sum + arr[i] > limit){
                numCount++;
                sum = 0;
            }
            sum += arr[i];
        }
        return numCount;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a=Integer.parseInt(in.nextLine());
        for(int i=0;i<a;i++){
            if(i>0) System.out.println();
            String []str0=in.nextLine().split(" ");
            int k=Integer.parseInt(str0[0]);
            int length=Integer.parseInt(str0[1]);
            String []str1=in.nextLine().split(" ");
            int []arr=new int[length];
            for(int j=0;j<length;j++){
                arr[j]=Integer.parseInt(str1[j]);
            }
            int result = minmaxseq(arr,k);
            System.out.print(result);
        }
        in.close();
    }

}
