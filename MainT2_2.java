package ojtest;

/*
链表回文：
判断一个单向链表是否为回文结构。自定义链表数据结构，要求时间复杂度为O(n)，额外空间复杂度为O(1)。
Input
输入第一行为用例个数， 每个测试用例输入的每一行的值用空格隔开，第一个值为节点个数，后面为每一个节点值
Output
是回文则输出true，不是则输出false，一行表示一个链表的结果。
Sample Input 1
4
3 1 2 1
4 1 2 2 1
3 3 5 3
6 a b c d c a
Sample Output 1
true
true
true
false
 */

import java.util.Scanner;

public class MainT2_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a=Integer.parseInt(in.nextLine());
        for(int i=0;i<a;i++){
            if(i>0) System.out.println();
            String []str=in.nextLine().split(" ");
            int length=Integer.parseInt(str[0]);//链表长度
            //开始把每个值存入链表中
            ListNode head = new ListNode(str[1]);
            ListNode current=head;
            for(int q=2;q<=length;q++) {
                current.next = new ListNode(str[q]);
                current=current.next;
            }
            /*
            ListNode head = new ListNode(1);
            head.next = new ListNode(2);
            head.next.next = new ListNode(3);
            head.next.next.next = new ListNode(2);
            head.next.next.next.next = new ListNode(1);
            */
            System.out.print(isPalindrome(head));
        }
        in.close();
    }

    public static class ListNode{
        public String value;
        public ListNode next;
        public ListNode(String value){
            this.value = value;
        }
    }

    //额外空间复杂度为O(1)的方法。
    /*
     * 思路：要求只在给定的链表中操作，不能使用额外的空间。
     * 1，先找出链表的重点。
     * 2，找到链表的中点后，将链表的右侧反转，并将中点处的指针指向空节点。
     * 3，从两端依次遍历并逐一判定节点值是否相等。
     * 4，判断结束后需要将链表还原，重新反转。
     * */
    public static boolean isPalindrome(ListNode head){
        if(head == null && head.next == null){
            return false;
        }
        boolean res = true;
        ListNode mid = head;
        ListNode cur = head;
        //找到链表的中点，当cur为空时，走到头，说明此时right是中点
        while(cur.next != null && cur.next.next != null){
            mid = mid.next;
            cur = cur.next.next;
        }
        //cur取右边的第一个节点
        cur = mid.next;
        //令原来右边第一个节点为空
        mid.next = null;
        ListNode right = null;
        //将中间节点右侧的链表反转并指向中间节点，也就是从头开始一个，从尾开始一个，两个链表都指向中间节点
        while(cur != null){
            right = cur.next;
            cur.next = mid;
            mid = cur ;
            cur = right ;
        }
        //结束循环后，mid是原来的最后一个尾节点，也就是现在的右侧的头结点，保留它
        right = mid;
        //从头开始，一边cur从head左边开头开始，另一边mid从mid右边开头开始
        cur = head;
        //判断左右两侧对称位置是否相等
        while(mid != null && cur != null){
            if(mid.value .equals(cur.value) == false){
                res = false;
                //有不等的，直接停了
                break;
            }
            mid = mid.next;
            cur = cur.next;
        }
        //将右侧的链表还原，变为原来的链表，这步可做可不做
        mid = null;
        while(right != null){
            cur = right.next;
            right.next = mid;
            mid = right;
            right =cur;
        }

        return res;
    }

}
