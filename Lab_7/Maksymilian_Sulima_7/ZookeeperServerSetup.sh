#!/usr/bin/zsh

# Create and move the myid files
echo 1 > myid
mv myid /tmp/zookeeper/zk1

echo 2 > myid
mv myid /tmp/zookeeper/zk2

echo 3 > myid
mv myid /tmp/zookeeper/zk3

# Start ZooKeeper servers in separate processes
/opt/apache-zookeeper-3.8.4-bin/bin/zkServer.sh --config /opt/apache-zookeeper-3.8.4-bin/conf1 start-foreground &
pid1=$!
/opt/apache-zookeeper-3.8.4-bin/bin/zkServer.sh --config /opt/apache-zookeeper-3.8.4-bin/conf2 start-foreground &
pid2=$!
/opt/apache-zookeeper-3.8.4-bin/bin/zkServer.sh --config /opt/apache-zookeeper-3.8.4-bin/conf3 start-foreground &
pid3=$!

# Function to shut down all ZooKeeper servers
shutdown_servers() {
  echo "Shutting down ZooKeeper servers..."
  kill $pid1
  kill $pid2
  kill $pid3
  wait $pid1
  wait $pid2
  wait $pid3
  echo "All ZooKeeper servers have been shut down."
}

# Trap INT signal (Ctrl+C) to shut down servers
trap shutdown_servers INT

# Wait for user to type "quit"
while true; do
  read -r input
  if [ "$input" = "quit" ]; then
    shutdown_servers
    break
  fi
done
