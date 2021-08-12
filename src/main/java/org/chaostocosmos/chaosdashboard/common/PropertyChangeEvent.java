package org.chaostocosmos.chaosdashboard.common;

import java.util.EventObject;
import java.util.Properties;

public class PropertyChangeEvent extends EventObject
{
	private Properties prop;
    
	private String key;
    
	private String value;
    
	private int appMode;

	public PropertyChangeEvent(Object source, Properties prop, String key, String value, int appMode)
	{
		super(source);
        this.prop = prop;
        this.key = key;
        this.value = value;
        this.appMode = appMode;
	}
    
    public String getKey()
    {
        return this.key;
    }
    
    public String getValue()
    {
        return this.value;
    }
    
    public int getMode()
    {
        return this.appMode;
    }
}
