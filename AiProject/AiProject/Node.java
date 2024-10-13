import java.util.ArrayList;
import java.util.List;

public class Node {
    private Node parent;                  
    private List<Node> children;         
    private Bottle[] bottles;            
    private int depth;                   
    private int pathCost;    
    private String operator;             

    public Node(Bottle[] bottles, Node parent, int depth, int pathCost,String operator) {
        this.bottles = bottles;         
        this.parent = parent;            
        this.depth = depth;              
        this.pathCost = pathCost;         
        this.children = new ArrayList<>();
        this.operator= operator;
    }


    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        this.children.add(child);
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
    

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Node at depth: ").append(depth).append(", Path cost: ").append(pathCost).append("\n");
        for (Bottle bottle : bottles) {
            result.append(bottle.toString()).append("\n");
        }
        return result.toString();
    }
}
