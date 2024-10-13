import java.util.List;

public class GenericSearch {

    public Node search(SearchProblem problem, DataContainer dataContainer) {
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
            for (Node child : children) {
                dataContainer.add(child);
            }
        }
    }
    
}