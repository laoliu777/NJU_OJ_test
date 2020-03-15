package ojtest;
/*
按照另一个数组排序
Description
Given two array A1[] and A2[], sort A1 in such a way that the relative order among
the elements will be same as those in A2. For the elements not present in A2. Append
them at last in sorted order. It is also given that the number of elements in A2[] are
smaller than or equal to number of elements in A1[] and A2[] has all distinct elements.
Input:A1[] = {2, 1, 2, 5, 7, 1, 9, 3, 6, 8, 8} A2[] = {2, 1, 8, 3} Output:
A1[] = {2, 2, 1, 1, 8, 8, 3, 5, 6, 7, 9}
Since 2 is present first in A2[], all occurrences of 2s should appear first in A[],
then all occurrences 1s as 1 comes after 2 in A[]. Next all occurrences of 8 and then
all occurrences of 3. Finally we print all those elements of A1[] that are not present
in A2[]
Input
The first line of input contains an integer T denoting the number of test cases. The
first line of each test case is M and N. M is the number of elements in A1 and N is
the number of elements in A2.The second line of each test case contains M elements.
The third line of each test case contains N elements.
Output
Print the sorted array according order defined by another array.
Sample Input 1
1
11 4
2 1 2 5 7 1 9 3 6 8 8
2 1 8 3
Sample Output 1
2 2 1 1 8 8 3 5 6 7 9
输入两个数组，对两个数组合并去重并排序
 */
import java.util.*;

public class Main1_4 {
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr1.length; i++) {
            map.put(arr1[i], map.getOrDefault(arr1[i], 0)+1);
        }
        int[] res=new int[arr1.length];
        int j=0;
        for (int i = 0; i < arr2.length; i++) {
            for (int k = 0; k < map.get(arr2[i]); k++) {
                res[j++]=arr2[i];
            }
            map.remove(arr2[i]);
        }
        List<Integer> mapkey=new ArrayList<>();
        for(int key:map.keySet()) {
            mapkey.add(key);
        }
        //对key值排序
        Collections.sort(mapkey);
        for (int i = 0; i < mapkey.size(); i++) {
            for (int k = 0; k < map.get(mapkey.get(i)); k++) {
                res[j++]=mapkey.get(i);
            }
        }
        return res;
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int a=in.nextInt();
        for(int i=0;i<a;i++){
            if(i>0) System.out.println();
            int b1=in.nextInt();
            int b2=in.nextInt();
            int[] C1=new int[b1];
            int[] C2=new int[b2];
            for(int j=0;j<b1;j++) C1[j]=in.nextInt();
            for(int j=0;j<b2;j++) C2[j]=in.nextInt();
            //List<int[]> list1 = Arrays.asList(C1);
            //List<int[]> list2 = Arrays.asList(C2);
            //int[] D=list.toArray(mysort(list1,list2));
            int[] D=relativeSortArray(C1,C2);
            for(int j=0;j<b1;j++){
                if(j>0) System.out.print(" ");
                System.out.print(D[j]);
            }
        }
        in.close();
    }
}
