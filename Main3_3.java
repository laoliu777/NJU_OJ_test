package ojtest;
/*
无限递归字符串查询
考虑字符串A =“12345”。无限字符串s通过对A递归执行无限步骤来构建。在第i步中，A与” $”串联，后跟A的反向。A = A | $ ... $ | reverse（A），其中|表示串联。
约束条件：1 <= Q <= 10 ^ 5，1 <= POS <= 10 ^ 12
Input
输入第一行为查询次数，后面为每次查询的具体字符位置。
Output
输出每一次查询位置上的字符。
Sample Input 1
2
3
10
Sample Output 1
3
2
其实就是一个无线循环的字符串：
12345$54321$$12345$54321$$12345$54321$$12345
 */
import java.util.Scanner;

public class Main3_3 {
    public static void main (String[] args) {
        final String PAT = "12345$54321";

        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-- > 0) {
            long pos = in.nextLong();
            if (pos == 0) System.out.println("");//特殊情况
            while (pos > PAT.length()) {
                long[] iter = findIter(pos); //找到对应pos字符串s应该需要多长，及递归的次数
                long start = (iter[0] + iter[1]) / 2; //找到新一次递归最开始的地方
                pos -= start; //找到pos在最新递归产生的字符串的位置(由于后半段和前半段一样，不断循环最终会在前11位里）
            }

            char res;
            if (pos <= 0) { //当pos <= 0的时候，说明pos在最新一次递归产生的$符那里
                res = '$';
            } else {
                res = PAT.charAt((int)pos - 1);
            }
            System.out.println(res);
        }
    }

    private static long[] findIter(long pos) {
        long len = 5; //字符初始长度
        int it = 0; //递归次数
        while (pos > len) {
            it++;
            len = len * 2 + it;
        }
        return new long[] {len, it};
    }
}
