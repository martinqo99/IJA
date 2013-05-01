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
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author      Frantisek Kolacek <xkolac12 @ stud.fit.vutbr.cz>
 * @version     0.91
 * @since       2013-04-30
 */
public class ReplayUI extends javax.swing.JFrame {

    private JToolBar mainMenuBar;
    private JButton mainMenuPrev;
    private JButton mainMenuPlay;
    private JButton mainMenuPause;
    private JButton mainMenuStop;
    private JButton mainMenuNext;
    private JButton mainMenuOpen;
    private JButton mainMenuLoad;
    
    private JButton mainMenuIncrease;
    private JTextField mainMenuInterval;
    private JButton mainMenuDecrease;
    
    private JButton mainMenuHelp;
    private JButton mainMenuQuit;

    private Container content;
    
    private int interval;

    public ReplayUI() {
        this.interval = 1;
        
        this.initWindow();
        this.initMenu();
        this.initContent();

        this.pack();
    }

    private void initWindow(){
        this.setTitle("Queen - Přehrávač partií");
        this.setIconImage(new ImageIcon(getClass().getResource("/gfx/icon.png")).getImage());

        this.setSize(600, 400);
        this.setResizable(false);
        this.setLocation(50, 50);

        this.setLocationRelativeTo(this);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.setVisible(true);
    }

    private void initMenu(){
        this.mainMenuBar = new JToolBar();
        this.mainMenuBar.setFloatable(false);


        this.mainMenuPrev = new JButton();
        this.mainMenuPlay = new JButton();
        this.mainMenuPause = new JButton();
        this.mainMenuStop = new JButton();
        this.mainMenuNext = new JButton();
        this.mainMenuOpen = new JButton();
        this.mainMenuLoad = new JButton();
        
        this.mainMenuIncrease = new JButton();
        
        this.mainMenuInterval = new JTextField(Integer.toString(this.interval), 1);
        this.mainMenuInterval.setEditable(false);
        
        this.mainMenuInterval.setHorizontalAlignment(JTextField.RIGHT);

        this.mainMenuDecrease = new JButton();
        
        this.mainMenuHelp = new JButton();
        this.mainMenuQuit = new JButton();

        this.mainMenuPrev.setIcon(new ImageIcon(getClass().getResource("/gfx/replay_prev.png")));
        this.mainMenuPlay.setIcon(new ImageIcon(getClass().getResource("/gfx/replay_play.png")));
        this.mainMenuPause.setIcon(new ImageIcon(getClass().getResource("/gfx/replay_pause.png")));
        this.mainMenuStop.setIcon(new ImageIcon(getClass().getResource("/gfx/replay_stop.png")));
        this.mainMenuNext.setIcon(new ImageIcon(getClass().getResource("/gfx/replay_next.png")));
        this.mainMenuOpen.setIcon(new ImageIcon(getClass().getResource("/gfx/replay_open.png")));
        this.mainMenuLoad.setIcon(new ImageIcon(getClass().getResource("/gfx/replay_load.png")));
        this.mainMenuIncrease.setIcon(new ImageIcon(getClass().getResource("/gfx/replay_increase.png")));
        this.mainMenuDecrease.setIcon(new ImageIcon(getClass().getResource("/gfx/replay_decrease.png")));
        this.mainMenuHelp.setIcon(new ImageIcon(getClass().getResource("/gfx/replay_help.png")));
        this.mainMenuQuit.setIcon(new ImageIcon(getClass().getResource("/gfx/replay_quit.png")));

        this.mainMenuBar.add(this.mainMenuPrev);
        this.mainMenuBar.add(this.mainMenuPlay);
        this.mainMenuBar.add(this.mainMenuPause);
        this.mainMenuBar.add(this.mainMenuStop);
        this.mainMenuBar.add(this.mainMenuNext);
        this.mainMenuBar.add(this.mainMenuOpen);
        this.mainMenuBar.add(this.mainMenuLoad);
        this.mainMenuBar.add(new JSeparator());
        
        this.mainMenuBar.add(this.mainMenuInterval);
        this.mainMenuBar.add(this.mainMenuIncrease);
        this.mainMenuBar.add(this.mainMenuDecrease);
        
        this.mainMenuBar.add(new JSeparator());
        this.mainMenuBar.add(this.mainMenuHelp);
        this.mainMenuBar.add(this.mainMenuQuit);

        //this.mainMenuOpen.setAccelerator(KeyStroke.getKeyStroke('O', KeyEvent.CTRL_DOWN_MASK));
        //this.mainMenuQuit.setAccelerator(KeyStroke.getKeyStroke('Q', KeyEvent.CTRL_DOWN_MASK));

        this.mainMenuOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDialogLoad(e);
            }
        });
        
        this.mainMenuIncrease.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleIncrease(e);
            }
        });
        
        this.mainMenuDecrease.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDecrease(e);
            }
        });

        this.mainMenuHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDialogAbout(e);
            }
        });

        // Bind close event to Quit
        this.mainMenuQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void initContent(){
        this.content = this.getContentPane();

        this.content.add(this.mainMenuBar, BorderLayout.NORTH);

        BattleGroundUI battleground = new BattleGroundUI();
        battleground.setDisabled(DisabledFigures.DISABLE_ALL);

        this.content.add(battleground, BorderLayout.SOUTH);
    }

    private void handleDialogLoad(ActionEvent e){
        JFileChooser dialog = new JFileChooser();

        dialog.setAcceptAllFileFilterUsed(false);
        dialog.addChoosableFileFilter(new FileNameExtensionFilter("txt", "txt"));
        dialog.addChoosableFileFilter(new FileNameExtensionFilter("xml", "xml"));

        if(dialog.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            String filename = dialog.getSelectedFile().getName();
            String directory = dialog.getCurrentDirectory().toString();
            String extension = dialog.getFileFilter().getDescription();
            String fullPath = directory + "/" + filename;

            JOptionPane.showMessageDialog(this, "Hra byla úspěšně načtena", "Queen - Načtení hry", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void handleIncrease(ActionEvent e){
        this.interval++;
        
        if(this.interval > 10)
            this.interval = 10;
        
        this.mainMenuInterval.setText(Integer.toString(this.interval));
    }
    
    private void handleDecrease(ActionEvent e){
        this.interval--;
        
        if(this.interval < 1)
            this.interval = 1;
        
        this.mainMenuInterval.setText(Integer.toString(this.interval));
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
            java.util.logging.Logger.getLogger(ReplayUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReplayUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReplayUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReplayUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReplayUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
