package NIO;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class Client {
	public static void main(String[] args) {
		SocketChannel socketChannel = null;
		ByteBuffer buffer = ByteBuffer.allocate(100);
		try {
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			socketChannel.connect(new InetSocketAddress("localhost", 8989));
			if(socketChannel.finishConnect()){
				int i = 0;
				while(true){
					TimeUnit.SECONDS.sleep(1);
					String info = "I'm " + i++ +"-th infomation from client";
					buffer.clear();
					buffer.put(info.getBytes());
					buffer.flip();
					if(buffer.hasRemaining()){
						System.out.println(buffer);
						socketChannel.write(buffer);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(socketChannel != null){
					socketChannel.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
