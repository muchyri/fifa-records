/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fifa;

import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author lenovo
 */
public class Test_class {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     /* Create_matches match=new Create_matches();
      //League_update update=new League_update(3,2);
      Scanner sc=new Scanner(System.in);
        match.prompt();
        match.create_leagueTable();
        match.addTeamToLeagueTable();
        match.createMatches_table();
        match.create_matchesPlayed()
                */;
            
    // League_Table table=new League_Table();
     //table.Create_table();
        /*  
       String choice= JOptionPane.showInputDialog("Enter number of teams to participate");
       int c=Integer.parseInt(choice);
       String [] teams=new String[c];
       int k=c*(c-1);
       int j=0;
       String [] games=new String[k];
       for(int i=1;i<=c;i++){
           String message=String.format("Enter team %s",i);
           teams[i-1]=JOptionPane.showInputDialog(message);
       }
        
            for(int p=0;p<=teams.length-1;p++){
         for(int w=0;w<=teams.length-1;w++){
             if(p!=w){
                 System.out.println("Team " + teams[p] + " Vs. " + "Team " + teams[w]);
                // for(int j=0;j<=games.length-1;j++){
               games [j]=String.format(teams[p]+" vs "+teams[w]);
               j++;
             //}
          
        }
            }
        } 
            League_form form=new League_form();
        for(String game:games){
            System.out.println(game);
        
        }
/*
        int [] teams = new int[4];
	for(int i = 0; i <= 3; i++)
	{
		teams[i] = i+1;
	}

	for(int i = 0; i <= teams.length-1; i++)
	{
		for(int j = 0; j <= teams.length-1; j++)
		{
			
			if(i!=j){
			System.out.println("Team " + teams[i] +
				" Vs. " + "Team " + teams[j]);
		}
                }
	 }
*/
      
    }}
