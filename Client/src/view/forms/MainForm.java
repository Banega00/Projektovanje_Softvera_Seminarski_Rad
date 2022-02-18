/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.forms;

import controller.Controller;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Bane
 */
public class MainForm extends javax.swing.JFrame {

    public MainForm() {
        
        this.setLocationRelativeTo(null);
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.adminPanel.setVisible(false);
        this.employeePanel.setVisible(false);
        if(Controller.getInstance().getUserAccount().isIsAdmin()){
            this.adminPanel.setVisible(true);
            this.setTitle(this.getTitle() + " - Administrator");
        }else{
            this.employeePanel.setVisible(true);
        }
        
        setResizable(false);
        this.setVisible(true);
        this.pack();
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        employeePanel = new view.forms.employee.EmployeePanel();
        adminPanel = new view.forms.admin.AdminPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 640));

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("Sistem za naručivanje jela"), this, org.jdesktop.beansbinding.BeanProperty.create("title"));
        bindingGroup.addBinding(binding);

        employeePanel.setPreferredSize(new java.awt.Dimension(840, 520));
        getContentPane().add(employeePanel, java.awt.BorderLayout.CENTER);

        adminPanel.setPreferredSize(new java.awt.Dimension(840, 520));
        getContentPane().add(adminPanel, java.awt.BorderLayout.PAGE_START);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.forms.admin.AdminPanel adminPanel;
    private view.forms.employee.EmployeePanel employeePanel;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
