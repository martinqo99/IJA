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
import java.io.File;
import java.util.Arrays;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import queen.basis.Move;
import queen.basis.Position;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
    public static void saveToFile(String fileName, Vector rounds) throws FileNotFoundException, IOException{
        if (fileName.matches(".*\\.txt")) { // ukladame do textoveho souboru
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
        else { // ukladame do XML
            try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.newDocument();
		Element game = doc.createElement("game");
		doc.appendChild(game);
                for(int i = 0; i < rounds.size(); i++){
                    Element move;
                    if (i % 2 == 0) {
                        move = doc.createElement("move");
                        move.appendChild(doc.createTextNode(((Move)rounds.get(i)).toString()));
                    }
                    else {
                        move = doc.createElement("countermove");
                        move.appendChild(doc.createTextNode(((Move)rounds.get(i)).toString()));
                    }
                    game.appendChild(move);
                }
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(fileName));
                transformer.transform(source, result);
            } catch (ParserConfigurationException pce) {
                throw new IOException();
            } catch (TransformerException tce) {
                throw new IOException();
            }
        }
    }

    /**
     *
     * @param fileName
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void loadFromFile(String fileName) throws FileNotFoundException, IOException{
        if (fileName.matches(".*\\.txt")) {
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
        else {
            try {
                File xmlFile = new File(fileName);
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(xmlFile);
                Element root = doc.getDocumentElement();
                if (!root.getNodeName().matches("game"))
                    throw new IOException();
                StringBuilder builder = new StringBuilder();
                NodeList move = doc.getElementsByTagName("move");
                NodeList countermove = doc.getElementsByTagName("countermove");
                for (int i = 0; i < move.getLength(); i++) {
                    builder.append(Integer.toString(i)+". ");
                    Element actMove = (Element)move.item(i);
                    Element actCountermove = (Element)countermove.item(i);
                    builder.append(actMove.getTextContent());
                    if (i < countermove.getLength())
                        builder.append(" "+actCountermove.getTextContent());
                    builder.append("\n");
                }
                this.loadFromRaw(builder.toString());
            } catch (Exception e) {
                throw new IOException();
            }
        }
    }

    /**
     *
     * @param raw
     * @throws IOException
     */
    public void loadFromRaw(String raw) throws IOException{
        this.initNotation();

        String buffer[] = raw.split("[\\r?\\n]+");

        this.raw.addAll(Arrays.asList(buffer));

        this.parse();
    }

    private void parse() throws IOException{
        Pattern regexFull = Pattern.compile("^([0-9]+)\\. ([a-z][0-9])([x\\-])([a-z][0-9]) ([a-z][0-9])([x\\-])([a-z][0-9])$");
        Pattern regexHalf = Pattern.compile("^([0-9]+)\\. ([a-z][0-9])([x\\-])([a-z][0-9])[ ]?$");

        // Radku po radce
        for(int i = 0; i < this.raw.size(); i++){
            String line = (String)this.raw.get(i);

            Matcher matchFull = regexFull.matcher(line);
            Matcher matchHalf = regexHalf.matcher(line);

            if(matchFull.find()){
                //System.out.println("Full: " + matchFull.group(0));

                String operator;

                operator = matchFull.group(3);
                this.rounds.add(new Move(new Position(matchFull.group(2)), new Position(matchFull.group(4)), ("x".equals(operator))? true : false ));

                operator = matchFull.group(6);
                this.rounds.add(new Move(new Position(matchFull.group(5)), new Position(matchFull.group(7)), ("x".equals(operator))? true : false ));
            }
            else if(matchHalf.find()){
                //System.out.println("Half: " + line);

                String operator;

                operator = matchHalf.group(3);
                this.rounds.add(new Move(new Position(matchHalf.group(2)), new Position(matchHalf.group(4)), ("x".equals(operator))? true : false ));
            }
            else
                throw new IOException();
        }

        //for(int i = 0; i < this.rounds.size(); i++)
        //    System.out.println(this.rounds.get(i).toString());
    }

    /**
     *
     * @return
     */
    public Vector getRounds(){
        return this.rounds;
    }

}
