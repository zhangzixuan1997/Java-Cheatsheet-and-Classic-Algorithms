//SeanZhang
//260873386
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
public class BattleGame {
    private static Random r=new Random();
    public static void playGame(String player, String monster,String spell){
        Character player1=null;
        Character player2=null;
        boolean shouldPlay=true;
        ArrayList<Spell> spells=new ArrayList<Spell>();
        try{
            player1= FileIO.readCharacter(player) ;
            player2= FileIO.readCharacter(monster);
            spells= FileIO.readSpells(spell);
        }catch(Exception e){
            shouldPlay=false;
            System.out.println("the game cannot be played");
            e.printStackTrace();
        }
        // To identify if the game should be played with spells
        if(shouldPlay){
            if(FileIO.readSpells(spell)==null)
                System.out.println("This game will be played without spells");
            Character.setSpells(spells);
            //player2.setSpell(spells);
            System.out.println("Name:   "+player1.getName()+"\n"+
                    "Health:   "+player1.getCurrHealth()+"\n"+
                    "Attack:   "+player1.getAttackValue()+"\n"+
                    "Number of wins:   "+player1.getNumWins());
            System.out.println();
            System.out.println("Name:   "+player2.getName()+"\n"+
                    "Health:   "+player2.getCurrHealth()+"\n"+
                    "Attack:   "+player2.getAttackValue()+"\n"+
                    "Number of wins:   "+player2.getNumWins());
            System.out.println();
            // Display the spells
            Character.displaySpells();
            while(player1.getCurrHealth()>0&&player2.getCurrHealth()>0){
                Scanner sc=new Scanner(System.in);
                System.out.println("command: ");
                //Get player 's command
                String str=sc.nextLine();
                String temp=str.toLowerCase();
                if(str.equals("attack")){
                    int num=r.nextInt();
                    double pAttNum=player1.getAttackDamage(num);
                    player2.takeDamage(pAttNum);
                    System.out.println(String.format("%s attacks for %1.2f damage!", player1.getName(),pAttNum));
                    System.out.println(String.format("%s cuurent health is %1.2f ", player2.getName(),player2.getCurrHealth()));
                    System.out.println();
                    double eAttNum=player2.getAttackDamage(num);
                    player1.takeDamage(eAttNum);
                    System.out.println(String.format("%s attacks for %1.2f damage!", player2.getName(),eAttNum));
                    System.out.println(String.format("%s cuurent health is %1.2f ", player1.getName(),player1.getCurrHealth()));
                }
                else if(str.equals("quit")){
                    System.out.println("Goodbye");
                    break;
                }
                else{
                    if(spells.isEmpty()){
                        System.out.println("input was not recognized, please use the attack or quit commands");}
                    else{
                        int num=r.nextInt();
                        double damage=player1.castSpell(temp,num);
                        // use %s %1.2f to define the format
                        //If the damage returned is less than or equal to 0, print a message 
                        //saying that the player tried to cast a spell, but they failed.
                        if(damage<=0){
                            System.out.println(String.format("%s tried cast %s, but they failed.", player1.getName(),str));
                            System.out.println();
                            double eAttNum=player2.getAttackDamage(num);
                            player1.takeDamage(eAttNum);
                            System.out.println(String.format("%s attacks for %1.2f damage", player2.getName(),eAttNum));
                            System.out.println(String.format("%s cuurent health is %1.2f ", player1.getName(),player1.getCurrHealth()));
                        }
                        else{
                            player2.takeDamage(damage);
                            System.out.println(String.format("%s cast %s dealing %1.2f damage", player1.getName(),str,damage));
                            System.out.println(String.format("%s cuurent health is %1.2f ", player2.getName(),player2.getCurrHealth()));
                            System.out.println();
                            double eAttNum=player2.getAttackDamage(num);
                            player1.takeDamage(eAttNum);
                            System.out.println(String.format("%s attacks for %1.2f damage", player2.getName(),eAttNum));
                            System.out.println(String.format("%s cuurent health is %1.2f ", player1.getName(),player1.getCurrHealth()));
                        }
                    }
                }
                if(player1.getCurrHealth()<=0){
                    System.out.println(String.format("%s is lose, congraduate %s!", player1.getName(),player2.getName()));
                    player2.increaseWins();
                    try {
                        FileIO.writeCharacter(player2, monster);
                    } catch (FileNotFoundException e) {

                        e.printStackTrace();
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }
                if(player2.getCurrHealth()<=0){
                    System.out.println(String.format("%s is lose, congratulations %s !", player2.getName(),player1.getName()));
                    player1.increaseWins();
                    try {
                        FileIO.writeCharacter(player1, player);
                    } catch (FileNotFoundException e) {

                        e.printStackTrace();
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }
            }



        }
    }
}
