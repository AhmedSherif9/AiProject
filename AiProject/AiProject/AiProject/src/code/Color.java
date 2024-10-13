package code;
public enum Color {
    R,
    G,
    Y,
    B,
    O;

    public static Color fromChar(char colorChar) {
        switch (colorChar) {
            case 'r': return Color.R;
            case 'g': return Color.G;
            case 'y': return Color.Y;
            case 'b': return Color.B;
            case 'o': return Color.O;
            default: throw new IllegalArgumentException("Invalid color: " + colorChar);
        }
    }
    
}
