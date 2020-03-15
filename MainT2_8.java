package ojtest;
/*
合并（归并）排序的核心思想是：每次从中间位置将数组分组再分别排序。请实现其非递归方案。
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

public class MainT2_8 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //给输出的换行
        int in_line=0;
        while (in.hasNextLine()){
            /*
            if(in_line>0)
                System.out.println();
            in_line++;
             */
            String []str=in.nextLine().split(" ");
            int []arr=new int[Integer.parseInt(str[0])];
            for(int i=0;i<Integer.parseInt(str[0]);i++){
                arr[i]=Integer.parseInt(str[i+1]);
            }
            int []result=mergeSort(arr);
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

    /*
     归并排序（非递归）
     从切分的数组长度为1开始，一次归并变回原来长度的2倍
     待排序数组
     排好序的数组
     */
    private static int[] mergeSort(int[] nums) {
        int len = 1;
        while (len <= nums.length) {
            for (int i = 0; i + len <= nums.length; i += len * 2) {
                int low = i, mid = i + len - 1, high = i + 2 * len - 1;
                if (high > nums.length - 1) {
                    high = nums.length - 1; //整个待排序数组为奇数的情况
                }
                merge(nums, low, mid, high);
            }
            len *= 2;
        }
        return nums;
    }

    /*
     将切分的数组进行归并排序，同递归版
     nums 带排序数组
     low 左边数组第一个元素索引
     mid 左边数组最后一个元素索引，mid + 1为右边数组第一个元素索引
     high 右边数组最后一个元素索引
     */
    private static void merge(int[] nums, int low, int mid, int high) {
        int[] tmpArray = new int[nums.length];
        int rightIndex = mid + 1;
        int tmpIndex = low;
        int begin = low;
        while (low <= mid && rightIndex <= high) {
            if (nums[low] <= nums[rightIndex]) {
                tmpArray[tmpIndex++] = nums[low++];
            } else {
                tmpArray[tmpIndex++] = nums[rightIndex++];
            }
        }
        while (low <= mid) {
            tmpArray[tmpIndex++] = nums[low++];
        }
        while (rightIndex <= high) {
            tmpArray[tmpIndex++] = nums[rightIndex++];
        }
        while (begin <= high) {
            nums[begin] = tmpArray[begin++];
        }
    }

}
