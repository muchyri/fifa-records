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

/**
 *
 * @author lenovo
 */
public class League_Table {
  public String league;
  public String choice;
   public int c;
   public int k;
   public int j;
   String [] teams;
   String [] games;
   
    public League_Table(){
     
    }
    public  void Create_table(){
        String messo;
        PreparedStatement ps;
             //create table after if statement 
                league= JOptionPane.showInputDialog("Enter league name:");
                //create league table
                if(!league.equals("")){
                String message=String.format("create table users_db.%s(pos int not null,team varchar(100) not null,GP int not null,W int not null,D int not null,L int not null,GF int not null,GA int not null,GD int not null,POINTS int not null)engine = innodb ",league);
                String registerUserQuery=message;
        try {
          ps=My_Cnx.getConnection().prepareStatement(registerUserQuery);
           ps.executeUpdate();
        } catch (SQLException ex) {
          Logger.getLogger(League_Table.class.getName()).log(Level.SEVERE, null, ex);
        }
        choice= JOptionPane.showInputDialog("Enter number of teams to participate(more than 3):"); 
        c=Integer.parseInt(choice);
        System.out.println(c);
        // if(choice.equals("") && c>2){
        teams=new String[c];
         k=c*(c-1);
        j=0;
      games=new String[k];
      
        for(int i=1;i<=c;i++){
            messo=String.format("enter team %s",i);
            if(!messo.equals("")){
            teams[i-1]=JOptionPane.showInputDialog(messo);
            String query=String.format("insert into %s (pos,team,gp,W,D,L,GF,GA,GD,POINTS)values(?,?,?,?,?,?,?,?,?,?)",league);
            String updateQuery=query;
            try {
                ps=My_Cnx.getConnection().prepareStatement(updateQuery);
                String pos=String.format("%s",i);
                int zero=0;
                String zeros=String.format("%s",zero);
                ps.setString(1,pos);
                ps.setString(2,teams[i-1]);
                ps.setString(3,zeros);
                 ps.setString(4,zeros);
                  ps.setString(5,zeros);
                 ps.setString(6,zeros);
                  ps.setString(7,zeros);
                   ps.setString(8,zeros);
                    ps.setString(9,zeros);
                     ps.setString(10,zeros);
                     if(ps.executeUpdate()!=0){
                         JOptionPane.showMessageDialog(null,"Update successful");
                     }
                
            } catch (SQLException ex) {
                Logger.getLogger(League_Table.class.getName()).log(Level.SEVERE, null, ex);
            }
            }else{
                JOptionPane.showMessageDialog(null,"cannot leave team blank");
            }  
        }
        //create tables for matches
         String messages=String.format("create table users_db.%s_matches(match_id int not null auto_increment,team1 varchar(100) not null,vs varchar(20) not null,team2 varchar(100) not null, primary key(match_id))engine = innodb",league);
                String registerUser_Query=messages;
        try {
          ps=My_Cnx.getConnection().prepareStatement(registerUser_Query);
           ps.executeUpdate();
        } catch (SQLException ex) {
          Logger.getLogger(League_Table.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        for(int p=0;p<=teams.length-1;p++){
            for(int w=0;w<=teams.length-1;w++){
               if(p!=w){
                  games [j]=String.format(teams[p]+" vs "+teams[w]);
                  j++; 
                  String query=String.format("insert into %s_matches(team1,vs,team2)values(?,?,?)",league);
            String updateQuery=query;
            try {
                ps=My_Cnx.getConnection().prepareStatement(updateQuery);
                String vs=String.format("vs");
              ps.setString(1,teams[p]);
              ps.setString(2,vs);
              ps.setString(3,teams[w]);
                
            } catch (SQLException ex) {
                Logger.getLogger(League_Table.class.getName()).log(Level.SEVERE, null, ex);
            }
               } 
            }
        }
        //create table for matches played
         String data=String.format("create table users_db.%s_matchesplayed(match_id int not null auto_increment,team1 varchar(100) not null,vs varchar(20) not null,team2 varchar(100) not null,score1 int not null,score2 int not null, primary key(match_id))engine = innodb",league);
                String register_UserQuery=data;
        try {
          ps=My_Cnx.getConnection().prepareStatement(register_UserQuery);
           ps.executeUpdate();
        } catch (SQLException ex) {
          Logger.getLogger(League_Table.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        
   // }
              //  else if(choice.equals("")){
                  //  JOptionPane.showMessageDialog(null,"cannot leave number of teams blank");
               // }else{
                //    JOptionPane.showMessageDialog(null,"minimum number of teams is three");
               // }
                }
                else{
                    JOptionPane.showMessageDialog(null,"cannot leave league name blank");
                }}
}
