package code;

import java.util.PriorityQueue;
import java.util.Comparator;

public class PriorityQueueContainer implements DataContainer {
    private PriorityQueue<Node> queue;

    public PriorityQueueContainer() {
        // Comparator to prioritize nodes with lower path cost
        queue = new PriorityQueue<>(Comparator.comparingInt(Node::getPathCost));
    }

    @Override
    public void add(Node node) {
        queue.add(node);
    }

    @Override
    public Node remove() {
        return queue.poll();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
