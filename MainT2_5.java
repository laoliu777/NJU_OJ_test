package ojtest;
/*
实现冒泡排序。
Input
输入的每一行表示一个元素为正整数的数组，所有值用空格隔开，第一个值为数值长度，其余为数组元素值。
Output
输出的每一行为排序结果，用空格隔开，末尾不要空格。
Sample Input
13 24 3 56 34 3 78 12 29 49 84 51 9 100
Sample Output
3 3 9 12 24 29 34 49 51 56 78 84 100
 */

import java.util.Scanner;

public class MainT2_5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()){
            String []str=in.nextLine().split(" ");
            int []arr=new int[Integer.parseInt(str[0])];
            for(int i=0;i<Integer.parseInt(str[0]);i++){
                arr[i]=Integer.parseInt(str[i+1]);
            }
            int []result=bubbleSort(arr);
            for(int i=0;i<Integer.parseInt(str[0]);i++){
                if(i>0)
                    System.out.print(" ");
                System.out.print(result[i]);
            }
            if(in.hasNextLine()==true)
                System.out.println();
        }
        in.close();
    }

    private static int[] bubbleSort(int[] arr) {
        //冒泡排序
        /*
        在概念上是排序算法中最简单的，但是运行起来非常慢，冒泡排序遵循以下几个规则（假如我们现在要给一队打乱的足球队员排序）：
        比较两个队员:
        如果左边的队员比右边的高，则交换位置
        向右移动一位，比较下面两个队员
         */
        //第一个for循环是程序需要执行要走多少趟
        int length=arr.length;
        if (length <= 1)
            return arr;       //如果只有一个元素就不用排序了

        for (int i = 0; i < length; ++i) {
            // 提前退出冒泡循环的标志位,即一次比较中没有交换任何元素，这个数组就已经是有序的了
            boolean flag = false;
            for (int j = 0; j < length - i - 1; ++j) {        //此处你可能会疑问的j<n-i-1，因为冒泡是把每轮循环中较大的数飘到后面，
                // 数组下标又是从0开始的，i下标后面已经排序的个数就得多减1，总结就是i增多少，j的循环位置减多少
                if (arr[j] > arr[j + 1]) {        //即这两个相邻的数是逆序的，交换
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) break;//没有数据交换，数组已经有序，退出排序
        }

        return arr;
    }
}
