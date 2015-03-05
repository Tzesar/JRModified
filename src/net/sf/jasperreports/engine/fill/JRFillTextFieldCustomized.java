package net.sf.jasperreports.engine.fill;

import net.sf.jasperreports.engine.JRCommonText;
import net.sf.jasperreports.engine.JRTextField;

/**
 * Created by augusto on 3/3/15.
 */
public class JRFillTextFieldCustomized extends JRFillTextField {

    public JRFillTextFieldCustomized(
            JRBaseFiller filler,
            JRTextField textField,
            JRFillObjectFactory factory
    ){
        super(filler, textField, factory);
    }

    public JRFillTextFieldCustomized(JRFillTextField textField, JRFillCloneFactory factory){
        super(textField, factory);
    }

    @Override
    public String getOwnMarkup(){
        return JRCommonText.MARKUP_HTML;
    }
}
