//SeanZhang
//260873386
import java.io.*;
import java.util.ArrayList;
public class FileIO {
    // initialize the player
    public static Character readCharacter(String fileName) {
        Character player = null;
        String name;
        double attack;
        double maxHealth;
        int wins;
        try {
            FileReader fr =  new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String currentLine = br.readLine();
            name = currentLine;
            currentLine = br.readLine();
            attack = Double.valueOf(currentLine);
            currentLine = br.readLine();
            maxHealth = Double.valueOf(currentLine);
            currentLine = br.readLine();
            wins = Integer.valueOf(currentLine);
            player = new Character(name, attack, maxHealth, wins);
            br.close();
            fr.close();
            return player;

        } catch (FileNotFoundException fnotf) {
            System.out.println("file not found");
            return null;
        } catch (IOException e) {
            System.out.println("io problem");
            return null;
        }
    }
    public static ArrayList<Spell> readSpells(String fileName) {
            try (BufferedReader br = new BufferedReader(new FileReader(new File(fileName)))) {
                ArrayList<String> data=new ArrayList<String>();
                ArrayList<Spell> spells=new ArrayList<Spell>();
                String line="  ";
                while((line=br.readLine())!=null){
                    data.add(line);
                }
                // read the spells into String
                for(String s:data){
                    String[] str=s.split("\t");
                    Spell sp=new Spell(str[0],
                    Double.parseDouble(str[1]),
                    Double.parseDouble(str[2]),
                    Double.parseDouble(str[3]));
                    spells.add(sp);
                }
                br.close();
                return spells;
            } catch (FileNotFoundException fnotf) {
                return null;
            } catch (IOException e) {
                return null;
            }
        }
    public static void writeCharacter(Character character,String fileName) throws IOException,FileNotFoundException{
        BufferedWriter bw=new BufferedWriter(new FileWriter(System.getProperty("user.dir")+fileName));
        // use array to store the things needed to be written
        String[] str=new String[4];
        str[0]=character.getName();
        str[1]=String.valueOf(character.getAttackValue());
        str[2]=String.valueOf(character.getMaxHealth());
        str[3]=String.valueOf(character.getNumWins());
        for(int i=0;i<4;i++){
            bw.write(str[i]);
            bw.newLine();
        }
        bw.close();
    }


}

