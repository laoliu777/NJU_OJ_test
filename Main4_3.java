package ojtest;
/*
按照要求保留数组元素使得和最大:
给定一个N个数的数组，我们需要最大化所选数的和。
在每个步骤中，您需要从数组中选择一个数字Ai，删除Ai和一个Ai -1的值（不管有没有还是有多个）。
重复这些步骤，直到数组为空。问题是使所选数字的和最大化。
Sample Input 1
2
3
1 2 3
6
1 2 2 2 3 4
Sample Output 1
4
10
说明：对第二组6个的用例：
先排序，排完从最大的开始删，删完4，然后4-1=3，选一个3删了发现只有一个正好；然后重复，删最大的2，把2-1=1也删了；再删2，没有1了；再删2；结束。4+2+2+2=10
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main4_3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int tnum=Integer.parseInt(in.nextLine());
        for(int tcase=0;tcase<tnum;tcase++){
            if(tcase>0)
                System.out.println();
            int n=Integer.parseInt(in.nextLine());
            String []str=in.nextLine().split(" ");
            ArrayList<Integer> a=new ArrayList<>();
            for(int i=0; i<n; i++)
                a.add(Integer.parseInt(str[i]));
            //先排序
            Collections.sort(a);
            int sum=0;
            //排完开始从最大的数删
            while(!a.isEmpty()) {
                //记录下最大的数字值，并删了，然后求和
                int x = a.remove(a.size()-1);
                sum += x;
                //查一下还有没有x-1这个数，有的话返回1，一起也删了
                int ind= Collections.binarySearch(a, (x-1));
                if(ind>=0)
                    a.remove(ind);
            }
            System.out.print(sum);
        }
        in.close();
    }
}
