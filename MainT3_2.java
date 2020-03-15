package ojtest;
/*
KD树构造和查找
Description
对给定的点集合构造KD树，要求如下：将方差最大的维度作为当前的分割维度，将数据集在分割维度上排序后的中位数作为分割点。程序要检索给定点对应的最近的K个点的坐标。
Input
输入第一行为测试用例个数，后面为测试用例，每一个用例包含三行，第一行为点集合（点之间用逗号隔开，两个坐标用空格隔开），第二行为检索的点，第三行为K值。
Output
输出每一个用例的最近K个点，按照距离从小到大的顺序打印。
Sample Input 1
1
3 5,6 2,5 8,9 3,8 6,1 1,2 9
8.2 4.6
2
Sample Output 1
8 6,9 3
 */
import java.text.DecimalFormat;
import java.util.*;

public class MainT3_2 {
    public static double[] nearest;
    public static Point[] s_points;
    public static int count = 0;
    public static Node root;
    public static Stack<Node> stack;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
        int cases = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < cases; i++) {
            String[] pts = sc.nextLine().trim().split(",");
            Point[] points = new Point[pts.length];
            for (int j = 0; j < pts.length; j++) {
                double[] nums = Arrays.stream(pts[j].split(" ")).mapToDouble(Double::parseDouble).toArray();
                points[j] = new Point(nums[0], nums[1]);
            }
            double[] q_points = Arrays.stream(sc.nextLine().trim().split(" ")).mapToDouble(Double::parseDouble).toArray();
            int k = Integer.parseInt(sc.nextLine());
            root = build(points);
            nearest = new double[k];
            s_points = new Point[k];
            count = 0;
            stack = new Stack<>();
            // printTree(root,0);
            search(root, q_points[0], q_points[1], k);
            // System.out.println(Arrays.toString(nearest));
            // System.out.println(count);
            Arrays.sort(s_points, (p1, p2) -> {
                return distance(q_points[0], q_points[1], p1) < distance(q_points[0], q_points[1], p2) ? -1 : 1;
            });
            for (int j = 0; j < count; j++) {
                System.out.print(decimalFormat.format(s_points[j].x) + " " + decimalFormat.format(s_points[j].y));
                if (j != count - 1) System.out.print(",");
            }
            System.out.println();
        }
    }
    public static double distance(Point a, Point b) {
        return Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2);
    }
    public static int maxIndex(double[] arr) {
        double r = Double.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > r) {
                r = arr[i];
                index = i;
            }
        }
        return index;
    }
    public static double distance(double x, double y, Point b) {
        return Math.pow(x - b.x, 2) + Math.pow(y - b.y, 2);
    }
    public static void search(Node node, double x, double y, int k) {
        if (node == null) return;
        while (!node.isLeaf) {
            stack.add(node);
            double judge = node.r == 0 ? x : y;
            if (node.value > judge) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        double dis = distance(x, y, node.point);
        nearest[count] = dis;
        s_points[count] = node.point;
        count++;
        while (!stack.isEmpty()) {
            Node n = stack.pop();
            if (n == null) continue;
            double d = distance(x, y, n.point);
            if (count < k) {
                nearest[count] = d;
                s_points[count] = n.point;
                count++;
            } else {
                int maxIndex = maxIndex(nearest);
                if (nearest[maxIndex] > d) {
                    nearest[maxIndex] = d;
                    s_points[maxIndex] = n.point;
                }
            }
            double j = n.r == 0 ? x : y;
            double r = Math.pow(n.value - j, 2);
            int maxIndex = maxIndex(nearest);
//            System.out.println(d + " " + r +" "+ n.point + " " +nearest[maxIndex]);
            if (nearest[maxIndex] > r) {
                Node tmp = n;
                if (n.value > j) {
                    tmp = n.right;
                } else tmp = n.left;
                while (tmp!=null && !tmp.isLeaf) {
                    stack.add(tmp);
                    double jj = tmp.r == 0 ? x : y;
                    if (tmp.value > jj) {
                        tmp = tmp.left;
                    } else tmp = tmp.right;
                }
                stack.add(tmp);
            }
        }
    }
    public static double average(Point[] points, int dimension) {
        double sum = 0.0;
        for (int i = 0; i < points.length; i++) {
            if (dimension == 0) {
                sum += points[i].x;
            } else sum += points[i].y;
        }
        return sum / points.length;
    }
    public static double variance(Point[] points, int dimension) {
        double average = average(points, dimension);
        double sum = 0.0;
        for (int i = 0; i < points.length; i++) {
            if (dimension == 0) {
                sum += (points[i].x - average) * (points[i].x - average);
            } else sum += (points[i].y - average) * (points[i].y - average);
        }
        return sum / points.length;
    }

    public static Point getMidPoint(Point[] points, int dimension) {
        Point[] sorted = Arrays.stream(points).sorted((p1, p2) -> {
            return dimension == 0 ? ((p1.x > p2.x) ? 1 : -1) : ((p1.y > p2.y) ? 1 : -1);
        }).toArray(Point[]::new);
//        if (sorted.length % 2 == 0) {
//            double sum = dimension == 0 ? (sorted[sorted.length / 2].x + sorted[sorted.length / 2 - 1].x) : (sorted[sorted.length / 2].y + sorted[sorted.length / 2 - 1].y);
//            return sum / 2.0;
//        }
        return sorted[sorted.length / 2];
    }

    public static List<Point[]> splitByDimension(Point[] points, int dimension, double mid) {
        Point[] less = Arrays.stream(points).filter(p -> dimension == 0 ? p.x < mid : p.y < mid).toArray(Point[]::new);
        Point[] more = Arrays.stream(points).filter(p -> dimension == 0 ? p.x > mid : p.y > mid).toArray(Point[]::new);
        List<Point[]> list = new ArrayList<>();
        list.add(less);
        list.add(more);
        return list;
    }
    public static void printTree(Node node, int level) {
        if (node == null) return;
        System.out.println("Level " +level +": " +node.point + " r:" + node.r);
        printTree(node.left, level+1);
        printTree(node.right,level+1);
    }
    public static Node build(Point[] points) {
        if (points.length == 0) return null;
        Node node = new Node();
        if (points.length == 1) {
            node.isLeaf = true;
            node.point = points[0];
            return node;
        }
        node.isLeaf = false;
        double x_variance = variance(points, 0);
        double y_variance = variance(points, 1);
        int dimension = 0;
        if (y_variance > x_variance) dimension = 1;
        Point mid = getMidPoint(points, dimension);
        List<Point[]> list = splitByDimension(points, dimension, dimension == 0 ? mid.x : mid.y);
        node.r = dimension;
        node.point = mid;
        node.value = dimension == 0 ? mid.x : mid.y;
        node.left = build(list.get(0));
        node.right = build(list.get(1));
        return node;
    }
    public static class Point {
        public double x;
        public double y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }
    public static class Node {
        public int r; //0x1y
        public double value;
        public Node left;
        public Node right;
        public Point point;
        public boolean isLeaf;
    }
}
