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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public void initGame(GameType gameType, Color playerColor, String remoteHost, String fileName){
        this.gameType = gameType;
        
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
        
        
        
        
        if(this.gameType == GameType.REPLAY)
            this.initReplay();
        else if(this.gameType == GameType.PLAYER_VS_PLAYER){
            this.disabled = DisabledFigures.DISABLE_NONE;
        }
        else if(this.gameType == GameType.PLAYER_VS_PC){
            this.playerColor = playerColor;
            
            this.disabled = (this.playerColor == Color.WHITE)? DisabledFigures.DISABLE_BLACK : DisabledFigures.DISABLE_WHITE;
        }
        else{
            throw new RuntimeException("Not implemented yet");
        }
    }
    
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
                    
                        this.battleGroundActiveField.toogle();
                        this.battleGroundActiveField = null; 
                    
                        if(this.battleground.isEndOfAllHope()){
                            JOptionPane.showMessageDialog(this, ((fieldUI.getField().getFigure().getColor() == Color.WHITE)? "Bílý" : "Černý")+ " hráč vyhrál hru!", "Queen - Konec hry", JOptionPane.INFORMATION_MESSAGE);
                            this.disabled = DisabledFigures.DISABLE_ALL;
                        }
                        
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
    
    public void createGame(GameType type, Color player, String remoteHost, String fileName){
        this.gameType = type;
        
        if(this.gameType == GameType.PLAYER_VS_PLAYER){
            
        }
        else if(this.gameType == GameType.PLAYER_VS_PC){
        
        }
        else{
            throw new RuntimeException("Not implemented yet");
        }        
    }
    
    public void setMoveHinting(boolean moveHinting){
        this.moveHinting = moveHinting;
    }
    
    public Desk getBattleground(){
        return this.battleground;
    }
    
    public GameType getGameType(){
        return this.gameType;
    }
    
    //public void setDisabled(DisabledFigures disabled){
    //    this.disabled = disabled;
    //}
    
    public Vector getRounds(){
        return this.battleground.getRounds();
    }
    
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
