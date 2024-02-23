/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author HP 250
 */
public class frmUpdate extends javax.swing.JFrame {

    /**
     * Creates new form frmUpdate
     */
    public frmUpdate() {
        initComponents();
        mLoadFirstName();
    }
    
    String strFirstName;
    String strLastName;
    int intActorID;
    
    private void mClearVariables()
    {
      strFirstName="";
      strLastName="";
      intActorID=0;
    } 
    private void mLoadFirstName()
    {
        String strDBConnectionString = "jdbc:mysql://localhost:3306/sakila";
        String strDBUser="root";
        String strDBPassword="Password";
        java.sql.Connection conMySQLConnectionString;
        Statement stStatement=null;
        ResultSet rs=null;
        try
        {
            conMySQLConnectionString = DriverManager.getConnection(strDBConnectionString,strDBUser,strDBPassword);
            stStatement = conMySQLConnectionString.createStatement();
            String strQuery ="Select first_name from Actor";
            stStatement.execute(strQuery);
            rs=stStatement.getResultSet();
        while(rs.next()) 
        {
            cboFirstName.addItem(rs.getString(1));
        }    
        } 
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        } 
        finally
        {
            try
            {
                stStatement.close();
            } 
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null,"Connection String not closed"+" "+ e);
            }    
        }    
    } 
    private void mReadActorDetails()
    {
        String strDBConnectionString = "jdbc:mysql://localhost:3306/sakila";
        String strDBUser="root";
        String strDBPassword="Password";
        java.sql.Connection conMySQLConnectionString;
        Statement stStatement=null;
        ResultSet rs=null;
        try
        {
            conMySQLConnectionString = DriverManager.getConnection(strDBConnectionString,strDBUser,strDBPassword);
            stStatement = conMySQLConnectionString.createStatement();
            String strQuery = "Select actor_id,first_name,last_name from Actor where first_name='"+cboFirstName.getSelectedItem().toString()+"'";
            stStatement.execute(strQuery);
            rs=stStatement.getResultSet();
        while(rs.next()) 
        {  
            intActorID=rs.getInt(1);
            strFirstName=rs.getString(2);
            strLastName=rs.getString(3);
        } 
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }        
        finally
        {
            try
            {
                stStatement.close();
            }  
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null,"Connection string not closed" + " "+ e);
            }    
        }    
    }
    private void mUpdateActor()
    {
        String strDBConnectionString = "jdbc:mysql://localhost:3306/sakila";
        String strDBUser="root";
        String strDBPassword="Password";
        java.sql.Connection conMySQLConnectionString;
        Statement stStatement=null;
        ResultSet rs=null;
        try
        {
            conMySQLConnectionString = DriverManager.getConnection(strDBConnectionString,strDBUser,strDBPassword);
            stStatement = conMySQLConnectionString.createStatement();
            String strQuery = "Update actor SET first_name ='"+strFirstName+"',last_name='"+strLastName+"'WHERE actor_id="+intActorID;
            stStatement.executeUpdate(strQuery);
            rs=stStatement.getResultSet();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        } 
        finally
        {
        try
        {
            stStatement.close();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"Connection string not closed" + " "+ e);
        }    
        }    
    }    
    private void mGetValuesFromGUI()
    {
        strFirstName=txtFirstName.getText();
        strLastName=txtLastName.getText();
    } 
    private void mSetValuesToUpperCase()
    {
        strFirstName=strFirstName.toUpperCase();
        strLastName=strLastName.toUpperCase();
    }        
    private void mSetValuesInGUI()
    {
        txtFirstName.setText(strFirstName);
        txtLastName.setText(strLastName);
    }      
    private void mClearTextFields()
    {
        txtFirstName.setText("");
        txtLastName.setText("");
    }        
    private void mClearComboBox()
    {
        String[] arrArray = new String[0];
        javax.swing.DefaultComboBoxModel model = new javax.swing.DefaultComboBoxModel(arrArray);
        cboFirstName.setModel(model);
    }        

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cboFirstName = new javax.swing.JComboBox();
        lblFirstName = new javax.swing.JLabel();
        lblLastName = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnReturn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cboFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFirstNameActionPerformed(evt);
            }
        });

        lblFirstName.setText("First Name");

        lblLastName.setText("Last Name");

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnReturn.setText("Return");
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cboFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblFirstName)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtFirstName))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblLastName)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtLastName)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnReturn)))
                .addContainerGap(258, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFirstName)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLastName)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnReturn))
                .addContainerGap(158, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        // TODO add your handling code here:
        frmMain frmM = new frmMain();
        frmM.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnReturnActionPerformed

    private void cboFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFirstNameActionPerformed
        // TODO add your handling code here:
        mReadActorDetails();
        mSetValuesInGUI();
    }//GEN-LAST:event_cboFirstNameActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        mGetValuesFromGUI();
        mSetValuesToUpperCase();
        mUpdateActor();
        mClearComboBox();
        mClearTextFields();
        mClearVariables();
        mLoadFirstName();
    }//GEN-LAST:event_btnSaveActionPerformed

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
            java.util.logging.Logger.getLogger(frmUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmUpdate().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReturn;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cboFirstName;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblLastName;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    // End of variables declaration//GEN-END:variables
}
