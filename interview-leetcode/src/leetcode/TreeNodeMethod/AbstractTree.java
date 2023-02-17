package leetcode.TreeNodeMethod;

/**
 * @Author: zkcheng
 * @Date: 2022/08/05/22:10
 * @Description:
 */
public abstract class AbstractTree {
    public int count = 0;

    /**
     * 查询
     *
     * @return
     */
    public abstract Node find(int o);

    /**
     * 插入
     *
     * @param o
     */
    public abstract boolean insert(int o);

    /**
     * 删除
     *
     * @param o
     */
    public abstract boolean delete(int o);

    /**
     * 节点个数
     *
     * @return
     */
    public int count() {
        return this.count;
    }

    /**
     * 递归前序遍历
     *
     * @param Node
     */
    public abstract void PreOrderTraversal(Node root);

    /**
     * 递归中序遍历
     *
     * @param Node
     */
    public abstract void InOrderTraversal(Node root);

    /**
     * 递归后序遍历
     *
     * @param Node
     */
    public abstract void PostOrderTraversal(Node root);

    /**
     * 非递归层序遍历
     *
     * @param Node
     */
    public abstract void LevelOrderTraversal(Node root);


    /**
     * 最大深度
     *
     * @param Node
     */
    public abstract int getMaxDepth(Node root);


    /**
     * 最大宽度--叶子节点个数
     *
     * @param Node
     */
    public abstract int getMaxWidth(Node root);


}
