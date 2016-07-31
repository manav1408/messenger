import javax.swing.JFrame;

public class ClientTest {

	public static void main(String[] args){
		Client abc;
		abc=new Client("127.0.0.1");
		abc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		abc.startRunning();
	}
}
