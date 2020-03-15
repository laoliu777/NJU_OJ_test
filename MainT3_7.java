package ojtest;
/*
拓扑排序解的个数
Description
给定有向无环图中所有边，计算图的拓扑排序解的个数。
Input
输入第一行为用例个数，后面每一行表示一个图中的所有边，边的起点和终点用空格隔开，边之间使用逗号隔开。
Output
输出拓扑排序解的个数。
Sample Input 1
1
a c,b c,c d,d e,d f,e g,f g
Sample Output 1
4
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainT3_7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = Integer.parseInt(sc.nextLine().trim());
        for (int i = 0; i < cases; i++) {
            List<String> points = new ArrayList<>();
            String line = sc.nextLine().trim();
            Arrays.stream(line.split(",")).forEach(s -> {
                String[] ps = s.split(" ");
                if (!points.contains(ps[0])) points.add(ps[0]);
                if (!points.contains(ps[1])) points.add(ps[1]);
            });
            int[] pre = new int[points.size()];
            Arrays.stream(line.split(",")).forEach(s -> {
                String[] ps = s.split(" ");
                int u = points.indexOf(ps[0]);
                int v = points.indexOf(ps[1]);
                pre[v] |= 1 << u;
            });
            // System.out.println(points);
            // System.out.println(Arrays.toString(pre));
            long[] dp = new long[1 << points.size()];
            dp[0] = 1;
            int totalStates = 1 << points.size();
            for (int j = 0; j < totalStates; j++) {
                if (dp[j] != 0) {
                    for (int k = 0; k < points.size(); k++) {
                        if (((j & pre[k]) == pre[k]) && ((j & (1 << k)) == 0)) {
                            dp[j | (1 << k)] += dp[j];
                            // System.out.println(j + " " + k + " " + dp[j | (1 << k)]);
                        }
                    }
                }
            }
            System.out.println(dp[(1 << (points.size() - 1)) - 1]);
        }
    }
}
