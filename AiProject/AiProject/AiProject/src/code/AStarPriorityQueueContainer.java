package code;

import java.util.PriorityQueue;
import java.util.Comparator;

public class AStarPriorityQueueContainer implements DataContainer {
    private PriorityQueue<Node> queue;

    public AStarPriorityQueueContainer() {
        queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.getHeuristicValue() + node.getPathCost()));
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
