package NIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * ���ô�ͳIO��ȡ�ļ�
 * @author sht
 *
 */
public class NormalIO {
	public static void main(String[] args) {
		String path = "src\\NIO\\NIO.txt";
		//NormalIO.readFile1(path);
		//NormalIO.readFile2(path);
		NormalIO.readFile3(path);
		
	}
	
	/**
	 * �ֽ�����ȡ��ӡ������̨
	 * @param path
	 */
	public static void readFile1(String path){
		File file = new File(path);
		try {
			FileInputStream fins = new FileInputStream(file);
			byte[] buff = new byte[50];
			int read = fins.read(buff);
			
			while(read !=-1){
				for(int i = 0; i < buff.length; i++){
					System.out.print((char)buff[i]);
				}
				read = fins.read(buff);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �ַ�����ȡ�ļ���ӡ������̨
	 * @param path
	 */
	public static void readFile2(String path){
		File file = new File(path);
		try {
			InputStream fins = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fins); 
			BufferedReader br = new BufferedReader(isr);
			while(br.ready()){
				 int c = br.read();  
				 System.out.print((char)c);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ����NIO��ȡ�ļ�������̨
	 * ����NIO�ļ����ؼ��ʣ�Channel Buffer Selector position limit capacity mark
	 * @author sht
	 * @param path
	 */
	public static void readFile3(String path){
//		RandomAccessFile aFile = null;
		FileInputStream aFile = null;
		try {
//			aFile = new RandomAccessFile(path, "rw");
			aFile = new FileInputStream(path);
			FileChannel fileChannel = aFile.getChannel();
			ByteBuffer buf = ByteBuffer.allocate(100);
			int bytesRead = fileChannel.read(buf);
			System.out.println();
			System.out.print("position:" + buf.position());
			System.out.print("  limit:" + buf.limit());
			System.out.println("  capacity:" + buf.capacity());
			while(bytesRead != -1){
				buf.flip();
				System.out.println("ִ��filp()������");
				System.out.print("position:" + buf.position());
				System.out.print("  limit:" + buf.limit());
				System.out.println("  capacity" + buf.capacity());
				while(buf.hasRemaining()){
					System.out.print((char)buf.get());
				}
				buf.compact();
				bytesRead = fileChannel.read(buf);
				System.out.println();
				System.out.print("position:" + buf.position());
				System.out.print("  limit:" + buf.limit());
				System.out.println("  capacity:" + buf.capacity());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(aFile !=null){
					aFile.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} 
	}
	
}
