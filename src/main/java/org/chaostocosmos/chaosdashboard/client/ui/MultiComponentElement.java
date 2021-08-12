/*
 * Filename : MultiComponentElement.java
 * Class : MultiComponentElement
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.awt.Component;

/**
 * ���� ������Ʈ
 * @author Kooin Shin
 * @since 2003. 8. 23.
 */
public interface MultiComponentElement
{
    /**
     * ���� �����Ѵ�.
     * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#setValue(java.lang.Object)
     */
    public void setValue(Object value);

    /**
     * ���� ��´�.
     * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#getValue()
     */
    public Object getValue();

    /**
     * ��Ƽ ������Ʈ�� ��´�.
     * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#getMultiComponentElement()
     */
    public Component getMultiComponentElement();
}