package ojtest;
/*
最小交换次数
Description
Given an array of N distinct elementsA[ ], find the minimum number of swaps required to sort the array.Your are required to complete the function which returns an integer denoting the minimum number of swaps, required to sort the array.
Input
The first line of input contains an integer T denoting the no of test cases . Then T test cases follow . Each test case contains an integer N denoting the no of element of the array A[ ]. In the next line are N space separated values of the array A[ ] .
Output
For each test case in a new line output will be an integer denoting minimum umber of swaps that are required to sort the array.
变成升序最少交换几次？
Sample Input 1
2
4
4 3 2 1
5
1 5 4 3 2
Sample Output 1
2
2
 */

import java.util.*;

public class Main1_2 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int a= Integer.parseInt(in.nextLine());
        for(int i=0;i<a;i++){
            if(i>0) System.out.println();
            int length=Integer.parseInt(in.nextLine());
            String[] str=str=in.nextLine().split(" ");
            int[] arr=new int[length];
            List<Integer> list =new ArrayList();
            for(int j=0;j<length;j++){
                arr[j]= Integer.parseInt(str[j]);
                list.add(Integer.parseInt(str[j]));
            }
            //开始解答
            //int res=getMinimumSwaps(list);
            int res=minSwap(arr,length);
            System.out.print(res);
        }
        in.close();
    }

    public static int getMinimumSwaps(List<Integer> numbers) {
        List<Integer> tempList = new ArrayList<>();
        tempList.addAll(numbers);
        Collections.sort(tempList);
        Map<Integer, Integer> indexOfNums = new HashMap<>();    // 标记排序好的位置
        for (int i = 0; i < tempList.size(); i++) {
            indexOfNums.put(tempList.get(i), i);
        }
        int result = 0;
        for (int i = 0; i < numbers.size(); i++){
            int endPos = indexOfNums.get(numbers.get(i));   // 当前数字的最终位置
            if (numbers.get(i) == tempList.get(i)) {    // 判断该数是否在最终位置
                continue;
            } else {        // 放当前数到最终位置
                int temp = numbers.get(i);
                numbers.set(i, numbers.get(endPos));
                numbers.set(endPos, temp);
                result++;
            }
        }
        return result;
    }

    private static int minSwap(int[] arr, int len) {
        int[] newArr = new int[len];
        for (int i = 0; i < len; i++) {
            newArr[i] = arr[i];
        }
        Arrays.sort(newArr);
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (arr[i] == newArr[i]) {
                continue;
            } else {
                int k = 0; //k = arr.index(newArr[i]);
                for (int j = 0; j < arr.length; j++) {
                    if (arr[j] == newArr[i]) {
                        k = j;
                        break;
                    }
                }
                //swap(arr[i],arr[k])
                int temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;
                count++;
            }
        }

        return count;
    }

}
