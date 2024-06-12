import org.apache.zookeeper.*;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class ZooKeeperWatcher implements Watcher {
    private static ZooKeeper zk;
    private static String externalAppCommand;
    private static Process externalAppProcess;

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        for (AddWatchMode c : AddWatchMode.values())
            System.out.println(c);
        if (args.length < 1) {
            System.out.println("Usage: external app and its arguments are required as command line arguments");
            System.exit(1);
        }
        externalAppCommand = args[0];
        for (int i = 1; i < args.length; i++) {
            externalAppCommand += " " + args[i];
        }
        zk = new ZooKeeper("localhost:2181,localhost:2182,localhost:2183", 3000, new ZooKeeperWatcher());
        zk.addWatch("/a", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (event.getType() == Event.EventType.NodeCreated) {
                    onNodeCreated();
                } else if (event.getType() == Event.EventType.NodeDeleted) {
                    onNodeDeleted();
                }
            }
        }, AddWatchMode.PERSISTENT);

        zk.addWatch("/a", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                try {
                    if (event.getType() == Event.EventType.NodeCreated) {
                        if (!zk.getChildren("/a", false).isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Number of descendants of node 'a': " +
                                    zk.getAllChildrenNumber("/a"));
                        }

                    }
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, AddWatchMode.PERSISTENT_RECURSIVE);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while (true) {
            try {
                line = reader.readLine().trim();
                if (line.equals("tree")) {
                    System.out.println(zk.getChildren("/a", false));
                    NodeTree nodeTree = new NodeTree(zk);
                    nodeTree.printTree("/a");

                } else {
                    System.out.println("Unknown command");
                }

            } catch (IOException | KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void onNodeCreated() {
        try {
            System.out.println("Starting external app: " + Arrays.toString(externalAppCommand.split(" ")));
            externalAppProcess = new ProcessBuilder(externalAppCommand.split(" ")).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void onNodeDeleted() {
        if (externalAppProcess != null) {
            externalAppProcess.destroy();
        }
    }


    @Override
    public void process(WatchedEvent event) {
    }
}
