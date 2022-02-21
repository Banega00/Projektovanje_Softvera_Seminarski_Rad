/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import exception.ValidationException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import main.ServerEnvVariables;
import persistence.db.DBConnectionFactory;
import validation.Validator;

/**
 *
 * @author Bane
 */
public class ServerConfigDialog extends javax.swing.JDialog {

    /**
     * Creates new form ServerConfigurationDialog
     */
    public ServerConfigDialog(java.awt.Frame parent, boolean modal) throws IOException {
        super(parent, modal);
        initComponents();

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(ServerEnvVariables.CONFIG_FILE_PATH));
            txtPort.setText(properties.getProperty(ServerEnvVariables.PORT));
            txtDbHost.setText(properties.getProperty(ServerEnvVariables.DB_HOST));
            txtDbPort.setText(properties.getProperty(ServerEnvVariables.DB_PORT));
            txtDbName.setText(properties.getProperty(ServerEnvVariables.DB_NAME));
            txtDbUserame.setText(properties.getProperty(ServerEnvVariables.DB_USERNAME));
            txtDbPassword.setText(properties.getProperty(ServerEnvVariables.DB_PASSWORD));
        } catch (IOException ex) {
            Logger.getLogger(ServerConfigDialog.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        
        setVisible(true);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtPort = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDbHost = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDbPort = new javax.swing.JTextField();
        txtDbName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDbUserame = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        txtDbPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Server configuration");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setText("Server configuration");

        txtPort.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtPort.setText("jTextField1");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel2.setText("Port");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setText("Database host");

        txtDbHost.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtDbHost.setText("jTextField1");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel4.setText("Database port");

        txtDbPort.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtDbPort.setText("jTextField1");

        txtDbName.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtDbName.setText("jTextField1");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel5.setText("Database name");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel6.setText("Database username");

        txtDbUserame.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtDbUserame.setText("jTextField1");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel7.setText("Database password");

        btnSave.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnSave.setText("Sačuvaj ");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        txtDbPassword.setText("jPasswordField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(jLabel1)
                .addGap(222, 245, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtDbPort, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtDbHost, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtPort, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4))
                        .addGap(115, 115, 115)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(txtDbPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(txtDbUserame)
                            .addComponent(txtDbName))
                        .addGap(83, 83, 83))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(252, 252, 252))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(1, 1, 1)
                        .addComponent(txtDbUserame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDbPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(1, 1, 1)
                        .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addGap(1, 1, 1)
                        .addComponent(txtDbHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDbPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDbName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(66, 66, 66)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            String port = txtPort.getText();
            String dbHost = txtDbHost.getText();
            String dbPort = txtDbPort.getText();
            String dbUsername = txtDbUserame.getText();;
            String dbPassword = txtDbPassword.getText();
            String dbName = txtDbName.getText();
            boolean signal = true;
            try {
                Validator.startValidation()
                        .validatePortNumber(port, "Port mora biti u intervalu 0-65535")
                        .throwIfInvalide();
            } catch (ValidationException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
                signal = false;
            }
            try {
                DBConnectionFactory.getInstance().testConnection(dbHost, dbPort, dbName, dbUsername, dbPassword);
            } catch (Exception ex) {
                signal = false;
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Greška kod povezivanja sa bazom", JOptionPane.ERROR_MESSAGE);
            }
            if (signal) {
                Properties props = new Properties();
                props.setProperty(ServerEnvVariables.PORT, port);
                props.setProperty(ServerEnvVariables.DB_HOST, dbHost);
                props.setProperty(ServerEnvVariables.DB_PORT, dbPort);
                props.setProperty(ServerEnvVariables.DB_NAME, dbName);
                props.setProperty(ServerEnvVariables.DB_USERNAME, dbUsername);
                props.setProperty(ServerEnvVariables.DB_PASSWORD, dbPassword);
                FileOutputStream out = new FileOutputStream(ServerEnvVariables.CONFIG_FILE_PATH);
                props.store(out, "");
                out.close();
            }
            this.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Greška prilikom čuvanja");
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtDbHost;
    private javax.swing.JTextField txtDbName;
    private javax.swing.JPasswordField txtDbPassword;
    private javax.swing.JTextField txtDbPort;
    private javax.swing.JTextField txtDbUserame;
    private javax.swing.JTextField txtPort;
    // End of variables declaration//GEN-END:variables
}
