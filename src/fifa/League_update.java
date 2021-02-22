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
/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
*/
public class League_update extends League_calculations{
    private int team;
    private int GF=0;
    private int GA=0;
    private int points=0;
    private int MP=0;
    private int GD=0;
    League_calculations lc=new League_calculations();
    public League_update(int score1,int score2){
        super(score1,score2);
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
        return GF+lc.GF();
    }
    public int getGA(){
        return GA+lc.GA();
    }
    public int getGD(){
        return GD+lc.GD();
    }
    public int getPoints(){
        return points+lc.points();
    }
    public int getMP(){
        return MP+1;
    }
  
}
