package com.java.HospitalProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.Button;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;

import com.toedter.calendar.JDateChooser;

public class OT_Reg extends JPanel {

	
	private static final long serialVersionUID = 1L;
	private static JFrame f;
	private JTextField opr_type;
	private JTextField anas_type;
	private int oprId;
	private JTextField txtpatientid;
	Connection con=ConnectToDB.getCon();
	private JTextField textName;
	private JTextField hh;
	private JTextField mm;
	private JTextField surgeon;
	private JTextField anas;
	

	
	public OT_Reg() {
		
		setSize(1375,768);
		setLayout(null);
		
		JLabel lblNitaveHospital = new JLabel("     NITAVE HOSPITAL");
		lblNitaveHospital.setBounds(367, 5, 627, 70);
		lblNitaveHospital.setFont(new Font("Tahoma", Font.BOLD, 58));
		add(lblNitaveHospital);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(OT_Reg.class.getResource("/images/Cesarean.png")));
		label.setBounds(882, 201, 360, 345);
		add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(OT_Reg.class.getResource("/images/sidebar11.png")));
		label_1.setBounds(10, 246, 85, 332);
		add(label_1);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblDate.setBounds(170, 296, 111, 32);
		add(lblDate);
		
		JLabel lblTypeOfOperation = new JLabel("Operation");
		lblTypeOfOperation.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTypeOfOperation.setBounds(170, 353, 162, 32);
		add(lblTypeOfOperation);
		
		JLabel lblTypeOfAnaesthesia = new JLabel("Anaesthesia");
		lblTypeOfAnaesthesia.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTypeOfAnaesthesia.setBounds(170, 408, 189, 32);
		add(lblTypeOfAnaesthesia);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTime.setBounds(562, 301, 85, 32);
		add(lblTime);
		
		opr_type = new JTextField();
		opr_type.setFont(new Font("Tahoma", Font.PLAIN, 20));
		opr_type.setColumns(10);
		opr_type.setBounds(372, 359, 500, 32);
		add(opr_type);
		
		anas_type = new JTextField();
		anas_type.setFont(new Font("Tahoma", Font.PLAIN, 20));
		anas_type.setColumns(10);
		anas_type.setBounds(372, 414, 500, 32);
		add(anas_type);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("ddMMMyyyy");
		dateChooser.setBounds(373, 301, 179, 32);
		add(dateChooser);
		dateChooser.getDateEditor().setEnabled(false);
		dateChooser.getDateEditor().getUiComponent().setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		hh = new JTextField();
		hh.setFont(new Font("Tahoma", Font.PLAIN, 21));
		hh.setColumns(10);
		hh.setBounds(642, 301, 74, 33);
		add(hh);
		
		mm = new JTextField();
		mm.setFont(new Font("Tahoma", Font.PLAIN, 21));
		mm.setColumns(10);
		mm.setBounds(732, 301, 74, 33);
		add(mm);
		
		String p[]=new String[]{"Am","Pm"};
		JComboBox<String> amPm = new JComboBox<String>(p);
		amPm.setFont(new Font("Tahoma", Font.PLAIN, 21));
		amPm.setBounds(822, 301, 68, 31);
		add(amPm);
		
		JTextArea textArea = new JTextArea(5,10);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textArea.setAutoscrolls(true);
		textArea.setLineWrap(true); 
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBounds(371, 540, 480, 70);
		add(textArea);
		JScrollPane scrollPane = new JScrollPane( textArea );
		scrollPane.setViewportView(textArea);
		scrollPane.setBounds(371, 565, 480, 70);
		add(scrollPane);
		
		Button button = new Button("BACK");
		button.setFont(new Font("Tahoma", Font.BOLD, 28));
		button.setBounds(330, 126, 140, 49);
		add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				FollowupOptionWindow.create();
			}
		});
		
		Button button_1 = new Button("LogOut");
		button_1.setFont(new Font("Tahoma", Font.BOLD, 28));
		button_1.setBounds(953, 126, 140, 49);
		add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				MainWindow.create();
			}
		});
		
		JButton button_2 = new JButton("SUBMIT");
		button_2.setFont(new Font("Tahoma", Font.BOLD, 28));
		button_2.setBounds(484, 646, 147, 49);
		add(button_2);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				try{
						String d1  = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
						PreparedStatement psmt=con.prepareStatement("insert into opr_reg values(?,?,?,?,?,?,?,?,?,?)");
						psmt.setInt(1,oprId);
						psmt.setInt(2,FollowupPatient.pid);
						psmt.setString(3,FollowupPatient.oid);
						psmt.setString(4,d1);
						psmt.setString(5,opr_type.getText());
						psmt.setString(6,anas_type.getText());
						psmt.setString(7,hh.getText()+":"+mm.getText()+" "+String.valueOf(amPm.getSelectedItem()));
						psmt.setString(8,surgeon.getText());
						psmt.setString(9,anas.getText());
						psmt.setString(10,textArea.getText());
						psmt.executeQuery();
						JOptionPane.showMessageDialog(null, "Record Inserted...!");
						f.dispose();
						FollowupOptionWindow.create();
					}
					catch(Exception e1)
					{
						JOptionPane.showMessageDialog(null, "Record is not Inserted correctly...!");
						e1.printStackTrace();
					}
				}
		});
		
		JLabel label_2 = new JLabel("Patient Id");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 30));
		label_2.setBounds(170, 201, 162, 31);
		add(label_2);
		
		txtpatientid = new JTextField();
		txtpatientid.setFont(new Font("Tahoma", Font.PLAIN, 21));
		txtpatientid.setEditable(false);
		txtpatientid.setColumns(10);
		txtpatientid.setBounds(372, 202, 248, 32);
		add(txtpatientid);
		
		textName = new JTextField();
		textName.setFont(new Font("Tahoma", Font.PLAIN, 21));
		textName.setEditable(false);
		textName.setColumns(10);
		textName.setBounds(372, 250, 500, 32);
		add(textName);
		
		JLabel label_3 = new JLabel("Name");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 30));
		label_3.setBounds(170, 246, 96, 31);
		add(label_3);
		
		Button home = new Button("HOME");
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				AddEditSearch.create();
			}
		});
		home.setFont(new Font("Tahoma", Font.BOLD, 28));
		home.setBounds(650, 126, 140, 49);
		add(home);
		
		JLabel label_4 = new JLabel(":");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 30));
		label_4.setBounds(719, 299, 18, 31);
		add(label_4);
		
		JLabel lblSurgeon = new JLabel("Surgeon");
		lblSurgeon.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblSurgeon.setBounds(170, 463, 128, 32);
		add(lblSurgeon);
		
		surgeon = new JTextField();
		surgeon.setFont(new Font("Tahoma", Font.PLAIN, 21));
		surgeon.setColumns(10);
		surgeon.setBounds(371, 465, 330, 32);
		add(surgeon);
		
		JLabel lblOperation = new JLabel("Operation");
		lblOperation.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblOperation.setBounds(170, 565, 162, 32);
		add(lblOperation);
		
		JLabel lblNotes = new JLabel("notes");
		lblNotes.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNotes.setBounds(180, 600, 96, 32);
		add(lblNotes);
		
		JLabel lblAnaesthe = new JLabel("Anaesthetise");
		lblAnaesthe.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblAnaesthe.setBounds(170, 512, 215, 32);
		add(lblAnaesthe);
		
		anas = new JTextField();
		anas.setFont(new Font("Tahoma", Font.PLAIN, 21));
		anas.setColumns(10);
		anas.setBounds(373, 514, 328, 32);
		add(anas);
		
		try {
			String s="select max(opr_id) from opr_reg";
			PreparedStatement pst=con.prepareStatement(s);
			ResultSet rs1=pst.executeQuery(); 
			rs1.next();
			oprId=rs1.getInt(1)+1;
			s="select patient_id,patient_name,husband_name,father_name,surname from opd_reg where opd_id='"+FollowupPatient.oid+"'";
			pst=con.prepareStatement(s);
			rs1=pst.executeQuery();
			while(rs1.next())
			{
				
				txtpatientid.setText(rs1.getInt(1)+"");
				textName.setText(rs1.getString(2)+" "+rs1.getString(3)+" "+rs1.getString(4)+" "+rs1.getString(5));
				
			}
		} catch (Exception e) {}
	}
	public static void main(String args[]){
		create();
	}
	public static void create() {
		
		WindowUtil.setNativeLook();;
		f=new JFrame("Nitave Hospital");
		f.getContentPane().add(new OT_Reg());
		WindowUtil.setToCenter(f, WindowUtil.getScreenWidth(), WindowUtil.getScreenHeight());
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				int result = JOptionPane.showConfirmDialog(f, "Do you want to Exit ?", "Exit Confirmation : ", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION)               
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				else if (result == JOptionPane.NO_OPTION)   
					f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
		});
		f.setVisible(true);
	}
}
