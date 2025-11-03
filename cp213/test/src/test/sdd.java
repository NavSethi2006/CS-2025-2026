package test;

class Mystery {
    private static int counter = 0;
    private int id;
    
    public Mystery() {
        id = ++counter;
    }
    
    public void transform(Mystery other) {
        other = new Mystery();
        this.id = other.id;
    }
    
    public static void reset() {
        counter = 0;
    }
    
    public String toString() {
        return "M" + id;
    }
}

public class sdd {
    public static void main(String[] args) {
        String s1 = "JAVA";
        String s2 = new String("JAVA");
        String s3 = "JA" + "VA";
        String s4 = "JA";
        String s5 = "VA";
        String s6 = s4 + s5;
        
        System.out.println("s1 == s2: " + (s1 == s2));
        System.out.println("s1 == s3: " + (s1 == s3));
        System.out.println("s1 == s6: " + (s1 == s6));
        System.out.println("s1.equals(s2): " + s1.equals(s2));
        System.out.println("s1.equals(s6): " + s1.equals(s6));
        
        s2 = s2.intern();
        System.out.println("After intern - s1 == s2: " + (s1 == s2));
        
        StringBuilder sb = new StringBuilder("JAVA");
        System.out.println("s1.equals(sb): " + s1.equals(sb));
        System.out.println("sb.equals(s1): " + sb.equals(s1));
    }
}