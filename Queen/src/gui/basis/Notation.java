/*
 * Projekt: Queen
 * Predmet: IJA - Seminar Java
 * Autori:
 *          xkolac12 < xkolac12 @ stud.fit.vutbr.cz >
 *          xmatya03 < xmatya03 @ stud.fit.vutbr.cz >
 */

package gui.basis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import queen.basis.Move;
import queen.basis.Position;

/**
 * @author      Frantisek Kolacek <xkolac12 @ stud.fit.vutbr.cz>
 * @version     0.91
 * @since       2013-04-30
 */
public class Notation {

    private Vector raw;
    private Vector rounds;
    
    public void initNotation(){
        this.raw = new Vector();
        this.rounds = new Vector();
    }
    
    /**
     *
     * @param fileName
     * @param rounds
     * @throws FileNotFoundException
     */
    public static void saveToFile(String fileName, Vector rounds) throws FileNotFoundException{
        PrintWriter writer = new PrintWriter(fileName);

        int roundsCounter = 1;
        String roundsString = "";
                    
        for(int i = 0; i < rounds.size(); i++){
            if(i % 2 == 0)
                roundsString += Integer.toString(roundsCounter) + ". ";

                roundsString += ((Move)rounds.get(i)).toString();
                        
                if(i % 2 == 0)
                    roundsString += " ";
                else{
                    roundsString += "\n";
                    roundsCounter++;
                }
        }
        
        writer.print(roundsString);
        
        writer.close();
    }

    public void loadFromFile(String fileName) throws FileNotFoundException, IOException{
    
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder builder = new StringBuilder();
        String line = reader.readLine();
        
        while(line != null){

            builder.append(line);
            builder.append("\n");
            
            line = reader.readLine();
        }
        
        reader.close();
        
        this.loadFromRaw(builder.toString());
    }
    
    public void loadFromRaw(String raw) throws IOException{
        this.initNotation();
        
        String buffer[] = raw.split("[\\r?\\n]+");
        
        this.raw.addAll(Arrays.asList(buffer));         
        
        this.parse();
    }    
    
    private void parse() throws IOException{
        Pattern regexFull = Pattern.compile("^([0-9])\\. ([a-z][0-9])([x\\-])([a-z][0-9]) ([a-z][0-9])([x\\-])([a-z][0-9])$");
        Pattern regexHalf = Pattern.compile("^([0-9])\\. ([a-z][0-9])([x\\-])([a-z][0-9])[ ]?$");

        // Radku po radce
        for(int i = 0; i < this.raw.size(); i++){
            String line = (String)this.raw.get(i);
            
            Matcher matchFull = regexFull.matcher(line);
            Matcher matchHalf = regexHalf.matcher(line);
                        
            if(matchFull.find()){
                //System.out.println("Full: " + matchFull.group(0));
                
                String operator;
                
                operator = matchFull.group(2);
                this.rounds.add(new Move(new Position(matchFull.group(1)), new Position(matchFull.group(3)), ("-".equals(operator))? true : false ));
                
                operator = matchFull.group(5);
                this.rounds.add(new Move(new Position(matchFull.group(4)), new Position(matchFull.group(6)), ("-".equals(operator))? true : false ));                
            }
            else if(matchHalf.find()){
                //System.out.println("Half: " + line);
                
                String operator;
                
                operator = matchHalf.group(2);
                this.rounds.add(new Move(new Position(matchHalf.group(1)), new Position(matchHalf.group(3)), ("-".equals(operator))? true : false ));
            }
            else
                throw new IOException();            
        }
        
        
        
        for(int i = 0; i < this.rounds.size(); i++)
            System.out.println(this.rounds.get(i).toString());
    }
    
    public Vector getRounds(){
        return this.rounds;
    }
    
}
