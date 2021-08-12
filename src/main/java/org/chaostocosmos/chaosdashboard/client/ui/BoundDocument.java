/*
 * Filename : BoundDocument.java
 * Class : BoundDocument
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

/**
 * ���ڼ� ���� Ŭ����
 * @version 1.5.0
 * @author ������, Kooin Shin
 */
public class BoundDocument extends PlainDocument
{
    /** ���ڼ� ���� */
    protected int charLimit;

    /** �ؽ�Ʈ ������Ʈ */
    protected JTextComponent textComp;
    
    /** ��ȿ ���� ��� */
    char[] validChars;

    /**
     * ���ڼ� ���� �ؽ�Ʈ ������Ʈ�� ���ڷ� ���� ������
     * @param charLimit
     * @param textComp
     * @param validChar
     */
    public BoundDocument(int charLimit, JTextComponent textComp, String validChar)
    {
        this.charLimit = charLimit;
        this.textComp = textComp;
        this.validChars = validChar.toCharArray();
    }

    /**
     * ��ť��Ʈ�� ��Ʈ���� �߰��Ѵ�.
     * @see javax.swing.text.Document#insertString(int, String, AttributeSet)
     */
    public void insertString (int offs, String str, AttributeSet a) throws BadLocationException
    {
        if(textComp.getText().length() + str.length() <= charLimit)
        {
            if(validChars.length > 0)
            {
                for(int i=str.length()-1; i>=0; i--)
                {
                    char c = str.charAt(i);
                    for(int j=0; j<validChars.length; j++)
                        if(c == validChars[j])
                            super.insertString(offs, c+"", a);
                }
            }
            else
            {
                super.insertString(offs, str, a);
            }
        }
    }
}






