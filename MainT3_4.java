package ojtest;
/*
棋盘覆盖问题
Description
棋盘覆盖问题：给定一个大小为2^n2^n个小方格的棋盘，其中有一个位置已经被填充，现在要用一个L型（22个小方格组成的大方格中去掉其中一个小方格）形状去覆盖剩下的小方格。求出覆盖方案，即哪些坐标下的小方格使用同一个L型格子覆盖。注意：坐标从0开始。左上方的第一个格子坐标为(0,0)，第一行第二个坐标为(0,1)，第二行第一个为(1,0)，以此类推。
Input
输入第一行为测试用例个数，后面每一个用例有两行，第一行为n值和特殊的格子的坐标（用空格隔开），第二行为需要查找其属于同一个L型格子的格子坐标。
Output
输出每一行为一个用例的解，先按照行值从小到大、再按照列值从小到大的顺序输出每一个用例的两个坐标；用逗号隔开。
Sample Input 1
1
1 1 1
0 0
Sample Output 1
0 1,1 0
 */

import java.util.Scanner;
import java.util.Arrays;

public class MainT3_4 {
    public static int[][] q;
    public static int l_count;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < cases; i++) {
            int[] nums = Arrays.stream(sc.nextLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            int n = nums[0];
            int x = nums[1];
            int y = nums[2];
            nums = Arrays.stream(sc.nextLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            int q_x = nums[0];
            int q_y = nums[1];
            q = new int[1 << n][1 << n];
            l_count = 1;
            q[x][y] = -1;
            solve(0, 0, n, x, y);
            int maxLen = 1 << n;
            int x1 = -1,y1 = -1,x2 = -1,y2 = -1;
//            for (int j = 0; j < maxLen; j++) {
//                System.out.println(Arrays.toString(q[j]));
//            }
            for (int j = -1; j < 2; j++) {
                for (int k = -1; k < 2; k++) {
                    if (q_x + j >= 0
                            && q_x + j < maxLen
                            && q_y + k >= 0
                            && q_y + k < maxLen
                            && !(j == 0 && k == 0)
                            && q[q_x + j][q_y + k] == q[q_x][q_y]) {
                        if (x1 == -1) {
                            x1 = q_x + j;
                            y1 = q_y + k;
                        } else {
                            x2 = q_x + j;
                            y2 = q_y + k;
                        }
                    }
                }
            }
            System.out.println(x1 + " " + y1 + "," + x2 + " " + y2);
        }
    }
    public static void solve(int start, int end, int n, int x, int y) {
        if (n == 1) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    if (start + i == x && end + j == y) {
                        continue;
                    }
                    q[start + i][end + j] = l_count;
                }
            }
            l_count++;
        } else {
            int sub = 1 << (n - 1);
            int block = (x >= (start + sub) ? 1 : 0) + (y >= (end + sub) ? 2 : 0);
            if (block == 0) {
                q[start + sub][end + sub - 1] = l_count;
                q[start + sub - 1][end + sub] = l_count;
                q[start + sub][end + sub] = l_count;
                l_count++;
                solve(start, end, n - 1, x, y);
                solve(start + sub, end, n - 1, start + sub, end + sub - 1);
                solve(start, end + sub, n - 1, start + sub - 1, end + sub);
                solve(start + sub, end + sub, n - 1, start + sub, end + sub);
            }
            if (block == 1) {
                q[start + sub - 1][end + sub - 1] = l_count;
                q[start + sub - 1][end + sub] = l_count;
                q[start + sub][end + sub] = l_count;
                l_count ++;
                solve(start, end, n - 1, start + sub - 1, end + sub - 1);
                solve(start + sub, end, n - 1, x, y);
                solve(start, end + sub, n - 1, start + sub - 1, end + sub);
                solve(start + sub, end + sub, n - 1, start + sub, end + sub);
            }
            if (block == 2) {
                q[start + sub - 1][end + sub - 1] = l_count;
                q[start + sub][end + sub - 1] = l_count;
                q[start + sub][end + sub] = l_count;
                l_count ++;
                solve(start, end, n - 1, start + sub - 1, end + sub - 1);
                solve(start + sub, end, n - 1, start + sub, end + sub - 1);
                solve(start, end + sub, n - 1, x, y);
                solve(start + sub, end + sub, n - 1, start + sub, end + sub);
            }
            if (block == 3) {
                q[start + sub - 1][end + sub - 1] = l_count;
                q[start + sub - 1][end + sub] = l_count;
                q[start + sub][end + sub - 1] = l_count;
                l_count ++;
                solve(start, end, n - 1, start + sub - 1, end + sub - 1);
                solve(start + sub, end, n - 1, start + sub, end + sub - 1);
                solve(start, end + sub, n - 1, start + sub - 1, end + sub);
                solve(start + sub, end + sub, n - 1, x, y);
            }
        }
    }
}
