/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import queen.basis.*;

/**
 *
 * @author xkolac12 <xkolac12 at stud.fit.vutbr.cz>
 */
public class FieldButtonUI extends JButton {
    
    private Field field;
    private Image image;
    
    public FieldButtonUI(Field field){
        this(field, "");
    }
    
    public FieldButtonUI(Field field, String title){
        super(title);
        
        this.setBorder(null);
        this.setSize(50, 50);
        
        this.field = field;
        
        try{
            String fieldImage;
            
            if(this.field.getFigure() != null){
                fieldImage = (this.field.getFigure().getColor() == Color.BLACK)? "/gfx/black_stone.png" : "/gfx/white_stone.png";
            }
            else{
                fieldImage = (this.field.getColor() == Color.BLACK)? "/gfx/black.png" : "/gfx/white.png";
            }
            this.image = ImageIO.read(getClass().getResource(fieldImage));

            this.setIcon(new ImageIcon(this.image));
        }
        catch(IOException ex){
        
        }
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);        
    }
    
    @Override
    public Dimension getPreferredSize(){
        //Dimension size = super.getPreferredSize();
        Dimension size = new Dimension(50, 50);
        return size;
    }    
    
    public void setField(Field field){
        this.field = field;
    }
    
    public Field getField(){
        return this.field;
    }
    
}
