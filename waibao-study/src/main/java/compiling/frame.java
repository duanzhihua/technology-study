package compiling;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;


public class frame extends JFrame {

	private JPanel contentPane;
	private JTextArea grammar;
	private JTextArea string;
	private JTextArea stack;
	private JTextArea input;
	private JTextArea action;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JLabel lblNewLabel;
	int flag;
	private JLabel lblStatus;
	private JLabel lblDevelopedBy;
	private JLabel lblBhushanDeshmukh;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					frame frame = new frame();
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frame(String tgrammar,String sInput,String tStack,String tInput,String tAction,int flag) {
		super("developed by- Bhushan A. Deshmukh");
		this.flag=flag;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		grammar = new JTextArea(tgrammar);
		grammar.setBorder(null);
		grammar.setEditable(false);
		grammar.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		grammar.setOpaque(false);
		grammar.setBackground(Color.WHITE);
		grammar.setBounds(35, 89, 223, 204);
		contentPane.add(grammar);
		
		JLabel lblStack = new JLabel("Stack");
		lblStack.setHorizontalAlignment(SwingConstants.CENTER);
		lblStack.setOpaque(true);
		lblStack.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblStack.setBackground(Color.LIGHT_GRAY);
		lblStack.setBounds(295, 43, 250, 40);
		contentPane.add(lblStack);
		
		string = new JTextArea(sInput);
		string.setBorder(null);
		string.setEditable(false);
		string.setOpaque(false);
		string.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		string.setBackground(Color.WHITE);
		string.setBounds(20, 358, 250, 40);
		contentPane.add(string);
		
		JLabel lblInputString = new JLabel("Input String");
		lblInputString.setHorizontalAlignment(SwingConstants.CENTER);
		lblInputString.setOpaque(true);
		lblInputString.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblInputString.setBackground(Color.LIGHT_GRAY);
		lblInputString.setBounds(10, 314, 275, 40);
		contentPane.add(lblInputString);
		
		JLabel lblInput = new JLabel("Input ");
		lblInput.setHorizontalAlignment(SwingConstants.CENTER);
		lblInput.setOpaque(true);
		lblInput.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblInput.setBackground(Color.LIGHT_GRAY);
		lblInput.setBounds(555, 43, 250, 40);
		contentPane.add(lblInput);
		
		JLabel lblAction = new JLabel("Action");
		lblAction.setHorizontalAlignment(SwingConstants.CENTER);
		lblAction.setOpaque(true);
		lblAction.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblAction.setBackground(Color.LIGHT_GRAY);
		lblAction.setBounds(815, 43, 250, 40);
		contentPane.add(lblAction);
		
		JLabel lblGrammar = new JLabel("Grammar");
		lblGrammar.setOpaque(true);
		lblGrammar.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrammar.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblGrammar.setBackground(Color.LIGHT_GRAY);
		lblGrammar.setBounds(10, 43, 275, 40);
		contentPane.add(lblGrammar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(322, 89, 208, 539);
		contentPane.add(scrollPane);
		
		stack = new JTextArea(tStack);
		stack.setBorder(null);
		stack.setEditable(false);
		scrollPane.setViewportView(stack);
		stack.setOpaque(false);
		stack.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		stack.setBackground(Color.WHITE);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(null);
		scrollPane_1.setBounds(582, 89, 208, 539);
		contentPane.add(scrollPane_1);
		
		input = new JTextArea(tInput);
		input.setBorder(null);
		input.setEditable(false);
		scrollPane_1.setViewportView(input);
		input.setOpaque(false);
		input.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		input.setBackground(Color.WHITE);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBorder(null);
		scrollPane_2.setBounds(857, 89, 184, 539);
		contentPane.add(scrollPane_2);
		
		action = new JTextArea(tAction);
		action.setBorder(null);
		action.setEditable(false);
		scrollPane_2.setViewportView(action);
		action.setOpaque(false);
		action.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		action.setBackground(Color.WHITE);
		
		lblNewLabel = new JLabel("Shift Reduce Parsing");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel.setBounds(400, 5, 300, 35);
		contentPane.add(lblNewLabel);
		
		JLabel accept = new JLabel("String Accepted");
		accept.setHorizontalAlignment(SwingConstants.CENTER);
		accept.setFont(new Font("Century Gothic", Font.BOLD, 20));
		accept.setBackground(Color.LIGHT_GRAY);
		accept.setBounds(10, 446, 275, 40);
		accept.setVisible(false);
		contentPane.add(accept);
		
		JLabel reject = new JLabel("String Rejected");
		reject.setForeground(new Color(153, 0, 0));
		reject.setHorizontalAlignment(SwingConstants.CENTER);
		reject.setFont(new Font("Century Gothic", Font.BOLD, 20));
		reject.setBackground(Color.LIGHT_GRAY);
		reject.setBounds(10, 446, 275, 40);
		reject.setVisible(false);
		contentPane.add(reject);
		
		lblStatus = new JLabel("Status");
		lblStatus.setOpaque(true);
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblStatus.setBackground(Color.LIGHT_GRAY);
		lblStatus.setBounds(10, 409, 275, 40);
		contentPane.add(lblStatus);
		
		lblDevelopedBy = new JLabel("developed by-");
		lblDevelopedBy.setHorizontalAlignment(SwingConstants.LEFT);
		lblDevelopedBy.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblDevelopedBy.setBackground(Color.LIGHT_GRAY);
		lblDevelopedBy.setBounds(10, 514, 275, 40);
		contentPane.add(lblDevelopedBy);
		
		lblBhushanDeshmukh = new JLabel("Bhushan Deshmukh");
		lblBhushanDeshmukh.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBhushanDeshmukh.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblBhushanDeshmukh.setBackground(Color.LIGHT_GRAY);
		lblBhushanDeshmukh.setBounds(49, 546, 231, 40);
		contentPane.add(lblBhushanDeshmukh);
		
		if(flag==1)
			accept.setVisible(true);
		else if(flag==0)
			reject.setVisible(true);
	}
}