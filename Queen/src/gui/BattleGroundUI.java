/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import queen.basis.*;

/**
 *
 * @author xkolac12 <xkolac12 at stud.fit.vutbr.cz>
 */
public class BattleGroundUI extends JPanel {
    
    private Container content;
    
    private int dimension;
    
    private boolean battleGroundWhiteTurn;
    private FieldButtonUI battleGroundFrom;
    
    private Desk battleground;
    private FieldButtonUI battlegroundUI[];
    
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
        this.battleGroundWhiteTurn = true;
        this.battleground = new Desk(this.dimension);
        this.battlegroundUI = new FieldButtonUI[this.dimension * this.dimension];
        
        this.logUI = new JTextArea();
        this.logUI.setBackground(new Color(239, 239, 239));
        //this.logUI.setEditable(false);
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
    
    private void handleClickBattleGround(ActionEvent e){
        FieldButtonUI fieldUI = (FieldButtonUI)e.getSource();        

        // Pole neni aktivovane, muze klikat pouze na figurky
        if(this.battleGroundFrom == null){
            if(fieldUI.getField().getFigure() != null){
                if(this.battleGroundWhiteTurn && fieldUI.getField().getFigure().getColor() == Color.WHITE || !this.battleGroundWhiteTurn && fieldUI.getField().getFigure().getColor() == Color.BLACK){
                    this.battleGroundFrom = fieldUI;
                    this.battleGroundWhiteTurn = !this.battleGroundWhiteTurn;
                    fieldUI.toogle();                
                }
                else{
                    JOptionPane.showMessageDialog(this, "Wait for your turn!", "Queen - Move error", JOptionPane.ERROR_MESSAGE);
                }    
            }
            else{
                JOptionPane.showMessageDialog(this, "Cannot move with no figure!", "Queen - Move error", JOptionPane.ERROR_MESSAGE);
            }
        }
        // Pole jiz bylo aktivovane
        else{
            // Tahne na stejnou figuru
            if(fieldUI == this.battleGroundFrom){
                this.battleGroundFrom.toogle();
                this.battleGroundWhiteTurn = !this.battleGroundWhiteTurn;
                this.battleGroundFrom = null;
            }
            // Pokud se pokusime tahnout na jinou figuru
            else if(fieldUI.getField().getFigure() != null){
                JOptionPane.showMessageDialog(this, "Cannot move on another figure!", "Queen - Move error", JOptionPane.ERROR_MESSAGE);
            }
            // Uspesny klik
            else{
            
                
                this.battleGroundFrom.toogle();
                this.battleGroundFrom = null;
            }        
        }

        
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
