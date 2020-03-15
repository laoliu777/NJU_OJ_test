package ojtest;
/*
数组和窗口：
给定一个整型数组arr和一个大小为w的窗口，窗口从数组最左边滑动到最右边，每次向右滑动一个位置，求出每一次滑动时窗口内最大元素的和。
Input
输入第一行为用例个数， 每个测试用例输入的第一行为数组，每一个元素使用空格隔开；第二行为窗口大小。
Output
输出每个测试用例结果。
用例：
输入
 1
 4 3 5 4 3 3 6 7
 3
 输出
 32
*/

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class MainT1_3 {
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
            int w=Integer.parseInt(in.nextLine());
            int [] result = new int[str.length-w+1] ;
            //法一
            MainT1_3 class7 = new MainT1_3() ;
            result = class7.huachuang(arr,w,result);
            int res=0;
            for(int j=0;j<result.length;j++)
            {
                res+=result[j];
            }
            System.out.print(res);
            //法二
            /*
            solu2(w,arr)
             */
        }
        in.close();
    }

    //法一
    public int [] huachuang(int [] arr , int w,int [] result)
    {
        int [] arrw = new int[w] ;
        for (int i=0; i<=arr.length-w; i++)
        {
            for (int j=i ;j<w+i ;j++ )
            {
                arrw[j-i] = arr[j] ;
            }
            result[i] = getMax(arrw) ;
        }
        return result ;
    }

    public int getMax(int [] arrw)
    {
        int max = Integer.MIN_VALUE ;
        for(int index = 0 ; index<arrw.length ; index++)
        {
            if (arrw[index]>max)
            {
                max = arrw[index];
            }
        }
        return max ;
    }

    //法二
    public int solu2(int w,int[] arr) {
        int sum = 0;
        Deque<Integer> qmax = new LinkedList<Integer>();
        for (int i = 0; i < w; i++) {
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
                qmax.pollLast();
            }
            qmax.addLast(i);
        }
        sum += arr[qmax.peekFirst()];
        for (int i = 1; i < arr.length - w + 1; i++) {
            if (qmax.peekFirst() < i) {
                qmax.pollFirst();
            }
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i + w - 1]) {
                qmax.pollLast();
            }
            qmax.addLast(i + w - 1);
            sum += arr[qmax.peekFirst()];
            //System.out.println(arr[qmax.peekFirst()]);
        }
        return sum;
    }
}
