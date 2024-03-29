/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author tuxik
 */
public class Connect_dialog extends javax.swing.JDialog {

    /**
     * Creates new form Connect_dialog
     */
    public Connect_dialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        buttonGroup1.add(rad_white);
        buttonGroup1.add(rad_black);
    }

    
    public connect_struct showDialog() 
    {
        setVisible(true);
        connect_struct struct = new connect_struct();
        
        struct.file_path = txt_file.getText();
        try
        {
            struct.file_ext = struct.file_path.substring(struct.file_path.length()-3,struct.file_path.length());
        }
        catch(Exception ex)
        {
            struct.file_ext = "";
        }
        struct.port = Integer.parseInt(txt_port.getText());
        struct.manual_text = txt_manual.getText();
        
        if(rad_white.isSelected())
            struct.host_color = 1;
        else if(rad_white.isSelected())
            struct.host_color = 2;
        return struct;
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_IP = new javax.swing.JTextField();
        txt_port = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_file = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_manual = new javax.swing.JTextArea();
        btn_change = new javax.swing.JButton();
        btn_manual = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        rad_white = new javax.swing.JRadioButton();
        rad_black = new javax.swing.JRadioButton();
        btn_connect = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Zadejte IP:");

        jLabel2.setText("Zadejte port:");

        txt_IP.setText("127.0.0.1");

        txt_port.setText("6666");

        jLabel3.setText("Vyberte hru pro načtení, nebo zanechte prázdné:");

        txt_file.setEnabled(false);

        jLabel4.setText("Zadejte partii pro načtení ručně, nebo zanechte prázdé:");

        txt_manual.setColumns(20);
        txt_manual.setRows(5);
        jScrollPane1.setViewportView(txt_manual);

        btn_change.setText("Vybrat");
        btn_change.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_changeActionPerformed(evt);
            }
        });

        btn_manual.setText("Zadat");
        btn_manual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_manualActionPerformed(evt);
            }
        });

        jLabel5.setText("Chcete být hráč:");

        rad_white.setSelected(true);
        rad_white.setText("Bílý");

        rad_black.setText("Černý");

        btn_connect.setText("Připojit");
        btn_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_connectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_manual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(rad_white)
                                    .addComponent(rad_black))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(btn_connect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(txt_IP, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txt_port, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txt_file, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_change, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_IP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_file, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_change))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_manual)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rad_white)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rad_black)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(btn_connect))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_manualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_manualActionPerformed
        txt_file.setText("");
        txt_manual.setText("");
        txt_manual.setEnabled(true);
    }//GEN-LAST:event_btn_manualActionPerformed

    private void btn_changeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_changeActionPerformed
        JFileChooser c = new JFileChooser();
     
        c.setAcceptAllFileFilterUsed(false);
        
        c.addChoosableFileFilter(new FileNameExtensionFilter("txt","txt"));  
        c.addChoosableFileFilter(new FileNameExtensionFilter("xml","xml"));  
        
        int rVal = c.showOpenDialog(this);
        JTextField filename = new JTextField(), dir = new JTextField();        
                
        if (rVal == JFileChooser.APPROVE_OPTION) 
        {
            filename.setText(c.getSelectedFile().getName());
            dir.setText(c.getCurrentDirectory().toString());
            String extension = c.getFileFilter().getDescription();

            String full_name = dir.getText() + "/" + filename.getText();
            
            txt_file.setText(full_name);
        
        }        
        txt_manual.setText("");
        txt_manual.setEnabled(false);
    }//GEN-LAST:event_btn_changeActionPerformed

    private void btn_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_connectActionPerformed
        setVisible(false);  
        dispose();
    }//GEN-LAST:event_btn_connectActionPerformed

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
            java.util.logging.Logger.getLogger(Connect_dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Connect_dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Connect_dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Connect_dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Connect_dialog dialog = new Connect_dialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btn_change;
    private javax.swing.JButton btn_connect;
    private javax.swing.JButton btn_manual;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rad_black;
    private javax.swing.JRadioButton rad_white;
    private javax.swing.JTextField txt_IP;
    private javax.swing.JTextField txt_file;
    private javax.swing.JTextArea txt_manual;
    private javax.swing.JTextField txt_port;
    // End of variables declaration//GEN-END:variables
}
