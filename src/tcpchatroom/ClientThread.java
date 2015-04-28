/**
 * 
 */
package tcpchatroom;

/**
 * @author Tao
 *
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JTextArea;

/**
 * ����TCP�Ĳ������߳�������
 * 
 * �ͻ������߳�
 * ���շ������˷�������Ϣ����ʾ
 * 
 * @author ղ���
 *
 */

public class ClientThread extends Thread{
	private Socket socket;
	private BufferedReader in;
	private JTextArea textArea;

	public ClientThread(JTextArea area, Socket socket){
		this.textArea = area;
		this.socket = socket;
	}

	public void run(){
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while(true){
				//ѭ����ȡ
				String clientSentence = in.readLine();
				//��ʾ
				textArea.append(clientSentence + "\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}