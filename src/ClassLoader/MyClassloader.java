package ClassLoader;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

public class MyClassloader extends ClassLoader {

	public static ConcurrentHashMap<String,Class<?>> classes = new ConcurrentHashMap<String, Class<?>>();
	
	public static MyClassloader instance = new MyClassloader();
	
	public MyClassloader(){
		super(Thread.currentThread().getContextClassLoader());
	}
	
	public Class<?> load(String name,byte[] data,boolean resolve){
		Class<?> klass = defineClass(name, data,0, data.length);
		if(resolve){
			resolveClass(klass);
		}
		classes.put(name, klass);
		return klass;
	}

	@Override
	public  Class<?> loadClass(String name, boolean resolve)
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		Object value = classes.get(name);
		if(value !=null){
			Class<?> klass = (Class<?>) value;
			if(resolve){
				resolveClass(klass);
			}
			return klass;
		}else{
			byte[] data = read(findClassFile(name));//读取.class文件
			if(data == null){
				return super.loadClass(name, resolve);
			}else{
				
                synchronized (this) {
                	Object cc = classes.get(name); // 检查缓存  	
                	if (cc != null) {  
                		return (Class<?>) cc;  
                	} else  
                		return instance.load(name, data, resolve); // 自己load类文件  
				} 
               
			}
		}
		
	}
	
	
	
	public byte[] read(File file){
		return null;
	}
	
	public File findClassFile(String name){
		return null;
	}
	
	

}
