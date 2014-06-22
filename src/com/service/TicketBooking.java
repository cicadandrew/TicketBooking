package com.service;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ticket.Ticket;

public class TicketBooking extends JPanel {
	private JLabel Null1 = new JLabel("");
	private JLabel Null2 = new JLabel("");
	private JLabel Null3 = new JLabel("");
	private JLabel Null4 = new JLabel("");
	private JLabel studentID = new JLabel("              學生   ID");
	private JTextField sID = new JTextField();
	private DateFormat df = DateFormat.getDateInstance();
	private JLabel date = new JLabel("       日期 yyyy/mm/dd");
	private JTextField datei = new JTextField();
	private JLabel startStation = new JLabel("                  始站");
	private JTextField sStation = new JTextField();
	private JLabel endStation = new JLabel("                  終站");
	private JTextField eStation = new JTextField();
	private JLabel trainID = new JLabel("                  車型");
	private JTextField tID = new JTextField();
	private JLabel ticketnum = new JLabel("                  票數");
	private JTextField ticketn = new JTextField();
	private ButtonHandler hbtHandler = new ButtonHandler();
	private JButton Jbtn_CANCEL = new JButton("取消訂票 (ID)");
	private JLabel cancelExplain1 = new JLabel("  範圍輸入 \"A~B\", 單張輸入\"A\"");
	private JLabel cancelExplain2 = new JLabel("");
	private JTextField cancelID = new JTextField();
	private JButton Jbtn_YES = new JButton("確定");
	private JButton Jbtn_NO = new JButton("清除");
	private JButton Jbtn_PRINT = new JButton("輸出");
	private JLabel printExplain1 = new JLabel("  範圍輸入 \"A~B\", 單張輸入\"A\"");
	private JLabel printExplain2 = new JLabel("");
	private JTextField ticketNO = new JTextField();
	private List<Ticket> tickets = new ArrayList<Ticket>();
	private TicketOrderService service = TicketOrderFacetory.getService();

	public TicketBooking() {
		this.setSize(new Dimension(500, 500));
		this.setLayout(new GridLayout(13, 2));
		addLabel(studentID, sID);
		addLabel(date, datei);
		addLabel(startStation, sStation);
		addLabel(endStation, eStation);
		addLabel(trainID, tID);
		addLabel(ticketnum, ticketn);

		this.add(Null1);
		this.add(Null2);

		Jbtn_YES.addActionListener(hbtHandler);
		this.add(Jbtn_YES);

		Jbtn_NO.addActionListener(hbtHandler);
		this.add(Jbtn_NO);

		this.add(Null3);
		this.add(Null4);

		Jbtn_PRINT.addActionListener(hbtHandler);
		this.add(Jbtn_PRINT);

		this.add(ticketNO);

		this.add(printExplain1);
		this.add(printExplain2);
		Jbtn_CANCEL.addActionListener(hbtHandler);
		this.add(Jbtn_CANCEL);

		cancelID.setSize(150, 20);
		this.add(cancelID);

		this.add(cancelExplain1);
		this.add(cancelExplain2);

	}

	public void addLabel(JLabel label, JTextField textfeild) {
		label.setPreferredSize(new Dimension(150, 20));
		label.setFont(new Font("Serif", Font.BOLD, 16));
		this.add(label);

		textfeild.setSize(150, 20);
		this.add(textfeild);
	}

	public class ButtonHandler extends JButton implements ActionListener {
		public void actionPerformed(ActionEvent evtE) {
			if (evtE.getSource() == Jbtn_YES) {
				try {

					tickets.addAll(service.order(sID.getText(),
							df.parse(datei.getText()), sStation.getText(),
							eStation.getText(),
							Integer.parseInt(tID.getText()),
							Integer.parseInt(ticketn.getText()))); // No error

				} catch (TicketOrderException e) {
					System.err.print(e.getMessage());

				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Y");
			} else if (evtE.getSource() == Jbtn_NO) {
				sID.setText("");
				datei.setText("");
				sStation.setText("");
				eStation.setText("");
				tID.setText("");
				ticketn.setText("");
				System.out.println("N");
			} else if (evtE.getSource() == Jbtn_PRINT) {
				String[] printIndex = ticketNO.getText().split("-");
				if (printIndex.length == 1) {
					try {
						tickets.get(Integer.parseInt(printIndex[0]) - 1).saveTicket();
					} catch (NumberFormatException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					int from = Integer.parseInt(printIndex[0]);
					int to = Integer.parseInt(printIndex[1]);
					try {
						service.saveTickets(from, to);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else if (evtE.getSource() == Jbtn_CANCEL) {
				String[] cancelIndex = cancelID.getText().split("-");
				if (cancelIndex.length == 1) {
					tickets.get(Integer.parseInt(cancelIndex[0]) - 1).cancel();
					tickets.get(Integer.parseInt(cancelID.getText()))
							.printTicket();
				} else {
					for (int i = 0; i < cancelIndex.length; i++) {
						tickets.get(
								Integer.parseInt(cancelIndex[cancelIndex.length - 1]) - 1)
								.cancel();
						tickets.get(
								Integer.parseInt(cancelIndex[cancelIndex.length - 1]) - 1)
								.printTicket();
					}

				}

			}
		}
	}
}
