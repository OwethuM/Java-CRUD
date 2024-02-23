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
public class frmCRUD extends javax.swing.JFrame {

    /**
     * Creates new form frmCRUD
     */
    public frmCRUD() {
        initComponents();
        mLoadFirstName();
        mLoadGUIControls();
    }
    Boolean boolRecordExists=false;
    Boolean boolEdit=false;
    Boolean boolCreate=false;
    String strFirstName;
    String strLastName;
    int intActorID;
    
    private void mClearVaribles() 
    {
        strFirstName="";
        strLastName="";
        intActorID=0;
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
    private void mcheckIfItemsExistInTable()
    {
        String strDBConnectionString ="jdbc:mysql://localhost:3306/sakila";
        String strDBUser ="root";
        String strDBPassword="Password";
        java.sql.Connection conMySQLConnectionString;
        Statement stStatement=null;
        ResultSet rs=null;
        try
        {
            conMySQLConnectionString = DriverManager.getConnection(strDBConnectionString,strDBUser,strDBPassword);
            stStatement = conMySQLConnectionString.createStatement();
            String strQuery ="Select*from Actor where first_name='" +
                    strFirstName+"'and last_name='"+strLastName+"'";
            stStatement.execute(strQuery);
            rs=stStatement.getResultSet();
            boolRecordExists=rs.next();
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
     private void mCreateActor()
    {
        java.sql.Connection conMySQLConnectionString = null;
        String URL="jdbc:mysql://localhost:3306/sakila";
        String User="root";
        String Password="Password";
        try
        {
            conMySQLConnectionString = DriverManager.getConnection(URL,User,Password);
            Statement myStatement = conMySQLConnectionString.createStatement();
            String sqlinsert= "insert into Actor" + "(first_name,last_name)" + 
                    "values('"+strFirstName+"','"+strLastName +"')";
            myStatement.executeUpdate(sqlinsert);
            myStatement.close();
            JOptionPane.showMessageDialog(null,"Complete");
        } 
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }    
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
            String strQuery = "UPDATE actor SET first_name ='"+strFirstName+"',last_name='"+strLastName+"'WHERE actor_id="+intActorID;
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
    private void mClearComboBox()
    {
        String[] arrArray = new String[0];
        javax.swing.DefaultComboBoxModel model = new javax.swing.DefaultComboBoxModel(arrArray);
        cboFirstName.setModel(model);
    } 
     private void mDeleteActor()
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
            String strQuery = "Delete from actor where first_name ='"+strFirstName+"'and last_name ='"+strLastName+"'and actor_id="+ intActorID;
            stStatement = conMySQLConnectionString.prepareStatement(strQuery);
            stStatement.execute(strQuery);
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
            JOptionPane.showMessageDialog(null, "Connection String not closed"+" "+e);
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
      private void mLoadGUIControls()
      {
          txtFirstName.setEnabled(false);
          txtLastName.setEnabled(false);
          cboFirstName.setEnabled(true);
          btnCreate.setEnabled(true);
          btnLoad.setEnabled(true);
          btnEdit.setEnabled(true);
          btnSave.setEnabled(false);
          btnDelete.setEnabled(true);
          btnReturn.setEnabled(true);
          
      }
      private void mSaveGUIControls()
      {
          txtFirstName.setEnabled(false);
          txtLastName.setEnabled(false);
          cboFirstName.setEnabled(true);
          btnCreate.setEnabled(true);
          btnLoad.setEnabled(true);
          btnEdit.setEnabled(true);
          btnSave.setEnabled(false);
          btnDelete.setEnabled(false);
          btnReturn.setEnabled(true);
      } 
      private void mCreateGUIControls()
      {
          txtFirstName.setEnabled(true);
          txtLastName.setEnabled(true);
          cboFirstName.setEnabled(false);
          btnCreate.setEnabled(false);
          btnLoad.setEnabled(false);
          btnEdit.setEnabled(false);
          btnSave.setEnabled(true);
          btnDelete.setEnabled(true);
          btnReturn.setEnabled(false);
      }   
      private void mDeleteGUIControls()
      {
          txtFirstName.setEnabled(false);
          txtLastName.setEnabled(false);
          cboFirstName.setEnabled(true);
          btnCreate.setEnabled(true);
          btnLoad.setEnabled(true);
          btnEdit.setEnabled(true);
          btnSave.setEnabled(false);
          btnDelete.setEnabled(false);
          btnReturn.setEnabled(true);  
      } 
       private void mEditGUIControls()
      {
          txtFirstName.setEnabled(true);
          txtLastName.setEnabled(true);
          cboFirstName.setEnabled(false);
          btnCreate.setEnabled(false);
          btnLoad.setEnabled(false);
          btnEdit.setEnabled(false);
          btnSave.setEnabled(true);
          btnDelete.setEnabled(true);
          btnReturn.setEnabled(false);
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
        btnCreate = new javax.swing.JButton();
        btnLoad = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnReturn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblFirstName.setText("First Name");

        lblLastName.setText("Last Name");

        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnLoad.setText("Load");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFirstName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblLastName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtLastName))
                    .addComponent(btnCreate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLoad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReturn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(229, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLastName)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnCreate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLoad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReturn)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        // TODO add your handling code here:
        frmMain frmM = new frmMain();
        frmM.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnReturnActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // TODO add your handling code here:
        mCreateGUIControls();
        txtFirstName.requestFocusInWindow();
        btnDelete.setText("Cancel");
        boolCreate=true;
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        // TODO add your handling code here:
        mReadActorDetails();
        mSetValuesInGUI();
        mLoadGUIControls();
    }//GEN-LAST:event_btnLoadActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        mReadActorDetails();
        mSetValuesInGUI();
        mEditGUIControls();
        txtFirstName.requestFocusInWindow();
        btnDelete.setText("Cancel");
        boolEdit=true;
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if(boolCreate==true)
        {
            if(txtFirstName.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"The field cannot be left empty");
                txtFirstName.requestFocusInWindow();
            }
            else if (txtLastName.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"The field cannot be left empty");
                txtLastName.requestFocusInWindow();
            } 
            else
            {
                mGetValuesFromGUI();
                mSetValuesToUpperCase();
                mcheckIfItemsExistInTable();
                
                if(boolRecordExists==true)
                {
                    boolRecordExists=false;
                    JOptionPane.showMessageDialog(null,"Actor already Exists");
                }
                else if(boolRecordExists==false)
                {
                    boolCreate=false;
                    mCreateActor();
                    mClearTextFields();
                    mClearVaribles();
                    mClearComboBox();
                    mLoadGUIControls();
                }    
            }    
        }
        else if(boolEdit==true)
        {
            boolEdit=false;
            mGetValuesFromGUI();
            mSetValuesToUpperCase();
            mUpdateActor();
            mClearTextFields();
            mClearVaribles();
            mClearComboBox();
            mLoadFirstName();
            mLoadGUIControls();
        }    
        btnDelete.setText("Delete");
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        if("Delete".equals(btnDelete.getText()))
        {
            mReadActorDetails();
            mDeleteActor();
            mClearComboBox();
            mClearVaribles();
            mLoadFirstName();
            
        }  
        else if("Cancel".equals(btnDelete.getText()))
        {
            mClearTextFields();
            mClearVaribles();
            mClearComboBox();
            mLoadFirstName();
            mLoadGUIControls();
            btnDelete.setText("Delete");
        }    
    }//GEN-LAST:event_btnDeleteActionPerformed

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
            java.util.logging.Logger.getLogger(frmCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCRUD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnReturn;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cboFirstName;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblLastName;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    // End of variables declaration//GEN-END:variables
}
