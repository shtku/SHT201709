package ClassLoader;

import java.net.URL;

import sun.misc.Launcher;

/**
 * ����BootstrapClassLoader��������Щjava������
 * @author Administrator
 *
 */
public class Test {
	public static void main(String[] args) {
		 URL[] urls  = Launcher.getBootstrapClassPath().getURLs();  
        for (int i = 0; i < urls.length; i++) {    
            System.out.println(urls[i].toExternalForm());    
        }   
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
	}
}
