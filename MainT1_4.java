package ojtest;
/*
汉诺塔：
汉诺塔问题比较经典，现在修改一下游戏规则：现在限制不能从最左侧的塔直接到移动最右侧，也不能直接从最右侧直接移动到最左侧，
而是必须经过中间。求当塔有N层的时候，打印最右移动过程和最右移动总步数。
Sample Input 1
1
2
Sample Output 1
8
 */

import java.util.Scanner;
import java.util.Stack;

public class MainT1_4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a=Integer.parseInt(in.nextLine());
        for(int i=0;i<a;i++){
            if(i>0) System.out.println();
            int num=Integer.parseInt(in.nextLine());
            int result = hanoiProblem1(num,"left","mid","right");
            System.out.print(result);
        }
        in.close();
    }

    /*
法一：递归
首先，如果只剩最上层的塔需要移动，则如下处理：
如果希望从“左”移动到“中”，打印“Move 1 from left to mid”
如果希望从“中”移动到“左”，打印“Move 1 from mid to left”
如果希望从“中”移动到“右”，打印“Move 1 from mid to right”
如果希望从“右”移动到“中”，打印“Move 1 from right to mid”
如果希望从“左”移动到“右”，打印“Move 1 from left to mid” 和 “Move 1 from mid to right”
如果希望从“右”移动到“左”，打印“Move 1 from right to mid” 和 “Move 1 from mid to left”
此6条就是递归的终止条件，也就是只剩上层塔是打印过程
如果剩下N层塔，从最上到最小依次1~N，则如下判断：
1.如果剩下的N层塔都在最“左”，希望全部移动到“中”，则如下三步
①. 将1~N-1层塔全部从“左”移到“右”，明显交给递归过程
②. 将第N层塔从“左”移到“中”
③. 再将1~N-1层塔全部从“右”移到“中”，明显交给递归
2.如果剩下的N层塔从“中”移到“左”，从“左”移到“右”，从“右”移到“中”，过程与情况1同理，一样分为三步。
3.如果剩下的N层都在最“左”希望移到“右”，则分为五步
①. 将1~N-1层塔先全部从“左”移到“右”，明显交给递归过程。
②. 将第N层塔从“左”移到“中”
③. 将1~N-1层塔全部从“右”移到“左”，明显交给递归过程
④. 将第N层塔从“中”移到“右”
⑤. 将1~N-1层塔全部从“左”移到“右”，明显交给递归过程
4.如果剩下的N层塔都在“右”，希望全部移到“左”，过程和情况3同理，同样分为5步。
     */
    public static int hanoiProblem1(int num, String left, String mid, String right) {
        if (num < 1) {
            return 0;
        }
        return process(num, left, mid, right, left, right);
    }

    public static int process(int num, String left, String mid, String right, String form, String to) {
        if (num == 1) {
            if (form.equals(mid) || to.equals(mid)) {
                //System.out.println("Move 1 from" + form + "to" + mid);
                return 1;
            } else {
                //System.out.println("Move 1 from" + form + "to" + mid);
                //System.out.println("Move 1 from" + mid + "to" + to);
                return 2;
            }
        }
        if (form.equals(mid) || to.equals(mid)) {
            String another = (form.equals(left) || to.equals(left)) ? right : left;
            int part1 = process(num - 1, left, mid, right, form, another);
            int part2 = 1;
            //System.out.println("Move" + num + "form" + form + "to" + to);
            int part3 = process(num - 1, left, mid, right, another, to);
            return part1 + part2 + part3;
        } else {
            int part1 = process(num - 1, left, mid, right, form, to);
            int part2 = 1;
            //System.out.println("Move" + num + "form" + form + "to" + mid);
            int part3 = process(num - 1, left, mid, right, to, form);
            int part4 = 1;
            //System.out.println("Move" + num + "form" + mid + "to" + to);
            int part5 = process(num - 1, left, mid, right, form, to);
            return part1 + part2 + part3 + part4 + part5;
        }
    }

    /*
//法二：非递归，用栈
修改后的的汉若塔问题不能在从“左”直接移到“右”，也不能直接“右”直接移到“右”，而是要经过中间的过程。也就是说，实际只有4个动作 “左”到“中”、“中”到“右”、“右”到“中”、“中”到“左”
   现在我们把左、中、右三个地点抽象成栈，依次记为LS、MS和RS。最初所有的塔都在LS上。那么四个动作就可以看作是：某一个栈（from）把栈顶元素弹出，然后压入另一个栈里（to），作为这一个栈(to)是栈顶。
  例如，如果是7层塔，在最初时所有的塔都在LS上，LS从栈顶到栈底就依次1~7，如果现在发生了“左”到“中”的动作，这个动作对应的操作是LS栈将栈顶元素1弹出，然后1压入到MS栈中，成为MS的栈顶。其他操作同理。
  一个动作能发生的先决条件是不违反小压大的原则。
  from栈弹出的元素num如果想压入到to栈中，那么num的值必须小于当前to栈顶。
  还有一个原则不是很明显，但也非常重要，叫相邻不可逆原则，解释如下：
   1. 我们把4个动作依次定义为：L -> M，M -> L、M -> R和R -> M。
   2. 很明显，L -> M和M -> L过程互为逆过程，M -> R和R -> M互为逆过程，
   3. 在修改后的汉若塔游戏中，如果想走出最少步数，那么如何两个相邻的动作都不是互为逆的过程的。举例：如果上一步的动作是L -> M，那么这一步绝不是M -> L，直观地解释为：你在上一步把一个栈顶数从“左”移动到“右”，这一步为什么又要移回去呢？这必然不是取得最小步数的作法。同理，M -> R动作和R -> M动作也不可能相邻发生。
  有了小压大和相邻不可逆序原则后，可以推导出两个十分有用的结论——非递归的方法核心结论：
   1.游戏的第一个动作一定是L -> M，这显而易见的。
   1.在走出最少步数过程中的任何时刻，4个动作只有一个动作不违反小压大和相邻不可逆原则，另外三个动作一定都会违反。
  对于结论2，现在进行简单的证明
  因为游戏的第一个动作已经确定是L -> M，则以后的每一步都会有前一步动作。
  假设前一步的动作是L -> M:
   1. 根据小压大原则，L -> M的动作不会重复发生
   2. 根据相邻不可逆原则，M -> L的动作也不该发生
   3. 根据小压大原则，M -> R和R -> M只有一个达标
  假设前一步的动作是M -> L:
   1. 根据小压大原则，M -> L的动作不会重复发生
   2. 根据相邻不可逆原则，L -> M的动作也不该发生
   3. 根据小压大原则，M -> R和R -> M只有一个达标
  假设前一步的动作是M -> R:
   1. 根据小压大原则，M -> R的动作不会重复发生
   2. 根据相邻不可逆原则，R -> M的动作也不该发生
   3. 根据小压大原则，M -> L和L -> M只有一个达标
  假设前一步的动作是R -> M:
   1. 根据小压大原则，R -> M的动作不会重复发生
   2. 根据相邻不可逆原则，M -> R的动作也不该发生
   3. 根据小压大原则，M -> L和L -> M只有一个达标
  综上所述，每一步只会有一个动作达标，那么只要每一步都根据这两个原则考查所有的动作就可以，那个动作达标就走哪一个动作，反正每一次都只有一个动作满足要求，按顺序走下来即可。
     */
    public static enum Action {
        No, LToM, MToL, MToR, RToM
    }

    public static int hanoiProblem2(int num, String left, String mid, String right) {
        Stack<Integer> ls = new Stack<>();
        Stack<Integer> ms = new Stack<>();
        Stack<Integer> rs = new Stack<>();
        ls.push(Integer.MAX_VALUE);
        rs.push(Integer.MAX_VALUE);
        ms.push(Integer.MAX_VALUE);
        for (int i = num; i > 0; i--) {
            ls.push(i);
        }
        Action[] record = {Action.No};
        int step = 0;
        while (rs.size() != num + 1) {
            step += fStackToStack(record, Action.MToL, Action.LToM, ls, ms, left, mid);
            step += fStackToStack(record, Action.LToM, Action.MToL, ms, ls, mid, left);
            step += fStackToStack(record, Action.RToM, Action.MToR, ms, rs, mid, right);
            step += fStackToStack(record, Action.MToR, Action.RToM, rs, ms, right, mid);
        }
        return step;
    }

    public static int fStackToStack(Action[] record, Action preNoAct, Action nowAct, Stack<Integer> fStack,
                                    Stack<Integer> tStack, String from, String to) {
        if (record[0] != preNoAct && fStack.peek() < tStack.peek()) {
            tStack.push(fStack.pop());
            System.out.println("Move" + tStack.peek() + "from" + from + "to" + to);
            record[0] = nowAct;
            return 1;
        }
        return 0;

    }
}
