package ojtest;
/*
按照数值个数排序
Description
对给定数组中的元素按照元素出现的次数排序，出现次数多的排在前面，如果出现次数相同，则按照数值大小排序。例如，给定数组为{2, 3, 2, 4, 5, 12, 2, 3, 3, 3, 12}，则排序后结果为{3, 3, 3, 3, 2, 2, 2, 12, 12, 4, 5}。
Input
输入的第一行为用例个数；后面每一个用例使用两行表示，第一行为数组长度，第二行为数组内容，数组元素间使用空格隔开。
Output
每一个用例的排序结果在一行中输出，元素之间使用空格隔开。
Sample Input 1
1
4
2 3 2 5
Sample Output 1
2 2 3 5
 */
import java.util.*;
import java.util.stream.Collectors;

public class MainT3_8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < cases; i++) {
            int len = Integer.parseInt(sc.nextLine());
            String line = sc.nextLine();
            int[] nums = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < len; j++) {
                Integer cnt = map.get(nums[j]);
                if (cnt == null) map.put(nums[j], 1);
                else  map.put(nums[j], cnt + 1);
            }
            List<Integer> list = map.keySet().stream().collect(Collectors.toList());
            Collections.sort(list, (num1, num2) -> {
                if (map.get(num1) > map.get(num2)) return -1;
                else if (map.get(num1) == map.get(num2)) return num1 > num2 ? 1 : -1;
                else return 1;
            });
            for (int k = 0; k < list.size(); k++) {
                for (int j = 0; j < map.get(list.get(k)); j++) {
                    System.out.print(list.get(k));
                    if (!(k == list.size() - 1 && j == map.get(list.get(k)) - 1)) {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }
    }
}
