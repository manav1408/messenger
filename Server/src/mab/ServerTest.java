package mab;

import javax.swing.JFrame;

public class ServerTest {
	public static void main(String[] args){
		Server xyt=new Server();
		xyt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		xyt.startRunning();
		
	}

}
