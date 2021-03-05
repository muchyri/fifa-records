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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class Create_table {
    private String name;
    private String[] teams;
    private int no;
    
    public Create_table(String name,int no, String[] teams){
       this.name=name; 
       this.teams=teams;
       this.no=no;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void create(){
        PreparedStatement ps;
        String message=String.format("create table users_db.%s(team varchar(100) not null,MP int not null,W int not null,D int not null,L int not null,GF int not null,GA int not null,GD int not null,POINTS int not null)engine = innodb ",name);
    String registerUserQuery=message;
        try {
            ps=My_Cnx.getConnection().prepareStatement(registerUserQuery);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Create_table.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public void insert(){
        PreparedStatement ps;
        ResultSet rs;
        for(int i=1;i<=no;i++){
        String query=String.format("insert into %s (pos,team,MP,W,D,L,GF,GA,GD,POINTS)values(?,?,?,?,?,?,?,?,?,?)",name);
            String updateQuery=query;
            try {
                ps=My_Cnx.getConnection().prepareStatement(updateQuery);
                //String pos=String.format("%s",i);
                int zero=0;
                String zeros=String.format("%s",zero);
                ps.setInt(1,i);
                ps.setString(2,teams[i-1]);
                ps.setInt(3,0);
                 ps.setInt(4,0);
                  ps.setInt(5,0);
                 ps.setInt(6,0);
                  ps.setInt(7,0);
                   ps.setInt(8,0);
                    ps.setInt(9,0);
                     ps.setInt(10,0);
                     if(ps.executeUpdate()!=0){
                         System.out.println("insert successful");
                         //JOptionPane.showMessageDialog(null,"Update successful");
                     }
                
            } catch (SQLException ex) {
                Logger.getLogger(League_Table.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    }
}
