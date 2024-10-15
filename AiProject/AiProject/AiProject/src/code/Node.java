package code;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Node {
    private Node parent;                  
          
    private Bottle[] bottles;            
    private int depth;                
    private int pathCost;
    private String operator;

    public Node(Bottle[] bottles, Node parent, int depth, int pathCost,String operator) {
        this.bottles = bottles;         
        this.parent = parent;            
        this.depth = depth;              
        this.pathCost = pathCost;
        this.operator= operator;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

  

    public Bottle[] getBottles() {
        return bottles;
    }
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setBottles(Bottle[] bottles) {
        this.bottles = bottles;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getPathCost() {
        return pathCost;
    }

    public void setPathCost(int pathCost) {
        this.pathCost = pathCost;
    }
    
    public int getHeuristicValue1() {
    	int heuristicValue = 0;
    	for(int i=0;i<bottles.length;i++) {
    		if(	bottles[i].distinctColors() > 1) {
    			heuristicValue += 1;
    		}
    	}
        return heuristicValue;
    }
    
    //kam loon zeyada
    public int getHeuristicValue2() {
    	int NoOfColors = 0;
    	int NonEmptyBottles = 0;
    	for(int i=0;i<bottles.length;i++) {
    		if(bottles[i].distinctColors() > 0) {
    			NonEmptyBottles++;
    			NoOfColors += bottles[i].distinctColors();
    		}
    	}
    	int heuristicValue = NoOfColors - NonEmptyBottles;
        return heuristicValue;
    }
    
    
    

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Node at depth: ").append(depth).append(", Path cost: ").append(pathCost).append("\n");
        for (Bottle bottle : bottles) {
            result.append(bottle.toString()).append("\n");
        }
        return result.toString();
    }
    
    public String printAll() {
    	Stack<String> stack = new Stack<>();
    	Node node= this;
    	while(node.getParent() != null) {
    		stack.push(node.print());
    		stack.push(node.getOperator());
    		node = node.getParent();
    	}
    	stack.push(node.print());
    
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop()).append("\n");
        }
        return result.toString();
    }
    
    public String print() {
        StringBuilder result = new StringBuilder();
        for (Bottle bottle : bottles) {
        	result.append(bottle.getColors());
        }
        return result.toString();
    }
}
