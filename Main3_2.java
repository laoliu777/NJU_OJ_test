package ojtest;
/*
整除查询：
给定正整数数组和许多除数查询。 在每个查询Q [i]中，我们都得到一个整数K，我们需要计算数组中所有可以被K完全整除的元素。
约束：1 <= T <= 1001 <= N，M <= 1051 <= A [i]，Q [i] <= 105
输入项
输入的第一行包含一个整数T，它表示测试用例的数量。 然后是T测试用例。 每个测试用例包含三行。
每个测试用例的第一行包含两个整数N＆M，第二行包含N个以空格分隔的数组元素，第三行包含M个以空格分隔的查询。
输出量
对于每个测试用例，在新行中为每个查询Q [i]打印所需的计数。
样本输入1
2
6 3
2 4 9 15 21 20
2 3 5
3 2
3 4 6
2 3
样本输出1
3 3 2
2 2
 */
import java.util.Scanner;

public class Main3_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a=Integer.parseInt(in.nextLine());
        for(int i=0;i<a;i++){
            if(i>0)
                System.out.println();
            String []str1=in.nextLine().split(" ");
            int n=Integer.parseInt(str1[0]);
            int m=Integer.parseInt(str1[1]);
            int[] N=new int[n];
            int[] M=new int[m];
            String []str2=in.nextLine().split(" ");
            String []str3=in.nextLine().split(" ");
            for(int j=0;j<n;j++){
                N[j]= Integer.parseInt(str2[j]);
            }
            for(int j=0;j<m;j++){
                M[j]= Integer.parseInt(str3[j]);
            }
            int[] result = QueryDivide(M,N);
            for(int j=0;j<result.length;j++){
                if(j>0)
                    System.out.print(" ");
                System.out.print(result[j]);
            }
        }
        in.close();
    }

    private static int[] QueryDivide(int[] M, int[] N) {
        int m=M.length;
        int n=N.length;
        int[] res=new int[m];
        for(int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if(N[j]%M[i]==0)
                    res[i]++;
            }
        }
        return res;
    }


}
