package code;

import java.util.Stack;

public class WaterSortSearch extends GenericSearch{
	public static String solve(String initialState,String strategy,boolean visualize) {
		 DataContainer dataContainer;
		 GenericSearch gs = new GenericSearch();
		 Problem problem = new Problem(initialState);
		 System.out.print(problem.getInitialNode());
		 switch (strategy.toLowerCase()) {
         case "bf":
             dataContainer = new QueueContainer();  
             break;
         case "df":
             dataContainer = new StackContainer();  
             break;
         case "uc":
             dataContainer = new PriorityQueueContainer();  
             break;
         case "id":
             return iterativeDeepeningSearch(problem, visualize);  
         default:
             throw new IllegalArgumentException("Unknown strategy: " + strategy);
    }
		 Node solution = gs.search(problem, dataContainer);
		 System.out.println(problem.getExpansionCount());
		 return result(solution, problem);
	}
	public static String iterativeDeepeningSearch(Problem problem, boolean visualize) {
	    GenericSearch gs = new GenericSearch();  // Use GenericSearch
	    int depthLimit = 0;

	    while (true) {
	        DataContainer dataContainer = new StackContainer();  // Use stack for DLS (DFS behavior)
	        Node result = gs.depthLimitedSearch(problem, depthLimit, dataContainer);

	        if (result != null) {
	            return result(result, problem);  // Return the solution if found
	        }

	        depthLimit++;  // Increase depth limit and try again
	    }
	}


	
	private static Node depthLimitedSearch(Node node, Problem problem, int limit, boolean visualize) {
	    if (problem.isGoal(node)) {
	        return node;
	    }
	    if (node.getDepth() >= limit) {
	        return null;
	    }
	    for (Node child : problem.expand(node)) {
	        Node result = depthLimitedSearch(child, problem, limit, visualize);
	        if (result != null) {
	            return result;
	        }
	    }
	    return null;  
	}

	public static String result(Node node, Problem problem) {
		if(node == null) {
			return "NOSOLUTION";
		}
		else 
		{
			return plan(node, problem);
			
		}
	}
	public static String plan(Node node, Problem problem) {
	    Stack<String> stack = new Stack<>();
	    while (node.getParent() != null) {
	        stack.push(node.getOperator());  
	        node = node.getParent();        
	    }

	    StringBuilder result = new StringBuilder();
	    while (!stack.isEmpty()) {
	        result.append(stack.pop());  
	        if (!stack.isEmpty()) {
	            result.append(",");  
	        }
	    }

	    return result.toString();}  
	
	 public static void main(String[] args) {
	        // Example problem initialization
		 String grid0 = "5;4;b,y,r,b;b,y,r,r;y,r,b,y;e,e,e,e;e,e,e,e;";
	        String result = solve(grid0,"df",true);
	        System.out.print(result);

	       
	    }
	}