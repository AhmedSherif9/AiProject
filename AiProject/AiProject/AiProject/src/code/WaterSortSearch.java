package code;

import java.util.Stack;

public class WaterSortSearch extends GenericSearch{
	public static String solve(String initialState,String strategy,boolean visualize) {
		if(strategy == "ID") {
			int level = 0;
			while(true) {
				DataContainer dataContainer = new StackContainer();
				GenericSearch gs = new GenericSearch();
				Problem problem = new Problem(initialState);
				Node solution = gs.iterativeSearch(level,problem, dataContainer);
				 System.out.println(problem.getExpansionCount());
				 if(solution != null) {
//					 System.out.println(level);
					 return plan(solution)+";"+solution.getPathCost()+";"+problem.getExpansionCount();
				 }
				 level++;
			}
		}
		else {
		 DataContainer dataContainer;
		 GenericSearch gs = new GenericSearch();
		 Problem problem = new Problem(initialState);
		 System.out.print(problem.getInitialNode());
		 switch (strategy) {
         case "BF":
             dataContainer = new QueueContainer();  
             break;
         case "DF":
             dataContainer = new StackContainer();  
             break;
         case "UC":
             dataContainer = new PriorityQueueContainer();  
             break;
         case "GR1":
        	 dataContainer = new GreedyPriorityQueueContainer(1);  
             break;
         case "GR2":
        	 dataContainer = new GreedyPriorityQueueContainer(2);  
             break;
         case "AS1":
        	 dataContainer = new AStarPriorityQueueContainer(1);  
             break;
         case "AS2":
        	 dataContainer = new AStarPriorityQueueContainer(2);  
             break;
         default:
        	 return "NOSOLUTION";
         }
		 Node solution = gs.search(problem, dataContainer);
		 if(solution == null) {
			 return "NOSOLUTION";
		 }
		 System.out.print(plan(solution)+";"+solution.getPathCost()+";"+problem.getExpansionCount());  
		 return plan(solution)+";"+solution.getPathCost()+";"+problem.getExpansionCount();
		}
	}
	
	public static String plan(Node node) {
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
	
//	 public static void main(String[] args) {
//		 String grid0 = "5;4;b,y,r,b;b,y,r,r;y,r,b,y;e,e,e,e;e,e,e,e;";
//	     String result = solve(grid0,"id",true);
//	     System.out.print(result);      
//	    }
	}