/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.forms.admin;

import javax.swing.JFrame;

/**
 *
 * @author Bane
 */
public class AdminPanel extends javax.swing.JPanel {

    /**
     * Creates new form AdminPanel
     */
    public AdminPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        mealOfferPanel2 = new view.forms.admin.MealOfferPanel();
        employeesPanel2 = new view.forms.admin.EmployeesPanel();

        setPreferredSize(new java.awt.Dimension(1000, 640));

        jTabbedPane1.addTab("Ponude jela", mealOfferPanel2);
        jTabbedPane1.addTab("Zaposleni", employeesPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1015, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.forms.admin.EmployeesPanel employeesPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private view.forms.admin.MealOfferPanel mealOfferPanel2;
    // End of variables declaration//GEN-END:variables
}
