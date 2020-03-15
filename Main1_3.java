package ojtest;
/*
倒置个数
Description
有一个由N个实数构成的数组，如果一对元素A[i]和A[j]是倒序的，即i<j但是A[i]>A[j]则称它们是一个倒置，
设计一个计算该数组中所有倒置数量的算法。要求算法复杂度为O(nlogn)
Input
输入有多行，第一行整数T表示为测试用例个数，后面是T个测试用例，每一个用例包括两行，第一行的一个整数是元素个数，
第二行为用空格隔开的数组值。
Output
输出每一个用例的倒置个数，一行表示一个用例。
Sample Input 1
1
8
8 3 2 9 7 1 5 4
Sample Output 1
17
统计一个数组中，前面数字小于后面数字的数组对的个数
 */
import java.util.*;

public class Main1_3 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int a=in.nextInt();
        for(int i=0;i<a;i++){
            if(i>0) System.out.println();
            int count=0;
            int b=in.nextInt();
            int[] s=new int[b];
            for(int j=0;j<b;j++){
                s[j]=in.nextInt();
            }
            /*
            for(int n = 0;n<b-1;n++) {
                for(int m = n+1;m<b;m++) {
                    if(s[n]>s[m]) {
                        count++;
                    }
                }
            }
           System.out.print(count);
             */
            System.out.println(mergeSortAndCount(s, 0, s.length - 1));
        }
        in.close();
    }
    private static int mergeAndCount(int[] arr, int l, int m, int r)
    {

        // Left subarray
        int[] left = Arrays.copyOfRange(arr, l, m + 1);

        // Right subarray
        int[] right = Arrays.copyOfRange(arr, m + 1, r + 1);

        int i = 0, j = 0, k = l, swaps = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j])
                arr[k++] = left[i++];
            else {
                arr[k++] = right[j++];
                swaps += (m + 1) - (l + i);
            }
        }

        // Fill from the rest of the left subarray
        while (i < left.length)
            arr[k++] = left[i++];

        // Fill from the rest of the right subarray
        while (j < right.length)
            arr[k++] = right[j++];

        return swaps;
    }

    // Merge sort function
    private static int mergeSortAndCount(int[] arr, int l, int r)
    {

        // Keeps track of the inversion count at a
        // particular node of the recursion tree
        int count = 0;

        if (l < r) {
            int m = (l + r) / 2;

            // Total inversion count = left subarray count
            // + right subarray count + merge count

            // Left subarray count
            count += mergeSortAndCount(arr, l, m);

            // Right subarray count
            count += mergeSortAndCount(arr, m + 1, r);

            // Merge count
            count += mergeAndCount(arr, l, m, r);
        }

        return count;
    }
}
