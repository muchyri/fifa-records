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
public class League_testGui extends javax.swing.JFrame {
int score1;
int score2;
String team1;
String team2;
    public League_testGui() {
        initComponents();
    }
   /* public void updateLeagueTable(){
        League_calculations calc=new League_calculations(score1,score2);
        League_calculations calc2=new League_calculations(score2,score1);
         int i=jTable_matches.getSelectedRow();
        TableModel model=jTable_matches.getModel();
        String team1=model.getValueAt(i,0).toString();
       String team2=model.getValueAt(i,2).toString();
       int gf1=calc.GF();
       int ga1=calc.GA();
       int gd1=calc.GD();
       int point1=calc.points();
       int gf2=calc2.GF();
       int ga2=calc2.GA();
       int gd2=calc2.GD();
       int point2=calc2.points();
      int mp=0+1;
       PreparedStatement ps;
       ResultSet rs;
       String league=jTextField_leaguename.getText();
        String data=String.format("update %s set (mp,W,D,L,GF,GA,GD,POINTS) values(?,?,?,?,?,?,?,?) ",league);
                String query=data;
    try {
        ps=My_Cnx.getConnection().prepareStatement(query);
        ps.setInt(1,mp);
       // ps.setInt();
    } catch (SQLException ex) {
        Logger.getLogger(League_testGui.class.getName()).log(Level.SEVERE, null, ex);
    }
               
        
    }
    */
    public void matchesPlayed(){
        int status;
        PreparedStatement ps;
        ResultSet rs;
        int match_id ;
         int i=jTable_matches.getSelectedRow();
        String league=jTextField_leaguename.getText();
        int matchd=i+1;
        String query1=String.format("select* from %s_matches where match_id=%s", league,matchd);
        String query2=query1;
    try {
        ps=My_Cnx.getConnection().prepareStatement(query2);
        rs=ps.executeQuery();
        while(rs.next()){
        match_id=rs.getInt("match_id");
        status=rs.getInt("status");
        
         if(status==0){
       
        TableModel model=jTable_matches.getModel();
         team1=model.getValueAt(i,0).toString();
        team2=model.getValueAt(i,2).toString();
       String q1="Enter score for "+team1;
       String q2="Enter score for "+team2;
       if(q1.isEmpty()){
           throw new Exception("scores cannot be blank");
       }else{
      System.out.println("yaas");
        score1=Integer.parseInt(JOptionPane.showInputDialog(null,q1));
        score2= Integer.parseInt(JOptionPane.showInputDialog(null,q2));       
       }

       
        
         
        String vs="vs";
        status=1;
        String query3=String.format("update %s_matches set status=? where match_id=%s", league,match_id);
        String query4=query3;
        try{
        ps=My_Cnx.getConnection().prepareStatement(query4);
        ps.setInt(1,status);
        if(ps.executeUpdate()!=0){
            System.out.println("status updated successfully");
            // JOptionPane.showMessageDialog(null,"updated successfully");
        }
        }catch(SQLException ex){
            Logger.getLogger(League_form.class.getName()).log(Level.SEVERE, null, ex); 
        }
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
                System.out.println("matches played updated successfully");
               // JOptionPane.showMessageDialog(null,"updated successfully");
            }
        } catch (SQLException ex) {
            Logger.getLogger(League_form.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dataSet=String.format("select* from %s_matchesplayed",league);
        String query=dataSet;
        try {
            ps=My_Cnx.getConnection().prepareStatement(query);
            rs=ps.executeQuery();
             DefaultTableModel tbmodel=(DefaultTableModel)jTable_matchesplayed.getModel();
               int j=tbmodel.getRowCount();
               System.out.println("number of rows= "+j);
                if(j!=0){
                    for(int k=j-1;k>=0;k--){
                        tbmodel.removeRow(k);
                       // tbmodel.fireTableRowsDeleted(0,j);
                    }
                  System.out.println("number of rows= "+j);  
                }
            while(rs.next()){
                String team_1=rs.getString("team1");
                String v_s=rs.getString("vs");
                String team_2=rs.getString("team2");
                String score_1=String.valueOf(rs.getInt("score1"));
                String score_2=String.valueOf(rs.getInt("score2"));
                String tbData[]={team_1,v_s,team_2,score_1,score_2};
              //  DefaultTableModel tbmodel=(DefaultTableModel)jTable_matchesplayed.getModel();
               //int j=tbmodel.getRowCount();
             //  System.out.println("number of rows= "+j);
               // if(j!=0){
                  //  for(int k=j;k<=0;k--){
                   //     tbmodel.removeRow(k);
                   // }
               // }
                tbmodel.addRow(tbData);
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(League_form.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else{
                JOptionPane.showMessageDialog(null,"game has already been played");
                }
        }
    } catch (SQLException ex) {
        Logger.getLogger(League_testGui.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
        Logger.getLogger(League_testGui.class.getName()).log(Level.SEVERE, null, ex);
    }
    //catch (Exception e){
      //  System.out.println(e);
        //JOptionPane.showMessageDialog(null, e.getMessage());
    //}
    
       // Create_matches match=new Create_matches();
       // int status=match.getStatus();
       
    }
    public void displayMatchesPlayed(){
        PreparedStatement ps;
        ResultSet rs;
        String league=jTextField_leaguename.getText();
        String dataSet=String.format("select* from %s_matchesplayed",league);
        String query=dataSet;
        try {
            ps=My_Cnx.getConnection().prepareStatement(query);
            rs=ps.executeQuery();
             DefaultTableModel tbmodel=(DefaultTableModel)jTable_matchesplayed.getModel();
               int j=tbmodel.getRowCount();
               System.out.println("number of rows= "+j);
                if(j!=0){
                    for(int k=j-1;k>=0;k--){
                        tbmodel.removeRow(k);
                       // tbmodel.fireTableRowsDeleted(0,j);
                    }
                  System.out.println("number of rows= "+j);  
                }
            while(rs.next()){
                String team_1=rs.getString("team1");
                String v_s=rs.getString("vs");
                String team_2=rs.getString("team2");
                String score_1=String.valueOf(rs.getInt("score1"));
                String score_2=String.valueOf(rs.getInt("score2"));
                String tbData[]={team_1,v_s,team_2,score_1,score_2};
              //  DefaultTableModel tbmodel=(DefaultTableModel)jTable_matchesplayed.getModel();
               //int j=tbmodel.getRowCount();
             //  System.out.println("number of rows= "+j);
               // if(j!=0){
                  //  for(int k=j;k<=0;k--){
                   //     tbmodel.removeRow(k);
                   // }
               // }
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
               
                String data=String.format("Select* from %s order by points desc, GD desc, GF desc,GA asc",league);
                String query=data;
                ps=My_Cnx.getConnection().prepareStatement(query);
               rs=ps.executeQuery();
                DefaultTableModel tbmodel=(DefaultTableModel)jTable_leaguetable.getModel();
               int j=tbmodel.getRowCount();
                  
               System.out.println("number of rows= "+j);
                if(j!=0){
                    for(int k=j-1;k>=0;k--){
                        tbmodel.removeRow(k);
                       // tbmodel.fireTableRowsDeleted(0,j);
                    }
                  System.out.println("number of rows= "+j);  
                }
                int num=1;
               while(rs.next()){
                   //data added until finish
                   
                   String pos=String.valueOf(num);
                   String team=rs.getString("team");
                   String GP=String.valueOf(rs.getInt("MP"));
                   String W=String.valueOf(rs.getInt("W"));
                   String D=String.valueOf(rs.getInt("D"));
                   String L=String.valueOf(rs.getInt("L"));
                   String GF=String.valueOf(rs.getInt("GF"));
                   String GA=String.valueOf(rs.getInt("GA"));
                   String GD=String.valueOf(rs.getInt("GD"));
                   String POINTS=String.valueOf(rs.getInt("POINTS"));
                   //String array to store data in jtable
                   String tbData[]={pos,team,GP,W,D,L,GF,GA,GD,POINTS};
                   num++;
                  // DefaultTableModel tbmodel=(DefaultTableModel)jTable_leaguetable.getModel();
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
    /*public void update_leagueTable(){
         DatabaseMetaData dbm;
         ResultSet rs;
         PreparedStatement ps;
         String league=jTextField_leaguename.getText();
         String pos;
         String team;
         String GP;
         String W;
         String D;
         String L;
         String GF;
         String GA;
         String GD;
         String POINTS;
        try {
            dbm = (DatabaseMetaData) My_Cnx.getConnection().getMetaData();
            rs=dbm.getTables(null, null,league,null);
            if(rs.next()){   
                String data=String.format("Select* from %s ",league);
                String query=data;
                ps=My_Cnx.getConnection().prepareStatement(query);
               rs=ps.executeQuery();
               while(rs.next()){
                   //data added until finish
                    pos=String.valueOf(rs.getInt("pos"));
                    team=rs.getString("team");
                    GP=String.valueOf(rs.getInt("GP"));
                    W=String.valueOf(rs.getInt("W"));
                    D=String.valueOf(rs.getInt("D"));
                    L=String.valueOf(rs.getInt("L"));
                    GF=String.valueOf(rs.getInt("GF"));
                    GA=String.valueOf(rs.getInt("GA"));
                    GD=String.valueOf(rs.getInt("GD"));
                    POINTS=String.valueOf(rs.getInt("POINTS"));
                   //String array to store data in jtable
                  // String tbData[]={pos,team,GP,W,D,L,GF,GA,GD,POINTS};
                  // DefaultTableModel tbmodel=(DefaultTableModel)jTable_leaguetable.getModel();
                  // tbmodel.addRow(tbData);
               }
                
            }
            else{
                //table doesnt exist
                JOptionPane.showMessageDialog(null,"league doesn't exist. create new league");
            }
        } catch (SQLException ex) {
            Logger.getLogger(League_form.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }*/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_matches = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_matchesplayed = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_leaguetable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField_leaguename = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton_newleague = new javax.swing.JButton();
        jButton_displayleague = new javax.swing.JButton();
        jButton_playselectedmatch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable_matches.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TEAM1", "VS", "TEAM2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable_matches);

        jTable_matchesplayed.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TEAM1", "VS", "TEAM2", "SCORE1", "SCORE2"
            }
        ));
        jScrollPane2.setViewportView(jTable_matchesplayed);

        jTable_leaguetable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "POS", "TEAM", "GP", "W", "D", "L", "GF", "GA", "GD", "POINTS"
            }
        ));
        jScrollPane3.setViewportView(jTable_leaguetable);

        jLabel1.setBackground(new java.awt.Color(51, 51, 51));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("LEAGUE TABLE");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("MATCHES PLAYED");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("MATCHES");

        jTextField_leaguename.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_leaguename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_leaguenameActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("LEAGUE NAME");

        jButton_newleague.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_newleague.setText("new league");
        jButton_newleague.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_newleagueActionPerformed(evt);
            }
        });

        jButton_displayleague.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_displayleague.setText("display league");
        jButton_displayleague.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_displayleagueActionPerformed(evt);
            }
        });

        jButton_playselectedmatch.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton_playselectedmatch.setText("PLAY SELECTED MATCH");
        jButton_playselectedmatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_playselectedmatchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(237, 237, 237)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(359, 359, 359))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton_newleague)
                            .addGap(219, 219, 219)
                            .addComponent(jButton_displayleague))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(50, 50, 50)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(97, 97, 97))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addComponent(jTextField_leaguename, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_playselectedmatch, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_leaguename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_newleague)
                    .addComponent(jButton_displayleague))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton_playselectedmatch)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_newleagueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_newleagueActionPerformed
       Create_matches create=new Create_matches();
       create.prompt();
       create.create_leagueTable();
       create.addTeamToLeagueTable();
       create.createMatches_table();
       create.create_matchesPlayed();
    }//GEN-LAST:event_jButton_newleagueActionPerformed

    private void jButton_playselectedmatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_playselectedmatchActionPerformed
      String league=jTextField_leaguename.getText();
      matchesPlayed();
      //League_calculations lc=new League_calculations(score1,score2);
      League_update updata=new League_update(score1,score2,league,team1);
      League_update updata2=new League_update(score2,score1,league,team2);
      updata.getData();
      updata.update();
      updata2.getData();
      updata2.update();
      displayLeagueTable();
    }//GEN-LAST:event_jButton_playselectedmatchActionPerformed

    private void jButton_displayleagueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_displayleagueActionPerformed
       displayLeagueTable();
       displayLeagueMatches();
       displayMatchesPlayed();
       
    }//GEN-LAST:event_jButton_displayleagueActionPerformed

    private void jTextField_leaguenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_leaguenameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_leaguenameActionPerformed

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
            java.util.logging.Logger.getLogger(League_testGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(League_testGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(League_testGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(League_testGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new League_testGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_displayleague;
    private javax.swing.JButton jButton_newleague;
    private javax.swing.JButton jButton_playselectedmatch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable_leaguetable;
    private javax.swing.JTable jTable_matches;
    private javax.swing.JTable jTable_matchesplayed;
    private javax.swing.JTextField jTextField_leaguename;
    // End of variables declaration//GEN-END:variables
}
