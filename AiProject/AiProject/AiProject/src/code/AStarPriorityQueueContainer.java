package code;

import java.util.PriorityQueue;
import java.util.Comparator;

public class AStarPriorityQueueContainer implements DataContainer {
    private PriorityQueue<Node> queue;

    public AStarPriorityQueueContainer(int heuristicFunctionNumber) {
    	if(heuristicFunctionNumber == 1) {
    		queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.getHeuristicValue1() + node.getPathCost()));
    	}
        if(heuristicFunctionNumber == 2) {
        	queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.getHeuristicValue2() + node.getPathCost()));
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
