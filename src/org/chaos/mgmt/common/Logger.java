package org.chaos.mgmt.common;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.sun.org.apache.bcel.internal.Repository;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;

/**  
 * Logger : ·Î°Å Å¬·¡½º
 * Description : 
 * 
 *  
 * Modification Information  
 *  ---------   ---------   -------------------------------
 *  20140207	9ins		ìµœì´ˆ?‘ì„±
 *  
 * @author 9ins
 * @since 20140207
 * @version 1.0
 * @see Copyright ChaosToCosmos
 * 
 */
public class Logger {

	public static final boolean IS_STD_OUT = true;
	
	public static final int alignCharNum = 120;
	
    private PrintWriter log;
    
    private PrintStream ps;
    
    private String encoding;
    
    private long startTime;
    
    private long logInterval;
    
    private String fs = System.getProperty("file.separator");
    
    private String logSuffix = ".log";
    
    private File logFile;
    
    private String logName;

    private static Logger logger; 
    
    public static Logger getInstance() { 
    	if(logger == null)
    		logger = new Logger(24);
    	return logger;
    }
    
    public static Logger getInstance(int hour) {
    	return getInstance(24, "LOG", "UTF-8");
    }
    
    public static Logger getInstance(String logPath) {
    	return getInstance(24, "LOG", "UTF-8");
    }
    
    public static Logger getInstance(int hour, String logPath) {
    	return getInstance(hour, logPath, "UTF-8");
    }
    
    public static Logger getInstance(int hour, String logPath, String encoding) {
    	if(logger == null)
    		logger = new Logger(24, logPath, "UTF-8");
    	return logger;    	
    }
    
    private Logger(int hour) {
    	this((long)hour*60*60*1000);
    }   
    
    private Logger(long interval) {
    	this(interval, "LOG");
    }
    
    private Logger(int hour, String logPath) {
    	this((long)hour*60*60*1000, logPath);
    }
    
    private Logger(long interval, String logPath) {
    	this(interval, logPath, "UTF-8");
    }
    
    private Logger(int hour, String logPath, String encoding) {
    	this((long)hour*60*60*1000, logPath,  encoding);
    }
    
    private Logger(long startMillis, String logPath, String encoding) {
    	init(startMillis, logPath, encoding);
    }
    
    private void init(long millis, String logPath, String enc) {
    	this.logInterval = millis;
    	this.startTime = System.currentTimeMillis();
    	this.logName = logPath;
    	this.logFile = new File(logPath+logSuffix);
    	this.encoding = enc;
    	this.ps = System.out;
  		createLogFile(startTime);
    }    
    
    private void createLogFile(long millis) {
    	try {
    		//File currFile = new File(this.logFile.getPath());    		
    		if(this.logFile.exists()) {
    			if(log != null)
    				log.close();
        		if(!this.logFile.renameTo(new File(getLogFileName(millis)))) {        			
        			throw new IOException("Can't change log file name. BEFORE : "+this.logFile.getAbsolutePath());
        		}
    		} else {
    			if(!this.logFile.createNewFile()) {
    				throw new IOException("Can't create new file : "+this.logFile.getAbsolutePath());
    			}
    		}
    		log = new PrintWriter(new OutputStreamWriter(new FileOutputStream(this.logFile), this.encoding), true);
    	} catch(IOException e) { 
    		e.printStackTrace();
		}    	
    }
    
    public String getLogFileName(long millis) throws IOException {    	
    	String date = UtilBox.getFormattedDate(millis, 8);
    	String aPath = logFile.getCanonicalPath();
    	String path = aPath.substring(0, aPath.lastIndexOf(fs));
        return path+fs+logName+"_"+date+logSuffix;
    }
    
    private void compare() {
    	long currentMillis = System.currentTimeMillis();
    	long elapse = currentMillis-this.startTime;
    	if(elapse >= this.logInterval) {
    		System.out.println("elapse time : "+elapse+"   interval : "+this.logInterval);
    		this.startTime = currentMillis;
   			createLogFile(this.startTime);
    	}
    }    
    
    public void print(String msg, Class cls) {
    	print(msg, cls, true);
    }
    
    public void print(String msg, Class cls, boolean isSysOut) {
    	synchronized(log) {
            compare();
        	String msgStr = alignString(msg)+" class : "+cls.getName()+"\tLOG LINE : "+(new Exception(cls.getName())).getStackTrace()[2].getLineNumber();        	
            if(isSysOut && IS_STD_OUT) {
            	printStdOut(msgStr);
            }
            log.print(msgStr);
    	}
    }
    
    public void println(String msg, Class cls) {
    	println(msg, cls, true);
    }

    public void println(String msg, Class cls, boolean isSysOut) {
        synchronized (log) {
            compare();
            String msgStr = alignString(msg)+" class : "+cls.getName()+"\tLOG LINE : "+(new Exception(cls.getName())).getStackTrace()[2].getLineNumber();;
            if(isSysOut && IS_STD_OUT) {
            	printlnStdOut(msgStr);
            }
            log.println(msgStr);
    	}
    }    
    
    public void println(Throwable e, Class cls) {
    	println(e, cls, true);
    }
    
    public void println(Throwable e, Class cls, boolean isSysout) {
        synchronized(log) {
            compare();
            if(e != null) {            	
            	String title = alignString(e.getMessage())+" class : "+cls.getName()+"\tLOG LINE : "+(new Exception(cls.getName())).getStackTrace()[2].getLineNumber();
              	if(isSysout && IS_STD_OUT) {
              		printlnStdOut(title);
              	}
           		log.println(title);
                StackTraceElement[] elements = e.getStackTrace();
                for(int i=0; i<elements.length; i++) {
                	String msg = "\tat "+elements[i].toString();
                   	if(isSysout && IS_STD_OUT) {
                   		ps.println(msg);
                   	}
                	log.println(msg);
                }
            }
    	}
    }
    
    public String alignString(String msg) {    	
    	String msgStr = "["+ new Date() + "] " + msg;
    	int tab = alignCharNum - msgStr.length();
    	for(int i=0; i<tab; i++)
    		msgStr += " ";
    	return msgStr;
    }

	public void printStdOut(String str) {
		ps.print(str);
	}
	
	public void printlnStdOut(String str) {
		ps.println(str);
	}    
}
