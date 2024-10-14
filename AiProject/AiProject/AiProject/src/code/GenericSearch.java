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
    
    public Node depthLimitedSearch(Problem problem, int limit, DataContainer dataContainer) {
        dataContainer.add(problem.getInitialNode());

        while (!dataContainer.isEmpty()) {
            Node node = dataContainer.remove();

            // If goal is found, return it
            if (problem.isGoal(node)) {
                return node;
            }

            // Only expand if within the depth limit
            if (node.getDepth() < limit) {
                List<Node> children = problem.expand(node);
                for (Node child : children) {
                    dataContainer.add(child);  // Add child to the search space
                }
            }
        }
        return null;  // No solution found at this depth
    }
    
}