package ojtest;
/*
分配问题
Description
对给定的n个任务与n个人之间的成本矩阵完成成本最低的任务分配策略。
Input
输入：第一行为用例个数，之后为每一个用例；用例的第一行为任务个数，即n；用例的第二行为使用逗号隔开的人员完成任务的成本；每一个成本描述包括人员序号、任务序号和成本，使用空格隔开。人员序号和任务序号都是从1到n的整数，序号出现的次序没有固定规则。
Output
输出：每一个用例输出一行，从序号为1的人员开始，给出其分配的任务序号，使用空格隔开；使用逗号将多个解隔开。结果按照人员分配的任务序号大小排，第一个人员的任务序号大的放在前面，如果相同则看第二个人员的任务，以此类推。
Sample Input 1
1
4
2 1 6,1 2 2,1 3 7,1 4 8,1 1 9,2 2 4,2 3 3,2 4 7,3 1 5,3 2 8,3 3 1,3 4 8,4 1 7,4 2 6,4 3 9,4 4 4
Sample Output 1
2 1 3 4
 */


import java.util.*;

public class MainT3_1 {
    public static int min = Integer.MAX_VALUE;
    public static List<int[]> solutions;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < cases; i++) {
            int n = Integer.parseInt(sc.nextLine().trim());
            int[][] m = new int[n+1][n+1];
            Arrays.stream(sc.nextLine().trim().split(",")).forEach(s -> {
                int[] nums = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();
                m[nums[0]][nums[1]] = nums[2];
            });
            min = Integer.MAX_VALUE;
            solutions = new ArrayList<>();
            dfs(1, m, new int[n + 1], new boolean[n + 1], 0, n);
            // System.out.println(min);
            Collections.sort(solutions, (a, b)-> {
                for (int j = 1; j <= n; j++) {
                    if (a[j] > b[j]) {
                        return -1;
                    } else if (a[j] < b[j]) {
                        return 1;
                    }
                }
                return 0;
            });
            for (int j = 0; j < solutions.size(); j++) {
                int[] arr =solutions.get(j);
                for (int k = 1; k <= n; k++) {
                    System.out.print(arr[k]);
                    if (k != n) System.out.print(" ");
                }
                if (j != solutions.size() - 1) System.out.print(",");
            }
            System.out.println();
        }
    }
    public static void dfs(int i, int[][] m, int[] solution, boolean[] visited, int cost, int n) {
        if (i > n && cost <= min) {
            if (cost < min)
                solutions.clear();
            min = cost;
            solutions.add(Arrays.copyOf(solution, solution.length));
        } else if (cost < min) {
            for (int j = 1; j <= n; j++) {
                if (!visited[j] && cost + m[i][j] <= min) {
                    solution[i] = j;
                    visited[j] = true;
                    dfs(i + 1, m, solution, visited, cost + m[i][j], n);
                    visited[j] = false;
                }
            }
        }
    }
}
