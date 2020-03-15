package ojtest;
/*
字符串匹配问题：
给定文本txt[0..n-1]和模式pat[0..m-1]，编写一个函数search（char pat[]，char txt[]），将所有出现的pat[]都打印在txt[]中。您可以假设n> m。
Input
输入第一行是用例个数，后面一行表示一个用例；用例包括两部分，第一部分为给定文本，第二部分为搜索串，两部分使用","隔开。
Output
每一个用例输出一行，每行按照找到的位置先后顺序排列，使用空格隔开。
Sample Input 1
2
THIS IS A TEST TEXT,TEST
AABAACAADAABAABA,AABA
Sample Output 1
10
0 9 12
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Main3_4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a=Integer.parseInt(in.nextLine());
        for(int i=0;i<a;i++){
            if(i>0)
                System.out.println();
            String []str=in.nextLine().split(",");
            String str1=str[0];
            String str2=str[1];
            int[] result = search(str1,str2);
            //char[] c1=str[0].toCharArray();
            for(int j=0;j<result.length;j++){
                if(j>0)
                    System.out.print(" ");
                System.out.print(result[j]);
            }
        }
        in.close();
    }

    private static int[] search(String s1, String s2) {
        ArrayList list = new ArrayList();
        //String s1=c1.toString();
        int l1=s1.length();
        int l2=s2.length();
        for(int i=0;i+l2<=l1;i++){
            String substr1=s1.substring(i,i+l2);
            if(substr1.equals(s2))
                list.add(i);
        }
        int[] res = new int[list.size()];
        for(int i = 0;i<list.size();i++){
            res[i] = (int) list.get(i);
        }
        return res;
    }


}
