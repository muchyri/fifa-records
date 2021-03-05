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
public class Create_matches {
  private String league;
  private String choice;
   private int c;
   private int k;
   private int j;
   private int status=0;
   private String [] teams;
   private String [] games;
   String messo;
   private int GF=0;
   private int GA=0;
   private int GD=0;
   private int MP=0;
   private int points=0;
   private int W=0;
   private int D=0;
   private int L=0;
   public void setStatus(int status){
       this.status=status;
   }
   public int getStatus(){
       return status;
   }
   
   public void setGF(int GF){
       this.GF=GF;
   }
   public int getGF(){
       return GF;
   }public void setGA(int GA){
       this.GA=GA;
   }
   public int getGA(){
       return GA;
   }
   public void setGD(int GD){
       this.GD=GD;
   }
   public int getGD(){
       return GD;
   }
   public void setMP(int MP){
       this.MP=MP;
   }
   public int getMP(){
       return MP;
   }
   public void setPoints(int points){
       this.points=points;
   }
   public int getPoints(){
       return points;
   }
   public void setW(int W){
       this.W=W;
   }
   public int getW(){
       return W;
   }
   public void setD(int D){
       this.D=D;
   }
   public int getD(){
       return D;
   }
   public void setL(int L){
       this.L=L;
   }
   public int getL(){
       return L;
   }
   public Create_matches(){
       
   }
   public Create_matches(int MP,int W,int D,int L,int GF,int GA,int GD,int points){
       this.MP=MP;
       this.W=W;
       this.D=D;
       this.L=L;
       this.GF=GF;
       this.GA=GA;
       this.GD=GD;
       this.points=points;
   }
   public void prompt(){
       league= JOptionPane.showInputDialog("Enter league name:");
       if(!league.equals("")){
         choice= JOptionPane.showInputDialog("Enter number of teams to participate(atleast 3):");
         c=Integer.parseInt(choice);
         if(!choice.equals("") && c>2 && !choice.equals(null)){
             teams=new String[c];
              k=c*(c-1);
        j=0;
      games=new String[k];
             for(int i=1;i<=c;i++){
            messo=String.format("enter team %s",i);
            if(!messo.equals("") && !league.equals(null)){
            teams[i-1]=JOptionPane.showInputDialog(messo);
         }
       }
       
   }
       }     
}
public void create_leagueTable(){
    
    PreparedStatement ps;
    String message=String.format("create table users_db.%s(pos int not null,team varchar(100) not null,MP int not null,W int not null,D int not null,L int not null,GF int not null,GA int not null,GD int not null,POINTS int not null)engine = innodb ",league);
    String registerUserQuery=message;
      try {
          ps=My_Cnx.getConnection().prepareStatement(registerUserQuery);
          ps.executeUpdate();
      } catch (SQLException ex) {
          Logger.getLogger(Create_matches.class.getName()).log(Level.SEVERE, null, ex);
      }
}
public void addTeamToLeagueTable(){
    PreparedStatement ps;
    ResultSet rs;
    for(int i=1;i<=c;i++){
        String query=String.format("insert into %s (pos,team,MP,W,D,L,GF,GA,GD,POINTS)values(?,?,?,?,?,?,?,?,?,?)",league);
            String updateQuery=query;
            try {
                ps=My_Cnx.getConnection().prepareStatement(updateQuery);
                //String pos=String.format("%s",i);
                int zero=0;
                String zeros=String.format("%s",zero);
                ps.setInt(1,i);
                ps.setString(2,teams[i-1]);
                ps.setInt(3,MP);
                 ps.setInt(4,getW());
                  ps.setInt(5,getD());
                 ps.setInt(6,getL());
                  ps.setInt(7,getGF());
                   ps.setInt(8,getGA());
                    ps.setInt(9,getGD());
                     ps.setInt(10,getPoints());
                     if(ps.executeUpdate()!=0){
                         JOptionPane.showMessageDialog(null,"Update successful");
                     }
                
            } catch (SQLException ex) {
                Logger.getLogger(League_Table.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
    public void createMatches_table(){
       PreparedStatement ps2;
  String messages=String.format("create table users_db.%s_matches(match_id int not null auto_increment,status int not null,team1 varchar(100) not null,vs varchar(20) not null,team2 varchar(100) not null, primary key(match_id))engine = innodb",league);
                String registerUser_Query=messages;
        try {
          ps2=My_Cnx.getConnection().prepareStatement(registerUser_Query);
           ps2.executeUpdate();
        } catch (SQLException ex) {
          Logger.getLogger(League_Table.class.getName()).log(Level.SEVERE, null, ex);
    } 
     
        for(int p=0;p<=teams.length-1;p++){
            for(int w=0;w<=teams.length-1;w++){
               if(p!=w){
                 // games [j]=String.format(teams[p]+" vs "+teams[w]);
                 // j++; 
                 String team1=teams[p];
                 String team2=teams[w];
                String query=String.format("insert into %s_matches(status,team1,vs,team2)values(?,?,?,?)",league);
            String updateQuery=query;
            try {
                ps2=My_Cnx.getConnection().prepareStatement(updateQuery);
                String vs=String.format("vs");
                ps2.setInt(1,status);
              ps2.setString(2,team1);
              ps2.setString(3,vs);
              ps2.setString(4,team2);
              if(ps2.executeUpdate()!=0){
                         JOptionPane.showMessageDialog(null,"Update successful");
                     }
                
            } catch (SQLException ex) {
                Logger.getLogger(League_Table.class.getName()).log(Level.SEVERE, null, ex);
            }
               }
               
            }
        }
        /*for(String games:games){
                      System.out.println(games);
                  }*/
    }
    public void create_matchesPlayed(){
        PreparedStatement ps3;
         String data=String.format("create table users_db.%s_matchesplayed(match_id int not null auto_increment,team1 varchar(100) not null,vs varchar(20) not null,team2 varchar(100) not null,score1 int not null,score2 int not null, primary key(match_id))engine = innodb",league);
                String register_UserQuery=data;
        try {
          ps3=My_Cnx.getConnection().prepareStatement(register_UserQuery);
           ps3.executeUpdate();
        } catch (SQLException ex) {
          Logger.getLogger(League_Table.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
}