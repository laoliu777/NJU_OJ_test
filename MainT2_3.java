package ojtest;
/*
链表区间逆序：
将单个链表的每K个节点之间逆序，打印出新链表；最后不足K的节点数不需要逆序；要求时间复杂度为O(n)，额外空间复杂度为O(1)。
Input
输入第一行为用例个数， 每个测试用例输入的每一行的值用空格隔开，第一个表示链表长度，中间为节点值，最后代表K。
Output
输出的每一行为新的链表，节点值用空格隔开，末尾不要空格。
Sample Input
2
8 1 2 3 4 5 6 7 8 3
8 a b c d e f g h 4
Sample Output
3 2 1 6 5 4 7 8
d c b a h g f e
*/
import java.util.Scanner;

public class MainT2_3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a=Integer.parseInt(in.nextLine());
        for(int i=0;i<a;i++){
            if(i>0)
                System.out.println();
            String []str=in.nextLine().split(" ");
            int length=Integer.parseInt(str[0]);
            /*
            String []arr=new String[Integer.parseInt(str[0])];
            for(int j=0;j<arr.length;j++){
                arr[j]=str[j+1];
            }
             */
            int k=Integer.parseInt(str[length+1]);
            Node node=new Node(str[1]);
            Node cur=node;
            for(int j=2;j<=length;j++){
                cur.next=new Node(str[j]);
                cur=cur.next;
            }
            Node rev_node=ReverseK(node,k);
            Node rev_cur=rev_node;
            int cou_cur=1;
            while(rev_cur!=null){
                System.out.print(rev_cur.value);
                rev_cur=rev_cur.next;
                if(cou_cur<length)
                    System.out.print(" ");
                cou_cur++;
            }
            /*
            int[] result = reverseArray(arr,k);
            for(int j=0;j<result.length;j++){
                if(j>0)
                    System.out.print(" ");
                System.out.print(result[j]);
            }
             */
        }
        in.close();
    }

    public static class Node{
        String value;
        public Node next;
        public Node(String data){
            this.value = data;
        }
    }

    /*
    时间复杂度O(N)，空间复杂度O(1)。
　　不需要利用栈，用变量记录每一组开始的第一个节点和最后一个节点，然后直接逆序调整，把这一组的节点都逆序。需要注意头节点的更新以及每组节点两头的连接。
     */
    //(利用变量存储)每k个节点之间逆序
    public static Node ReverseK(Node head, int K) {
        if (K < 2) {
            return head;
        }
        //pre是这k个节点的前一个，curnext是k个后一个
        //start是k个中的第一个，cur是k个中的最后一个
        Node cur = head;
        Node start = null;
        Node pre = null;
        Node curnext = null;
        //统计当前有几个数了，到没到k个
        int count = 1;
        while (cur != null) {
            curnext = cur.next;
            //计数，看有没有满k个，满k个了，就把这k个倒序
            if (count == K) {
                //一开始的时候（第一次的k个），对start，head赋值
                if(pre==null){
                    start=head;
                    head=cur;
                }
                else{
                    start=pre.next;
                }
                //也可以写成： start = pre == null ? head : pre.next;
                //同： head = pre == null ? cur : head;
                //把这k个逆序，然后头尾也逆，四个分别对应：left, start, end, right
                resign(pre, start, cur, curnext);
                //把前一个pre改成此时的k个中的最后一个，但是注意：这时候cur原来是k个中的最后一个，但是逆序完变成第一个了！反而原来的第一个start变成最后一个！
                pre = start;
                count = 0;
            }
            count++;
            cur = curnext;
        }
        return head;
    }

    //调整k个节点逆序，同时把一头一尾也给换序了
    public static void resign(Node left, Node start, Node end, Node right) {
        //left是这k个节点的前一个，right是k个后一个，start是k个的第一个，end是k个的最后一个
        //先指向k的第一个
        Node pre = start;
        Node cur = start.next;
        Node curnext = null;
        //链表的逆转，当cur没到k之外时，将这k个节点先反转，即变为left->(原start<-原end)->right
        while (cur != right) {
            curnext = cur.next;
            cur.next = pre;
            pre = cur;
            cur = curnext;
        }
        //此时需要把一头left一尾right的链给反过来
        //如果前面不为空（不是第一次的k个）left->end
        if (left != null) {
            left.next = end;
        }
        //start肯定得指向下面的right
        start.next = right;
    }

}
