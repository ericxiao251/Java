package Memory_Match;
import javax.swing.*;

public class Memory_Match_Card extends JButton{
    // Holds the picture of the Country
    // Holds what the name of the Country
    // card_state false=turned dowsn
    // card_state true=turned up
    private final ImageIcon Back=new ImageIcon("/Users/ricebaby251/Documents/Summer_2015/Game/Matching_Game/back.gif");
    private ImageIcon Front;
    private String Country;
    private boolean State=false;
    
    public Memory_Match_Card(String theCountry){
        Country=theCountry;
        Front=new ImageIcon(theCountry);    
    }

    public void turnFaceDown(){ 
        super.setIcon(Back);
        State=false;
    }

    public void turnFaceUp(){   
        super.setIcon(Front);
        State = true;
    }
    
    public void flipCard(){
        if(State==true)
            turnFaceDown();
    else
            turnFaceUp();        
    }
    
    public String getCountry(){
        return Country;
    }    
    
    public void changeCountry(String theCountry){
    Country=theCountry;
    Front=new ImageIcon(theCountry);
    }
}