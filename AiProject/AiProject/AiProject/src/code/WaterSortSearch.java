package code;

import java.util.Stack;

public class WaterSortSearch extends GenericSearch{
	public static String solve(String initialState,String strategy,boolean visualize) {
		if(strategy.toLowerCase() == "id") {
			int level = 0;
			while(true) {
				DataContainer dataContainer = new StackContainer();
				GenericSearch gs = new GenericSearch();
				Problem problem = new Problem(initialState);
				Node solution = gs.iterativeSearch(level,problem, dataContainer);
				 System.out.println(problem.getExpansionCount());
				 if(solution != null) {
					 System.out.println(level);
					 return result(solution, problem);
				 }
				 level++;
			}
		}
		else {
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
         case "gr1":
        	 dataContainer = new GreedyPriorityQueueContainer(1);  
             break;
         case "gr2":
        	 dataContainer = new GreedyPriorityQueueContainer(2);  
             break;
         case "as1":
        	 dataContainer = new AStarPriorityQueueContainer(1);  
             break;
         case "as2":
        	 dataContainer = new AStarPriorityQueueContainer(2);  
             break;
         default:
             throw new IllegalArgumentException("Unknown strategy: " + strategy);
         }
		 Node solution = gs.search(problem, dataContainer);
		 System.out.println(problem.getExpansionCount());
		 return result(solution, problem);
		}
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

	    return result.toString();
	    }  
	
	 public static void main(String[] args) {
		 String grid0 = "5;4;b,y,r,b;b,y,r,r;y,r,b,y;e,e,e,e;e,e,e,e;";
	     String result = solve(grid0,"id",true);
	     System.out.print(result);      
	    }
	}