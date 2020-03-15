package ojtest;
/*
深度优先遍历
Description
按照给定的起始顶点深度优先遍历给定的无向图，尝试所有可能的遍历方式，打印遍历过程中出现的最大深度。
Input
输入第一行是用例个数，后面每个用例使用多行表示，用例的第一行是图中节点的个数n和起始点，用空格隔开，后面n+1行为图的邻接矩阵，其中第一行为节点名称。值之间使用空格隔开。
Output
输出深度优先遍历中遇到的最大深度。
Sample Input 1
1
4 a
a b c d
a 0 1 1 0
b 1 0 1 0
c 1 1 0 1
d 0 0 1 0
Sample Output 1
4
 */

import java.util.Arrays;
        import java.util.Scanner;

public class MainT3_5 {
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
            boolean[] visited = new boolean[nums];
            visited[startIndex] = true;
            dfs(startIndex, edges, visited, 1);
            System.out.println(max);
        }
    }
    public static void dfs(int i, boolean[][] edges, boolean[] visited, int depth) {
        if (max < depth) {
            max = depth;
        }
        for (int j = 0; j < edges[i].length; j++) {
            if (edges[i][j] && !visited[j]) {
                visited[j] = true;
                dfs(j, edges, visited, depth + 1);
                visited[j] = false;
            }
        }
    }
}
