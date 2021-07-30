/*
 * Filename : MultiComponentElement.java
 * Class : MultiComponentElement
 */
package org.chaos.mgmt.client.ui;

import java.awt.Component;

/**
 * 다중 컴포넌트
 * @author Kooin Shin
 * @since 2003. 8. 23.
 */
public interface MultiComponentElement
{
    /**
     * 값을 설정한다.
     * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#setValue(java.lang.Object)
     */
    public void setValue(Object value);

    /**
     * 값을 얻는다.
     * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#getValue()
     */
    public Object getValue();

    /**
     * 멀티 컴포넌트를 얻는다.
     * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#getMultiComponentElement()
     */
    public Component getMultiComponentElement();
}