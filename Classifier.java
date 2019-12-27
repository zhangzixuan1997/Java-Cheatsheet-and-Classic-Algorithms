//Student ID:260873386
//Student Name: Sean Zhang
public class Classifier {
    public static void main(String[] args) {
      //Get input from user
        int beak = Integer.parseInt(args[0]);
        int claw = Integer.parseInt(args[1]);
        String color = args[2];
      //Define boolean to get statements in the if clause
        boolean typeA = isTypeA(beak, claw, color);
        boolean typeB = isTypeB(beak, claw, color);
        boolean typeC = isTypeC(beak, claw, color);
      //Out put results 
        if (typeA) {
            if (typeB) {
                System.out.println("The type of bird is A B.");
            } else if (typeC) {
                System.out.println("The type of bird is A C.");
            } else {
                System.out.println("The type of bird is A.");
            }
        }
        else if (typeB) {
            if (typeC) {
                System.out.println("The type of bird is B C.");}
             else {
                System.out.println("The type of bird is B.");}
        }
        else if (typeC){
        System.out.println("The type of bird is C.");}

        else{
            System.out.println("This bird is not part of the study.");}
        }
    // Method to determine whether the bird is type A
    // The following if clauses can be simplified but I didnot so it would be easier to read.
    public static boolean isTypeA (int beak, int claw, String color)
    {
        if((beak==1)&&(claw==0)&&(color.equals("Gray"))){
        return true;}
        else if ((beak==2)&&(claw==1)&&(color.equals("Gray"))){
        return true;}
        else if ((beak==3)&&(claw==2)&&(color.equals("Gray"))){
        return true;}
        else if ((beak==4)&&(claw==3)&&(color.equals("Gray"))){
        return true;}
        else if ((beak==4||beak==5)&&(claw==4)&&(color.equals("Gray"))){
        return true;}
        else{
        return false;}
    }
    // Method to determine whether the bird is type B
    public static boolean isTypeB (int beak, int claw, String color)
    {
        if((beak==4)&&(claw==4||claw==5)&&(color.equals("Gray"))){
            return true;}
        else if ((beak==5)&&(claw==6||claw==7)&&(color.equals("Gray")||color.equals("Blue"))){
            return true;}
        else if ((beak==6)&&(claw==8||claw==9)&&(color.equals("Blue"))){
            return true;}
        else if ((beak==7)&&(claw==10)&&(color.equals("Blue"))){
            return true;}
        else if ((beak==8)&&(claw==11)&&(color.equals("Blue"))){
            return true;}
        else{
            return false;}
    }
    // Method to determine whether the bird is type C
    public static boolean isTypeC (int beak, int claw, String color)
    {
        if((beak==5)&&(claw==6||claw==7)&&(color.equals("Blue")||color.equals("Green"))){
            return true;}
        else if ((beak==6)&&(claw==8||claw==9)&&(color.equals("Green"))){
            return true;}
        else if ((beak==7)&&(claw==10)&&(color.equals("Green"))){
            return true;}
        else if ((beak==8)&&(claw==11)&&(color.equals("Green"))){
            return true;}
        else if ((beak==9)&&(claw==12)&&(color.equals("Green"))){
            return true;}
        else{
            return false;}
    }
}
