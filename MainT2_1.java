package ojtest;
/*
最长公共子序列：
Description
给定两个字符串，返回两个字符串的最长公共子序列（不是最长公共子字符串），可能是多个。
Input
输入第一行为用例个数， 每个测试用例输入为两行，一行一个字符串
Output
如果没有公共子序列，不输出，如果有多个则分为多行，按字典序排序。
Sample Input
1
1A2BD3G4H56JK
23EFG4I5J6K7
Sample Output
23G456K
23G45JK
 */

import java.util.*;

public class MainT2_1 {
    //用一个set类似于动态数组或者链表（但是set重复的不会插入）存最后得到的所有字符串
    public static Set<String> all_lcs= new HashSet<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a=Integer.parseInt(in.nextLine());
        for(int i=0;i<a;i++){
            if(i>0) System.out.println();
            all_lcs.clear();
            String str1=in.nextLine();
            String str2=in.nextLine();
            commonseq(str1,str2);
            //把set先转成数组
            String[] all_str=new String[all_lcs.size()];
            int str_num=0;
            for (String str : all_lcs) {
                all_str[str_num]=str;
                str_num++;
            }
            Arrays.sort(all_str);
            for(int j=0;j<all_str.length;j++){
                if(j>0) System.out.println();
                System.out.print(all_str[j]);
            }
            /*法二的解答：
            List<String> res = LCS(str1, str2);
            for (String resString : res) {
                System.out.println(resString);
            }
             */
         }
        in.close();
    }

    /*首先理解递归的公式：
    if(x[i]=y[j]) c(i,j)=c(i-1,j-1)+1;
    if(x[i]!=y[j]) c(i,j)=max{c(i-1,j),c(i,j-1)};
     */
    private static void commonseq(String str1, String str2) {
        int l1= str1.length();
        int l2= str2.length();
        /*
        创建table矩阵，记录重复的字母：怎么计算数字？就用刚刚的公式来计算！
          x a b c
        y 0 0 0 0
        a 0 1 1 1
        a 0 1 1 1
        c 0 1 1 2
        从1,1开始出发，比较左和上谁大就取谁，然后继续走
         */
        //先把矩阵里的数都求出来，其实如果只要求序列长度的话，右下角的数就是，但是要打印出所有的序列，得画表求
        int[][] table=new int[l1+1][l2+1];
        for (int i = 0; i <= l1; i++) {
            table[i][0] = 0;
        }
        for (int j = 0; j <= l2; j++) {
            table[0][j] = 0;
        }
        for(int i=1;i<=l1;i++)
            for(int j=1;j<=l2;j++){
                if(str1.charAt(i-1)==str2.charAt(j-1))
                    table[i][j]=table[i-1][j-1]+1;
                else if(table[i][j-1]>table[i-1][j])
                    table[i][j]=table[i][j-1];
                else
                    table[i][j]=table[i-1][j];
            }
        //矩阵画完，开始从右下角遍历
        String lcs_str = "";
        PrintAllLCS(str1, str2, l1, l2, table, lcs_str);
    }

    //该函数找出所有的LCS的序列，并将其存在set(all_lcs)中，递归求解！
    public static void PrintAllLCS(String str1, String str2, int i, int j, int[][] table, String lcs_str) {
        //注意这里形参lcs_str不可以为引用，这里需要每次调用lcs_str都重新生成一个对象
        while (i > 0 && j > 0) {
            if (str1.charAt(i-1)== str2.charAt(j-1)) {
                lcs_str = str1.charAt(i-1) + lcs_str ; //逆向存放，每次把得到的字母放在最前面！不是最后面！
                --i;
                --j;
            }
            else {
                if (table[i - 1][j] > table[i][j - 1]) //向上走
                    --i;
                else if (table[i - 1][j] < table[i][j - 1]) //向左走
                    --j;
                else { //此时向上向右均为LCS的元素
                    PrintAllLCS(str1, str2, i - 1, j, table, lcs_str);
                    PrintAllLCS(str1, str2, i, j - 1, table, lcs_str);
                    return;
                }
            }
        }
        all_lcs.add(lcs_str);
    }


    //写法二：
    private static List<String> LCS(String str1, String str2) {
        char[] cstr1 = str1.toCharArray();
        char[] cstr2 = str2.toCharArray();
        int[][] dp = fillDP(cstr1, cstr2);
        int m = cstr1.length, n = cstr2.length;

        int maxLen = dp[m][n];

        char[] resCharArray = new char[maxLen];

        Set<String> resSet = new HashSet<String>();
        getLCSString(cstr1, cstr2, m, n, dp, maxLen, resCharArray, resSet);

        List<String> resList = new ArrayList<String>();
        for(String temp : resSet) {
            resList.add(temp.trim());
        }
        Collections.sort(resList);
        return resList;
    }

    private static int[][] fillDP(char[] str1, char[] str2) {
        int m = str1.length, n = str2.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp;
    }

    private static void getLCSString(char[] str1, char[] str2, int i, int j, int[][] dp, int index, char[] resCharArray, Set<String> res) {

        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
            return;
        }

        while (index > 0) {
            // dp[i][j]同时等于dp[i][j-1]和dp[i-1][j]时，说明此时有两种路径得到最长公共子序列
            if (i > 1 && j > 1 && dp[i][j] == dp[i][j - 1] && dp[i][j] == dp[i - 1][j]) {
                // 要往上(i-1)回溯，往左(j-1)回溯
                getLCSString(str1, str2, i - 1, j, dp, index, resCharArray, res);
                getLCSString(str1, str2, i, j - 1, dp, index, resCharArray, res);
                return;
            } else if (i > 1 && dp[i][j] == dp[i - 1][j]) {
                i--;
            } else if (j > 1 && dp[i][j] == dp[i][j - 1]) {
                j--;
            } else {
                index--;
                i--;
                j--;
                resCharArray[index] = str1[i];
            }
        }
        res.add(String.valueOf(resCharArray));
    }

}
