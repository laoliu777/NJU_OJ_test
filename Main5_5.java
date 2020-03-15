package ojtest;
/*
路上的球    !!!
描述
    There are two parallel roads, each containing N and M buckets, respectively.
    Each bucket may contain some balls.
    The buckets on both roads are kept in such a way that they are sorted according to the number of balls in them.
    Geek starts from the end of the road which has the bucket with a lower number of balls
    (i.e. if buckets are sorted in increasing order, then geek will start from the left side of the road).
    The geek can change the road only at the point of intersection(which means, buckets with the same number of balls on two roads).
    Now you need to help Geek to collect the maximum number of balls.
输入
    The first line of input contains T denoting the number of test cases.
    The first line of each test case contains two integers N and M, denoting the number of buckets on road1 and road2 respectively.
    2nd line of each test case contains N integers, number of balls in buckets on the first road.
    3rd line of each test case contains M integers, number of balls in buckets on the second road.
    Constraints:1<= T <= 1000，1<= N <= 10^3，1<= M <=10^3，0<= A[i],B[i]<=10^6
输出
    For each test case output a single line containing the maximum possible balls that Geek can collect.
输入样例 1
1
5 5
1 4 5 6 8
2 3 4 6 9
输出样例 1
29
2+3+4+5+6+9
*/

import java.util.Scanner;


public class Main5_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfCases = scanner.nextInt();
        while (numOfCases-- > 0) {
            int numOnRoad1 = scanner.nextInt();
            int numOnRoad2 = scanner.nextInt();
            int[] road1 = new int[numOnRoad1];
            for (int i = 0; i < numOnRoad1; i++) {
                road1[i] = scanner.nextInt();
            }
            int[] road2 = new int[numOnRoad2];
            for (int i = 0; i < numOnRoad2; i++) {
                road2[i] = scanner.nextInt();
            }
            System.out.println(getMaxOfCollectBalls(road1, road2, numOnRoad1, numOnRoad2));
        }
        scanner.close();
    }

    // 得到可以收集到的最大球数
    public static int getMaxOfCollectBalls(int[] road1, int[]road2, int numOnRoad1, int numOnRoad2) {
        int pos1 = 0;
        int pos2 = 0;
        int firRoadCollected = 0;
        int secRoadCollected = 0;
        int result = 0;
        // 两路同时开始，并在数字相等时：比较大小然后总和前面的结果，并为下一次循环做准备
        while (pos1 < numOnRoad1 && pos2 < numOnRoad2) {
            if (road1[pos1] < road2[pos2]) {    // 走第一条
                firRoadCollected += road1[pos1];
                pos1++;
            } else if (road1[pos1] > road2[pos2]) {     // 走第二条
                secRoadCollected += road2[pos2];
                pos2++;
            } else {    // 遇到相等的数字时停下
                int intersection = road1[pos1];     // 当前的相等球数
                int partSummary = Math.max(firRoadCollected, secRoadCollected);     // 看前面走哪一条收集的球多
                result += (partSummary + intersection);  // 当前结果即为：前面较多的一路收集的球数 + 当前球数相等的这部分
                // 两路临时计数清空
                firRoadCollected = 0;
                secRoadCollected = 0;
                // 位置前移
                pos1++;
                pos2++;
            }
        }
        // 走某一条路没有走完的部分
        // 此时的 firRoadCollected 与 secRoadCollected 记录着最后一次相等时到现在的数量
        // 此时继续累加为最后总结比较做准备
        while (pos1 < numOnRoad1) {
            firRoadCollected += road1[pos1];
            pos1++;
        }
        while (pos2 < numOnRoad2) {
            secRoadCollected += road2[pos2];
            pos2++;
        }
        // 最后总结比较
        int lastSummary = Math.max(firRoadCollected, secRoadCollected);
        result += lastSummary;
        return result;
    }
}
