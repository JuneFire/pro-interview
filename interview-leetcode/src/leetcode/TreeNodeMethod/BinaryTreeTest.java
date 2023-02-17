package leetcode.TreeNodeMethod;

/**
 * @Author: zkcheng
 * @Date: 2022/08/05/22:13
 * @Description:
 */
public class BinaryTreeTest {
    public static void main(String[] args) {
        Tree t = new Tree();

       /*//循环输入节点
        * Scanner scanner=new Scanner(System.in);
        int x=0;

        System.out.print("请输入第一个节点");
        x=scanner.nextInt();
        t.insert(x);
        Node node = t.find(x);//保留根节点

        System.out.print("输入-1将停止输入");
        while(true){
        	x=scanner.nextInt();
        	if(x==-1)
        		break;
        	else
        		t.insert(x);
        }
        */
        t.insert(80);
        t.insert(100);
        t.insert(40);
        t.insert(20);
        t.insert(90);
        t.insert(120);


        Node node = t.find(80);//保留根节点

        //System.out.println(node.leftNode.data);
        System.out.println("\n先序遍历");
        t.PreOrderTraversal(node);

        System.out.println("\n中序遍历");
        t.InOrderTraversal(node);

        System.out.println("\n后序遍历");
        t.PostOrderTraversal(node);

        System.out.println("\n层序遍历");
        t.LevelOrderTraversal(node);

        System.out.println("\n共有" + t.count() + "个节点");
        //System.out.println();

        System.out.println("二叉树深度为" + t.getMaxDepth(node));

        System.out.println("二叉树叶子节点数为" + t.getMaxWidth(node));




    }


}
