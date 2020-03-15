package ojtest;
/*
固定和的元素对：
输入一个数组和一个数字，在数组中查找两个数，使得它们的和正好是输入的那个数字，统计这样两个数的对数。
输入第一行为用例个数， 每个测试用例输入第一行是数组，每一个数用空格隔开；第二行是数字和。
Sample Input 1
1
1 2 4 7 11 0 9 15
11
Sample Output 1
3
 */
import java.util.*;

public class MainT1_6 {
    public static int sum(int[] arr,int num) {
        Arrays.sort(arr);
        //quickSort(arr,0, arr.length-1);
        int res=0,i=0,j=arr.length-1;
        while(i<j){
            if(arr[i]+arr[j]==num) {
                res++;
                //这里应该默认数字不会重复，或者重复的话不会两头都重复，不然res加的应该是两头重复个数的乘积！
                if(arr[i]+arr[j-1]==num)
                    j--;
                else
                    i++;
            }
            else if(arr[i]+arr[j]>num)
                j--;
            else if(arr[i]+arr[j]<num)
                i++;
        }
        return res;
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int a=Integer.parseInt(in.nextLine());
        for(int i=0;i<a;i++){
            if(i>0) System.out.println();
            String []str=in.nextLine().split(" ");
            int []arr=new int[str.length];
            /*
            int arrlength=0;
            for(String s : str){
                //使用foreach依次将字符串数组的元素转换为整型，也可以转换为其他类型
                arr[arrlength]=Integer.parseInt(s);
                arrlength++;
            }
             */
            for(int j=0;j<str.length;j++){
                arr[j]=Integer.parseInt(str[j]);
            }
            int num=Integer.parseInt(in.nextLine());
            int res=sum(arr,num);
            System.out.print(res);
        }
        in.close();
    }

    /*
    public static void quickSort(int[] arr,int low,int high){
        int i,j,temp,t;
        if(low>high){
            return;
        }
        i=low;
        j=high;
        //temp就是基准位
        temp = arr[low];

        while (i<j) {
            //先看右边，依次往左递减
            while (temp<=arr[j]&&i<j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp>=arr[i]&&i<j) {
                i++;
            }
            //如果满足条件则交换
            if (i<j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        //递归调用左半数组
        quickSort(arr, low, j-1);
        //递归调用右半数组
        quickSort(arr, j+1, high);
    }
     */
}
