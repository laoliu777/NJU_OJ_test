package ojtest;

/*
调整数组使差最小：
有两个序列 a,b，大小都为 n,序列元素的值任意整数，无序； 要求：通过交换 a,b 中的元素，使[序列 a 元素的和]与[序列 b 元素的和]之间的差最小。
Input
输入第一行为用例个数， 每个测试用例输入为两行，分别为两个数组，每个值用空格隔开。
1
100 99 98 1 2 3
1 2 3 4 5 40
Output
输出变化之后的两个数组内元素和的差绝对值。
48
*/

import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.util.Arrays;
        import java.util.List;
        import java.util.stream.Collectors;

public class MainT1_8 {
    public static void main(String[] args){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(readLine(br));
        boolean isFirst = true;
        for(int testIndex = 0; testIndex < testNum; testIndex++){
            if(isFirst){
                isFirst = false;
            }else {
                System.out.println();
            }
            //solution
            int[] arr1 = readInts(br);
            int[] arr2 = readInts(br);
            int res = swapToMinusDiff(arr1, arr2);
            //print
            System.out.print(res);
        }
    }
    //function
    public static int swapToMinusDiff(int[] arr1, int[] arr2){
        int[] index = new int[arr1.length];
        for(int i = 0; i < arr1.length; i++){
            index[i] = i;
        }
        int[] arr = Arrays.copyOf(arr1, arr1.length + arr2.length);
        for(int i = 0; i < arr2.length; i++){
            arr[i + arr1.length] = arr2[i];
        }
        int minDiff = Integer.MAX_VALUE, sum = sum(arr);
        boolean hasNext = true;
        while(hasNext){
            int subSum = 0;
            for(int i = 0; i < index.length; i++){
                subSum += arr[index[i]];
            }
            minDiff = Math.min(minDiff, Math.abs(2 * subSum - sum));
            hasNext = addIndex(index, arr.length, index.length - 1);
        }
        return minDiff;
    }
    private static boolean addIndex(int[] index, int len, int end){
        if(index[0] == len / 2) return false;
        if(index[end] != len / 2 + end){
            index[end]++;
        }else{
            addIndex(index, len, end - 1);
            index[end] = index[end - 1] + 1;
        }
        return true;
    }
    public static int sum(int[] arr){
        int sum = 0;
        for(int num: arr){
            sum += num;
        }
        return sum;
    }
    //IO
    private static String readLine(BufferedReader br){
        try {
            return br.readLine();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    private static String[] readStrings(BufferedReader br){
        String line = readLine(br);
        if(line == null || line.equals("")){
            return new String[0];
        }
        return line.split(" ", -1);
    }
    private static int[] readInts(BufferedReader br){
        String[] strings = readStrings(br);
        return stringsToInts(strings);
    }
    private static int[] stringsToInts(String[] strings){
        int[] ints = new int[strings.length];
        for(int i = 0; i < strings.length; i++){
            ints[i] = Integer.parseInt(strings[i]);
        }
        return ints;
    }
    private static List<Integer> toArray(int[] src){
        return Arrays.stream(src).boxed().collect(Collectors.toList());
    }
    private static void print(Object[] objs){
        StringBuffer buffer = new StringBuffer();
        for(Object obj: objs){
            buffer.append(obj).append(' ');
        }
        buffer.delete(buffer.length() - 1, buffer.length());
        System.out.print(new String(buffer));
    }
}