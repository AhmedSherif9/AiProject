package code;

import java.util.PriorityQueue;
import java.util.Comparator;

public class GreedyPriorityQueueContainer implements DataContainer {
    private PriorityQueue<Node> queue;

    public GreedyPriorityQueueContainer() {
        queue = new PriorityQueue<>(Comparator.comparingInt(Node::getHeuristicValue));
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
