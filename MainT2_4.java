package ojtest;
/*
实现插入排序。
Input
输入第一行为用例个数， 每个测试用例输入的每一行代表一个数组，其中的值用空格隔开，第一个值表示数组的长度。
Output
输出排序的数组，用空格隔开，末尾不要空格。
Sample Input
1
13 24 3 56 34 3 78 12 29 49 84 51 9 100
Sample Output
3 3 9 12 24 29 34 49 51 56 78 84 100
 */
import java.util.Scanner;

public class MainT2_4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a=Integer.parseInt(in.nextLine());
        for(int i=0;i<a;i++){
            if(i>0)
                System.out.println();
            String []str=in.nextLine().split(" ");
            int length=Integer.parseInt(str[0]);
            int []arr=new int[length];
            for(int j=0;j<arr.length;j++){
                arr[j]= Integer.parseInt(str[j+1]);
            }
            int[] result = insertSort(arr);
            for(int j=0;j<result.length;j++){
                if(j>0)
                    System.out.print(" ");
                System.out.print(result[j]);
            }
        }
        in.close();
    }

    private static int[] insertSort(int[] array) {
        //直接插入排序
        /*
        在排序之前我们需要搞清一个思路，新插入一个数据的时候，排序过后的数组都是从小到大排列好的，
        所以我们需要从后往前查找，直到找到比我们要插入的数字还小的值。
        这个时候我们需要一个变量j作为标识
         */
        for(int i = 1;i<array.length;i++){
            int temp = array[i];
            int j;
            for(j = i-1;j>=0;j--){
                //将大于temp的数向后移动一步
                if(array[j]>temp){
                    array[j+1] = array[j];//记录j的值也就是temp要插入的位置
                }else{
                    break;
                }
            }
            array[j+1] = temp;
        }

        return array;
    }
}
