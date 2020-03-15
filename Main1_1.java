package ojtest;
/*
按数值个数排序
Description
Given an array of integers, sort the array according to frequency of elements.
For example, if the input array is {2, 3, 2, 4, 5, 12, 2, 3, 3, 3, 12}, then modify
the array to {3, 3, 3, 3, 2, 2, 2, 12, 12, 4, 5}. If frequencies of two elements are
same, print them in increasing order.
Input
The first line of input contains an integer T denoting the number of test cases.
The description of T test cases follows. The first line of each test case contains
a single integer N denoting the size of array. The second line contains N space-separated
integers A1, A2, ..., AN denoting the elements of the array.（1 ≤ T ≤ 70；30 ≤ N ≤ 130 ）
Output
Print each sorted array in a seperate line. For each array its numbers should be seperated by space.
Sample Input 1
1
5
5 5 4 6 4 19982 183 23
Sample Output 1
4 4 5 5 6 23 183 19982
*/
import java.util.*;

public class Main1_1 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int a= Integer.parseInt(in.nextLine());
        for(int tcase=0;tcase<a;tcase++){
            if(tcase>0) System.out.println();
            //开始解答，法一
            int length=Integer.parseInt(in.nextLine());
            String[] arrayStr=in.nextLine().split(" ");
            int[] arr=new int[length];
            for(int i = 0; i < length; i++){
                arr[i] = Integer.parseInt(arrayStr[i]);
            }
            Map<Integer, Integer> num = new HashMap<>();
            // 读入数据并记录
            for (int i = 0; i < arr.length; i++) {
                int key = arr[i];
                //if (num.get(key)!=null) {   也行！
                if (num.containsKey(key)) {
                    int curCount = num.get(key);
                    num.put(key, curCount+1);//计数加一
                } else {
                    num.put(key, 1);
                }
            }
            int[] numb=new int[num.size()];
            int[] numcount=new int[num.size()];
            List<Map.Entry<Integer, Integer>> tempList = new ArrayList<>(num.entrySet());
            int kkk=0;
            for(Map.Entry<Integer, Integer> e:tempList) {
                numb[kkk] = e.getKey();
                numcount[kkk] = e.getValue();
                kkk++;

            }
            for(int i=0;i<kkk-1;i++) {
                for (int j = i + 1; j < kkk; j++) {
                    if ((numcount[i] < numcount[j]) || (numcount[i] == numcount[j] && numb[i] > numb[j])) {
                        int temp1 = numcount[j];
                        numcount[j] = numcount[i];
                        numcount[i] = temp1;

                        int temp2 = numb[j];
                        numb[j] = numb[i];
                        numb[i] = temp2;
                    }
                }
            }
            StringBuilder result = new StringBuilder();
            for (int i=0;i<kkk;i++) {
                int key = numb[i];
                int count = numcount[i];
                while (count-- > 0) {
                    result.append(key + " ");
                }
            }
            String s= String.valueOf(result.deleteCharAt(result.length()-1));//把最后一个空格删了！
            System.out.print(result);
        }
        in.close();
    }

    /*
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int a= Integer.parseInt(in.nextLine());
        for(int tcase=0;tcase<a;tcase++){
            if(tcase>0) System.out.println();
            //开始解答，法一
            int length=Integer.parseInt(in.nextLine());
            String[] arrayStr=in.nextLine().split(" ");
            int[] arr=new int[length];
            for(int i = 0; i < length; i++){
                arr[i] = Integer.parseInt(arrayStr[i]);
            }
            System.out.print(arraySortFreq(arr));
        }
        in.close();
    }

    private static String arraySortFreq(int[] arr) {
        // 记录数字及出现次数，HashMap：key+value
        Map<Integer, Integer> num = new HashMap<>();
        // 读入数据并记录
        for (int i = 0; i < arr.length; i++) {
            int key = arr[i];
            //if (num.get(key)!=null) {   也行！
            if (num.containsKey(key)) {
                int curCount = num.get(key);
                num.put(key, curCount+1);//计数加一
            } else {
                num.put(key, 1);
            }
        }
        // 排序，默认是从小到大，但是你从大到小要重写一下方法！（写完他就执行了，所以已经排好了
        //写法一：写完他就执行了，所以已经排好了
        List<Map.Entry<Integer, Integer>> tempList = new ArrayList<>(num.entrySet());
        Collections.sort(tempList, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2) {
                int count1 = e1.getValue();
                int count2 = e2.getValue();
                if (count1 < count2) {
                    return 1;//返回1系统认为此时前者大于后者，系统默认从小到大排，你要改变他的比较算法！
                } else if (count1 > count2) {
                    return -1;
                } else {
                    //若频率相等，则按照从小到大
                    int key1 = e1.getKey();
                    int key2 = e2.getKey();
                    return key1<key2 ? -1 : 1;
                }
            }
        });
        //后面要用法一的话，用tempList，不是之前的num了！
        //已经排完了，结束
        // 输出结果
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : tempList) {
            int key = entry.getKey();
            int count = entry.getValue();
            while (count-- > 0) {
                result.append(key + " ");
            }
        }
        result.deleteCharAt(result.length()-1);//把最后一个空格删了！
        return String.valueOf(result);
    }

     */

}