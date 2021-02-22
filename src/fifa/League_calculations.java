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
public class League_calculations {
   private int score1;
    private int score2;
    private int gd;
    private int points=0;
    public League_calculations(){
        
    }
public League_calculations(int score1,int score2){
    this.score1=score1;
    this.score2=score2;
}   
public void setScore1(int score1){
    this.score1=score1;
}
public void setScore2(int score2){
    this.score2=score2;
}
public int getScore1(){
    return score1;
}
public int getScore2(){
    return score2;
}
public int GF(){
    return score1;
}
public int GA(){
    return score2;
}
public int GD(){
     gd=score1-score2;
     return gd;
}
public int points(){
    if(GD()>1){
        return points+3;
    }else if(GD()==1){
        return points+1;
    }else{
        return points;
    }
}
}

