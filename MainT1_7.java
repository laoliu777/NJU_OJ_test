package ojtest;
/*
先升后降
Description
从一列不重复的数中筛除尽可能少的数使得从左往右看，这些数是从小到大再从大到小的。
Input
输入第一行为用例个数， 每个测试用例输入是一个数组，数值通过空格隔开。
4
1 2 4 7 11 10 9 15 3 5 8 6
1 3 5 4 7 6 4 5 3
1 2 3
3 2 1
1
23 3 30 2
Output
输出筛选之后的数组，用空格隔开。如果有多种结果，则一行一种结果，
单个输入的所有结果按从小到大排序，排序的key的优先级随index递增而递减
例如 3 4 7 6； 1 3 7 5； 1 2 7 6； 1 3 7 6 排序成 1 2 7 6；1 3 7 5；1 3 7 6； 3 4 7 6；
1 2 4 7 11 10 9 8 6
1 3 4 7 6 4 3
1 3 4 7 6 5 3
1 3 5 7 6 4 3
1 3 5 7 6 5 3
1 2 3
3 2 1
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class MainT1_7 {
    public static void main(String[] args) {
        //solution1
        /*
        Scanner in = new Scanner(System.in);
        int a=Integer.parseInt(in.nextLine());
        for(int i=0;i<a;i++){
            if(i>0) System.out.println();
            //solution
            String []str=in.nextLine().split(" ");
            int []arr=new int[str.length];
            for(int j=0;j<str.length;j++){
                arr[j]=Integer.parseInt(str[j]);
            }
            //int[] result = newarray(arr);
            //int res =DoubleEndLIS(arr,arr.length);
            int[] result = newarray1(arr);
            for(int j=0;j<result.length;j++){
                if(j>0) System.out.print(" ");
                System.out.print(result[j]);
            }

            //System.out.print(res);
        }
        in.close();
         */
        //solution2
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
            int[] arr = readInts(br);
            ArrayList<String> res = getNum(arr);
            print(res);
        }
    }

    //法二：function2
    public static ArrayList<String> getNum(int[] arr) {
        DPPair[] dpPair1 = LIDS(arr, true);
        int[] arr2 = reverse(arr);
        DPPair[] dpPair2 = LIDS(arr2, true);
        for(DPPair dpPair: dpPair2){
            for(ArrayList<Integer> list: dpPair.combine){
                if(list.get(0) < list.get(list.size() - 1)){
                    Collections.reverse(list);
                }
            }
        }
        int[] sum = new int[dpPair1.length];
        for(int i = 0; i < dpPair1.length; i++){
            sum[i] = dpPair1[i].value + dpPair2[dpPair2.length - i - 1].value;
        }
        ArrayList<Integer> mids = new ArrayList<>();
        for(int i = 0; i < sum.length; i++){
            if(mids.isEmpty() || sum[i] == sum[mids.get(0)]){
                mids.add(i);
            }else if(sum[i] > sum[mids.get(0)]){
                mids.clear();
                mids.add(i);
            }
        }
        ArrayList<ArrayList<Integer>> resArray = new ArrayList<>();
        for(int mid: mids){
            for(ArrayList<Integer> e1: dpPair1[mid].combine){
                for(ArrayList<Integer> e2: dpPair2[dpPair2.length - 1 - mid].combine){
                    ArrayList<Integer> list = new ArrayList<>();
                    list.addAll(e1);
                    list.addAll(e2);
                    resArray.add(list);
                }
            }
        }
        HashSet<String> resArrayDel = new HashSet<>();
        for(ArrayList<Integer> list: resArray){
            resArrayDel.add(arrayToString(list));
        }
        ArrayList<String> res = new ArrayList<>(resArrayDel);
        Collections.sort(res);
        return res;
    }
    public static DPPair[] LIDS(int[] nums, boolean isLIS){
        DPPair[] dpPairs = new DPPair[nums.length + 1];
        for(int i = 1; i < dpPairs.length; i++){
            dpPairs[i] = new DPPair(1, 1);
            ArrayList<Integer> list = new ArrayList<>();
            list.add(nums[i - 1]);
            dpPairs[i].combine.add(list);
        }
        dpPairs[0] = new DPPair(0, 0);
        for(int i = 2; i < nums.length + 1; i++){
            for(int j = 1; j < i; j++){
                if(isLIS ? nums[i - 1] >= nums[j - 1] : nums[i - 1] <= nums[j - 1]){
                    if(dpPairs[j].step + 1 > dpPairs[i].step){
                        dpPairs[i].step = dpPairs[j].step + 1;
                        dpPairs[i].index.clear();
                        dpPairs[i].index.add(j - 1);
                        dpPairs[i].combine.clear();
                        combineAdd(dpPairs, nums, i, j);
                    }else if(dpPairs[j].step + 1 == dpPairs[i].step){
                        combineAdd(dpPairs, nums, i, j);
                    }
                }
            }
            dpPairs[i].value = Math.max(dpPairs[i - 1].value, dpPairs[i].step);
        }
        for(int i = 2; i < nums.length + 1; i++) {
            if (dpPairs[i].combine.get(0).size() < dpPairs[i - 1].combine.get(0).size()) {
                dpPairs[i].combine = dpPairs[i - 1].combine;
            } else if (dpPairs[i].combine.get(0).size() == dpPairs[i - 1].combine.get(0).size()) {
                for (ArrayList<Integer> e : dpPairs[i - 1].combine) {
                    dpPairs[i].combine.add(e);
                }
            }
        }
        return dpPairs;
    }
    public static void combineAdd(DPPair[] dpPair, int[] nums, int i, int j){
        for(ArrayList<Integer> e: dpPair[j].combine){
            ArrayList<Integer> list = (ArrayList<Integer>)e.clone();
            list.add(nums[i - 1]);
            dpPair[i].combine.add(list);
        }
    }
    static class DPPair{
        int step;
        int value;
        ArrayList<Integer> index = new ArrayList<>();
        ArrayList<ArrayList<Integer>> combine = new ArrayList<>();
        DPPair(int step, int value){
            this.step = step;
            this.value = value;
        }
    }
    public static int[] reverse(int[] nums){
        int[] nums2 = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            nums2[nums2.length - i - 1] = nums[i];
        }
        return nums2;
    }
    private static String arrayToString(ArrayList<Integer> objs){
        StringBuffer buffer = new StringBuffer();
        for(Object obj: objs){
            buffer.append(obj).append(' ');
        }
        buffer.delete(buffer.length() - 1, buffer.length());
        return new String(buffer);
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
        int[] ints = new int[strings.length];
        for(int i = 0; i < strings.length; i++){
            ints[i] = Integer.parseInt(strings[i]);
        }
        return ints;
    }
    private static List<Integer> toArray(int[] src){
        return Arrays.stream(src).boxed().collect(Collectors.toList());
    }
    private static void print(ArrayList<String> objs){
        StringBuffer buffer = new StringBuffer();
        for(Object obj: objs){
            buffer.append(obj).append('\n');
        }
        buffer.delete(buffer.length() - 1, buffer.length());
        System.out.print(new String(buffer));
    }



    //法一：
    public static int[] newarray(int [] arr)
    {
        int len=arr.length;
        int []LIS = new int[len];
        int []lefToRight = new int[len];        //leftToRight[i]表示0~i最长子序列长度-1
        int []rightToLeft = new int[len];
        int maxLen = 0;        //记录总共的(上升+下降)最长子序列长度
        int low, high, mid;

        for (int i = 0; i < len; ++i) {
            lefToRight[i]  = 0;
            LIS[i] = 0;
        }

        LIS[0] = arr[0];
        for (int i = 1; i < len; i++) {
            low = 0; high = lefToRight[i-1];
            while (low <= high) {
                mid = (low + high)/2;
                if (LIS[mid] < arr[i]) {
                    low = mid + 1;
                } else {
                    high = mid -1;
                }
            }
            LIS[low] = arr[i];
            if (low > lefToRight[i-1]) {
                lefToRight[i] = lefToRight[i-1] + 1;    //最长子序列长度加1
            }
            else {
                lefToRight[i] = lefToRight[i-1];
            }
        }
        //leftToRight的每个值增加1，因为他们是最长子序列值-1
        //此时leftToRight表示的是最长子序列的真正值。
        for (int i = 0; i < len; i++) {
            lefToRight[i]++;
        }

        //从右到左
        for (int i = 0; i < len; i++) {
            rightToLeft[i] = 0;
            LIS[i] = 0;
        }

        int k = 0;
        LIS[0] = arr[len-1];
        for (int i = len -2; i >= 0; --i) {
            low = 0; high = rightToLeft[k];
            while (low <= high) {
                mid = (low + high)/2;
                if (LIS[mid] < arr[i]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            LIS[low] = arr[i];
            if (low > rightToLeft[k]) {
                rightToLeft[k+1] = rightToLeft[k] + 1;
            } else {
                rightToLeft[k+1] = rightToLeft[k];
            }
            ++k;
        }

        for (int i = 0; i < k; ++i) {
            rightToLeft[i]++;
        }

        //求最大值即为要求的
        for (int i = 0; i < len; ++i) {
            //cout<<"i: "<<i<<" "<<lefToRight[i]<<"  "<<rightToLeft[len-i-1]<<endl;
            if (lefToRight[i] + rightToLeft[len-i-1] > maxLen)
                maxLen = lefToRight[i] + rightToLeft[len-i-1];
        }
        //maxLen为最大序列的长度

        //return len - maxLen + 1;
        return lefToRight;
    }

    public static int DoubleEndLIS(int []array,int len)
    {
        int left,mid,right;
        int max=0;
        int k =0;
        //LIS数组中存储的是 递增子序列中最大值最小的子序列的最后一个元素（最大元素）在array中的位置
        int []LIS = new int[len];
        //从左到右LIS中最长子序列中最大值最小的子序列的最后一个元素所在的位置,也就是0~i的数字序列中最长递增子序列的长度-1
        int []B = new int[len];
        //从右到左LIS中最长子序列中最大值最小的子序列的最后一个元素所在的位置,也就是len-1~i的数字序列中最长递增子序列的长度-1
        int []C = new int[len];
        //从左到右
        for(int i = 0;i < len;++i)//LIS数组清零
        {
            B[i] = 0;
            LIS[i] = 0;
        }
        LIS[0] = array[0];
        for(int i = 1;i < len;++i)
        {
            left = 0;
            right = B[k];
            while(left <= right)
            {
                mid = (left + right)/2;
                if(array[i] < LIS[mid])
                {
                    right = mid - 1;
                }
                else
                {
                    left = mid + 1;
                }
            }

            LIS[left] = array[i];//将array[i]插入到LIS中
            if(left > B[k])
            {
                B[k+1] = B[k] + 1;
                k++;
            }
        }
        for(int i = 0;i < k;++i)
        {
            B[i]++;
        }
        //从右到左
        for(int i = 0;i < len;++i)//LIS数组清零
        {
            C[i] = 0;
            LIS[i] = 0;
        }
        k = 0;
        LIS[0] = array[len-1];
        for(int i = len-2;i >= 0;--i)
        {
            left = 0;
            right = C[k];
            while(left <= right)
            {
                mid = (left + right)/2;
                if(array[i] < LIS[mid])
                {
                    right = mid - 1;
                }
                else
                {
                    left = mid + 1;
                }
            }
            LIS[left] = array[i];
            if(left > C[k])
            {
                C[k+1] = C[k] + 1;
                k++;
            }
        }
        for(int i = 0;i <= k;++i)
        {
            C[i]++;
        }

        //求max
        for(int i = 0;i < len;++i)
        {
            System.out.println(B[i]+" "+C[i]);
            if(B[i]+C[i]>max)
                max=B[i] + C[i];
        }

        return max;
    }

    //法三
    public static int[] LIS(int[] A,int n)
    {
        int[] d = new int[n];
        int len = 1;
        int i,j;
        for(i=0;i<n;i++){
            d[i]=1;
            for(j=0;j<i;j++){
                if(A[j]<=A[i] && (d[j]+1)>=d[i])
                    d[i]=d[j]+1;
            }
            if(d[i]>len) len=d[i];
        }
        return d;
    }

    public static int[] ULIS(int[] A,int n)
    {
        int[] d = new int[n];
        int len = 1;
        int i,j;
        for(i=n-1;i>=0;i--){
            d[i]=1;
            for(j=n-1;j>i;j--){
                if(A[j]<=A[i] && (d[j]+1)>=d[i])
                    d[i]=d[j]+1;
            }
            if(d[i]>len) len=d[i];
        }
        return d;
    }

    public static int[] newarray1(int []arr){
        int len=arr.length;
        int []a=LIS(arr,len);
        int []b=ULIS(arr,len);
        int  max=0;
        int  num=0;
        int current=0;
        for (int i=0;i<len;i++){
            if(a[i]+b[i]>max){
                num =a[i];
                max=a[i]+b[i];
                current=i;
            }
        }
        int []c=new int[len+1];
        int k=1,t=0,i;
        int min,min_index=0;
        for (k=1;k<=num;k++){
            min=10000;
            for (i=min_index;i<=current;i++){
                if(a[i]==k&&arr[i]<min){
                    min=arr[i];
                    min_index=i;
                }
            }
            c[t++]=min;
        }
        int hint_index=current;
        for (k=max-num-1;k>=1;k--){
            min=10000;
            for (i=len-1;i>hint_index;i--){
                if(b[i]==k&&arr[i]<min ){
                    min=arr[i]; min_index=i;
                }
            }
            hint_index=min_index;
            c[t++]=min;
        }
        return c;
    }
}

