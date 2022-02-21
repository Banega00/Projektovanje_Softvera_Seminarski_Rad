/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.forms.employee;

import communication.Communication;
import communication.Operation;
import communication.Request;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Bane
 */
public class EmployeeMainForm extends javax.swing.JFrame {

    /**
     * Creates new form EmployeeMainForm
     */
    public EmployeeMainForm() {
        initComponents();
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                listenForRequests();
//            }
//
//        }).start();
    }

    public void listenForRequests() {
        while(!Communication.getInstance().getSocket().isClosed()){
                    try {
                        Request request = (Request) Communication.getInstance().getIn().readObject();
                        System.out.println(request.getOperation());
                        if(request.getOperation() == Operation.DISCONNECT){
                            JOptionPane.showMessageDialog(null, "Veza sa serverom je prekinuta");
                            this.dispose();
                            return;
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(EmployeeMainForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(EmployeeMainForm.class.getName()).log(Level.SEVERE, null, ex);
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

        employeePanel1 = new view.forms.employee.EmployeePanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistem za naručivanje jela");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(employeePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 962, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(employeePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.forms.employee.EmployeePanel employeePanel1;
    // End of variables declaration//GEN-END:variables
}
