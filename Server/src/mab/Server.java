package mab;

import java.net.*;  
import java.io.*;  
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

class Server extends JFrame{  
	private JTextField userText;
	private JTextArea chatWindow;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;
	
	public Server()
	{
		super("messenger");
		userText=new JTextField();
		userText.setEditable(false);
		userText.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						sendMessage(event.getActionCommand());
						userText.setText("");
						
					}
					
				}
			);
		
		add(userText,BorderLayout.NORTH);
		chatWindow=new JTextArea();
		add(new JScrollPane(chatWindow));
		setSize(300,150);
		setVisible(true);
	}
	
	public void startRunning(){
		try{
			server=new ServerSocket(6789,100);
			while(true){
				
				try{
					waitForConnection();
					setupStreams();
					whileChatting();
					
				}catch(EOFException eofException){
					showMessage("\n server end");
					
				}
				finally{
					
					close();
				}
				
			}
			
		}catch(IOException ioException){
			
			ioException.printStackTrace();
		}
		
		
	}

	private void showMessage(final String text) {
		SwingUtilities.invokeLater(
				new Runnable(){
					public void run(){
						chatWindow.append(text);
					}
					
				}
			);
		
	}

	private void close() {
		showMessage("\n closing");
		ableToType(false);
		try{
			output.close();
			input.close();
			connection.close();
		}catch(IOException ioException){
			ioException.printStackTrace();
			
		}
		
	}

	private void ableToType(final boolean tof) {
		SwingUtilities.invokeLater(
				new Runnable(){
					public void run(){
						userText.setEditable(tof);
					}
					
				}
			);
		
	}

	private void whileChatting() throws IOException {
	String message="connected";
	sendMessage(message);
	ableToType(true);
	do{
		try{
			message=(String)input.readObject();
			showMessage("\n" +message);
		}catch(ClassNotFoundException classNotFoundException){
			
			showMessage("\n not identified");
		}
		
	}while(!message.equals("CLIENT - END"));
		
	}

	private void sendMessage(String message) {
		try{
			output.writeObject("SERVER - " + message);
			output.flush();
			showMessage("SERVER - "+ message);
		}catch(IOException ioException){
			
			chatWindow.append("\n error");
		}
		
	}

	private void setupStreams() throws IOException{
		output=new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input=new ObjectInputStream(connection.getInputStream());
		showMessage("streams setup \n");
		
	}

	private void waitForConnection() throws IOException{
		showMessage("waiting.. \n");
		connection =server.accept();
		showMessage("connected to "+connection.getInetAddress().getHostName());
	
		
	}
}  