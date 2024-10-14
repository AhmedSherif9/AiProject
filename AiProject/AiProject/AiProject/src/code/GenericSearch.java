package code;

import java.util.List;

public class GenericSearch {

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
            problem.setExpansionCount(); //incrementing the count by 1
            
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
            Node node = dataContainer.remove();
            if (problem.isGoal(node)) {
                return node;
            }
            
            if(node.getDepth() < level) {
            	List<Node> children = problem.expand(node);
                problem.setExpansionCount(); //incrementing the count by 1
            
                for (Node child : children) {
                	dataContainer.add(child);
                }}
         
        	}
    }
    
}