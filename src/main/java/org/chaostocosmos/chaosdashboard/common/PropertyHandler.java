package org.chaostocosmos.chaosdashboard.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JOptionPane;

public class PropertyHandler {
    private static File propertyPath = null;
    
    private static Hashtable<String, PropertyStream> pStream;
    
    private static IPropertyChangeListener[] listeners;
    
    private String propertyExt;
    
    private String propertyPathStr;
    
    private static String charsetName;
    
    public PropertyHandler(String propertyPath) {
    	this(propertyPath, "properties", true);
    }    
    
    public PropertyHandler(String propertyPath, String propertyExt) {
        this(propertyPath, propertyExt, true);
    }
    
    public PropertyHandler(String propertyPath, String propertyExt, boolean autoLoad) {
    	this(propertyPath, propertyExt, autoLoad, System.getProperty("file.encoding"));
    }
    
    public PropertyHandler(String propertyPath, String propertyExt, boolean autoLoad, String charsetName_) {
    	this.propertyExt = propertyExt;
        this.propertyPathStr = propertyPath;
        charsetName = charsetName_;
        init();
        if(autoLoad) {
            load();
        }    	
    }
    
    private void init() {
        listeners = new IPropertyChangeListener[0];                
    	propertyPath = new File(this.propertyPathStr);
        File[] fileList = propertyPath.listFiles();
        pStream = new Hashtable<String, PropertyStream>();
        for(int i=0; i<fileList.length; i++) {
            if(fileList[i].isFile()) {
                String filename = fileList[i].getName();
                String ext = filename.substring(filename.indexOf(".")+1);
                if(ext.equals(this.propertyExt)) {
                    pStream.put(filename, new PropertyStream(fileList[i]));
                }
            }
        }
    }    
    
    public static void load() {
    	for(PropertyStream ps : pStream.values()) {
    		ps.read();
    	}
    }

    public static void reLoad() {
    	load();
    }
    
    public static String getProperty(String filename, String key) {
        Properties p = getProperties(filename);
        return p.getProperty(key);
    }

    public static Object[] getPropertyValues(String fileName, String key) {
        return getPropertyValues(fileName, key, ",");
    }    

    public static Object[] getPropertyValues(String filename, String key, String delim) {
        Properties p = getProperties(filename);
        return getPropertyValues(p, key, delim);
    }
    
    public static Object[] getPropertyValues(Properties p, String key, String delim) {
        String value = p.getProperty(key);
        if(value != null) {
        	return getObjectsFromString(value, delim);
        }
        throw new IllegalArgumentException("No property value found.  key : "+key);
    }
    
    public static Object[] getObjectsFromString(String str, String delim) {
        if(str != null) {
            StringTokenizer st = new StringTokenizer(str, delim);
            if(st.countTokens() > 0) {
                String[] tokens = new String[st.countTokens()];
                for(int i=0; i<tokens.length; i++) {
              		tokens[i] = st.nextToken().trim();
                }
                return tokens;
            } else {
            	return null;
            }
        }
        throw new NullPointerException();
    }

    public static Properties getProperties(String filename) {
        Properties p  = null;
        try {
            PropertyStream ps = getPropertyStream(filename);
            p = ps.getProperties();
        } catch(IOException e) {
        	e.printStackTrace();
        }
        return p;
    }
    
    public static PropertyStream getPropertyStream(String filename_) throws IOException {
        String filename = filename_.toLowerCase();
        PropertyStream ps = pStream.get(filename);
        if(ps == null) {
        	throw new IOException(filename+" not found.");
        }
        return ps;
    }
    
    public static void setProperty(String filename, String key, String value) {
    	try {
    		PropertyStream ps = getPropertyStream(filename);
    		ps.replaceProperty(key, value);
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public static void printProperty(String filename) {
    	try {
			PropertyStream ps = getPropertyStream(filename);
			String text = ps.getAllText();
			System.out.println(text);
		} catch (IOException e) {
			e.printStackTrace();
		}    	
    }
        
    public static String getPropertyPath() {
        return propertyPath.getAbsolutePath();
    }
    
    public void dispatchPropertyChangeEvent(Object src, Properties prop, String key, String val, int appMode) {
        for(int i=0; i<listeners.length; i++) {
            listeners[i].propertyValueChanged(new PropertyChangeEvent(src, prop, key, val, appMode));
        }
    }
    
    public static void addPropertyChangeListener(IPropertyChangeListener listener) {
        IPropertyChangeListener[] tmp = new IPropertyChangeListener[listeners.length+1];
        int i;
        for(i=0; i<listeners.length; i++) {
            tmp[i] = listeners[i];
        }
        tmp[++i] = listener;
        listeners = tmp;
    }
    
    public static void removePropertyChangeListener(Object listener) {        
        int i;
        for(i=0; i<listeners.length; i++) {
            if(listeners[i].hashCode() == listener.hashCode()) {
                IPropertyChangeListener[] tmp = new IPropertyChangeListener[listeners.length-1];
                int k;
                for(k=0; k<i; k++) { 
                	tmp[k] = listeners[k];
                }
                for(k=i+1; k<listeners.length; k++) {
                	tmp[k-1] = listeners[k];
                }
                listeners = tmp;
                break;
            }
        }
    }
    
    public static String getEncoding() {
    	return charsetName;
    }
    
    public class PropertyStream {
    	
    	private File propFile;
        
    	private BufferedReader br;
        
    	private BufferedWriter bw;
        
    	private Properties prop = null;
        
        private PropertyStream(File f) {
            this.propFile = f;
        }
        
        public void read() {
        	try	{
        		this.prop = readProperties();
        	} catch(IOException e) {
        		e.printStackTrace();
        	}
        }
        
        public Properties readProperties() throws IOException {
            String line = "";
            Properties property = new Properties();
            this.br = new BufferedReader(new InputStreamReader(new FileInputStream(this.propFile), charsetName));
            while((line = this.br.readLine()) != null) {
                int idx = line.indexOf("#");
                if(idx != -1) {
                    line = line.substring(0, idx);
                }
                idx = line.indexOf("=");
                if(idx != -1) {
                    String key = line.substring(0, line.indexOf("=")).trim();
                    String value = line.substring(line.indexOf("=")+1).trim();
                    property.setProperty(key, value);
                }
            }
            this.br.close();
            return property;
        }
        
        public void replaceProperty(String key, String value) throws IOException {        	
            String text = getAllText();
            String rText = "";
            String line = "";
            String body = "";
            String comment = "";            
            String ls = System.getProperty("file.separator");
            int idx;
            while((idx = text.indexOf(ls)) != -1) {
                line = text.substring(0, idx);
                text = text.substring(idx+1);
                idx = line.indexOf("#");
                if(idx != -1) {
                    comment = line.substring(idx);
                    body = line.substring(0, idx);
                } else {
                	body = line;
                }
                idx = body.indexOf("=");
                if(idx != -1) {
                    String key_ = body.substring(0, idx).trim();
                    if(key_.equals(key.trim())) {
                        line = ls + key + " = " + value + " " + comment;
                    }
                }
                rText += line;
                body = "";
                line = "";
                comment = "";
            }
            this.bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.propFile), charsetName));
            bw.write(rText);
            bw.close();
        }

        public String getAllText() throws IOException {
            String allText = "";
            String line;
            br = new BufferedReader(new InputStreamReader(new FileInputStream(this.propFile), charsetName));
            while((line = br.readLine()) != null) {
                allText += line + System.getProperty("file.separator");
            }
            br.close();
            return allText;
        }
        
        public String getProperty(String key) throws IOException {
            return this.prop.getProperty(key);
        }
        
        public void setProperty(String key, String value) throws IOException {
            this.prop.setProperty(key, value);
        }
        
        public Properties getProperties() throws IOException {
            return this.prop;
        }
        
        public long length() {
            return this.propFile.length();
        } 

        public String getName() {
            return this.propFile.getName();
        }
        
        public File getFile() {
            return this.propFile;
        }
    }    
}


