/*
 * Projekt: Queen
 * Predmet: IJA - Seminar Java
 * Autori:
 *          xkolac12 < xkolac12 @ stud.fit.vutbr.cz >
 *          xmatya03 < xmatya03 @ stud.fit.vutbr.cz >
 */

package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author      Frantisek Kolacek <xkolac12 @ stud.fit.vutbr.cz>
 * @version     0.91
 * @since       2013-04-30
 */
public class QueenUI extends JFrame {

    private JMenuBar mainMenuBar;
    private JMenu mainMenuGame;
    private JMenuItem mainMenuGameNew;
    private JMenuItem mainMenuGameLoad;
    private JMenuItem mainMenuGameSave;
    private JMenuItem mainMenuGameReplay;
    private JMenuItem mainMenuGameQuit;
    private JMenu mainMenuHelp;
    private JMenuItem mainMenuHelpAbout;
    
    private Container content;
    
    /**
     * Creates new form QueenUI
     */
    public QueenUI() {
        initWindow();
        initMenu();
        initContent();
        
        this.pack();
    }

    private void initWindow(){
        this.setTitle("Queen - IJA projekt");
        this.setIconImage(new ImageIcon(getClass().getResource("/gfx/icon.png")).getImage());

        this.setSize(600, 400);
        this.setResizable(false);
        this.setLocation(50, 50);
        
        this.setLocationRelativeTo(this);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        this.setVisible(true);
    }
    
    private void initMenu(){
        //Create menu bar
        this.mainMenuBar = new JMenuBar();
        
        //Create each menu
        this.mainMenuGame = new JMenu("Hra");
        this.mainMenuHelp = new JMenu("Nápověda");
        
        this.mainMenuGame.setMnemonic('H');
        this.mainMenuHelp.setMnemonic('N');
        
        //Create menu items
        this.mainMenuGameNew = new JMenuItem("Nová");
        this.mainMenuGameLoad = new JMenuItem("Načíst");
        this.mainMenuGameSave = new JMenuItem("Uložit");
        this.mainMenuGameReplay = new JMenuItem("Přehrát");
        this.mainMenuGameQuit = new JMenuItem("Ukončit");
        this.mainMenuHelpAbout = new JMenuItem("O programu");
        
        this.mainMenuGameNew.setIcon(new ImageIcon(getClass().getResource("/gfx/icon_new.png")));
        this.mainMenuGameLoad.setIcon(new ImageIcon(getClass().getResource("/gfx/icon_load.png")));
        this.mainMenuGameSave.setIcon(new ImageIcon(getClass().getResource("/gfx/icon_save.png")));
        this.mainMenuGameReplay.setIcon(new ImageIcon(getClass().getResource("/gfx/icon_replay.png")));
        this.mainMenuGameQuit.setIcon(new ImageIcon(getClass().getResource("/gfx/icon_quit.png")));
        this.mainMenuHelpAbout.setIcon(new ImageIcon(getClass().getResource("/gfx/icon_help.png")));
        
        this.mainMenuGameNew.setAccelerator(KeyStroke.getKeyStroke('N', KeyEvent.CTRL_DOWN_MASK));
        this.mainMenuGameLoad.setAccelerator(KeyStroke.getKeyStroke('O', KeyEvent.CTRL_DOWN_MASK));
        this.mainMenuGameSave.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_DOWN_MASK));
        this.mainMenuGameReplay.setAccelerator(KeyStroke.getKeyStroke('R', KeyEvent.CTRL_DOWN_MASK));
        this.mainMenuGameQuit.setAccelerator(KeyStroke.getKeyStroke('Q', KeyEvent.CTRL_DOWN_MASK));
        
        this.mainMenuGameLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDialogLoad(e);
            }
        });
        
        this.mainMenuGameSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDialogSave(e);
            }
        });
        
        this.mainMenuGameReplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDialogReplay(e);
            }
        });
        
        
        // Bind close event to Quit
        this.mainMenuGameQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        this.mainMenuHelpAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDialogAbout(e);
            }
        });
        
        this.mainMenuGame.add(this.mainMenuGameNew);
        this.mainMenuGame.add(this.mainMenuGameLoad);
        this.mainMenuGame.add(this.mainMenuGameSave);
        this.mainMenuGame.add(this.mainMenuGameReplay);
        this.mainMenuGame.add(new JSeparator());
        this.mainMenuGame.add(this.mainMenuGameQuit);
        this.mainMenuHelp.add(this.mainMenuHelpAbout);
        
        this.mainMenuBar.add(this.mainMenuGame);
        this.mainMenuBar.add(this.mainMenuHelp);
        
        this.setJMenuBar(this.mainMenuBar);        
    }

    private void initContent(){
        this.content = this.getContentPane();
        
        this.content.add(new BattleGroundUI());
    }
    
    private void handleDialogLoad(ActionEvent e){
        DialogLoad dialog = new DialogLoad(this, true);
        dialog.setLocationRelativeTo(this);
        
        dialog.setVisible(true);
    }
    
    private void handleDialogSave(ActionEvent e){
        DialogSave dialog = new DialogSave(this, true);
        dialog.setLocationRelativeTo(this);
        
        dialog.setVisible(true);
    }
    
    private void handleDialogReplay(ActionEvent e){
        Thread thread = new Thread(){
            @Override
            public void run(){
                JFrame dialog = new ReplayUI();
                dialog.setVisible(true);
            }
        };
        
        thread.start();
    }
    
    private void handleDialogAbout(ActionEvent e){
        DialogAbout dialog = new DialogAbout(this, true);
        dialog.setLocationRelativeTo(this);
        
        dialog.setVisible(true);        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QueenUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QueenUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QueenUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QueenUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QueenUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}