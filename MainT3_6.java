package ojtest;
/*
广度优先遍历图
Description
按照给定的起始顶点广度优先遍历图，每一次通过字母顺序选择顶点查找下一层邻接点，打印遍历顺序。
Input
输入第一行为测试用例个数，后面每一个用例用多行表示，用例第一行是节点个数n和开始顶点，用空格隔开，后面n+1行为图的邻接矩阵，其中第一行为节点名称。值之间使用空格隔开。
Output
输出遍历顺序，用空格隔开
Sample Input 1
1
4 a
a b c d
a 0 1 1 0
b 1 0 1 0
c 1 1 0 1
d 0 0 1 0
Sample Output 1
a b c d
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MainT3_6 {
    public static int max = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = Integer.parseInt(sc.nextLine().trim());
        for (int i = 0; i < cases; i++) {
            String line = sc.nextLine();
            int nums = Integer.parseInt(line.split(" ")[0]);
            String start = line.split(" ")[1];
            String[] points = sc.nextLine().trim().split(" ");
            boolean[][] edges = new boolean[nums][nums];
            for (int j = 0; j < nums; j++) {
                int[] relations = Arrays.stream(sc.nextLine().trim().split(" ")).skip(1).mapToInt(Integer::parseInt).toArray();
                for (int k = 0; k < relations.length; k++) {
                    if (relations[k] == 1) {
                        edges[j][k] = true;
                    }
                }
            }
            max = 0;
            int startIndex = 0;
            for (int j = 0; j < nums; j++) {
                if (points[j].equals(start)) {
                    startIndex = j;
                    break;
                }
            }
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(startIndex);
            int count = 0;
            boolean[] visited = new boolean[nums];
            while (!queue.isEmpty()) {
                int current = queue.poll();
                count++;
                System.out.print(points[current]);
                if (count != nums) System.out.print(" ");
                visited[current] = true;
                for (int j = 0; j < nums; j++) {
                    if (edges[current][j] && !visited[j] && !queue.contains(j)) {
                        queue.offer(j);
                    }
                }
            }
        }
    }
}
