package ojtest;
/*
实现计数排序，通过多次遍历数组，统计比每一个元素小的其它元素个数，根据该统计量对数据进行排序。
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

public class MainT2_6 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()){
            String []str=in.nextLine().split(" ");
            int []arr=new int[Integer.parseInt(str[0])];
            for(int i=0;i<Integer.parseInt(str[0]);i++){
                arr[i]=Integer.parseInt(str[i+1]);
            }
            int []result=countSort(arr);
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

    public static int[] countSort(int[] array){
        int max = getMax(array);                        //获取数组的最大值，数组所有值都在0 - max之间
        int[] countArray = new int[max + 1];            //创建一个max+1大小的数组用于表示从0 - max所有数字的重复次数
        int[] resultArray = new int[array.length];
        System.arraycopy(array, 0, resultArray, 0, array.length);      //用于存储排好序的数组
        for(int i = 0; i < array.length; i++){              //循环array数组
            countArray[array[i]]++;                         //因为countArray的下标代表array中的数字，而值代表array中元素的出现次数，所以countArray[array[i]]++
        }
        for(int i = 1; i < countArray.length; i++){
            countArray[i] = countArray[i] + countArray[i - 1];      //将countArray中的每一个元素变成与前一个元素相加的和
        }
        for(int i = 0; i < resultArray.length ; i++){
            array[--countArray[resultArray[i]]] = resultArray[i];                //从array的最后一位开始依次放入resultArray中，放入的位置是 --countArray[array[i]]
        }

        return array;
    }

    private static int getMax(int[] array){
        int max = array[0];
        for(int i = 0; i < array.length; i++){
            if(array[i] < 0){
                throw new RuntimeException("数组中有数小于0");
            }
            if(max < array[i]){
                max = array[i];
            }
        }
        return max;
    }

}


