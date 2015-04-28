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
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JTextArea;

/**
 * ����TCP�Ĳ������߳�������
 * 
 * ���������׽������߳�
 * ��Ӧÿһ���ͻ���
 * 
 * @author ղ���
 *
 */

public class ServerThread extends Thread{
	private Socket socket;
	private BufferedReader in;
	private JTextArea textArea;
	private ArrayList<Socket> clientList;

	public ServerThread(ArrayList<Socket> clientList, JTextArea area, Socket sock){
		socket = sock;
		textArea = area;
		this.clientList = clientList;
	}

	public void run(){
		try {
			while(true){
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String clientSentence = in.readLine();
				//��ʾ�ͻ��˷�������Ϣ
				textArea.append(clientSentence + "\n");
				//Ⱥ�������пͻ���
				for(Socket client : clientList){
					PrintWriter out = new PrintWriter(client.getOutputStream(),true);
					out.println(clientSentence);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}