package org.chaostocosmos.chaosdashboard.common;

import java.util.EventListener;

public interface IPropertyChangeListener extends EventListener
{
    public void propertyValueChanged(PropertyChangeEvent pce);
}
