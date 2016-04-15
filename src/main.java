import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.SelectableChannel;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;



public class main 
{
	
    protected void createAndShowGUI() {
        JFrame f = new JFrame();
        f.setSize(400,500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
        snake game=new snake();
     
       
  
     
     f.add(game);
        f.setResizable(false);
       
        f.setVisible(true);
    }
    
    public static void main(String[] args) 
    {
        System.out.println(Thread.currentThread().getName());
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                System.out.println(Thread.currentThread().getName());
                main app = new main();
                app.createAndShowGUI();
            }
        });
    }

	
	
}