package ojtest;
/*
能否成环
描述
Given an array of strings A[ ], determine if the strings can be chained together to form a circle. A string X can be chained together with another string Y if the last character of X is same as first character of Y. If every string of the array can be chained, it will form a circle. For example, for the array arr[] = {"for", "geek", "rig", "kaf"} the answer will be Yes as the given strings can be chained as "for", "rig", "geek" and "kaf".
输入
The first line of input contains an integer T denoting the number of test cases. Then T test cases follow.
The first line of each test case contains a positive integer N, denoting the size of the array.
The second line of each test case contains a N space seprated strings, denoting the elements of the array A[ ].
1 <= T
0 < N
0 < A[i]
输出
If chain can be formed, then print 1, else print 0.
输入样例 1
2
3
abc bcd cdf
4
ab bc cd da
输出样例 1
0
1
 */


import java.util.*;

public class Main3_7 {
    static char first;

    public static int ifCircle(List<String> list) {
        //给first赋值
        first = list.get(0).charAt(0);
        if (isCircle(list))
            return 1;
        else
            return 0;
    }

    public static boolean isCircle(List<String> list) {
        //System.out.println("size" + list.size());
        String cur = list.get(0);
        //字符串长度为1，返回true；
        if (list.size() == 1)
            return cur.charAt(cur.length() - 1) == first;
        //当字符串数量为多个时，当存在能够衔接的字符串，去掉这两个字符串，重造字符串数组，递归调用iscircle
        for (int i = 1; i < list.size(); i++) {
            if (cur.charAt(cur.length() - 1) == list.get(i).charAt(0)) {
                List<String> nList = new ArrayList<>(list);
                nList.add(0, nList.remove(i));
                nList.remove(1);
                return isCircle(nList);
            }
        }
        return false;
    }
    //读取输入，调用ifcircle
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int rounds = Integer.valueOf(scan.nextLine());
        for (int i = 0; i < rounds; i++) {
            scan.nextLine();
            String lines = scan.nextLine();
            String[] arrS = lines.split(" ");
            List<String> line = new ArrayList<>();
            for (int j = 0; j < arrS.length; j++) {
                line.add(arrS[j]);
                //System.out.println(arrS[j]);
            }
            System.out.println(ifCircle(line));
        }

    }
}
