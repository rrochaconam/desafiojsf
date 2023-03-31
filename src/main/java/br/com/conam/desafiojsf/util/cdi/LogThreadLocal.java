package br.com.conam.desafiojsf.util.cdi;

public class LogThreadLocal {
    
	private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();


    private LogThreadLocal() {
    	
    }
    
    public static String getChave() {
    	return threadLocal.get();
    }
    
    public static void setChave(String chave) {
    	threadLocal.set(chave);
    }

}
