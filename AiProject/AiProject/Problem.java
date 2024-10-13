public class Problem {
     
private Node initialNode;
    
    public Problem(String init,String strategy) {
        parseProblem(init);
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

    public boolean canPour (Bottle A , Bottle B){
        if(B.getCapacity() == B.getColors().size()){
            return false;
        }
        if(A.getColors.size() == 0){
            return false;
        }
        if(A.getColors().size() == 1 && B.getColors().size()==0){
            return false;
        }
        if (A.topColor().equals(B.topColor())|| B.getColors().size()==0){
            return true;
        }
        return true;
    }
    public Set<Color> availableColors(Bottle x) {
        Set<Color> distinctColors = new HashSet<>();
        if (x.getColors().size() == 0) {
            return distinctColors; 
        }
        Stack<Color> colorsCopy = (Stack<Color>) x.getColors().clone();
    
        for (Color color : colorsCopy) {
            distinctColors.add(color); 
        }
        return distinctColors;
    }
    public boolean isGoal(Node x){
        Bottle[] b = x.getBottles();
        List<Color> availableColor= new ArrayList<>();
        for (int i = 0; i < b.length(); i++) {
            Set<Color> eachBottle = availableColors(b[i]);
            if(eachBottle.size()>1){
                return false;
            }
            else {
                if(eachBottle.size()==1){
                   for(Color color: eachBottle){
                        if(availableColor.contains(color)){
                            return false;
                        }
                        else{
                            availableColor.add(color);
                        }
                   }
                }
            }
        }
        return true;
    }
    public List<Node> expand(Node node){
        List<Node> children = new ArrayList<>();
        Bottle[] bottles = node.getBottles().clone();
        for(int i=0 ; i<bottles.size();i++){
            for(int j= 0;j<bottles.size();j++){
                if(i!=j){
                    if(canPour(bottles[i],bottles[j])){
                        Bottle[] newBottles = new Bottle[bottles.length];
                        for(int k=0 ; k<newBottles.length();k++){
                            if(k!=i && k!=j){
                                newBottles[k] = bottles[k];
                            }
                            else{
                                Color addedColor = bottles[i].pop();
                                bottles[j].push(addedColor);


                            }
                        }
                        Node child = new Node(); 
                    }
                }
            }
        }

    }

 
   
    
}
