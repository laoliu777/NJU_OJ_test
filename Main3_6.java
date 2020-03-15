package ojtest;
/*
（3）和最大的连续降序字符
描述
 问题如下：给定的是一个长度为L的字符串。她的任务是从给定的字符串中找到最长的字符串，该字符串中的字符按其ASCII码的降序排列并以算术级数排列。
 她希望公共差异应尽可能小（至少1），并且字符串的字符应具有较高的ASCII值。
输入
输入的第一行包含一个整数T，它表示测试用例的数量。 每个测试包含一个长度为L的字符串s。
1 <= T <= 100
3 <= L <= 1000
A <= s [i] <= Z
该字符串至少包含三个不同的字符。
输出
对于每个测试用例，打印最长的字符串。
情况1：可以使用两个最大长度的字符串-“CBA”和“ RPQ”。但是他希望该字符串具有较高的ASCII值，因此输出为“ RPQ”。
情况2：最大长度的字符串为“ JGDA”。
输入样例
2
ABCPQR
ADGJPRT
输出样例 1
RQP
JGDA
 */
import java.util.Scanner;

public class Main3_6 {

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-->0) {
            char[] s = sc.next().toCharArray();
            boolean[] ch = new boolean[26];
            for(int i = 0; i<s.length; i++)
                ch[s[i]-'A'] = true;
            String res = "";
            for(int i = 1; i<26; i++) {
                for(int j = 25; j>=0; j--) {
                    if(ch[j]) {
                        String temp = "";
                        temp+=(char)('A'+j);
                        for(int k = j-i; k>=0; k-=i) {
                            if(ch[k])
                                temp+=(char)('A'+k);
                            else
                                break;
                        }
                        if(temp.length()>res.length())
                            res = temp;
                    }
                }
            }
            System.out.println(res);
        }
    }


}
