package code;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Bottle {
    private int capacity;      
    private Stack<Color> colors;  

   
    public Bottle(int capacity) {
        this.capacity = capacity;
        this.colors = new Stack<>(); 
    }
  
    public int getCapacity() {
        return capacity;
    }

   
    public Stack<Color> getColors() {
        return colors;
    }

    
    public boolean isFull() {
        return colors.size() >= capacity;
    }

   
    public boolean isEmpty() {
        return colors.isEmpty();
    }

    
    public Color topColor() {
        if (isEmpty()) {
            return null; 
        }
        return colors.peek(); 
    }

    public int distinctColors() {
        Set<Color> distinctColors = new HashSet<>(colors);
        return distinctColors.size(); 
    }

    @Override
    public String toString() {
        return "Bottle{" +
                "capacity=" + capacity +
                ", colors=" + colors +
                '}';
    }
  
}
