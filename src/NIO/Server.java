package NIO;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
/**
 * socket服务器，利用传统IO
 * @author sht
 *
 */
public class Server {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		InputStream in = null;
		try {
			serverSocket = new ServerSocket(8989);
			int recvSize = 0;
			byte[] recvBuf = new byte[100];
			while(true){
				Socket clntSocket = serverSocket.accept();
				SocketAddress address = clntSocket.getRemoteSocketAddress();
				System.out.println("Handling client at " + address);
				in = clntSocket.getInputStream();
				while((recvSize = in.read(recvBuf)) != -1){
					byte[] temp = new byte[recvSize];
					System.arraycopy(recvBuf, 0, temp, 0, recvSize);
					System.out.println(new String(temp));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(serverSocket != null){
					serverSocket.close();
				}
				if(in != null){
					in.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
	}
}
