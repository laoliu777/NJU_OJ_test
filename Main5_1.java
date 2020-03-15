package ojtest;
/*
管道网络
殖民地中的每个房屋最多只能有一个管道进入，最多只能有一个管道离开。
储罐和水龙头的安装方式应是，每户只有一根出水管但没有进水管的房屋都在其屋顶上安装了一个储水箱，而每户只有一根进水管而没有出水管的房屋都应安装水龙头。
寻找构建管网的有效方法。
Input
第一行包含一个整数T，表示测试用例的数量。 对于每个测试用例，第一行包含两个整数n＆p，分别表示房屋数量和管道数量。
接下来，p行包含3个整数输入a，b和d，d表示从房屋a到房屋b的管道的直径。约束：1 <= T <= 50，1 <= n <= 20，1 <= p <= 50，1 <= a，b <= 20，1 <= d <= 100
Output
For each test case, the output is the number of pairs of tanks and taps installed i.e n followed by n lines that contain three integers: house number of tank, house number of tap and the minimum diameter of pipe between them.
Sample Input 1
1
9 6
7 4 98
5 9 72
4 6 10
2 8 22
9 7 17
3 1 66
Sample Output 1
3
2 8 22
3 1 66
5 6 10

 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main5_1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfCases = Integer.parseInt(scanner.nextLine());
        while (numOfCases-- > 0) {
            String[] tempInput1 = scanner.nextLine().split(" ");
            int numOfHouses = Integer.parseInt(tempInput1[0]);
            int numOfPipes = Integer.parseInt(tempInput1[1]);
            int[][] base = new int[numOfPipes][3];
            for (int i = 0; i < numOfPipes; i++) {
                String[] tempInput2 = scanner.nextLine().split(" ");
                base[i][0] = Integer.parseInt(tempInput2[0]);
                base[i][1] = Integer.parseInt(tempInput2[1]);
                base[i][2] = Integer.parseInt(tempInput2[2]);
            }
            solve(numOfHouses, numOfPipes, base);
        }
        scanner.close();
    }

    // 解决该问题
    public static void solve(int numOfHouses, int numOfPipes, int[][] base) {
        // 各house的前一点、下一点以及两house间的管道直径
        int[] preHouse = new int[numOfHouses + 1];
        int[] nextHouse = new int[numOfHouses + 1];
        int[] diameters = new int[numOfHouses + 1];
        // 根据题意采集相应信息
        for (int i = 0; i < numOfPipes; i++) {
            preHouse[base[i][1]] = base[i][0];
            nextHouse[base[i][0]] = base[i][1];
            diameters[base[i][0]] = base[i][2];  // 两house间的管道直径（记录在出点上）
        }
        // 存放结果
        List<String> result = new ArrayList<>();
        // 遍历寻找
        for (int initHouse = 1; initHouse < numOfHouses; initHouse++) {
            // 判断是否为起始house，即preHouse为空（这里表示为0）
            if (preHouse[initHouse] == 0 && nextHouse[initHouse] != 0) {
                int[] minDiameter = new int[1];
                minDiameter[0] = Integer.MAX_VALUE;
                int endHouse = findNext(initHouse, minDiameter, nextHouse, diameters);
                String path = initHouse + " " + endHouse + " " + minDiameter[0];   // 构造一条路径
                result.add(path);
            }
        }
        // 输出结果
        System.out.println(result.size());
        for (String path : result) {
            System.out.println(path);
        }
    }

    // 寻找下一个（DFS）
    public static int findNext(int curHouse, int[] minDiameter, int[] nextHouse, int[] diameters) {
        // 是否为终点
        if (nextHouse[curHouse] == 0) {
            return curHouse;
        }
        // 根据题意寻找路径上的最小直径
        if (diameters[curHouse] < minDiameter[0]) {
            minDiameter[0] = diameters[curHouse];
        }
        // 不为终点，则继续寻找
        return findNext(nextHouse[curHouse], minDiameter, nextHouse, diameters);
    }

}
