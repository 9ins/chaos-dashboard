/*
 * Filename : BoundDocument.java
 * Class : BoundDocument
 */
package org.chaos.mgmt.client.ui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

/**
 * 글자수 제한 클래스
 * @version 1.5.0
 * @author 박함태, Kooin Shin
 */
public class BoundDocument extends PlainDocument
{
    /** 글자수 제한 */
    protected int charLimit;

    /** 텍스트 컴포넌트 */
    protected JTextComponent textComp;
    
    /** 유효 문자 목록 */
    char[] validChars;

    /**
     * 글자수 제한 텍스트 컴포넌트를 인자로 갖는 생성자
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
     * 도큐먼트에 스트링을 추가한다.
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






