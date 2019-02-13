package BST_A2;

public class BST implements BST_Interface {
    public BST_Node root;
    int size;

    public BST(){ size=0; root=null; }

    @Override
    public boolean insert(String s) {
        if (root != null && root.insertNode(s, root)) {
            size++;
            return true;
        } else if (root == null ) {
            size++;
            root = new BST_Node(s);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean remove(String s) {
        BST_Node temp = root;
        root = root.removeNode(s);
        return !temp.equals(root);
    }

    @Override
    public String findMin() {
        return root.findMin().getData();
    }

    @Override
    public String findMax() {
        return root.findMax().getData();
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public boolean contains(String s) {
        return root.containsNode(s, root);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return root.getHeight();
    }

    @Override
    //used for testing, please leave as is
    public BST_Node getRoot(){ return root; }

}
