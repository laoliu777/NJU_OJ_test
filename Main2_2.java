package ojtest;

/*
点的凸包：
Convex Hull of a set of points, in 2D plane, is a convex polygon with minimum area such that each point lies either on the boundary of polygon or inside it. Now given a set of points the task is to find the convex hull of points.
先排序，从左到右；
再找到最上和最下两个极点；
再对这个四边形看每条边有没有外点？
Sample Input 1
2
3
1 2 3 1 5 6
3
1 2 4 4 5 1
Sample Output 1
1 2, 3 1, 5 6
1 2, 4 4, 5 1
 */

import java.awt.Point;
import java.util.*;

public class Main2_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfCases = scanner.nextInt();
        for (int i = 0; i < numOfCases; i++) {
            List<Point> oriPointList = new ArrayList<>();   // 用于存放点
            int numOfPoints = scanner.nextInt();    // 点的个数
            for (int j = 0; j < numOfPoints; j++) {
                Point temp = new Point(scanner.nextInt(), scanner.nextInt());
                oriPointList.add(temp);
            }
            initAndAnalyze(oriPointList);
        }
        scanner.close();
    }

    // 分析并初始化问题
    public static void initAndAnalyze(List<Point> oriPointList) {
        // 确定初始划分直线
        Point minXPoint = new Point(Integer.MAX_VALUE, 0);
        Point maxXPoint = new Point(Integer.MIN_VALUE, 0);
        for (Point thisPoint : oriPointList) {     // 找出具有最小x坐标以及最大x坐标的点
            if (thisPoint.x < minXPoint.x) {
                minXPoint = thisPoint;
            } else if (thisPoint.x == minXPoint.x) {
                if (thisPoint.y < minXPoint.y) {
                    minXPoint = thisPoint;
                }
            }
            if (thisPoint.x > maxXPoint.x) {
                maxXPoint = thisPoint;
            } else if (thisPoint.x == maxXPoint.x) {
                if (thisPoint.y > maxXPoint.y) {
                    maxXPoint = thisPoint;
                }
            }
        }
        // 先看是否都在一条直线上
        boolean isAllOnALine = true;
        for (Point thisPoint : oriPointList) {
            if (!isOnLine(thisPoint, minXPoint, maxXPoint)) {
                isAllOnALine = false;
                break;
            }
        }
        if (isAllOnALine) {     // 全在一条直线上
            StringBuilder result = new StringBuilder();
            sortForResult(oriPointList);
            for (int i = 0; i < oriPointList.size(); i++) {
                Point thisPoint = oriPointList.get(i);
                if (i != 0 && i != oriPointList.size()-1) {
                    result.append(thisPoint.x + " " + thisPoint.y + ", " + thisPoint.x + " " + thisPoint.y + ", ");
                } else {
                    result.append(thisPoint.x + " " + thisPoint.y + ", ");
                }
            }
            result.deleteCharAt(result.length() - 1);
            result.deleteCharAt(result.length() - 1);
            System.out.println(result);
        } else {    // 不全都在一条直线上
            // 解决问题
            solveUp(minXPoint, maxXPoint, oriPointList);
            solveDown(minXPoint, maxXPoint, oriPointList);
            // 按要求排序并输出结果
            sortForResult(oriPointList);
            StringBuilder result = new StringBuilder();
            for (Point thisPoint : oriPointList) {
                result.append(thisPoint.x + " " + thisPoint.y + ", ");
            }
            result.deleteCharAt(result.length() - 1);
            result.deleteCharAt(result.length() - 1);
            System.out.println(result);
        }
    }

    // 解决直线上方问题
    public static void solveUp(Point A, Point B, List<Point> oriPointList) {
        Point needPoint = null;
        double maxDisOfPL = Double.MIN_VALUE;
        Iterator<Point> iter1 = oriPointList.iterator();
        while (iter1.hasNext()) {  // 寻找上侧距离该直线最远的点
            Point thisPoint = iter1.next();
            if (!(thisPoint.equals(A) || thisPoint.equals(B))) {    // 不是A或者B
                if (A.x != B.x) {   // 直线不是竖直的情况下
                    if (isUpToLine(thisPoint, A, B)) {  // 是否在 上
                        double newDisOfPL = getDisOfPL(thisPoint, A, B);
                        if (newDisOfPL > maxDisOfPL) {  // 距离最大则替换
                            maxDisOfPL = newDisOfPL;
                            needPoint = thisPoint;
                        }
                    }
                }
            }
        }
        // 终止条件
        if (needPoint == null) {    // 没找到该点
            return;
        }
        // 三角形内的点删除（不包括边上的）
        Iterator<Point> iter2 = oriPointList.iterator();
        while (iter2.hasNext()) {
            Point thisPoint = iter2.next();
            if (!(thisPoint.equals(A) || thisPoint.equals(B) || thisPoint.equals(needPoint))) {
                if (!(isOnLine(thisPoint, A, B) || isOnLine(thisPoint, A, needPoint) || isOnLine(thisPoint, needPoint, B))) {
                    if (isInTriangle(thisPoint, A, B, needPoint)) {
                        iter2.remove();
                    }
                }
            }
        }
        // 分解问题
        solveUp(A, needPoint, oriPointList);
        solveUp(B, needPoint, oriPointList);
    }

    // 解决直线下方问题
    public static void solveDown(Point A, Point B, List<Point> oriPointList) {
        Point needPoint = null;
        double maxDisOfPL = Double.MIN_VALUE;
        Iterator<Point> iter1 = oriPointList.iterator();
        while (iter1.hasNext()) {  // 寻找下侧距离该直线最远的点
            Point thisPoint = iter1.next();
            if (!(thisPoint.equals(A) || thisPoint.equals(B))) {    // 不是A或者B
                if (A.x != B.x) {   // 直线不是竖直的情况下
                    if (!isUpToLine(thisPoint, A, B)) {  // 是否在 下
                        double newDisOfPL = getDisOfPL(thisPoint, A, B);
                        if (newDisOfPL > maxDisOfPL) {  // 距离最大则替换
                            maxDisOfPL = newDisOfPL;
                            needPoint = thisPoint;
                        }
                    }
                }
            }
        }
        // 终止条件
        if (needPoint == null) {
            return;
        }
        // 三角形内的点删除（不包括边上的）
        Iterator<Point> iter2 = oriPointList.iterator();
        while (iter2.hasNext()) {
            Point thisPoint = iter2.next();
            if (!(thisPoint.equals(A) || thisPoint.equals(B) || thisPoint.equals(needPoint))) {
                if (!(isOnLine(thisPoint, A, B) || isOnLine(thisPoint, A, needPoint) || isOnLine(thisPoint, needPoint, B))) {
                    if (isInTriangle(thisPoint, A, B, needPoint)) {
                        iter2.remove();
                    }
                }
            }
        }
        // 分解问题
        solveDown(A, needPoint, oriPointList);
        solveDown(B, needPoint, oriPointList);
    }

    // 判断点在直线的 上 或 下
    public static boolean isUpToLine(Point X, Point A, Point B) {
        boolean isUp = false;
        if (A.y == B.y) {    // 直线水平
            if (X.y > A.y) {    // 满足
                isUp = true;
            }
        } else {    // 直线倾斜
            // 直线上的两点P1(X1,Y1) P2(X2,Y2)。则直线的一般式方程AX+BY+C=0中，A B C分别等于：
            //  A = Y2 - Y1
            //  B = X1 - X2
            //  C = X2*Y1 - X1*Y2
            double a = B.y - A.y;
            double b = A.x - B.x;
            double c = B.x * A.y - A.x * B.y;
            if (b < 0) {    // 保证y的系数大于0
                a = -a;
                b = -b;
                c = -c;
            }
            double result = a * X.x + b * X.y + c;
            // 把直线方程写成一般是ax+by+c=0
            // 然后把点带入左边，在b>0时（即要保证x的系数大于0），若大于0则在直线上方,若小于0则在直线下方
            if (result > 0) {
                isUp = true;
            }
        }
        return isUp;
    }

    // 判断点是否在三个点围成的三角形内
    public static boolean isInTriangle(Point X, Point A, Point B, Point C) {
        double ABC = getTriAngleArea(A, B, C);
        double ABX = getTriAngleArea(A, B, X);
        double ACX = getTriAngleArea(A, C, X);
        double BCX = getTriAngleArea(B, C, X);
        // 若面积之和等于原三角形面积，证明点在三角形内
        if (ABX + ACX + BCX == ABC) {
            return true;
        } else {
            return false;
        }
    }

    // 得到三角形面积
    public static double getTriAngleArea(Point A, Point B, Point C) {
        // 公式：S = 1/2 * (x1y2+x2y3+x3y1-x1y3-x2y1-x3y2)
        double result = Math.abs((A.x*B.y + B.x*C.y + C.x*A.y - A.x*C.y - B.x*A.y - C.x * B.y) / 2.0D);
        return result;
    }

    // 判断点是否在直线上
    public static boolean isOnLine(Point X, Point A, Point B) {
        boolean result = false;
        double a = B.y - A.y;
        double b = A.x - B.x;
        double c = B.x * A.y - A.x * B.y;
        double judge = a * X.x + b * X.y + c;
        if (judge == 0) {
            result = true;
        }
        return result;
    }

    // 得到点到线段距离
    public static double getDisOfPL(Point X, Point A, Point B) {
        // 初始化需要的值
        double a = B.y - A.y;   // 直线一般式：Ax+By+c=0
        double b = A.x - B.x;
        double c = B.x * A.y - A.x * B.y;
        // 利用点到直线距离公式： d = |(ax+by+c) / sqrt(a^2+b^2)|
        double disOfPL = Math.abs((a*X.x + b*X.y + c) / Math.sqrt(a*a + b*b));
        return disOfPL;
    }

    // 按照输出要求对结果进行排序
    public static void sortForResult(List<Point> pointList) {
        for (int i = 0; i < pointList.size(); i++) {
            int minIndex = i;
            for (int j = i+1; j < pointList.size(); j++) {
                Point A = pointList.get(j);
                Point B = pointList.get(minIndex);
                if (isSmallerThan(A, B)) {
                    minIndex = j;
                }
            }
            Point temp = pointList.get(i);
            pointList.set(i, pointList.get(minIndex));
            pointList.set(minIndex, temp);
        }
    }

    // 按规定比较点的大小
    public static boolean isSmallerThan(Point A, Point B) {
        boolean result = false;
        if (A.x == B.x) {
            if (A.y < B.y) {
                result = true;
            }
        } else if (A.x < B.x) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

}
