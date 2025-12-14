import java.util.Stack;

final public class Tree {
    TreeNode root;
    public void addNewNode(int value, int index , int parentIndex , boolean right) {
        if (root == null) {
            root.key = value;
            root.index = index;
            root.parent = null;

        }
        else {
            TreeNode n = new TreeNode();
            n.index = index;
            n.key = value;
        }
    }
    public void walkThroughTree () {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (stack.size() > 0) {
            System.out.print(stack.peek().key);
            TreeNode n = stack.pop();
            if (n.left != null) {
                stack.push(n.left);
            }
            if (n.right != null) {
                stack.push(n.right);
            }
        }
    }
    private class TreeNode {
        TreeNode right;
        TreeNode left;
        TreeNode parent;
        Integer key;
        Integer index;
    }
}
