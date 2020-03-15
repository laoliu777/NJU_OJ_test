package ojtest;
/*
实现Shell排序
实现Shell排序，对给定的无序数组，按照给定的间隔变化（间隔大小即同组数字index的差），打印排序结果，注意不一定是最终排序结果！
Input
输入第一行表示测试用例个数，后面为测试用例，每一个用例有两行，第一行为给定数组，第二行为指定间隔，每一个间隔用空格隔开。
Output
输出的每一行为一个用例对应的指定排序结果。
Sample Input 1
1
49 38 65 97 76 13 27 49 55 4
5 3
Sample Output 1
13 4 49 38 27 49 55 65 97 76
 */


import java.util.Scanner;

public class MainT3_3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int tcase= Integer.parseInt(in.nextLine());
        for(int tnum=0;tnum<tcase;tnum++){
            if(tnum>0)
                System.out.println();
            String []str1=in.nextLine().split(" ");
            String []str2=in.nextLine().split(" ");
            int []arr=new int[str1.length];
            int []sqeu=new int[str2.length];
            for(int i=0;i<str1.length;i++){
                arr[i]=Integer.parseInt(str1[i]);
            }
            for(int i=0;i<str2.length;i++){
                sqeu[i]=Integer.parseInt(str2[i]);
            }
            int []result=shellSort(arr,sqeu);
            for(int i=0;i<result.length;i++){
                if(i>0)
                    System.out.print(" ");
                System.out.print(result[i]);
            }
        }
        in.close();
    }

    public static int[] shellSort(int[] arr, int[] delta){
        for (int j = 0; j < delta.length; j++) {
            for (int k = 0; k < delta[j] && k < arr.length; k++) {
                for (int l = k; l < arr.length; l += delta[j]) {
                    for (int m = l + delta[j]; m < arr.length; m += delta[j]) {
                        if (arr[l] > arr[m]) {
                            int temp = arr[l];
                            arr[l] = arr[m];
                            arr[m] = temp;
                        }
                    }
                }
            }
        }
        return arr;
    }

}
