package org.chaos.mgmt.common;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.chaos.mgmt.mbeans.MessageInfo;


public class UtilBox {

	protected int intValue(float fValue)
    {
        int iValue = (int)(fValue+0.5);
        return iValue;
    }
    
    public static boolean randomBoolean()
    {
         int n = randomInt(1, 2);
         if(n == 1) {
             return true;
         } else {
             return false;
         }
    }

    public static int randomSelect(int[] arr)
    {
         return arr[randomInt(0, arr.length)];
    }

    public static int randomInt(int lower, int upper)
    {
         if(lower == upper) {
        	 return lower;
         }
         int max = Math.max(lower, upper);
         int min = Math.min(lower, upper);
         int gap = max - min + 1;
         double ran = Math.random();
         int num = (int)(ran*gap)+min;
         return num;
    }

    public static double randomFloat(float lower, float upper)
    {
         if(lower == upper) {
        	 return lower;
         }
         float max = Math.max(lower, upper);
         float min = Math.min(lower, upper);
         float gap = max - min + 1;
         float ran = (float)(Math.random()*gap)+min;
         return ran;
    }

    public static double randomDouble(double lower, double upper)
    {
         if(lower == upper) {
        	 return lower;
         }
         double max = Math.max(lower, upper);
         double min = Math.min(lower, upper);
         double gap = max - min;
         double ran = Math.random()*gap+min;
         return ran;
    }

    public static long randomLong(long lower, long upper)
    {
         if(lower == upper) {
        	 return lower;
         }
         long max = Math.max(lower, upper);
         long min = Math.min(lower, upper);
         long gap = max - min + 1;
         long ran = (long)(Math.random()*gap)+min;
         return ran;
    }
    
    public static String getCurrentDate()
    {
        long time = Calendar.getInstance().getTime().getTime();
        String date = getFormattedDate(time, 0);
        return date;
    }

    public static String getFormattedDate(long millis, int type)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(millis));
        String year = cal.get(Calendar.YEAR)+"";
        int m = cal.get(Calendar.MONTH)+1;
        String month = (m<10)?"0"+m:m+"";
        int d = cal.get(Calendar.DAY_OF_MONTH);
        String day = (d<10)?"0"+d:d+"";
        int h = cal.get(Calendar.HOUR_OF_DAY);
        String hour = (h<10)?"0"+h:h+"";
        int mi = cal.get(Calendar.MINUTE);
        String minute = (mi<10)?"0"+mi:mi+"";
        int s = cal.get(Calendar.SECOND);
        String second = (s<10)?"0"+s:s+"";
        String date = null;
        switch(type)
        {
            case 0:
            	date = year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
               	break;
            case 1:
            	date = year+"-"+month+"-"+day+" "+hour+"-"+minute+"-"+second;
               	break;
            case 2:
            	date = year+"/"+month+"/"+day+" "+hour+":"+minute+":"+second;
               	break;
            case 3:
            	date = year+"/"+month+"/"+day+" "+hour+"-"+minute+"-"+second;
            	break;
            case 4:
            	date = year+month+day+hour+minute+second;
            	break;
            case 5:
            	date = year+"��"+month+"��"+day+"��"+hour+"��"+minute+"��"+second+"��";
            	break;
            case 6:
            	date = year+"."+month+"."+day+" "+hour+":"+minute+":"+second;
            	break;
            case 7:
            	date = year+month+day;
            	break;
            case 8:
            	date = year+"_"+month+"_"+day+"_"+hour+"_"+minute+"_"+second;
            	break;
            default:
            	date = year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
        }
        return date;
    }

    public static String getFormattedDate(String dateString, int type)
    {
        long millis = getMillisFromString(dateString, 4);
        return getFormattedDate(millis, type);
    }

     public static long getMillisFromString(String dateString, int type)
     {
         long time;
         if(dateString.length() != 14 && dateString.length() != 8) {
            return System.currentTimeMillis();
         }
         
         if(type == 4)
         {
            int year = Integer.parseInt(dateString.substring(0, 4));
            int month = Integer.parseInt(dateString.substring(4, 6));
            int day = Integer.parseInt(dateString.substring(6, 8));
            int hour = Integer.parseInt(dateString.substring(8, 10));
            int minute = Integer.parseInt(dateString.substring(10, 12));
            int second = Integer.parseInt(dateString.substring(12, 14));
            time = getTimeMillis(year, month, day, hour, minute, second);
         }
         else if(type == 7)
         {
            int year = Integer.parseInt(dateString.substring(0, 4));
            int month = Integer.parseInt(dateString.substring(4, 6));
            int day = Integer.parseInt(dateString.substring(6, 8));
            int hour = 0;
            int minute = 0;
            int second = 0;
            time = getTimeMillis(year, month, day, hour, minute, second);
         }
         else
         {
             time = getMillisFromString(dateString);
         }
         return time;
    }

    public static long getMillisFromString(String dateString)
    {
        long time;
        StringTokenizer st = new StringTokenizer(dateString, "����Ͻú���/-:. ");
        if(st.countTokens() != 6) {
            throw new IllegalArgumentException("��¥����� �̻��մϴ�.");
        }
        int year = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        int second = Integer.parseInt(st.nextToken());
        time = getTimeMillis(year, month, day, hour, minute, second);
        return time;
    }

    public static long getTimeMillis(int year, int month, int day, int hour, int minute, int second)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month-1, day, hour, minute, second);
        long millis = cal.getTime().getTime();
        return millis;
    }

    public static int getCurrentExerTime(long time) {
    	synchronized(UtilBox.class) {
        	Calendar cal = Calendar.getInstance();
        	cal.setTimeInMillis(time);
        	int hour = cal.get(Calendar.HOUR_OF_DAY)*60*60;
        	int minute = cal.get(Calendar.MINUTE)*60;
        	int second = cal.get(Calendar.SECOND);
           	int ret = hour+minute+second;
        	return ret;
    		
//        	int ret = -1;
//        	Calendar calendar = Calendar.getInstance();
//        	int current = calendar.get(Calendar.DAY_OF_MONTH);
//        	
//        	calendar = Calendar.getInstance();
//        	calendar.setTimeInMillis(time);
//        	int exer = calendar.get(Calendar.DAY_OF_MONTH);
//        	
//        	Calendar cal = Calendar.getInstance();
//        	cal.setTimeInMillis(time);
//        	int hour = cal.get(Calendar.HOUR_OF_DAY)*60*60;
//        	int minute = cal.get(Calendar.MINUTE)*60;
//        	int second = cal.get(Calendar.SECOND);
//        	if(current == exer) {
//            	ret = hour+minute+second;
//        	} else {
//            	ret = hour+minute+second+86400;
//        	}
//        	return ret;
    	}
    }

    public static long convertRealTime(int secondsOfDay) {
    	synchronized(UtilBox.class) {    		
    		Calendar cal = Calendar.getInstance();
    		int currentSeconds = (cal.get(Calendar.HOUR_OF_DAY)*60*60)+(cal.get(Calendar.MINUTE)*60)+cal.get(Calendar.SECOND);
    		if(secondsOfDay > currentSeconds) {
    			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-1);
    		}   
    		cal.set(Calendar.HOUR_OF_DAY, 0);
      		cal.set(Calendar.MINUTE, 0);
       		cal.set(Calendar.SECOND, 0);
       		cal.set(Calendar.MILLISECOND, 0);
       		long millis = cal.getTimeInMillis()+secondsOfDay*1000;    		
    		return millis;		
    	}
    }
    
    public static void setSystemTimeToInputTime(int year, int month, int day, int hour, int minute, int second) throws IOException
    {
        String command = "#date ";
        String timeString = getStr(month)+getStr(day)+getStr(hour)+getStr(minute)+year;
        Runtime.getRuntime().exec(command+timeString);
    }

    public static String getStr(int time)
    {
    	String tStr = "";
    	if(time < 10)
    		tStr = "0"+time;
    	return tStr;
    }   
    
    public static void setCenterScreen(Window win)
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = win.getSize();
        if(frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if(frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        win.setLocation((screenSize.width - frameSize.width) / 2 , (screenSize.height - frameSize.height)/ 2);
    }

    public static void showMessageBox(Window owner, String title, String message)
    {
        JOptionPane jop = new JOptionPane(message, JOptionPane.WARNING_MESSAGE);
        JDialog jd = jop.createDialog(owner, title);
        UtilBox.setCenterScreen(jd);
        jd.setVisible(true);
    }

    public static int getScreenWidth()
    {
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }

    public static String noSpace(String source_)
    {
    	String source = source_;
        if(source == null) {
            source = "";
        }
        int idx = -1;
        while ((idx=source.indexOf(" ")) != -1)
        {
            String str1 = source.substring(0, idx);
            String str2 = source.substring(idx+1, source.length());
            source = str1+str2;
        }
        return source;
    }

    public static String replaceString(String old, String new_, String s_)
    {
        String replaced = "";
        String s = s_;
        int idx;
        while((idx=s.indexOf(old)) != -1 )
        {
            replaced += s.substring(0, idx);
            replaced += new_;
            s = s.substring(idx+old.length());
        }
        replaced += s;
        return replaced;
    }

    public static String replaceToParams(String delim, String s_, Object[] params)
    {
    	String s = s_;
        String replaced = "";
        int idx;
        int idx_ = 0;
        int len = params.length;
        while((idx=s.indexOf(delim)) != -1 && idx_ < len)
        {
            replaced += s.substring(0, idx);
            replaced += params[idx_++];
            s = s.substring(idx+delim.length());
        }
        replaced += s;
        return replaced;
    }
    
    public static int getMaxLength(String[] strs) {
        if(strs == null || strs.length == 0) {
            return -1;
        }
        int max = 0;
        for(int i=0; i<strs.length; i++) {
            int len = strs[i].length();
            if(len > max) {
                max = len;
            }
        }
        return max;
    }

    public static Object[] getVectorToArray(Vector v) {
        Object[] objs = new Object[v.size()];
        for(int i=0; i<v.size(); i++) {
            objs[i] = v.elementAt(i);
        }
        return objs;
    }

    public static Vector getSelectedColumns(Vector data, int[] columns)
    {
        Vector v = new Vector();
        for(int i=0; i<data.size(); i++)
        {
            Object[] row = (Object[])data.elementAt(i);
            Object[] objs = new Object[columns.length];
            for(int k=0; k<objs.length; k++) {
                objs[k] = row[columns[k]];
            }
            v.add(objs);
        }
        return v;
    }
   
    public static Vector addVectorToVector(Vector v1, Vector v2)
    {
        Object[] o1 = v1.toArray();
        Object[] o2 = v2.toArray();
        Object[] o = new Object[o1.length+o2.length];
        System.arraycopy(o1, 0, o, 0, o1.length);
        System.arraycopy(o2, 0, o, o1.length, o2.length);
        Vector v = new Vector();
        for(int i=0; i<o.length; i++) {
            v.add(o[i]);
        }
        return v;
    }    
   
    public static Hashtable addHashtableToHashtable(Hashtable h1, Hashtable h2) {
    	Enumeration enu = h2.keys();
    	while(enu.hasMoreElements()) {
    		Object key = enu.nextElement();
    		h1.put(key, h2.get(key));
    	}
    	return h1;
    }
    
    public static int[] getObjectsToInts(Object[] values) {
    	int[] vals = new int[values.length];
    	for(int i=0; i<vals.length; i++)
    		vals[i] = Integer.parseInt((values[i]+"").trim());
    	return vals;
    }
    
    public static float[] getObjectsToFloats(Object[] values) {
    	float[] vals = new float[values.length];
    	for(int i=0; i<vals.length; i++)
    		vals[i] = Float.parseFloat((values[i]+"").trim());
    	return vals;
    }
    
    public static boolean isIn(int[] arr, int val) {
    	boolean is = false;
    	for(int i=0; i<arr.length; i++) {
    		if(arr[i] == val) {
    			is = true;
    			break;
    		}
    	}
    	return is;
    }
   
    public static void printHexCode(byte[] bytes) {
    	String hex = "";
        for(int i=0; i<bytes.length; i++)
        {
            String str = Integer.toHexString((int)bytes[i]).toUpperCase();
            hex += (str.length() == 1)?"0"+str+" ":str+" ";
        }
        System.out.println(hex);
    }

    public static String getStackTraceMsg(Throwable thro){
        String msg = thro.getMessage();
        StackTraceElement[] elements = thro.getStackTrace();
        for(int i=0; i<elements.length; i++) {
            msg += "\n"+elements[i].toString();
        }
        return msg;
    }
    
    public static byte[] getStringBytes(String str, String charsetType) throws IllegalCharsetNameException, UnsupportedCharsetException {
        Charset charset = Charset.forName(charsetType);
        //CharsetEncoder encoder = charset.newEncoder();
        ByteBuffer buff = charset.encode(str);
        byte[] bytes = buff.array();
        return bytes;
    }
    
    public static int getClassByteSize(Object obj) throws IllegalArgumentException, IllegalAccessException {
    	int size = 0;
		Field[] fields1 = obj.getClass().getSuperclass().getDeclaredFields();
		Field[] fields2 = obj.getClass().getDeclaredFields();
		Field[] fields = new Field[fields1.length+fields2.length];
		System.arraycopy(fields1, 0, fields, 0, fields1.length);
		System.arraycopy(fields2, 0, fields, fields1.length, fields2.length);
		for(int i=0; i<fields.length; i++) {
			if(fields[i].getName().equals("isMaster")) {
				continue;
			}
			if(fields[i].getType() == Byte.TYPE) {
				size += 1;
			} else if(fields[i].getType() == Short.TYPE) {
				size += 2;
			} else if(fields[i].getType() == Integer.TYPE) {
				size += 4;
			} else if(fields[i].getType() == Long.TYPE) {
				size += 8;
			} else if(fields[i].getType() == Float.TYPE) {
				size += 4;
			} else if(fields[i].getType() == Double.TYPE) {
				size += 8;
			} else if(fields[i].getType() == String.class) {
				size += ((String)fields[i].get(obj)).length();
			} else if(fields[i].getType() == Object.class) {
				size += getClassByteSize(fields[i].get(obj));
			}
		}
		return size;    	
    }
    
	public static Object setValues(Object obj, Object[] values) throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = obj.getClass().getFields();
		for(int i=0; i<fields.length; i++) {
			if(fields[i].getType().getName().equals("java.lang.String")) {
				fields[i].set(obj, values[i]);
			} else if(fields[i].getType().getName().equals("boolean")){
				fields[i].setBoolean(obj, Boolean.parseBoolean(values[i]+""));
			} else if(fields[i].getType().getName().equals("byte")){
				fields[i].setByte(obj, Byte.parseByte(values[i]+""));
			} else if(fields[i].getType().getName().equals("short")){
				fields[i].setShort(obj, Short.parseShort(values[i]+""));
			} else if(fields[i].getType().getName().equals("int")){
				fields[i].setInt(obj, Integer.parseInt(values[i]+""));
			} else if(fields[i].getType().getName().equals("long")){
				fields[i].setLong(obj, Long.parseLong(values[i]+""));					
			} else if(fields[i].getType().getName().equals("float")){
				fields[i].setFloat(obj, Float.parseFloat(values[i]+""));
			} else if(fields[i].getType().getName().equals("double")){
				fields[i].setDouble(obj, Double.parseDouble(values[i]+""));
			} else {
				throw new IllegalArgumentException("primitive type only : "+fields[i].getType().getName());
			}
		}
		return obj;
	}
	
	public static String[] getDeclaredFields(String className) throws ClassNotFoundException {
		Class cls = Class.forName(className);
		Field[] fields = cls.getDeclaredFields();
		String[] names = new String[fields.length];
		for(int i=0; i<fields.length; i++)
			names[i] = fields[i].getName();
		return names;
	}
	
	public static Object cloneObject(Object obj) throws ClassNotFoundException, SecurityException, NoSuchFieldException, InstantiationException, IllegalAccessException {
		String className = obj.getClass().getName();
		Field[] fields = obj.getClass().getFields();
		Class cls = Class.forName(className);
		Object retObj = cls.newInstance();
		for(int i=0; i<fields.length; i++) {
			Field field = cls.getField(fields[i].getName());
			field.set(retObj, fields[i].get(obj));
		}
		return retObj;
	}
	
	public static void copy(Object src, Object target) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		if(!src.getClass().equals(target.getClass())) {
			throw new IllegalArgumentException("��: /���� Ŭ������ �ƴմϴ�.");
		}
		Field[] fields = src.getClass().getFields();
		for(Field field : fields) {
			Field f = target.getClass().getField(field.getName());
			f.set(target, field.get(src));
		}
	}
	
	public static int getExconId(int fieldId) {
		Object[] vals = PropertyHandler.getPropertyValues("up_msg_id_mapping.properties", fieldId+"", "=");
		int ret = fieldId;
		if(vals != null && vals[0] != null && !vals[0].equals("")) {
			ret = Integer.parseInt(vals[0]+"");
		}
		return ret;
	}
	
	public static int getFieldId(int exconId) {
		Object[] vals = PropertyHandler.getPropertyValues("down_msg_id_mapping.properties", exconId+"", "=");
		int ret = exconId;
		if(vals != null && vals[0] != null && !vals[0].equals("")) {
			ret = Integer.parseInt(vals[0]+"");
		}
		return ret;
	}
	
	public static String getDownMsgName(int exconId) {
		Object[] vals = PropertyHandler.getPropertyValues("down_msg_id_mapping.properties", exconId+"", "=");
		return vals[1]+"";
	}
	
    public static String getAbsolutePath(String path_) {
        String path = path_.trim();
        char ch = path.charAt(0);
        if(ch == '.' || ch == '/') {
            if(ch == '.') {
                path = path.substring(1);
            }
            String userHome = System.getProperty("user.dir");
            File f = new File(userHome+path);
            path = f.getAbsolutePath();
        }
        return path;
    }
    
    public static void playSound(String filename) throws MalformedURLException {
        playSound(new File(filename));
    }
    
    public static void playSound(File file) throws MalformedURLException
    {
        URL url_ = new URL("file", "localhost", file.getAbsolutePath());
        AudioClip ac = Applet.newAudioClip(url_);
        ac.play();
    }
    
    public static long getSolarisVmSize(boolean isSwap) throws IOException {
    	Runtime run = Runtime.getRuntime();
    	long swapSize = -1;
    	long freeSize = -1;
		Process p = run.exec("vmstat 1 2");
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		br.readLine(); br.readLine(); br.readLine();
		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line, " ");
		st.nextToken();	st.nextToken();	st.nextToken();
		swapSize = Long.parseLong(st.nextToken().trim());
		freeSize = Long.parseLong(st.nextToken().trim());
		br.close();
		p.destroy();
		if(isSwap) {
			return swapSize;
		} else {
			return freeSize;
		}
    }
    
    public static long getSolarisTotalMemorySize() throws IOException {
    	Runtime run = Runtime.getRuntime();
		long mega = -1;
		Process p = null;
		BufferedReader br = null;
		p = run.exec("/usr/sbin/prtconf");
		br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		br.readLine();
		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line, " ");
		st.nextToken();
		st.nextToken();
		mega = Long.parseLong(st.nextToken().trim());			
		br.close();
		p.destroy();
		return mega;
    }
    
    public static int[] getSolarisCpuUsage(int cpuNum) throws IOException {
    	Runtime run = Runtime.getRuntime();
    	int[] cpu = new int[cpuNum];
		Process p = run.exec("mpstat 1 2");
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		for(int i=0; i<cpuNum+1; i++) {
			br.readLine();
		}
		String line = br.readLine();
		for(int i=0; i<cpu.length; i++) {
			line = br.readLine().trim();
			line = line.substring(0, line.lastIndexOf(" ")).trim(); //idle
			line = line.substring(0, line.lastIndexOf(" ")).trim(); //wt
			line = line.substring(line.lastIndexOf(" ")).trim(); //sys
			cpu[i] = Integer.parseInt(line);
		}
		br.close();
		p.destroy();
		return cpu;
    }

	public static void printSystemProperties() {
		Properties props = System.getProperties();
		Enumeration enu = props.keys();
		PrintStream ps = System.out;
		while(enu.hasMoreElements()) {
			String key = enu.nextElement()+"";
			ps.println(key+" : "+props.get(key));
		}
	}
}