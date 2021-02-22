/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fifa;

import com.mysql.cj.jdbc.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author lenovo
 */
public class League_form extends javax.swing.JFrame {

    /**
     * Creates new form League_form
     */
    public League_form() {
        initComponents();
    }
   /* public void delete_match(){
        //trying to delete a row form the database but experiencing a problem with choosing the match after deleting the match
        //need to find a way to make someone not be able to select a game twice.
        //this option is not working.
        int j=jTable_matches.getSelectedRow()+1;
      String league=jTextField_leaguename.getText();
        PreparedStatement ps;
        ResultSet rs;
        String vs="vs";
        String  data=String.format("delete from  %s_matches where match_id=%s",league,j);
        JOptionPane.showMessageDialog(null,data);
        String registerUserQuery=data;
        try {
            ps=My_Cnx.getConnection().prepareStatement(registerUserQuery);
             if(ps.executeUpdate()!=0){
                JOptionPane.showMessageDialog(null,"deleted successfully");
            }
        } catch (SQLException ex) {
            Logger.getLogger(League_form.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    */
    public void match_played(){
      int i= jTable_matches.getSelectedRow();
       TableModel model=jTable_matches.getModel();
       String team1=model.getValueAt(i,0).toString();
       String team2=model.getValueAt(i,2).toString();
       String q1="Enter score for "+team1;
       String q2="Enter score for "+team2;
       int score1=Integer.parseInt(JOptionPane.showInputDialog(null,q1));
      int score2= Integer.parseInt(JOptionPane.showInputDialog(null,q2));
      String league=jTextField_leaguename.getText();
        /*DefaultTableModel model2=(DefaultTableModel)jTable_playedmatches.getModel();
               String vs="vs";
          model2.addRow(new Object[]{team1,vs,team2,score1,vs,score2});   
          */
        PreparedStatement ps;
        ResultSet rs;
        String vs="vs";
        String  data=String.format("insert into %s_matchesplayed (team1,vs,team2,score1,score2) values(?,?,?,?,?)",league);
        String registerUserQuery=data;
        try {
            ps=My_Cnx.getConnection().prepareStatement(registerUserQuery);
            ps.setString(1,team1);
            ps.setString(2,vs);
            ps.setString(3,team2);
            ps.setInt(4,score1);
            ps.setInt(5, score2);
            if(ps.executeUpdate()!=0){
                JOptionPane.showMessageDialog(null,"updated successfully");
            }
        } catch (SQLException ex) {
            Logger.getLogger(League_form.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dataSet=String.format("select* from %s_matchesplayed",league);
        String query=dataSet;
        try {
            ps=My_Cnx.getConnection().prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next()){
                String team_1=rs.getString("team1");
                String v_s=rs.getString("vs");
                String team_2=rs.getString("team2");
                String score_1=String.valueOf(rs.getInt("score1"));
                String score_2=String.valueOf(rs.getInt("score2"));
                String tbData[]={team_1,v_s,team_2,score_1,score_2};
                DefaultTableModel tbmodel=(DefaultTableModel)jTable_playedmatches.getModel();
               int j=tbmodel.getRowCount();
                if(j!=0){
                    for(int k=j;k<=0;k--){
                        tbmodel.removeRow(k);
                    }
                }
                tbmodel.addRow(tbData);
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(League_form.class.getName()).log(Level.SEVERE, null, ex);
        }
        //delete_match();
        
        String dataSet2=String.format("select* from %s_matches",league);
        String query2=dataSet2;
        try {
            ps=My_Cnx.getConnection().prepareStatement(query2);
            rs=ps.executeQuery();
            while(rs.next()){
                String team_1=rs.getString("team1");
                String v_s=rs.getString("vs");
                String team_2=rs.getString("team2");
                String score_1=String.valueOf(rs.getInt("score1"));
                String score_2=String.valueOf(rs.getInt("score2"));
                String tbData[]={team_1,v_s,team_2,score_1,score_2};
                DefaultTableModel tbmodel=(DefaultTableModel)jTable_matches.getModel();
               int j=tbmodel.getRowCount();
                if(j!=0){
                    for(int k=j;k<=0;k--){
                        tbmodel.removeRow(k);
                    }
                }
                tbmodel.addRow(tbData);
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(League_form.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void displayLeagueTable(){
         DatabaseMetaData dbm;
         ResultSet rs;
         String league=jTextField_leaguename.getText();
        try {
            dbm = (DatabaseMetaData) My_Cnx.getConnection().getMetaData();
            rs=dbm.getTables(null, null,league,null);
            if(rs.next()){
                //table exists
                //JOptionPane.showMessageDialog(null,"Table exists");
                PreparedStatement ps;
               
                String data=String.format("Select* from %s ",league);
                String query=data;
                ps=My_Cnx.getConnection().prepareStatement(query);
               rs=ps.executeQuery();
               while(rs.next()){
                   //data added until finish
                   String pos=String.valueOf(rs.getInt("pos"));
                   String team=rs.getString("team");
                   String GP=String.valueOf(rs.getInt("GP"));
                   String W=String.valueOf(rs.getInt("W"));
                   String D=String.valueOf(rs.getInt("D"));
                   String L=String.valueOf(rs.getInt("L"));
                   String GF=String.valueOf(rs.getInt("GF"));
                   String GA=String.valueOf(rs.getInt("GA"));
                   String GD=String.valueOf(rs.getInt("GD"));
                   String POINTS=String.valueOf(rs.getInt("POINTS"));
                   //String array to store data in jtable
                   String tbData[]={pos,team,GP,W,D,L,GF,GA,GD,POINTS};
                   DefaultTableModel tbmodel=(DefaultTableModel)jTable_leaguetable.getModel();
                   tbmodel.addRow(tbData);
               }
                
            }
            else{
                //table doesnt exist
                JOptionPane.showMessageDialog(null,"league doesn't exist. create new league");
            }
        } catch (SQLException ex) {
            Logger.getLogger(League_form.class.getName()).log(Level.SEVERE, null, ex);
        }

    }       
    public void displayLeagueMatches(){
       DatabaseMetaData dbm;
         ResultSet rs;
         String league=jTextField_leaguename.getText();
        try {
            dbm = (DatabaseMetaData) My_Cnx.getConnection().getMetaData();
            rs=dbm.getTables(null, null,league,null);
            if(rs.next()){
                //table exists
                //JOptionPane.showMessageDialog(null,"Table exists");
                PreparedStatement ps;
               
                String data=String.format("Select* from %s_matches",league);
                String query=data;
                ps=My_Cnx.getConnection().prepareStatement(query);
               rs=ps.executeQuery();
               while(rs.next()){
                   //data added until finish
                   String match_id=String.valueOf(rs.getInt("match_id"));
                   String team1=rs.getString("team1");
                   String vs=rs.getString("vs");
                   String team2=rs.getString("team2");
                   //String array to store data in jtable
                   String tbData[]={team1,vs,team2};
                   DefaultTableModel tbmodel=(DefaultTableModel)jTable_matches.getModel();
                   tbmodel.addRow(tbData);
               }
            }
                
        
        } catch (SQLException ex) {
            Logger.getLogger(League_form.class.getName()).log(Level.SEVERE, null, ex);
        }

    }         
    
    public void displayMatchesPlayed(){
        String league=jTextField_leaguename.getText();
        ResultSet rs;
        PreparedStatement ps;
         String dataSet=String.format("select* from %s_matchesplayed",league);
        String query=dataSet;
        try {
            ps=My_Cnx.getConnection().prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next()){
                String team_1=rs.getString("team1");
                String v_s=rs.getString("vs");
                String team_2=rs.getString("team2");
                String score_1=String.valueOf(rs.getInt("score1"));
                String score_2=String.valueOf(rs.getInt("score2"));
                String tbData[]={team_1,v_s,team_2,score_1,score_2};
                DefaultTableModel tbmodel=(DefaultTableModel)jTable_playedmatches.getModel();
               int j=tbmodel.getRowCount();
                if(j!=0){
                    for(int k=j;k<=0;k--){
                        tbmodel.removeRow(k);
                    }
                }
                tbmodel.addRow(tbData);
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(League_form.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel1 = new javax.swing.JLabel();
        jTextField_leaguename = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_matches = new javax.swing.JTable();
        jButton_newleague = new javax.swing.JButton();
        jButton_playmatch = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_playedmatches = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_leaguetable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jButton_displayleague = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("LEAGUE COMPETITION");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("league name:");

        jTable_matches.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "team1", "vs", "team2"
            }
        ));
        jScrollPane1.setViewportView(jTable_matches);

        jButton_newleague.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_newleague.setText("create new league");
        jButton_newleague.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_newleagueActionPerformed(evt);
            }
        });

        jButton_playmatch.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton_playmatch.setText("play selected match");
        jButton_playmatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_playmatchActionPerformed(evt);
            }
        });

        jTable_playedmatches.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "team1", "vs", "team2", "score1", "score2"
            }
        ));
        jScrollPane2.setViewportView(jTable_playedmatches);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("PLAYED MATCHES");

        jTable_leaguetable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "pos", "team", "GP", "W", "D", "L", "GF", "GA", "GD", "points"
            }
        ));
        jScrollPane3.setViewportView(jTable_leaguetable);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("LEAGUE TABLE");

        jButton_displayleague.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_displayleague.setText("display league");
        jButton_displayleague.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_displayleagueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(317, 317, 317)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(jLabel2)
                        .addGap(37, 37, 37)
                        .addComponent(jTextField_leaguename, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jButton_newleague)
                        .addGap(57, 57, 57)
                        .addComponent(jButton_displayleague, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(131, 131, 131)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(250, 250, 250)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jButton_playmatch, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)))
                .addGap(113, 113, 113))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_leaguename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_newleague)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jButton_displayleague))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_playmatch)
                .addContainerGap(102, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_newleagueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_newleagueActionPerformed
     League_Table table=new League_Table();
     table.Create_table();
    }//GEN-LAST:event_jButton_newleagueActionPerformed

    private void jButton_playmatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_playmatchActionPerformed
            match_played();
    }//GEN-LAST:event_jButton_playmatchActionPerformed

    private void jButton_displayleagueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_displayleagueActionPerformed
  
       DatabaseMetaData dbm;
         ResultSet rs;
         String league=jTextField_leaguename.getText();
        
        try {
            dbm = (DatabaseMetaData) My_Cnx.getConnection().getMetaData();
            rs=dbm.getTables(null, null,league,null);
            if(rs.next()){
        displayLeagueTable();
     displayLeagueMatches();  
     displayMatchesPlayed();
        }
            else{
                JOptionPane.showMessageDialog(null,"Create new league");
            }
                } catch (SQLException ex) {
            Logger.getLogger(League_form.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
    }//GEN-LAST:event_jButton_displayleagueActionPerformed

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
            java.util.logging.Logger.getLogger(League_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(League_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(League_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(League_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new League_form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_displayleague;
    private javax.swing.JButton jButton_newleague;
    private javax.swing.JButton jButton_playmatch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable_leaguetable;
    private javax.swing.JTable jTable_matches;
    private javax.swing.JTable jTable_playedmatches;
    private javax.swing.JTextField jTextField_leaguename;
    // End of variables declaration//GEN-END:variables
}
