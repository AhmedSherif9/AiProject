package code;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Problem {
    
private Node initialNode;
private int expansionCount;
    
    public Problem(String init) {
        parseProblem(init);
        expansionCount = 0;
    }
    
    public Node getInitialNode(){
        return initialNode;
    }

    public void parseProblem(String init) {
      
        String[] parts = init.split(";");
        
       
        int numberOfBottles = Integer.parseInt(parts[0]);
        
       
        int bottleCapacity = Integer.parseInt(parts[1]);
        
       
        Bottle[] bottles = new Bottle[numberOfBottles];

    
        for (int i = 0; i < numberOfBottles; i++) {
            bottles[i] = new Bottle(bottleCapacity); 
        }

        for (int i = 2; i < parts.length; i++) {
            String[] colors = parts[i].split(","); 
            for (int j = colors.length-1;j>=0;j--) {
                if (!colors[j].equals("e")) { 
                    bottles[i - 2].getColors().push(Color.fromChar(colors[j].charAt(0)));
                }
            }
        }

        this.initialNode = new Node(bottles, null, 0, 0, ""); 
    }

    public boolean canPour(Bottle A, Bottle B) {
        // Cannot pour if B is full
        if (B.getColors().size() >= B.getCapacity()) {
            return false;
        }
        // Cannot pour if A is empty
        if (A.getColors().isEmpty()) {
            return false;
        }
        // Can pour if B is empty or if the top colors match
        return B.getColors().isEmpty() || A.topColor().equals(B.topColor());
    }

    public boolean isGoal(Node node) {
    	Bottle[] b = node.getBottles();
    	
    	for(int i =0; i<b.length;i++) {
    		if(b[i].distinctColors()>1) {
    			return false;
    		}	
    	}
    	return true;
    }
    private Bottle[] deepCopyBottles(Bottle[] originalBottles) {
        Bottle[] newBottles = new Bottle[originalBottles.length];

        for (int i = 0; i < originalBottles.length; i++) {
            // Create a new bottle with the same capacity
            newBottles[i] = new Bottle(originalBottles[i].getCapacity());

            // Use a shallow copy for the stack (since Color is immutable)
            newBottles[i].getColors().addAll(originalBottles[i].getColors());
        }

        return newBottles;
    }
    
 // Declare the visited set globally or pass it between methods
    Set<String> visitedStates = new HashSet<>();

    public List<Node> expand(Node node) {
        List<Node> children = new ArrayList<>();

        // Iterate over all pairs of bottles
        for (int i = 0; i < node.getBottles().length; i++) {
            for (int j = 0; j < node.getBottles().length; j++) {
                if (i != j && canPour(node.getBottles()[i], node.getBottles()[j])) {
                    // Deep copy the bottles to avoid modifying the original node's bottles
                    Bottle[] newBottles = deepCopyBottles(node.getBottles());

                    // Perform as many valid pours as possible from bottle i to bottle j
                    while (canPour(newBottles[i], newBottles[j])) {
                        Color poppedColor = newBottles[i].getColors().pop();
                        newBottles[j].getColors().push(poppedColor);
                    }

                    // Generate the state string for this new configuration
                    String state = stateToString(newBottles);

                    // Ensure this state hasn't been visited yet
                    if (!visitedStates.contains(state)) {
                        visitedStates.add(state);  // Mark the state as visited

                        // Create the operator string
                        String operator = "pour_"+i+"_"+j;

                        // Create a new child node with the modified bottles
                        Node child = new Node(newBottles, node, node.getDepth() + 1, node.getPathCost() + 1, operator);

                        // Debug print to verify correct state generation
                        System.out.println("Generated child with state: " + state);

                        // Add the child node to the list of children
                        children.add(child);
                      
                    }
                }
            }
        }
        return children;
    }



    // Helper method to convert the state of bottles into a string
    private String stateToString(Bottle[] bottles) {
        StringBuilder stateBuilder = new StringBuilder();
        for (Bottle bottle : bottles) {
            stateBuilder.append(bottle.getColors().toString()).append("|");
        }
        return stateBuilder.toString();
    }
    
    public int getExpansionCount() {
    	return expansionCount;
    }
    
    public void setExpansionCount() {
    	expansionCount++;
    }
    
}

