/*
 * Projekt: Queen
 * Predmet: IJA - Seminar Java
 * Autori:
 *          xkolac12 < xkolac12 @ stud.fit.vutbr.cz >
 *          xmatya03 < xmatya03 @ stud.fit.vutbr.cz >
 */

package gui;
import gui.basis.GameType;
import javax.swing.*;
import java.awt.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author      Frantisek Kolacek <xkolac12 @ stud.fit.vutbr.cz>
 * @version     0.91
 * @since       2013-04-30
 */
public class DialogNew extends javax.swing.JDialog {

    private boolean accepted;
    
    /**
     * Creates new form DialogNew
     */
    public DialogNew(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.accepted = false;
    }
    
    public boolean isAccepted(){
        return this.accepted;
    }
    
    public GameType getGameType(){
        if(this.radioPlayerVsPlayer.isSelected())
            return GameType.PLAYER_VS_PLAYER;
        else if(this.radioPlayerVsPC.isSelected())
            return GameType.PLAYER_VS_PC;
        else
            return GameType.PLAYER_VS_NETWORK;
    }
    
    public Color getPlayerColor(){
        if("Bílá".equals(this.inputPlayerColor.getSelectedItem().toString()))
            return Color.WHITE;
        else
            return Color.BLACK;
    }
    
    public String getRemoteHost(){
        return this.inputRemoteHost.getText();
    }
    
    public boolean isStoredGame(){
        return this.checkFileName.isSelected();
    }
    
    public String getStoredGameFileName(){
        return this.inputFileName.getText();
    }    
    
    
    private void handleInputFile(){
        if(!this.inputFileName.isEnabled())
            return;
        
        JFileChooser dialog = new JFileChooser();

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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        inputPlayerColor = new javax.swing.JComboBox();
        inputRemoteHost = new javax.swing.JTextField();
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
        radioPlayerVsPC.setText("Hráč proti PC");
        radioPlayerVsPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioPlayerVsPCActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioPlayerVsNetwork);
        radioPlayerVsNetwork.setText("Síťová hra");
        radioPlayerVsNetwork.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioPlayerVsNetworkActionPerformed(evt);
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
                    .addComponent(radioPlayerVsNetwork))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(radioPlayerVsPlayer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioPlayerVsPC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioPlayerVsNetwork))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Parametry hry"));

        jLabel1.setText("Barva hráče");

        jLabel2.setText("Vzdálený host");

        inputPlayerColor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bílá", "Černá" }));

        inputRemoteHost.setText("localhost");
        inputRemoteHost.setToolTipText("Zadejte adresu vzdáleného počítače");
        inputRemoteHost.setEnabled(false);
        inputRemoteHost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputRemoteHostActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                        .addComponent(inputRemoteHost, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(inputPlayerColor, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(inputPlayerColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(inputRemoteHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
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
                .addContainerGap(26, Short.MAX_VALUE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
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
        this.inputRemoteHost.setEnabled(false);
    }//GEN-LAST:event_radioPlayerVsPlayerActionPerformed

    private void radioPlayerVsNetworkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioPlayerVsNetworkActionPerformed
        this.inputRemoteHost.setEnabled(true);
    }//GEN-LAST:event_radioPlayerVsNetworkActionPerformed

    private void radioPlayerVsPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioPlayerVsPCActionPerformed
        this.inputRemoteHost.setEnabled(false);
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
        if(this.getGameType() == GameType.PLAYER_VS_NETWORK && this.getRemoteHost().isEmpty()){
            JOptionPane.showMessageDialog(this, "Nebyla zadána adresa vzdáleného hosta!", "Queen - Chyba při vytváření hry", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(this.isStoredGame() && this.getStoredGameFileName().isEmpty()){
            JOptionPane.showMessageDialog(this, "Nebyl vybrán soubor obsahující uloženou hru!", "Queen - Chyba při vytváření hry", JOptionPane.ERROR_MESSAGE);
            return;        
        }
        
        this.accepted = true;
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(DialogNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogNew dialog = new DialogNew(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttFileName;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox checkFileName;
    private javax.swing.JTextField inputFileName;
    private javax.swing.JComboBox inputPlayerColor;
    private javax.swing.JTextField inputRemoteHost;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton radioPlayerVsNetwork;
    private javax.swing.JRadioButton radioPlayerVsPC;
    private javax.swing.JRadioButton radioPlayerVsPlayer;
    // End of variables declaration//GEN-END:variables
}
