package m;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.*;
import java.util.*;
import javax.swing.table.TableCellRenderer;
public class compaction extends JFrame implements ActionListener{
  JFrame f1;
  JButton add, End, compact;
  JLabel label4,l2;
  JTable table2,table3;
  JScrollPane scroll2,scroll3;
  int counter = 0;
  public  Process[][] array;
  public  String[][] arrayS;
  public  Process[][] waiting;
  public  String[][] waitingS;
       
  public compaction() {
	  f1 = new JFrame("Compaction"); 
	  f1.setSize(540, 700);
	  f1.setLocationRelativeTo(null);
  	  f1.setLayout(null);
      f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f1.getContentPane().setBackground(new Color(245,245,245));
      f1.setVisible(true);
      String[] columnNames2 = {
  			"memory" 
  		};
      String[] columnNames3 = {
		   "waiting"
	    };
      array = new Process[10][1];
      arrayS = new String [10][1];
      waitingS=new String [10][1];
      waiting= new Process [10][1];
  
      for (int i = 0; i < 10; i++) {
	  array[i][0] = null;}
      
      for (int i = 0; i < 10; i++) {
	  waiting[i][0] = null;}
      
  //create table 1

  table2 = new JTable(arrayS, columnNames2) {
   @Override
   public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
    Component comp = super.prepareRenderer(renderer, row, column);
    Color blue = new Color(204,  229,255);
    comp.setBackground(array[row][0]==null ? Color.white : blue);
    return comp;
   }
  };
  //create table 2
  table3 = new JTable(waitingS, columnNames3) {
	   @Override
	   public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
	    Component comp = super.prepareRenderer(renderer, row, column);
	    Color blue = new Color(204,  229,255);
	    comp.setBackground(waiting[row][0]==null ? Color.white :blue);
	    return comp;
	   }
	  };
  table2.setFont(new Font("Garamond", Font.BOLD, 18));
  table2.setEnabled(false);
  table2.setRowHeight(70);
  table2.setRowMargin(WIDTH);
  scroll2 = new JScrollPane(table2);
  scroll2.setBounds(330, 100, 100, 410);
  scroll2.getVerticalScrollBar().setBackground(Color.white);

  //

table3.setFont(new Font("Garamond", Font.BOLD, 18));
table3.setEnabled(false);
table3.setRowHeight(70);
table3.setRowMargin(WIDTH);
scroll3 = new JScrollPane(table3);
scroll3.setBounds(80, 100, 100, 410);
scroll3.getVerticalScrollBar().setBackground(Color.white);

//add
  add = new JButton("Insert a process");
  add.setBounds(20, 560, 150, 50);
  add.setBackground(new Color(229,229,229));
 add.setFont(new Font("SansSerif", Font.BOLD, 15));
  add.addActionListener(this);
  
  //End
  End = new JButton("End a process");
  End.setBounds(180, 560, 150, 50);
  End.setBackground(new Color(229,229,229));
  End.setFont(new Font("SansSerif", Font.BOLD, 15));
  End.addActionListener(this);
  
  //compaction
    compact = new JButton("compact");
  compact.setBounds(340, 560, 150, 50);
  compact.setBackground(new Color(229,229,229));
  compact.setFont(new Font("SansSerif", Font.BOLD, 15));
  compact.addActionListener(this);
  
  
  label4 = new JLabel("");
  label4.setBounds(150, 10, 400, 100);
  label4.setFont(new Font("SansSerif", Font.BOLD, 18));
  l2=new JLabel("");
  l2.setBounds(150, 30, 400, 100);
  l2.setFont(new Font("SansSerif", Font.BOLD, 15));

  f1.add(label4);
  f1.add(l2);
  f1.add(add);
  f1.add(End);
  f1.add(compact);
  f1.add(scroll2);
  f1.add(scroll3);
  f1.repaint();
 }
 
 public  void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	 if(e.getSource()==add) {
	 Process p1 = new Process();
	  
	 if( !isFull(array)) {
		 label4.setText(p1.getName()+" with priorty : "+p1.getPriorty()+" added");
	 while (true) {
	
		 Random rand = new Random();
	      int v = rand.nextInt(10);
	 for (int i = v; i < array.length; i++) { 
		 
		 if (array[i][0]==null) {
			 array[i][0]=p1;
			 arrayS[i][0]=p1.getName();
			  f1.repaint();
		 break;}
	 }
	 break;
	 }
	 }else {
	     int d=0;
	     Process  maxValue = array[0][0];
	         for (int i = 0; i < array.length; i++) {
	             if (array[i][0].getPriorty() > maxValue.getPriorty()) {
	             maxValue = array[i][0];
	             
	             d = i;}
	             }//end for
	         // if new process is less priorty 
	        if( maxValue.getPriorty()>p1.getPriorty()) {
	        	l2.setText(maxValue.getName()+" with priorty "+maxValue.getPriorty()+"   is wating");
	       
	        for(int i=0;i<waiting.length;i++) {
	        	if(waiting[i][0]==null) {
	        		waiting[i][0]=maxValue;
	                waitingS[i][0]=	maxValue.getName();	
	      		  f1.repaint();
	                break;}
	        }
	        array[d][0]=p1;
	        arrayS[d][0]=p1.getName();
	        
	      
	      }else {

	        	l2.setText(p1.getName()+" with priorty "+p1.getPriorty()+"  is waiting");
	    	  for(int i=0;i<waiting.length;i++) {
		        	if(waiting[i][0]==null) {
		        		waiting[i][0]=p1;
		                waitingS[i][0]=	p1.getName();	
		                f1.repaint();
		                break;}
		        }
	      }
	    	  //end if
	        
	 }
	 
	 }else if(e.getSource()==End) {
		 int value; 
		 if (!isEmpty(array)) {
		     while (true) {
		      Random random = new Random();
		      value = random.nextInt(10);
		      
		      if (array[value][0]!=null) {
		       

	        	l2.setText(array[value][0].getName()+"  with priority "+array[value][0].getPriorty()+" is end"); 
	        	array[value][0] =null;
			       arrayS[value][0] ="";
		       f1.repaint();}else continue;       	
		      break;
		      }
		       //to check muvalue not equals null
		     if(!isEmpty(waiting)) {
		       int x=0;
			     Process maxValue=null;
			     for (int i = 0; i < waiting.length; i++) { 
			    	 if(waiting[i][0]!=null) {
			    		 maxValue=waiting[i][0];
			    		 break;
			    	 }
			     }
			     //to get max priority 
			         for (int i = 0; i < waiting.length; i++) {
			        	 if(waiting[i][0]!=null) 
			             if (waiting[i][0].getPriorty() <= maxValue.getPriorty()) {
			             maxValue = waiting[i][0];
			             
			             x= i;}
			            }//end for
			       
			       /* for(int i=0;i<array.length;i++) {
			        	if(array[i][0]==null) {
			        		array[i][0]=waiting[x][0];
			        	arrayS[i][0]=waiting[x][0].getName();	
			        		break;}
			        }*/
			        label4.setText( waiting[x][0].getName()+" With priorty "+waiting[x][0].getPriorty()+" is return to memory");
			        array[value][0]=  waiting[x][0];  ///// 
			        		 arrayS[value][0]=  waiting[x][0].getName();
			                 waiting[x][0]=null;
			        		waitingS[x][0]="";
			        		f1.repaint();
			 
		     
		     }
		    } else
	    	     return;
		 
		 
	 }else if(e.getSource()==compact) {
		 for (int i = 0; i < 10; i++) {
		     if (array[i][0]==null) {
		      for (int p = i + 1; p < 10; p++) {
		       if (array[p][0]!=null) {
		    	   array[i][0] = array[p][0];
		    	   arrayS[i][0]= array[p][0].getName() ;
		    	   array[p][0] =null;
		    	   arrayS[p][0] ="";
		        break;
		       }
		       if (p == 10)
		        break;
		      }
		     }
		    }
		    f1.repaint();
		 
		 
	 }
		
	}
 public  boolean isFull(Process[][]ar) {
  for (int i = 0; i < ar.length; i++)
   if (ar[i][0]==null)
    return false;
  return true;
 }

 public  boolean isEmpty(Process[][]ar) {
  for (int i = 0; i < ar.length; i++)
   if (ar[i][0]!=null)
    return false;
  return true;
 }

}