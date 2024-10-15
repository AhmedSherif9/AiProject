package code;

import java.util.List;
import java.util.Stack;

public class GenericSearch {
	
	private int maxDepth;
	
	public GenericSearch() {
    	maxDepth = 0;
	}

    public int getMaxDepth() {
		return maxDepth;
	}

	public Node search(Problem problem, DataContainer dataContainer) {
        dataContainer.add(problem.getInitialNode());
  
        while (true) {
            if (dataContainer.isEmpty()) {
                return null;
            }
            Node node = dataContainer.remove();
            if (problem.isGoal(node)) {
                return node;
            }

            List<Node> children = problem.expand(node);
            if (!children.isEmpty()) {
            	problem.setExpansionCount(); //incrementing the count by 1
            }
            
            for (Node child : children) {
                dataContainer.add(child);
            }
           
        }
    }
    
    public Node iterativeSearch(int level,Problem problem, DataContainer dataContainer) {
        dataContainer.add(problem.getInitialNode());
  
        while(true) {
            if (dataContainer.isEmpty()) {
                return null;
            }
            
            if(getMaxCurrentDepth(dataContainer) > maxDepth) {
            	maxDepth = getMaxCurrentDepth(dataContainer);
            }
            
            Node node = dataContainer.remove();
            if (problem.isGoal(node)) {
                return node;
            }
            
            if(node.getDepth() < level) {
            	List<Node> children = problem.expand(node);
            	 if (!children.isEmpty()) {
                 	problem.setExpansionCount(); //incrementing the count by 1
                 }
            
                for (Node child : children) {
                	dataContainer.add(child);
                }}
         
        	}
    }
    
    public int getMaxCurrentDepth(DataContainer dataContainer) {
    	Stack<Node> stack = new Stack<>();
    	int max = 0;
    	while(!dataContainer.isEmpty()) {
    		Node node = dataContainer.remove();
    		if(node.getDepth() > max) {
    			max = node.getDepth();
    		}
    		stack.push(node);
    	}
    	while(!stack.isEmpty()) {
    		dataContainer.add(stack.pop());
    	}
    	return max;
    }
    
}