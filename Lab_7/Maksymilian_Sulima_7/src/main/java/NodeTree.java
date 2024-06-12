import org.apache.zookeeper.*;

import javax.swing.*;
import java.util.Stack;

public class NodeTree {
    private final ZooKeeper zk;

    public NodeTree(ZooKeeper zk) {
        this.zk = zk;
    }

    public void printTree(String path) {
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder();
        stringBuilder.append(path).append("\n");

        Stack<String> stack = new Stack<>();
        stack.push(path);
        while (!stack.isEmpty()) {
            String currentPath = stack.pop();
            try {
                for (String child : zk.getChildren(currentPath, false)) {
                    stack.push(currentPath + "/" + child);
                    stringBuilder.append(currentPath).append("/").append(child).append("\n");
                }
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        JOptionPane.showMessageDialog(null, stringBuilder.toString());
    }
}
