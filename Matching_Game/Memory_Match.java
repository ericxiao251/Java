package Memory_Match;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class Memory_Match extends JFrame implements ActionListener{
    Memory_Match_Deck theCards = new Memory_Match_Deck("/Users/ricebaby251/Documents/Summer_2015/Game/Matching_Game/Countries/");
    JButton btnNew = new JButton("New Game");//
    
    public Memory_Match(){
        Container c=getContentPane();//
        c.setLayout(new BorderLayout());//

        JPanel pnlNew = new JPanel();//
        pnlNew.add(btnNew);//
        btnNew.addActionListener(this);//
        c.add(pnlNew,BorderLayout.NORTH);//
        c.add(theCards,BorderLayout.CENTER);
        for(int i=0; i<theCards.getsize(); i++)
            theCards.dealCard();

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                dispose();
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e){
        theCards.shuffle();
    }

    public static void main(String args[]) {
        System.out.println("Starting MemoryMatch...");
        Memory_Match mainFrame = new Memory_Match();
        mainFrame.setSize(400, 400);
        mainFrame.setTitle("MemoryMatch");
        mainFrame.setVisible(true);
    }
}