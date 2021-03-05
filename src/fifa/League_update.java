/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fifa;

/**
 *
 * @author lenovo
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class League_update{
    private String team;
    private int GF;
    private int GA;
    private int points;
    private int MP;
    private int GD;
    private int pos;
    private int W;
    private int D;
    private int L;
   private String league;
   private int score1;
   private int score2;
    
    public League_update(int score1,int score2,String league,String team){
        
        this.score1=score1;
        this.score2=score2;
        this.league=league;
        this.team=team;
                }
    public void setTeam(String team){
        this.team=team;
    }
    
   // League_calculations lc=new League_calculations(score1,score2);
    public String getTeam(){
        return team;
    }
    public void setLeague(String league){
        this.league=league;
    }
    public String getLeague(){
        return league;
    }
    public void getData(){
        PreparedStatement ps;
        ResultSet rs;
        String query=String.format("select* from %s where team='%s'",league,getTeam());
        String query1=query;
        try {
            ps=My_Cnx.getConnection().prepareStatement(query1);
            rs=ps.executeQuery();
            while(rs.next()){
            pos=rs.getInt("pos");
            //team=rs.getString("team");
            MP= rs.getInt("MP");
            W=rs.getInt("W");
            D=rs.getInt("D");
            L=rs.getInt("L");
            GF=rs.getInt("GF");
            GA=rs.getInt("GA");
            GD=rs.getInt("GD");
            points=rs.getInt("POINTS");
            }
        } catch (SQLException ex) {
            Logger.getLogger(League_update.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int getW(){
        int gd=score1-score2;
        if(gd>0){
            return W+1;
        }else
            return W;
    }
    public int getD(){
        int gd =score1-score2;
        if (gd==0){
            return D+1;
        }else
            return D;
    }
     public int getL(){
        int gd=score1-score2;
        if(gd<1){
            return L+1;
        }else
            return L;
    }
   public void setGF(int GF){
       this.GF=GF;
   }
   public void setGA(int GA){
       this.GA=GA;
   }
   public void setGD(int GD){
       this.GD=GD;
   }
   public void setPoints(int points){
       this.points=points;
   }
    public int getGF(){
      //  return GF+lc.GF();
      return GF+score1;
    }
    public int getGA(){
        //return GA+lc.GA();
        return GA+score2;
    }
    public int getGD(){
        //return GD+lc.GD();
        return GD+ (score1-score2);
    }
    public int getPoints(){
       // return points+lc.points();
       if((score1-score2)>0){
        return points+3;
    }else if((score1-score2)==0){
        return points+1;
    }else{
        return points;
    }
    }
    public int getMP(){
        return MP+1;
    }
    
  public void update(){
      PreparedStatement ps;
      ResultSet rs;
      
      //error occurs here where it has a problem with the syntax
      //check on the syntax 
       String data=String.format("update %s set MP=?,W=?,D=?,L=?,GF=?,GA=?,GD=?,POINTS=? where team='%s' ",getLeague(),getTeam());
                String query=data;
        try {
            ps=My_Cnx.getConnection().prepareStatement(query);
            ps.setInt(1,getMP());
            ps.setInt(2,getW());
            ps.setInt(3,getD());
            ps.setInt(4,getL());
            ps.setInt(5,getGF());
            ps.setInt(6,getGA());
            ps.setInt(7,getGD());
            ps.setInt(8,getPoints());
            if(ps.executeUpdate()!=0){
               // JOptionPane.showMessageDialog(null," league updated successfully");
                System.out.println("league updated successfully");
            }
                    
            
        } catch (SQLException ex) {
            Logger.getLogger(League_update.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
}
