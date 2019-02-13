package BST_A2;

public class BST_Playground {
    /*
     * you will test your own BST implementation in here
     *
     * we will replace this with our own when grading, and will
     * do what you should do in here... create BST objects,
     * put data into them, take data out, look for values stored
     * in it, checking size and height, and looking at the BST_Nodes
     * to see if they are all linked up correctly for a BST
     *
     */

    public static void main(String[]args){
        if (insertTest1()) {
            System.out.println("Pass: Insert Test 1");
        } else {
            System.out.println("Fail: Insert Test 1");
        }

        if (heightTest1()) {
            System.out.println("Pass: Height Test 1");
        } else {
            System.out.println("Fail: Height Test 1");
        }

        if (removeTest1()) {
            System.out.println("Pass: Remove Test 1");
        } else {
            System.out.println("Fail: Remove Test 1");
        }

        if (containsTest()) {
            System.out.println("Pass: Contains Test 1");
        } else {
            System.out.println("Fail: Contains Test 1");
        }

        if (removeTest()) {
            System.out.println("Pass: Remove Test 1");
        } else {
            System.out.println("Fail: Remove Test 1");
        }
    }

    private static boolean insertTest1() {
        BST tree = new BST();
        tree.insert("C");
        tree.insert("B");
        tree.insert("A");
        return (tree.getRoot().data.equals("C") &&  tree.getRoot().left.data.equals("A")
                && tree.getRoot().left.right.data.equals("B") && tree.height() == 1);
    }

    private static boolean heightTest1() {
        BST tree = new BST();
        tree.insert("B");
        tree.insert("A");
        tree.insert("D");
        tree.insert("C");
        tree.insert("F");
        tree.insert("E");
        return tree.height() == 3;
    }

    private static boolean removeTest1() {
        BST tree = new BST();
        tree.insert("B");
        tree.insert("A");
        tree.insert("C");
        tree.remove("C");
        return tree.height() == 1;
    }

    private static boolean containsTest() {
        BST tree = new BST();
        tree.insert("B");
        tree.insert("A");
        tree.insert("D");
        tree.insert("C");
        tree.insert("E");
        return tree.contains("D");
    }

    private static boolean removeTest() {
        BST tree = new BST();
        tree.insert("B");
        tree.insert("A");
        tree.insert("D");
        tree.insert("C");
        tree.insert("E");
        tree.remove("B");
        tree.remove("A");
        tree.remove("D");
        tree.remove("C");
        tree.remove("E");
        return tree.empty();
    }

    private static BST generateTree() {
        BST tree = new BST();

        int i;
        int j;
        for (i = 0; i < 5; i++) {
            int rand = (int) (Math.random() + 10);
            String s = "";
            for (j = 0; j < rand; j++) {
                s += generateRandomChar();
            }
            tree.insert(s);
        }

        return tree;
    }

    private static char generateRandomChar() {
        int rand_ascii = 97 + ((int) (Math.random() * 26));
        return (char) rand_ascii;
    }

    static void printLevelOrder(BST tree){
        //will print your current tree in Level-Order...
        //https://en.wikipedia.org/wiki/Tree_traversal
        int h=tree.getRoot().getHeight();
        for(int i=0;i<=h;i++){
            printGivenLevel(tree.getRoot(), i);
        }
    }

    static void printGivenLevel(BST_Node root,int level){
        if(root==null)return;
        if(level==0)System.out.print(root.data+" ");
        else if(level>0){
            printGivenLevel(root.left,level-1);
            printGivenLevel(root.right,level-1);
        }
    }

    static void printInOrder(BST_Node root){
        //will print your current tree In-Order
        if(root!=null){
            printInOrder(root.getLeft());
            System.out.print(root.getData() + " ");
            printInOrder(root.getRight());
        }
    }
}
