/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.forms.employee;

import controller.Controller;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.MealOffer;
import view.forms.OrderDetailsForm;

/**
 *
 * @author Bane
 */
public class MealOfferEmployeePanel extends javax.swing.JPanel {

    /**
     * Creates new form MealOfferEmployeePanel
     */
    private List<MealOffer> mealOffers;

    public MealOfferEmployeePanel() {
        initComponents();
        try {
//            fillTable();
        } catch (Exception ex) {
            Logger.getLogger(MealOfferEmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Greška prilikom učitavanja ponuda jela");
        }
    }

    public void fillTable() throws Exception {
        mealOffers = Controller.getInstance().getMealOffers();
        DefaultTableModel dtm = (DefaultTableModel) this.tableMealOffers.getModel();
        int rows = dtm.getRowCount();
        if (rows >= 0) {
            for (int i = rows - 1; i >= 0; i--) {
                dtm.removeRow(i);
            }
        }

        int i = 1;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        for (MealOffer mo : this.mealOffers) {
            String date = sdf.format(mo.getDate());
            dtm.addRow(new Object[]{i, date, mo.getAccount().getUsername()});
            i++;
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tableMealOffers = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnOrderMeals = new javax.swing.JButton();
        btnShowOrders = new javax.swing.JButton();

        tableMealOffers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RB", "Datum", "Kreirao"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableMealOffers);

        jLabel1.setText("Ponude jela");

        btnOrderMeals.setText("Poruči");
        btnOrderMeals.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderMealsActionPerformed(evt);
            }
        });

        btnShowOrders.setText("Pregledaj porudžbinu");
        btnShowOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowOrdersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnOrderMeals, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnShowOrders, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(313, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnOrderMeals)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnShowOrders)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnOrderMealsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderMealsActionPerformed
        int row = this.tableMealOffers.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Izaberite ponudu iz tabele za koju želite da poručite jela");
            return;
        }

        MealOffer mo = this.mealOffers.get(row);
        try {
            mo = Controller.getInstance().getMealOffer(mo.getId());
            new OrderMealsForm(null, true, mo);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Greska prilikom učitavanja jela iz ponude");
            Logger.getLogger(MealOfferEmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

    }//GEN-LAST:event_btnOrderMealsActionPerformed

    private void btnShowOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowOrdersActionPerformed
        int row = this.tableMealOffers.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Izaberite ponudu iz tabele za koju želite da prikažete porudžbinu");
            return;
        }
        MealOffer mo = this.mealOffers.get(row);
        try {
            new OrderDetailsForm(null, true, mo);
        } catch (Exception ex) {
            Logger.getLogger(MealOfferEmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Greška prilikom učitavanja porudžbine");
        }
    }//GEN-LAST:event_btnShowOrdersActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOrderMeals;
    private javax.swing.JButton btnShowOrders;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableMealOffers;
    // End of variables declaration//GEN-END:variables
}
