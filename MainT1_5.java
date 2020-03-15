package ojtest;
/*
区间第k最小
找到给定数组的给定区间内的第K小的数值。
输入第一行为用例个数， 每个测试用例输入的第一行为数组，每一个数用空格隔开；第二行是区间（第几个数到第几个数，两头均包含），两个值用空格隔开；第三行为K值。
输入
1
1 2 3 4 5 6 7
3 5
2
输出
4
 */
import java.util.*;

public class MainT1_5 {
    //法一：交换排序，貌似不可行？
    public static int find_k(int[] array, int k) {
        int n = array.length;
        boolean success = true;
        for (int i=0; i<k; i++) {
            // 设定一个排序完成的标记
            // 若为 true，则表示此次循环没有进行交换，即待排序列已经有序，排序已然完成
            for (int j=n-1; j>i; j--) {
                success = true;
                if (array[j] < array[j-1]) {
                    //java里不能直接交换，形参交换的是值，不是参数
                    //swap(array[j], array[j-1]);
                    int temp=array[j];
                    array[j]=array[j-1];
                    array[j-1]=temp;

                    success = false;
                }
            }
            if (success) {
                break;
            }
        }
        return array[k-1];
    }

    /*
    public static void swap(int i, int j) {
        int temp=i;
        i=j;
        j=temp;
    }
     */

    //法二，直接用arrays的快排
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int a=Integer.parseInt(in.nextLine());
        for(int i=0;i<a;i++){
            if(i>0) System.out.println();
            String []str=in.nextLine().split(" ");
            int []arr=new int[str.length];
            for(int j=0;j<str.length;j++){
                arr[j]=Integer.parseInt(str[j]);
            }
            String []str1=in.nextLine().split(" ");
            int start=Integer.parseInt(str1[0]);
            int end=Integer.parseInt(str1[1]);
            int []arr1=new int[end-start+1];
            for(int j=start-1;j<end;j++)
                arr1[j-start+1]=arr[j];
            int k=Integer.parseInt(in.nextLine());
            //MainT1_5 t=new MainT1_5();
            //int res=find_k(arr1,k);
            Arrays.sort(arr1);
            int res=arr1[k-1];
            System.out.print(res);
        }
        in.close();
    }

    //法三：推荐，使用一个伴随数组
    public static int find_k3(int[] a, int k) {
        HashMap map = new HashMap();
        int []b=new int[a.length];
        for (int i = 0; i < a.length; i++) {
            map.put(a[i], i); // 将值和下标存入Map
        }
        // 排列
        List list = new ArrayList();
        Arrays.sort(a); // 升序排列
        for (int i = 0; i < a.length; i++) {
            list.add(a[i]);
        }
        // 查找原始下标，但是如果重复的话给的是第一个的位置
        for (int i = 0; i < a.length; i++){
            b[i]= (int) map.get(a[i]);
        }
        return a[k-1];
    }

}
