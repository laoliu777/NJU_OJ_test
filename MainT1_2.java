package ojtest;
/*
子矩阵问题
给定一个矩形区域，每一个位置上都是1或0，求该矩阵中每一个位置上都是1的最大子矩形区域中的1的个数。
Input
输入第一行为测试用例个数。每一个用例有若干行，第一行为矩阵行数n和列数m，下面的n行每一行是用空格隔开的0或1。
Output
输出一个数值。
用例：
输入
1
3 4
1 0 1 1
1 1 1 1
1 1 1 0
输出
6
 */
import java.util.Scanner;
import java.util.Stack;

public class MainT1_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a=Integer.parseInt(in.nextLine());
        for(int i=0;i<a;i++){
            if(i>0) System.out.println();
            String []str1=in.nextLine().split(" ");
            int m=Integer.parseInt(str1[0]);//行
            int n=Integer.parseInt(str1[1]);//列
            int [][]arr=new int[m][n];
            for(int p=0;p<m;p++){
                String []str=in.nextLine().split(" ");
                for(int q=0;q<n;q++)
                    arr[p][q]=Integer.parseInt(str[q]);
            }
            //法一
            int result = countmax(arr);
            System.out.print(result);
            /*
            法二：
            int[] h = new int[n];
            int max = 0;
            for(int i=0; i<m; i++) {
                for(int j=0; j<n; j++) {
                    int tip = input.nextInt();
                    if(i>0 && rec[i-1][j]!=0 && tip!=0) {
                        rec[i][j] = rec[i-1][j] + 1;
                        h[j] = rec[i][j];
                    }
                    else{
                        rec[i][j] = tip;
                        h[j] = tip;
                    }
                }
                max = Math.max(max, LargestRectangleArea(h));
            }
            System.out.println(max);
             */
        }
        in.close();
    }

    //法一
    public static int countmax(int [][] arr)
    {
        int max=0;
        int m=arr.length;
        int n=arr[0].length;
        int count=0;
        boolean break_flag=false;
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++) {
                for (int p = i; p < m; p++)
                    for (int q = j; q < n; q++) {
                        count = 0;
                        break_flag = false;
                        for (int a = i; a < p + 1; a++) {
                            for (int b = j; b < q + 1; b++) {
                                if (arr[a][b] != 1) {
                                    break_flag = true;
                                    break;
                                }
                                count++;
                                if (a == p && b == q)
                                    if (max < count)
                                        max = count;
                            }
                            if (break_flag == true)
                                break;
                        }
                        if (break_flag == true)
                            break;
                    }
                if (break_flag == true)
                    break;
            }
        return max;
    }


    //法二
    public static int LargestRectangleArea(int[] height){
        if (height.length==0) return 0;
        Stack<Integer> stack = new Stack<Integer>();
        int i=1, max = height[0];
        stack.push(0);

        while(i< height.length||(i==height.length&& !stack.isEmpty()) ){
            if(i!=height.length && (stack.isEmpty() ||height[i] >= height[stack.peek()])){
                stack.push(i);
                i++;
            }
            else {
                int top  = height[stack.pop()];
                int currMax = !stack.isEmpty()? top *(i - stack.peek()-1): top *i;
                max = Math.max(currMax, max);
            }
        }

        return max;
    }
}

