package Memory_Match;
import java.io.File;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Memory_Match_Deck extends JPanel implements ActionListener{
    int size;
    Memory_Match_Card[] theDeck;
    int CurrentCard=0;
    String []country;
    int firstClick=20,secondClick=20,matches=0,turns=0;
    Timer t;
    int delay=1000;
    
    // initializes Memory_Match_Deck
    public Memory_Match_Deck(String path){
        File temp =new File(path);
        size=2*(temp.listFiles().length-1);
        
        String[] temp_deck=temp.list();
        country=new String[size/2];
        for(int i=1;i<temp_deck.length;i++)
            country[i-1]=temp_deck[i];
        
        setLayout(new GridLayout(4,size/4));
        int cardNum=0;
        
        theDeck=new Memory_Match_Card[size];
        for(int i=0;i<size/2;i++){
            theDeck[cardNum]= new Memory_Match_Card(path+country[i]);
            add(theDeck[cardNum]);
            theDeck[cardNum].addActionListener(this);
            cardNum++;
            theDeck[cardNum] = new Memory_Match_Card(path+country[i]);
            add(theDeck[cardNum]);
            theDeck[cardNum].addActionListener(this);
            cardNum++;
        }
    }
    
    public int getsize(){
        return size;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        int selection=0;
        for (int c=0; c<size; c++)
            if (e.getSource() == theDeck[c])
                selection = c;  

        if (firstClick != selection)                
            if (firstClick == 20){
                firstClick = selection;
                theDeck[firstClick].flipCard();
            }       
            else{
                turns++;
                secondClick = selection;
                theDeck[selection].flipCard();
                if (theDeck[firstClick].getCountry().equals(theDeck[secondClick].getCountry())){
                    matches++;
                    theDeck[firstClick].setEnabled(false);
                    theDeck[secondClick].setEnabled(false);
                    if (matches==10){
                        JOptionPane msg = new JOptionPane();
                        msg.showMessageDialog(this,"Game over in " + String.valueOf(turns) +
                        " tries.");
                    }
                    firstClick=20;
                }
                else
                    myDelay();                  
            }
    }
    
    private void myDelay(){
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                theDeck[firstClick].flipCard();
                theDeck[secondClick].flipCard();
                firstClick=20;  
                t.stop();
            }
        };
        t = new Timer(delay,al);
        t.start();
        t.setRepeats(false);
    }
    
    public void shuffle(){
        for(int c=0;c<size;c++){
            int spot = (int)(Math.random()*size);
            String tempCountry = theDeck[spot].getCountry();
            theDeck[spot].changeCountry(theDeck[c].getCountry());
            theDeck[c].changeCountry(tempCountry);
        }
        for (int c=0; c<size; c++){
            theDeck[c].turnFaceDown();
            theDeck[c].setEnabled(true);
        }
        matches=0;
        firstClick=20;
        secondClick=20;
        turns=0;
    }
    public void dealCard(){
        Memory_Match_Card tempCard = theDeck[CurrentCard];
        if (CurrentCard<size/2)
            CurrentCard++;
        else
            CurrentCard=0;
        tempCard.turnFaceDown();
    }
}