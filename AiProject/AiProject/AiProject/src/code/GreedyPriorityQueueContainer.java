package code;

import java.util.PriorityQueue;
import java.util.Comparator;

public class GreedyPriorityQueueContainer implements DataContainer {
    private PriorityQueue<Node> queue;

    public GreedyPriorityQueueContainer(int heuristicFunctionNumber) {
    	if(heuristicFunctionNumber == 1) {
    		queue = new PriorityQueue<>(Comparator.comparingInt(Node::getHeuristicValue1));
    	}
        if(heuristicFunctionNumber == 2) {
        	queue = new PriorityQueue<>(Comparator.comparingInt(Node::getHeuristicValue2));
    	}
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
