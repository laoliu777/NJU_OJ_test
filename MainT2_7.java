package ojtest;
/*
快速排序的核心思想是使用元素的值对数组进行划分。实现其非递归方案。
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
import java.util.Stack;

public class MainT2_7 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()){
            String []str=in.nextLine().split(" ");
            int []arr=new int[Integer.parseInt(str[0])];
            for(int i=0;i<Integer.parseInt(str[0]);i++){
                arr[i]=Integer.parseInt(str[i+1]);
            }
            int []result=GenerQuickSort(arr,0,arr.length-1);
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

    //快速排序
        /*
        3.1、在一个数组中选取一个基准数，根据这个基准数将数组分为两组，一组为大于基准数，另外一组为小于基准数；
         3.2、找出两个下标，一个为末尾下标，一个为开始下标；
         3.3、先从末尾下标开始于基准数比较，有两种情况：
                 3.3.1、arr[end] > base 当末尾数大于基准数时，end下标变成倒数第二位，即end--；
                 3.3.2、arr[end] < base 当末尾数小于基准数时，首尾交换位置即arr[start] = arr[end]，同时start++；
        3.4、分成两堆之后，再次进行3.3操作；
         */
    //法一：非递归实现
    public static int[] GenerQuickSort(int[] arr, int left, int right) {
        //定义一个栈
        Stack<Integer> stack = new Stack<>();
        //前提条件就是left < right
        if(left < right) {
            stack.push(left);  //将left压进栈中
            stack.push(right);  //将right压进栈中
            while(!stack.isEmpty()) {  //当栈不为空时，进行以后的操作
                int height = stack.pop();  //取出栈中第一位，我最后压进的是right，所以它是最末位的下标；
                int low = stack.pop();  //最底下的是left的下标。到底哪个对应那个，要看你是怎么压进去的

                int index = getTargetIndex(arr, low, height);  //得到基准数的下标
                if(index-1 > low) {   //判断基准数左边的数的下标与开始下标的大小
                    stack.push(low);    //根据情况压进去以基准数分开的下标的左半边部分的低位
                    stack.push(index -1);   //根据情况压进去以基准数分开的下标的左半边部分的高位
                }

                if(index+1 < height) {  //判断基准数右边的数的下标与开始下标的大小
                    stack.push(index + 1);   //根据情况压进去以基准数分开的下标的右半边部分的低位
                    stack.push(height);   //根据情况压进去以基准数分开的下标的右半边部分的高位
                }
            }
        }
        return arr;
    }

    //这是寻求中间位置的方法
    //用于获取每一次基准数下表的函数
    public static int getTargetIndex(int[] arr, int left, int right) {
        //这个检验是为了保证程序的健壮性
        if(left >= right || arr == null) {
            return -1;
        }

        //key为基准数，其它数都要与这个key比较
        int key = arr[left];
        while(left < right) {    //为什么使用while？因为我们要循环的操作不止一次
            while(key < arr[right] && left < right) //当末尾数大于基准数时的情况
                right--;   //当最右边的数大于基准数时，末尾下标进行--操作,while的作用域只有这一行
            arr[left] = arr[right];   //当最右边的数小于基准数时，要做的操作

            while(key >= arr[left] && left < right) //当末尾数小于基准数时的情况，这里包含了相等的情况
                left++;    //当最右边的数小于基准数时，开始下标进行++操作,while的作用域只有这一行
            arr[right] = arr[left];   //当最左边的数大于基准数时，要做的操作
        }
        arr[left] = key;  //当所有下标都遍历完了以后，将基准数放在left下标位置
        return left;     //返回基准数的下标
    }


    //法二：递归实现
    public static int[] QuickSort(int[] arr, int left, int right) {
        //这个if必不可少，因为是递归结束的条件
        if(left < right) {
            int baseIndex = getTargetIndex(arr, left, right);
            QuickSort(arr, left, baseIndex - 1);
            QuickSort(arr, baseIndex + 1,right);
        }
        return arr;
    }

}

