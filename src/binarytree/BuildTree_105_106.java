package binarytree;

/**
 * 105.根据前序中序遍历序列构造二叉树
 * 106.根据中序后序遍历序列构造二叉树
 */
public class BuildTree_105_106 {
    public static void main(String[] args) {
        int[] preOrder = {1, 2, 3, 4, 5};
        int[] inOrder = {2, 1, 4, 3, 5};
        int[] postOrder = {2, 4, 5, 3, 1};


        System.out.println("前序和中序构造二叉树：" + buildTree1(inOrder, preOrder).left.val);
        System.out.println("中序和后序构造二叉树：" + buildTree2(inOrder, postOrder).left.val);
    }

    /**
     * 递归实现(前序中序构造二叉树)
     *
     * @param preOrder
     * @param inOrder
     * @return
     */
    public static TreeNode buildTree1(int[] preOrder, int[] inOrder) {
        return buildTreePreIn(preOrder, 0, preOrder.length, inOrder, 0, inOrder.length);
    }

    public static TreeNode buildTreePreIn(int[] inOrder, int inLeft, int inRight, int[] preOrder, int preLeft, int preRight) {
        if (inRight - inLeft < 1) {
            return null;
        }
        if (inRight - inLeft == 1) {
            return new TreeNode(inOrder[inLeft]);
        }

        int rootValue = preOrder[preLeft];
        TreeNode root = new TreeNode(rootValue);

        int rootIndex = 0;
        for (int i = inLeft; i < inRight; i++) {
            if (inOrder[i] == rootValue) {
                rootIndex = i;
                break;
            }
        }

        root.left = buildTreePreIn(inOrder, inLeft, rootIndex, preOrder, preLeft + 1, preLeft+1 + (rootIndex - inLeft));
        root.right = buildTreePreIn(inOrder, rootIndex + 1, inRight, preOrder, preLeft+1+ (rootIndex - inLeft), preRight);
        return root;
    }

    /**
     * 递归实现（中序后序构造二叉树）
     * 思路：以 后序数组的最后一个元素为切割点，先切中序数组，根据中序数组，反过来在切后序数组。一层一层切下去，每次后序数组最后一个元素就是节点元素
     * 1.如果数组大小为零的话，说明是空节点了
     * 2.如果不为空，那么取后序数组最后一个元素作为节点元素
     * 3.找到后序数组最后一个元素在中序数组中的位置，作为切割点
     * 4.切割中序数组，切成中序左数组和中序右数组（注意顺序别错）
     * 5.切割后序数组，切成后序左数组和后序右数组
     * 6.递归处理左区间和右区间
     *
     * @param inOrder
     * @param postOrder
     * @return
     */
    public static TreeNode buildTree2(int[] inOrder, int[] postOrder) {
        return buildTreeInPost(inOrder, 0, inOrder.length, postOrder, 0, postOrder.length);
    }

    public static TreeNode buildTreeInPost(int[] inOrder, int inLeft, int inRight, int[] postOrder, int postLeft, int postRight) {
        //注意：区间前闭后开
        //1.没有元素了
        if (inRight - inLeft < 1) {
            return null;
        }
        //只有一个元素
        if (inRight - inLeft == 1) {
            return new TreeNode(inOrder[inLeft]);
        }
        //2.取得后序数组最后一个元素，即为当前根节点，构造二叉树节点
        int rootValue = postOrder[postRight - 1];
        TreeNode root = new TreeNode(rootValue);

        //3.在中序中找到切割点
        int rootIndex = 0;
        for (int i = inLeft; i < inRight; i++) {
            if (inOrder[i] == rootValue) {
                rootIndex = i;
                break;
            }
        }

        //4.切割中序数组，得到中序左数组和中序右数组,递归处理左区间和右区间
        //5.切割后序数组，得到后序左数组和后序右数组,递归处理左区间和右区间

        //根据rootIndex切割,注意：区间前闭后开，画图理解较容易看出具体取值
        root.left = buildTreeInPost(inOrder, inLeft, rootIndex,
                postOrder, postLeft, postLeft + (rootIndex - inLeft));//若此处前闭后闭最后参数为(rootIndex - inLeft)+1
        root.right = buildTreeInPost(inOrder, rootIndex + 1, inRight,//此时rootIndex位置已经被确定为当前根节点，故从rootIndex+1开始
                postOrder, postLeft + (rootIndex - inLeft), postRight - 1);//postRight-1(第一次在取不到的postRight=postOrder.length位置，
        // 又因为前闭后开故指向当前已经被确定为根节点的位置)

        return root;
    }
}
