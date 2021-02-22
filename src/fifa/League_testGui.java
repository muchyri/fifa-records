/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fifa;

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
    public League_testGui() {
        initComponents();
    }
    public void matchesPlayed(){
        int i=jTable_matches.getSelectedRow();
        TableModel model=jTable_matches.getModel();
        String team1=model.getValueAt(i,0).toString();
       String team2=model.getValueAt(i,2).toString();
       String q1="Enter score for "+team1;
       String q2="Enter score for "+team2;
        score1=Integer.parseInt(JOptionPane.showInputDialog(null,q1));
        score2= Integer.parseInt(JOptionPane.showInputDialog(null,q2));
        String league=jTextField_leaguename.getText();
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
                DefaultTableModel tbmodel=(DefaultTableModel)jTable_matchesplayed.getModel();
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
        jButton_displayleague1 = new javax.swing.JButton();

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

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("LEAGUE NAME");

        jButton_newleague.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_newleague.setText("new league");

        jButton_displayleague.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_displayleague.setText("display league");

        jButton_displayleague1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton_displayleague1.setText("PLAY SELECTED MATCH");

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
                            .addComponent(jButton_displayleague1, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
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
                .addComponent(jButton_displayleague1)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JButton jButton_displayleague1;
    private javax.swing.JButton jButton_newleague;
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
