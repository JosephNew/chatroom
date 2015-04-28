/**
 * 
 */
package tcpchatroom;

/**
 * @author Tao
 *
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JTextArea;

/**
 * ����TCP�Ĳ������߳�������
 * 
 * �������˼����˿��߳�
 * ����ָ���˿ڣ����յ��µ�������������һ��ServerThread���̴߳���
 * 
 * @author ղ���
 *
 */

public class ListenerThread extends Thread{
	private int port;
	private ServerSocket ss;
	private JTextArea textArea;
	private ArrayList<Socket> clientList;

	public ListenerThread(JTextArea area, int port){
		this.port = port;
		this.textArea = area;
		this.clientList = new ArrayList<Socket>();
	}

	public void run(){
		try {
			ss = new ServerSocket(port);
			while(true){
				Socket socket = ss.accept();
				//����ͻ��б�
				clientList.add(socket);
				ServerThread thread = new ServerThread(clientList, textArea, socket);
				thread.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}