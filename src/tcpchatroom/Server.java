/**
 * 
 */
package tcpchatroom;

/**
 * @author Tao
 *
 */
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * ����TCP�Ĳ������߳�������
 * 
 * ���������ڳ���
 * 
 * @author ղ���
 *
 */
public class Server extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label_localPort = new JLabel("���ض˿ڣ�");
	private JTextField textField_localPort = new JTextField(5);
	private JButton button_bind = new JButton("��������");

	/* ��Ϣ���� */
	private JTextArea contentWindow = new ContentArea();
	private  JScrollPane scrollPane = new JScrollPane(contentWindow);

	public Server(){
		setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
		setTitle("TCP Chat Room Server");
		setSize(600, 400);
		setResizable(false);

		contentWindow.setLineWrap(true);
		contentWindow.setEditable(false);

		JPanel p1 = new JPanel();
		p1.setSize(600, 40);
		p1.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
		p1.add(label_localPort);
		p1.add(textField_localPort);
		p1.add(button_bind);

		add(p1);
		add(scrollPane);

		ButtonBindListener bindListener = new ButtonBindListener();
		button_bind.addActionListener(bindListener);

		//ȱʡ�˿�
		textField_localPort.setText("10000");
	}

	class ContentArea extends JTextArea{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		protected ContentArea(){
			super(15, 40);
		}
	}

	class ButtonBindListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int port = Integer.parseInt(textField_localPort.getText());
			//�½������̣߳����տͻ�����������
			ListenerThread thread = new ListenerThread(contentWindow, port);
			thread.start();

			//����������ֹ�޸Ĳ���
			button_bind.setEnabled(false);
			button_bind.setText("�ѿ���");
			textField_localPort.setEditable(false);
		}

	}

	public static void main(String[] args) {
		Server chatRoom = new Server();
		chatRoom.setLocationRelativeTo(null);
		chatRoom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chatRoom.setVisible(true);
	}
}