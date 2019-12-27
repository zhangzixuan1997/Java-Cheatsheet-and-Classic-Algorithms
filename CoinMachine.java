//Fill in your name and student number
//Name: SeanZhang
//Student Number: 260873386

public class CoinMachine {
    
    public static void main(String args[]) {
        if(args.length < 2) {
            System.out.println("You need to enter two arguments to this program. Try typing 'run CoinMachine 400 215' in Dr. Java, or 'java VendingMachine 400 215' on the command line.");
            return;
        } 
        int cash = getInputInteger(args[0]);
        int price = getInputInteger(args[1]);
        
        //========================
        //Enter your code below
        System.out.println("Amount received:" + cash);
        System.out.println("Cost of the item:" + price);
        //definite the change and coins
        int change = cash - price;//Calculate the total change amount
        System.out.println("Required change:" + change);
        int twoonies = change/200;
        change = change - twoonies*200;
        int loonie = change/100;
        change = change - loonie*100;
        int quarter = change/25;
        change = change - quarter*25;
        int dime = change/10;
        change = change - dime*10;
        int nickel = change/5;
        //Print results
        System.out.println("Change:");
        System.out.println("\t"+"twoonies x "+twoonies);
        System.out.println("\t"+"loonie x "+loonie);
        System.out.println("\t"+"quarter x "+quarter);
        System.out.println("\t"+"dime x "+dime);
        System.out.println("\t"+"nickel x "+nickel)  ;
     
        //Enter your code above
        //========================
    }
    
    public static int getInputInteger(String arg) {
        try
        {
            return Integer.parseInt(arg);
        } catch(NumberFormatException e) {
            System.out.println("ERROR: " + e.getMessage() + " This argument must be an integer!");
        }
        
        //error, return 0
        return 0;
    }
}