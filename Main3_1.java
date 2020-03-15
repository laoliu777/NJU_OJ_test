package ojtest;
/*
对称子字符串
给定字符串“str”的数字，请找到最长字符串“str”的长度，以使子字符串的长度为2k位，左k位的总和等于右k位的总和。
Input
输入第一行是测试用例的个数，后面每一行表示一个数字组成的字符串，例如："123123"
Output
输出找到的满足要求的最长子串的长度。例如，给定的例子长度应该是 6。每行对应一个用例的结果。
Sample Input 1
1
1538023
Sample Output 1
4
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main3_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a=Integer.parseInt(in.nextLine());
        for(int i=0;i<a;i++){
            if(i>0)
                System.out.println();
            String str=in.nextLine();
            int length=str.length();
            int []arr=new int[length];
            for(int j=0;j<arr.length;j++){
                arr[j]= Integer.parseInt(str.substring(j,j+1));
            }
            int result = parstr(arr);
            System.out.print(result);
        }
        in.close();
    }

    private static int parstr(int[] arr) {
        int length=arr.length/2;
        //记录左右的和
        int sum1=0,sum2=0;
        boolean suc=false;
        while(length>0){
            for(int i=0;i+length*2<=arr.length;i++){
                for(int j=i;j<i+length;j++){
                    sum1+=arr[j];
                    sum2+=arr[j+length];
                }
                if(sum1==sum2){
                    suc=true;
                    break;
                }
                else{
                    sum1=0;
                    sum2=0;
                }
            }
            if(suc)
                break;
            length--;
        }
        return length*2;
    }


}
