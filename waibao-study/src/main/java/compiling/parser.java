package compiling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class parser {

	static PrintWriter writer;
	static HashSet<Character> set; 
	static String mystack,myinput,myaction,mytoken,tofile,mygrammar;
	
	public static void main(String []args)
	{
		writer=null;
		
		int i,lip,r;
		int f = 0;
		String input;
		Scanner scan = new Scanner(System.in);
		int np;
		
		np=Integer.parseInt(JOptionPane.showInputDialog(null,	"Enter No of Productions")); 
		
		grammer []g=new grammer[np];
		
		System.out.println("Enter Grammer Productions: ");
		mygrammar="";
		for(i=0;i<np;i++)
		{
			JTextField xField = new JTextField(5);
			JTextField yField = new JTextField(5);
			g[i]=new grammer();
			Object[] message = {
			    "Left:", xField,
			    "Right:",yField, 
			};
			JOptionPane.showConfirmDialog(null, message, "Enter Production", JOptionPane.OK_CANCEL_OPTION);
			g[i].p=xField.getText();
			g[i].prod=yField.getText();
			mygrammar+=(i+1)+". "+g[i].p+" -> "+g[i].prod+"\r\n";
		}
		
		System.out.println("Grammar is");
		for(i=0;i<np;i++)
		{
			System.out.println(g[i].p+"->"+g[i].prod);
		}
		
		//scan.nextLine();
		System.out.print("Enter Input: ");
		input=JOptionPane.showInputDialog(null,	"Input String");
		lip=input.length();
		
		Stack<Character> stack=new Stack<Character>();
		LinkedList queue=new LinkedList();
		
		for(i=0;i<lip;i++)
		{
			queue.addLast(input.charAt(i));
		}
		System.out.print("\n\nStack\t\tInput\t\tAction");
		tofile="\r\n\r\nStack\t\t\t\tInput\t\t\t\tAction";
		
		mystack="\n"+pStack(stack);
		//tofile+="\r\n"+stack;
		System.out.print("\n");
		printStack(stack);
		
		myinput="\n"+pQueue(queue);
		//tofile+="\t\t\t\t"+queue;
		System.out.print("\t\t");
		printQueue(queue);
		
		stack.push((Character)queue.removeFirst());
		String temp;
		
		myaction="";
		do
		{
			r=1;
			while(r!=0)
			{
				System.out.print("\n");
				printStack(stack);
				mystack+="\n"+pStack(stack);
				
				System.out.print("\t\t");
				myinput+="\n"+pQueue(queue);
				printQueue(queue);
				tofile+="\r\n"+stack+"\t\t\t\t"+queue;
				
				if(r==2)
	            {
					myaction+="\r\nreduced by "+(f+1);
					tofile+="\t\t\t\treduced";
					System.out.print("\t\treduced");
	            }
	            else
	            {
					myaction+="\r\nshifted";
					tofile+="\t\t\t\tShifted";
	            	System.out.print("\t\tShifted");
	            }
	            r=0;      
	            	
	            //reducing
	            temp="";
	            Object[]arr=stack.toArray();
	            Collections.reverse(Arrays.asList(arr));
	            for(Object a: arr )
				{
	            	f=0;
	            	temp+=""+(Character)a;

	            	//check if temp belongs to any production
	            	for(int z=0;z<np;z++)
	            	{
	            		f=z;
	            		r=1;
	            		if(temp.equals(g[z].prod)||(new String((new StringBuffer(temp)).reverse())).equals(g[z].prod))
	            		{
	            			r=2;
	            			break;
	            		}
	            	}
	            	if(r==2)
	            	{
	            		//removing the reduced part from stack
	            		for(int z=0; z<temp.length();z++)
	            			stack.pop();
	            		stack.push((Character)g[f].p.charAt(0));
	            		break;
	            	}
	            	else if (stack.toArray().length==temp.length()&&r==1&&!queue.isEmpty())//shifted
	            	{
	            		//nothing for stack can be reduced taking net inout symbol
	            		stack.push((Character)queue.removeFirst());
	            		break;
	            	}
	            	r=0;
	            	
				}
			}
			
			
		}while(!stack.isEmpty() && !queue.isEmpty());
		
		
		int flag=0;
		for(int z=0;z<np;z++)
    	{
    		f=z;
    		r=1;
    		if(	stack.peek() == g[z].p.charAt(0))
    		{
    			flag=1;
    			break;
    		}
    	}
		stack.pop();
		
		
		
		
		
		
		if(flag==1&&queue.isEmpty()&&stack.isEmpty())		//checking accept condition
		{
			frame jf=new frame(mygrammar,input,mystack,myinput,myaction,1);
			jf.setVisible(true);
			System.out.println("\nString Accepted");
			tokenGenerator(input);
			macroExpander(input);
			stackFileGenerator(tofile);
		}
		else{
			System.out.println("\nString Rejected");
			frame jf=new frame(mygrammar,input,mystack,myinput,myaction,0);
			jf.setVisible(true);
		}
	}

	static String pQueue(Queue q)
	{
		String temp="";
		for(Object a: q.toArray() )
		{
			temp+=(Character)a;
		}
		return temp;
	}
	
	static String pStack(Stack stk)
	{
		String temp="";
		for(Object a: stk.toArray() )
		{
			temp+=(Character)a;
		}
		return temp;
	}
	
	static void printQueue(Queue q)
	{
		for(Object a: q.toArray() )
		{
			System.out.print((Character)a);
		}
	}
	
	static void printStack(Stack stk)
	{
		for(Object a: stk.toArray() )
		{
			System.out.print((Character)a);
		}
	}
	
	static void printArr(Object[] stk)
	{
		for(Object a: stk )
		{
			System.out.print((Character)a);
		}
	}
	
	static void stackFileGenerator(String tofile)
	{
		try {
			writer=new PrintWriter(new File("f:\\stack.txt"));
			writer.write(tofile);
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void tokenGenerator(String ip)
	{
		String temp="Tokens Generated\r\n";
		set =new HashSet<Character>();
		for(char c:ip.toCharArray())
		{
			set.add(c);
		}
		Iterator<Character> itr =set.iterator();
		while(itr.hasNext())
		{
			temp+=""+itr.next()+"\r\n";
			
		}
		//System.out.println(temp);
		try {
			writer=new PrintWriter(new File("f:\\tokens.txt"));
			writer.write(temp);
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void macroExpander(String input)
	{
		String ip=input;

		char []ar=ip.toCharArray();

		Random rand =new Random();
		String macro="MACRO EXAPNSION\r\n\r\n"; 
		macro+="\r\n OPER\t AREG "+" z "+" "+rand.nextInt(50000);
		macro+="\r\n\t+ MOVER\t AREG "+ar[0]+" "+rand.nextInt(50000);
		
		//System.out.println("\n\t+ MOVER\t "+REG +" "+ MEM_VAL);
		
		
		for(int i1=0;i1<ar.length;i1++)
		{
			if(ar[i1]=='('||ar[i1]==')')
				continue;
			if(ar[i1]=='+')
				macro+="\r\n\t+ ADD  \t AREG "+ (ar[i1+1]=='('||ar[i1+1]==')'?'u':ar[i1+1])+" "+rand.nextInt(50000);
			if(ar[i1]=='-')
				macro+="\r\n\t+ SUB  \t AREG "+ (ar[i1+1]=='('||ar[i1+1]==')'?'g':ar[i1+1])+" "+rand.nextInt(50000);
			if(ar[i1]=='*')
				macro+="\r\n\t+ MUL  \t AREG "+ (ar[i1+1]=='('||ar[i1+1]==')'?'i':ar[i1+1])+" "+rand.nextInt(50000);
			if(ar[i1]=='/')
				macro+="\r\n\t+ DIV  \t AREG "+ (ar[i1+1]=='('||ar[i1+1]==')'?'p':ar[i1+1])+" "+rand.nextInt(50000);
		}
		macro+="\r\n\t+ MOVEM\t z AREG "+rand.nextInt(50000);
		
		try {
			writer=new PrintWriter(new File("f:\\macros.txt"));
			writer.write(macro);
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void div(char MEM_VAL,char INCR_VAL) 
	{
		String REG = "AREG";
		System.out.println("\n\t+ DIV  \t "+REG+" "+INCR_VAL);
	}
	
	void mul(char MEM_VAL,char INCR_VAL) 
	{
		String REG = "AREG";
		System.out.println("\n\t+ MUL  \t "+REG+" "+INCR_VAL);
	}
	
	void sub(char MEM_VAL,char INCR_VAL) 
	{
		String REG = "AREG";
		System.out.println("\n\t+ SUB  \t "+REG+" "+INCR_VAL);
	}
	
	void add(char MEM_VAL,char INCR_VAL) 
	{
		String REG = "AREG";
		//System.out.println("\n\t+ MOVER\t "+REG +" "+ MEM_VAL);
		System.out.println("\n\t+ ADD  \t "+REG+" "+INCR_VAL);
		//System.out.println("\n\t+ MOVEM\t "+REG+" "+MEM_VAL);
	}
	
}

class grammer{
	public String p;
	public String prod;
}