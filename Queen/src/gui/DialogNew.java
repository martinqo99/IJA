/*
 * Projekt: Queen
 * Predmet: IJA - Seminar Java
 * Soubor: DialogNew.java
 * Autori:
 *          xkolac12 < xkolac12 @ stud.fit.vutbr.cz >
 *          xmatya03 < xmatya03 @ stud.fit.vutbr.cz >
 * 
 * Trida DialogNew predstavuje dialog pro zalozeni nove hry
 */

package gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;

import gui.basis.GameDifficulty;
import gui.basis.GameType;

/**
 * @author      Frantisek Kolacek <xkolac12 @ stud.fit.vutbr.cz>
 * @author      Petr Matyas <xmatya03 @ stud.fit.vutbr.cz>
 * @version     0.91
 * @since       2013-04-30
 */
public class DialogNew extends JDialog {

    private boolean accepted;
    
    /**
     * Vytvori dialog pro novou hru
     */
    public DialogNew(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.accepted = false;
    }
    
    /**
     * Vraci, zda byl formular potvrzen
     * @return true, pokud byl formular odeslan, jinak false
     */
    public boolean isAccepted(){
        return this.accepted;
    }
    
    /**
     * Vraci typ hry
     * @return typ hry
     */
    public GameType getGameType(){
        if(this.radioPlayerVsPlayer.isSelected())
            return GameType.PLAYER_VS_PLAYER;
        else if(this.radioPlayerVsPC.isSelected())
            return GameType.PLAYER_VS_PC;
        else if(this.radioPlayerVsNetworkLocal.isSelected())
            return GameType.PLAYER_VS_NETWORK_LOCAL;
        else
            return GameType.PLAYER_VS_NETWORK_REMOTE;
    }
    
    /**
     * Vraci obtiznost hry
     * @return obtiznost hry
     */
    public GameDifficulty getGameDifficulty(){
        switch (this.inputPlayerDiff.getSelectedItem().toString()) {
            case "Normální":
                return GameDifficulty.GAME_NORMAL;
            case "Hardcore":
                return GameDifficulty.GAME_HARDCORE;
            default:
                return GameDifficulty.GAME_CHUCK_NORRIS;
        }
    }
    
    /**
     * Vraci barvu hrace
     * @return barva hrace
     */
    public Color getPlayerColor(){
        if("Bílá".equals(this.inputPlayerColor.getSelectedItem().toString()))
            return Color.WHITE;
        else
            return Color.BLACK;
    }
    
    /**
     * Vraci adresu vzdaleneho hrace
     * @return adresa vzdaleneho hrace
     */
    public String getRemoteHost(){
        return this.inputRemoteHost.getText();
    }
    
    /**
     * Vraci port vzdaleneho hrace
     * @return port vzdaleneho hrace
     */
    public int getRemotePort(){
        int port;
        
        try{
            port = Integer.parseInt(this.inputRemoteport.getText());
        }
        catch(NumberFormatException err){
            port = 5678;
        }
        
        
        return port;
    }
    
    /**
     * Vraci port lokalni hry
     * @return port lokalni hry
     */
    public int getLocalPort(){
        int port;
        
        try{
            port = Integer.parseInt(this.inputLocalPort.getText());
        }
        catch(NumberFormatException err){
            port = 5678;
        }
        
        
        return port;
    }
    
    /**
     * Vraci, zda-li se ma nacist ulozena hra
     * @return true, pokud se ma nacist ulozena hra, jinak false
     */
    public boolean isStoredGame(){
        return this.checkFileName.isSelected();
    }
    
    /**
     * Vraci nazev souboru s ulozenou hrou
     * @return nazev souboru s ulozenou hrou
     */
    public String getStoredGameFileName(){
        return this.inputFileName.getText();
    }    
    
    
    private void handleInputFile(){
        if(!this.inputFileName.isEnabled())
            return;
        
        JFileChooser dialog = new JFileChooser();
        
        try{
            File f = new File(new File("./examples/").getCanonicalPath());
            dialog.setCurrentDirectory(f);
        } catch(IOException err){}

        dialog.setAcceptAllFileFilterUsed(false);
        dialog.addChoosableFileFilter(new FileNameExtensionFilter("txt", "txt"));
        dialog.addChoosableFileFilter(new FileNameExtensionFilter("xml", "xml"));

        if(dialog.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            String filename = dialog.getSelectedFile().getName();
            String directory = dialog.getCurrentDirectory().toString();
            String extension = dialog.getFileFilter().getDescription();
            String fullPath = directory + "/" + filename;
            
            this.inputFileName.setText(fullPath);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        radioPlayerVsPlayer = new javax.swing.JRadioButton();
        radioPlayerVsPC = new javax.swing.JRadioButton();
        radioPlayerVsNetwork = new javax.swing.JRadioButton();
        radioPlayerVsNetworkLocal = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        inputPlayerColor = new javax.swing.JComboBox();
        inputRemoteHost = new javax.swing.JTextField();
        inputRemoteport = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        inputLocalPort = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        inputPlayerDiff = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        inputFileName = new javax.swing.JTextField();
        checkFileName = new javax.swing.JCheckBox();
        buttFileName = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Queen - Nová hra");
        setMaximumSize(new java.awt.Dimension(300, 300));
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Typ hry"));

        buttonGroup1.add(radioPlayerVsPlayer);
        radioPlayerVsPlayer.setSelected(true);
        radioPlayerVsPlayer.setText("Hráč proti hráči");
        radioPlayerVsPlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioPlayerVsPlayerActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioPlayerVsPC);
        radioPlayerVsPC.setText("Hráč proti počítači");
        radioPlayerVsPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioPlayerVsPCActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioPlayerVsNetwork);
        radioPlayerVsNetwork.setText("Síťová hra - připojit");
        radioPlayerVsNetwork.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioPlayerVsNetworkActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioPlayerVsNetworkLocal);
        radioPlayerVsNetworkLocal.setText("Síťová hra - hostovat");
        radioPlayerVsNetworkLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioPlayerVsNetworkLocalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioPlayerVsPlayer)
                    .addComponent(radioPlayerVsPC)
                    .addComponent(radioPlayerVsNetwork)
                    .addComponent(radioPlayerVsNetworkLocal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(radioPlayerVsPlayer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioPlayerVsPC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioPlayerVsNetwork)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioPlayerVsNetworkLocal))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Parametry hry"));

        jLabel1.setText("Barva hráče");

        jLabel2.setText("Vzdálený host");

        inputPlayerColor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bílá", "Černá" }));
        inputPlayerColor.setEnabled(false);

        inputRemoteHost.setText("localhost");
        inputRemoteHost.setToolTipText("Zadejte adresu vzdáleného počítače");
        inputRemoteHost.setEnabled(false);
        inputRemoteHost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputRemoteHostActionPerformed(evt);
            }
        });

        inputRemoteport.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        inputRemoteport.setText("5678");
        inputRemoteport.setEnabled(false);

        jLabel3.setText("Místní port");

        inputLocalPort.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        inputLocalPort.setText("5678");
        inputLocalPort.setEnabled(false);

        jLabel4.setText("Obtížnost počítače");

        inputPlayerDiff.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normální", "Hardcore", "Chuck Norris" }));
        inputPlayerDiff.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                        .addComponent(inputRemoteHost, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputRemoteport, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(inputLocalPort, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inputPlayerColor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inputPlayerDiff, 0, 134, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(inputPlayerColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputPlayerDiff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(inputRemoteHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputRemoteport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(inputLocalPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Uložená hra"));

        inputFileName.setEnabled(false);
        inputFileName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inputFileNameMouseClicked(evt);
            }
        });
        inputFileName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputFileNameActionPerformed(evt);
            }
        });

        checkFileName.setText("Načíst hru ze souboru");
        checkFileName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkFileNameActionPerformed(evt);
            }
        });

        buttFileName.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        buttFileName.setText("Zvolit soubor");
        buttFileName.setEnabled(false);
        buttFileName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttFileNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(checkFileName)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(inputFileName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttFileName)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(checkFileName)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputFileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttFileName, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Vytvořit hru");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Zrušit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkFileNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkFileNameActionPerformed
        if(this.checkFileName.isSelected()){
            this.inputFileName.setEnabled(true);
            this.buttFileName.setEnabled(true);
        }
        else{
            this.inputFileName.setEnabled(false);
            this.buttFileName.setEnabled(false);
        }
    }//GEN-LAST:event_checkFileNameActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void inputRemoteHostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputRemoteHostActionPerformed

    }//GEN-LAST:event_inputRemoteHostActionPerformed

    private void radioPlayerVsPlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioPlayerVsPlayerActionPerformed
        this.checkFileName.setEnabled(true);        
        this.inputRemoteHost.setEnabled(false);
        this.inputRemoteport.setEnabled(false);
        this.inputLocalPort.setEnabled(false);
        this.inputPlayerColor.setEnabled(false);
        this.inputPlayerDiff.setEnabled(false);
    }//GEN-LAST:event_radioPlayerVsPlayerActionPerformed

    private void radioPlayerVsNetworkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioPlayerVsNetworkActionPerformed
        this.checkFileName.setEnabled(true);
        this.inputRemoteHost.setEnabled(true);
        this.inputRemoteport.setEnabled(true);
        this.inputLocalPort.setEnabled(false);
        this.inputPlayerColor.setEnabled(true);
        this.inputPlayerDiff.setEnabled(false);
    }//GEN-LAST:event_radioPlayerVsNetworkActionPerformed

    private void radioPlayerVsPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioPlayerVsPCActionPerformed
        this.checkFileName.setEnabled(true);
        this.inputRemoteHost.setEnabled(false);
        this.inputRemoteport.setEnabled(false);
        this.inputLocalPort.setEnabled(false);
        this.inputPlayerColor.setEnabled(true);
        this.inputPlayerDiff.setEnabled(true);
    }//GEN-LAST:event_radioPlayerVsPCActionPerformed

    private void inputFileNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputFileNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputFileNameActionPerformed

    private void inputFileNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inputFileNameMouseClicked
        //this.handleInputFile();
    }//GEN-LAST:event_inputFileNameMouseClicked

    private void buttFileNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttFileNameActionPerformed
        this.handleInputFile();
    }//GEN-LAST:event_buttFileNameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(this.getGameType() == GameType.PLAYER_VS_NETWORK_REMOTE && this.getRemoteHost().isEmpty()){
            JOptionPane.showMessageDialog(this, "Nebyla zadána adresa vzdáleného hosta!", "Queen - Chyba při vytváření hry", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(this.getGameType() == GameType.PLAYER_VS_NETWORK_REMOTE && this.inputRemoteport.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Nebyla zadána adresa port hosta!", "Queen - Chyba při vytváření hry", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(this.getGameType() == GameType.PLAYER_VS_NETWORK_LOCAL && this.inputLocalPort.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Nebyl zadán lokální port!", "Queen - Chyba při vytváření hry", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(this.isStoredGame() && this.getStoredGameFileName().isEmpty()){
            JOptionPane.showMessageDialog(this, "Nebyl vybrán soubor obsahující uloženou hru!", "Queen - Chyba při vytváření hry", JOptionPane.ERROR_MESSAGE);
            return;        
        }
        
        this.accepted = true;
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void radioPlayerVsNetworkLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioPlayerVsNetworkLocalActionPerformed
        this.checkFileName.setEnabled(false);
        this.inputFileName.setText("");
        this.inputRemoteHost.setEnabled(false);
        this.inputRemoteport.setEnabled(false);
        this.inputLocalPort.setEnabled(true);
        this.inputPlayerColor.setEnabled(false);
        this.inputPlayerDiff.setEnabled(false);
    }//GEN-LAST:event_radioPlayerVsNetworkLocalActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttFileName;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox checkFileName;
    private javax.swing.JTextField inputFileName;
    private javax.swing.JTextField inputLocalPort;
    private javax.swing.JComboBox inputPlayerColor;
    private javax.swing.JComboBox inputPlayerDiff;
    private javax.swing.JTextField inputRemoteHost;
    private javax.swing.JTextField inputRemoteport;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton radioPlayerVsNetwork;
    private javax.swing.JRadioButton radioPlayerVsNetworkLocal;
    private javax.swing.JRadioButton radioPlayerVsPC;
    private javax.swing.JRadioButton radioPlayerVsPlayer;
    // End of variables declaration//GEN-END:variables
}
