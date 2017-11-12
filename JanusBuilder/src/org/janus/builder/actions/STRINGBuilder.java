package org.janus.builder.actions;

import org.janus.actions.Action;
import org.janus.datatype.DefaultData;
import org.janus.dict.actions.ListeningValue;
import org.janus.value.RefDataValue;
import org.jdom2.Element;

public class STRINGBuilder extends DefaultBuilderAction {

    public STRINGBuilder() {

    }

    @Override
    protected Action createAction(Element elem) {
        String defValue = elem.getAttributeValue("default");

        if (defValue == null) {
            defValue = "";
        }
        DefaultData d = new DefaultData(defValue);

        ListeningValue v = new ListeningValue(d);
        
        String refName = elem.getAttributeValue("ref");
        if (refName != null) {
            RefDataValue ref = new RefDataValue();
            ref.setSourceName(refName);
            ref.addActionListener(v);
        }
        
        return v;
    }

}
