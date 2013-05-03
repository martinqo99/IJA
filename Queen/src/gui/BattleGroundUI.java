/*
 * Projekt: Queen
 * Predmet: IJA - Seminar Java
 * Autori:
 *          xkolac12 < xkolac12 @ stud.fit.vutbr.cz >
 *          xmatya03 < xmatya03 @ stud.fit.vutbr.cz >
 */

package gui;

import gui.basis.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.RuntimeErrorException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import queen.basis.*;

/**
 * @author      Frantisek Kolacek <xkolac12 @ stud.fit.vutbr.cz>
 * @version     0.91
 * @since       2013-04-30
 */
public class BattleGroundUI extends JPanel {

    private Container content;

    private int dimension;
    private FieldButtonUI battleGroundActiveField;

    private Desk battleground;
    private FieldButtonUI battlegroundUI[];

    private GameType gameType;
    private Color playerColor;
    private DisabledFigures disabled;
    private boolean moveHinting;
    private boolean hardCoreMode;
    
    private int localPort;
    private ServerSocket localServer;
    private Socket handler;
    private PrintWriter handlerInput;
    private BufferedReader handlerOutput;

    private JTextArea logUI;

    /**
     * Creates new form BattleGroundUI
     */
    public BattleGroundUI() {
       super();

       this.initWindow();
       this.initBattleGround();

       this.add(this.content, BorderLayout.EAST);
       this.add(this.logUI, BorderLayout.WEST);

       this.setVisible(true);
    }

    private void initWindow(){
        this.content = new Container();
        this.setBackground(new Color(220, 220, 220));
        this.setLayout(new BorderLayout(0, 0));

        this.dimension = 8;
        this.battleground = new Desk(this.dimension);
        this.battlegroundUI = new FieldButtonUI[this.dimension * this.dimension];

        this.gameType = GameType.PLAYER_VS_PLAYER;
        this.playerColor = Color.WHITE;
        this.disabled = DisabledFigures.DISABLE_NONE;
        this.moveHinting = true;
        this.hardCoreMode = false;
        
        this.localPort = 5678;
 
        this.logUI = new JTextArea();
        this.logUI.setBackground(new Color(239, 239, 239));
        this.logUI.setEditable(false);
        this.logUI.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.logUI.setPreferredSize(new Dimension(150, 400));

    }

    private void initBattleGround(){
        LayoutManager layout = new GridLayout(0, 9);

        this.content.setLayout(layout);
        this.content.setBackground(Color.WHITE);

        JLabel tempLabel;

        for(int row = this.dimension; row > 0; row--){
            // Add row labels
            tempLabel = new JLabel(Integer.toString(row), SwingConstants.CENTER);
            tempLabel.setFont(new Font(null, Font.BOLD, 20));
            tempLabel.setForeground(new Color(152, 152, 152));
            this.content.add(tempLabel);

            for(char column = 'a'; column < (char)('a' + this.dimension); column++){
                // Create fields
                int pos = this.battleground.pos(column, row);

                this.battlegroundUI[pos] = new FieldButtonUI(this.battleground.at(column, row));

                this.battlegroundUI[pos].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleClickBattleGround(e);
                    }
                });

                //this.fields[pos].setText(this.fields[pos].getField().getPosition().toString());
                this.content.add(this.battlegroundUI[pos]);
            }

            this.content.setVisible(true);
            this.add(this.content);
        }

        // Blank label
        this.content.add(new JLabel());

        // Add column labels
        for(char column = 'A'; column < (char)('A' + this.dimension); column++){
            tempLabel = new JLabel(Character.toString(column), SwingConstants.CENTER);
            tempLabel.setFont(new Font(null, Font.BOLD, 20));
            tempLabel.setForeground(new Color(152, 152, 152));
            this.content.add(tempLabel);
        }
    }

    /**
     *
     * @param gameType
     * @param playerColor
     * @param remoteHost
     * @param fileName
     */
    public void initGame(GameType gameType, Color playerColor, String remoteHost, int remotePort, int localPort,  String fileName){
        this.gameType = gameType;

        // Nacteni hry
        if(!fileName.isEmpty()){
            Notation notation = new Notation();
            try {
                notation.loadFromFile(fileName);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BattleGroundUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(BattleGroundUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            Vector rounds = notation.getRounds();

            for(int i = 0; i < rounds.size(); i++){
                Move round = (Move)rounds.get(i);

                this.battleground.move(round.getFrom(), round.getTo());
            }

            this.reload();

            if(this.battleground.isEndOfAllHope()){
                JOptionPane.showMessageDialog(this, "Byla načtena již ukončená hra!", "Queen - Konec hry", JOptionPane.ERROR_MESSAGE);
                this.disabled = DisabledFigures.DISABLE_ALL;
                return;
            }
        }

        // Samotne spousten
        if(this.gameType == GameType.REPLAY)
            this.initReplay();
        else if(this.gameType == GameType.PLAYER_VS_PLAYER){
            this.disabled = DisabledFigures.DISABLE_NONE;
        }
        else if(this.gameType == GameType.PLAYER_VS_PC){
            this.playerColor = playerColor;

            this.disabled = (this.playerColor == Color.WHITE)? DisabledFigures.DISABLE_BLACK : DisabledFigures.DISABLE_WHITE;

            // AI je na tahu
            if(this.playerColor != this.battleground.getRoundColor()){
                this.AImove();
            }
        }
        // Lokalni port binding
        else if(this.gameType == GameType.PLAYER_VS_NETWORK_LOCAL){
            try {  
                if(this.localServer != null)
                    this.localServer.close();
                
                if(this.handler != null)
                    this.handler.close();
            
                this.localPort = localPort;
                
                this.localServer = new ServerSocket(this.localPort);

                this.handler = this.localServer.accept();
                
                this.handlerInput = new PrintWriter(this.handler.getOutputStream(), true);
                this.handlerOutput = new BufferedReader(new InputStreamReader(this.handler.getInputStream()));
                
                String line = null;
                String roundsBuffer = "";
                boolean recordRounds = false;
                
                do{
                    while(!this.handlerOutput.ready());
                    
                    line = this.handlerOutput.readLine();
                    
                    if("END".equals(line))
                        break;
                    
                    if(recordRounds){
                        roundsBuffer += line + "\n";
                    }
                    
                    if("BEGIN".equals(line))
                        continue;
                    
                    if("BLACK".equals(line))
                        this.playerColor = Color.WHITE;

                    
                    if("WHITE".equals(line))
                        this.playerColor = Color.BLACK;

                    
                    if("ROUNDS".equals(line))
                        recordRounds = true;                   
                    
                    
                }while(true);
                
                this.disabled = (this.playerColor == Color.WHITE)? DisabledFigures.DISABLE_BLACK : DisabledFigures.DISABLE_WHITE;
                
                if(!roundsBuffer.isEmpty()){
                    Notation notation = new Notation();
                    notation.loadFromRaw(roundsBuffer);
                
                    Vector rounds = notation.getRounds();
                
                    for(int i = 0; i < rounds.size(); i++){
                        Move tmp = (Move)rounds.get(i);
                    
                        this.battleground.move(tmp.getFrom(), tmp.getTo());
                    }
                
                    this.reload();
                }
                
                if(this.playerColor == this.battleground.getRoundColor()){
                    JOptionPane.showMessageDialog(this, "Jste na tahu.", "Queen - Síťová hra", JOptionPane.INFORMATION_MESSAGE);
                }
                
            } catch (IOException ex) {
                this.disabled = DisabledFigures.DISABLE_ALL;
                JOptionPane.showMessageDialog(this, "Chyba síťové komunikace!", "Queen - Síťová hra", JOptionPane.ERROR_MESSAGE);
            }
        }
        // Vzdalene pripojovani
        else{
            try{
                this.playerColor = playerColor;

                this.disabled = (this.playerColor == Color.WHITE)? DisabledFigures.DISABLE_BLACK : DisabledFigures.DISABLE_WHITE;
                
                System.out.println("Connect to: " + remoteHost + ":" + Integer.toString(remotePort));
                
                if(this.handler != null)
                    this.handler.close();
                
                this.handler = new Socket(remoteHost, remotePort);
              
                this.handlerInput = new PrintWriter(this.handler.getOutputStream(), true);
                this.handlerOutput = new BufferedReader(new InputStreamReader(this.handler.getInputStream()));                
                
                Vector rounds = this.battleground.getRounds();
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
                
                this.handlerInput.print("BEGIN\n");
                this.handlerInput.print((this.playerColor == Color.WHITE)? "WHITE\n" : "BLACK\n");
                this.handlerInput.print("ROUNDS\n");
                this.handlerInput.print(roundsString);
                this.handlerInput.print("END\n");
                this.handlerInput.flush();
                
                if(this.playerColor == this.battleground.getRoundColor()){
                    JOptionPane.showMessageDialog(this, "Jste na tahu.", "Queen - Síťová hra", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            catch(IOException ex){
                this.disabled = DisabledFigures.DISABLE_ALL;
                JOptionPane.showMessageDialog(this, "Chyba síťové komunikace!", "Queen - Síťová hra", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     *
     */
    public void initReplay(){
        this.gameType = GameType.REPLAY;
        this.disabled = DisabledFigures.DISABLE_ALL;
    }

    /* Vsechna ta klikaci magie */
    private void handleClickBattleGround(ActionEvent e){
        FieldButtonUI fieldUI = (FieldButtonUI)e.getSource();

        if(this.disabled == DisabledFigures.DISABLE_ALL)
            return;

        if(fieldUI.getField() == null && this.disabled == DisabledFigures.DISABLE_EMPTY_FIELDS)
            return;

        if(fieldUI.getField().getFigure() != null && fieldUI.getField().getFigure().getColor() == Color.BLACK && this.disabled == DisabledFigures.DISABLE_BLACK)
            return;

        if(fieldUI.getField().getFigure() != null && fieldUI.getField().getFigure().getColor() == Color.WHITE && this.disabled == DisabledFigures.DISABLE_WHITE)
            return;

        // Pole neni aktivovane, muze klikat pouze na figurky
        if(this.battleGroundActiveField == null){
            if(fieldUI.getField().getFigure() != null){
                if(fieldUI.getField().getFigure().getColor() == this.battleground.getRoundColor()){

                    Vector assassins = this.battleground.getReadyAssassins();

                    if(assassins.size() > 0){
                        boolean isAssassin = false;

                        for(int i = 0; i < assassins.size(); i++){
                            Position assassin = (Position)assassins.get(i);

                            if(assassin.equals(fieldUI.getField().getFigure().getPosition())){
                                isAssassin = true;
                                break;
                            }
                        }

                        if(!isAssassin){
                            JOptionPane.showMessageDialog(this, "Hráč je povinen provést skok!", "Queen - Chybný tah", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }

                    this.battleGroundActiveField = fieldUI;
                    fieldUI.toogle();

                    // Mark possibilities
                    if(this.moveHinting){
                    Vector possibilities = fieldUI.getField().getFigure().canMovePossibilities();
                    for(int i = 0; i < possibilities.size(); i++){
                        Possibility possibility = (Possibility)possibilities.get(i);

                        this.battlegroundUI[this.battleground.pos(possibility.getPosition())].mark();
                    }
                    }


                }
                else{
                    JOptionPane.showMessageDialog(this, "Hráč není na tahu!", "Queen - Chybný tah", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "Tah lze provést pouze s figurkou!", "Queen - Chybný tah", JOptionPane.ERROR_MESSAGE);
            }
        }
        // Pole jiz bylo aktivovane
        else{
            // Tahne na stejnou figuru
            if(fieldUI == this.battleGroundActiveField){
                this.battleGroundActiveField.toogle();

                // Unmark possibilities
                if(this.moveHinting){
                Vector possibilities = this.battleGroundActiveField.getField().getFigure().canMovePossibilities();
                for(int i = 0; i < possibilities.size(); i++){
                    Possibility possibility = (Possibility)possibilities.get(i);

                    this.battlegroundUI[this.battleground.pos(possibility.getPosition())].reload();
                }
                }

                this.battleGroundActiveField = null;
            }
            // Pokud se pokusime tahnout na jinou figuru
            else if(fieldUI.getField().getFigure() != null){
                JOptionPane.showMessageDialog(this, "Nelze provést tah na další figurku!", "Queen - Chybný tah", JOptionPane.ERROR_MESSAGE);
            }
            // Uspesny klik
            else{
                //if(this.battleground.move(this.battleGroundActiveField.getField().getPosition(), fieldUI.getField().getPosition())){
                if(this.battleGroundActiveField.getField().getFigure().canMove(fieldUI.getField().getPosition())){

                    // Unmark possibilities
                    if(this.moveHinting){
                    Vector possibilities = this.battleGroundActiveField.getField().getFigure().canMovePossibilities();
                    for(int i = 0; i < possibilities.size(); i++){
                        Possibility possibility = (Possibility)possibilities.get(i);

                        this.battlegroundUI[this.battleground.pos(possibility.getPosition())].reload();
                    }
                    }


                    try{
                        Vector victims = this.battleground.move(this.battleGroundActiveField.getField().getPosition(), fieldUI.getField().getPosition());

                        Vector rounds = this.battleground.getRounds();
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

                        //this.logs.add(fieldUI.getField().getPosition().toString());
                        this.logUI.setText(roundsString);

                        fieldUI.reload();

                        for(int i = 0; i < victims.size(); i++){
                            Position position = (Position) victims.get(i);

                            this.battlegroundUI[this.battleground.pos(position)].reload();
                        }
                        
                        if(this.gameType == GameType.PLAYER_VS_NETWORK_LOCAL || this.gameType == GameType.PLAYER_VS_NETWORK_REMOTE){
                            try{
                            
                                Move tmp = new Move(this.battleGroundActiveField.getField().getPosition(), fieldUI.getField().getPosition(), (victims.size() > 0)? true : false);
                                this.handlerInput.print(tmp.toString());
                            
                                while(!this.handlerOutput.ready());
                                
                                tmp = new Move(this.handlerOutput.readLine());
                                
                                this.battleground.move(tmp.getFrom(), tmp.getTo());
                                this.reload();
                            }
                            catch(IOException ex){
                                this.disabled = DisabledFigures.DISABLE_ALL;
                                JOptionPane.showMessageDialog(this, "Chyba síťové komunikace!", "Queen - Síťová hra", JOptionPane.ERROR_MESSAGE);
                            }
                        }

                        this.battleGroundActiveField.toogle();
                        this.battleGroundActiveField = null;

                        if(this.battleground.isEndOfAllHope()){
                            JOptionPane.showMessageDialog(this, ((fieldUI.getField().getFigure().getColor() == Color.WHITE)? "Bílý" : "Černý")+ " hráč vyhrál hru!", "Queen - Konec hry", JOptionPane.INFORMATION_MESSAGE);
                            this.disabled = DisabledFigures.DISABLE_ALL;
                            return;
                        }

                        if(this.gameType == GameType.PLAYER_VS_PC)
                            this.AImove();



                    }
                    catch(RuntimeException err){
                        JOptionPane.showMessageDialog(this, err.getMessage(), "Queen - Chybný tah", JOptionPane.ERROR_MESSAGE);
                    }

                }
                else{
                    JOptionPane.showMessageDialog(this, "Nelze provést tah na toto pole!", "Queen - Chybný tah", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void AImove(){
        Vector possible = new Vector();

        // Pokud vybira jen ze skoku, bude chytrej, relativne
        Vector assassins = this.battleground.getReadyAssassins();
        if(assassins.size() > 0){
            for(int i = 0; i < assassins.size(); i++){
                Position assassin = (Position)assassins.get(i);

                Vector possibilities = this.battlegroundUI[this.battleground.pos(assassin)].getField().getFigure().canMovePossibilities();

                if(possibilities.size() > 0){
                    for(int j = 0; j < possibilities.size(); j++){
                        possible.add(possibilities.get(j));
                    }
                }
            }
        }
        // Bude uplne blbej, pardon, nahodnej
        else{
            for(int i = 0; i < this.battlegroundUI.length; i++){
                if(this.battlegroundUI[i].getField().getFigure() == null)
                    continue;

                if(this.battlegroundUI[i].getField().getFigure().getColor() != this.battleground.getRoundColor())
                    continue;

                Vector possibilities = this.battlegroundUI[i].getField().getFigure().canMovePossibilities();

                if(possibilities.size() > 0){
                    for(int j = 0; j < possibilities.size(); j++){
                        possible.add(possibilities.get(j));
                    }
                }
            }
        }

        // AI ma kam tahnout
        if(possible.size() > 0){
            Possibility possibility;
            if (!this.hardCoreMode) {
                Random rand = new Random();

                int randomNum = rand.nextInt(possible.size() + 1);

                if(randomNum >= possible.size())
                    randomNum = possible.size() - 1;

                possibility = (Possibility)possible.get(randomNum);
            }
            else {
                possibility = this.minimax(possible);
            }

            this.battleground.move(possibility.getKiller(), possibility.getPosition());

            this.reload();
        }
        // Nema, AI prohrala
        else{
            JOptionPane.showMessageDialog(this, "Hráč vyhrál hru!", "Queen - Konec hry", JOptionPane.INFORMATION_MESSAGE);
            this.disabled = DisabledFigures.DISABLE_ALL;
        }
    }

    private Possibility minimax(Vector possible){
        Vector movePoss; // blbost, hledam jen vrahy, prepisu si to jindy
        Vector assassins = this.battleground.getReadyAssassins(this.battleground.getRoundColor() == Color.BLACK ? Color.WHITE : Color.BLACK);
        Possibility best = null;
        int best_move_old = 0, best_move_new = 0;
        boolean first = true;

        for (int i = 0; i < possible.size(); i++){
            Possibility possibility = (Possibility)possible.get(i);

            for (int j = 0; j < assassins.size(); j++){
                Vector victims = ((Possibility)assassins.get(j)).getVictims();

                for (int k = 0; k < victims.size(); k++){
                    if (possibility.getPosition().equals((Position)victims.get(k)))//((Position)victims.get(k)).equals(possibility.getPosition())
                        best_move_new--; // odecteme 1 za kazdou figurku, ktera me potom bude ohrozovat
                }
            }
            best_move_new += possibility.killed(); // pricteme kolik jich zabijeme

            if (first) { // urcite to jde lip, ne nebudu to prepisovat
                best_move_old = best_move_new;
                best = new Possibility(possibility);
                first = false;
            }
            else if (best_move_new > best_move_old) { // nasli jsme lepsi pohyb
                best_move_old = best_move_new;
                best = new Possibility(possibility);
            }
            best_move_new = 0;
        }
        return best;
    }

    /**
     *
     * @param moveHinting
     */
    public void setMoveHinting(boolean moveHinting){
        this.moveHinting = moveHinting;
    }

    public void setHardCoreMode(boolean hardCoreMode){
        this.hardCoreMode = hardCoreMode;
    }

    /**
     *
     * @return
     */
    public Desk getBattleground(){
        return this.battleground;
    }

    /**
     *
     * @return
     */
    public GameType getGameType(){
        return this.gameType;
    }

    /**
     *
     * @return
     */
    public Vector getRounds(){
        return this.battleground.getRounds();
    }

    /**
     *
     */
    public void reload(){
        Vector rounds = this.battleground.getRounds();
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

        this.logUI.setText(roundsString);

        for(int i = 0; i < this.battlegroundUI.length; i++)
            this.battlegroundUI[i].reload();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
