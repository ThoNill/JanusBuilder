package org.janus.builder.actions;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import org.janus.table.DelegatingTableWrapper;
import org.janus.table.RefDataValueTableWrapper;
import org.janus.value.RefDataValue;
import org.janus.actions.Action;
import org.janus.bean.BeanDataValue;
import org.janus.dict.actions.ActionDictionary;
import org.janus.dict.actions.ActionEventSender;
import org.jdom2.Element;

public class TABLEBuilder extends DefaultBuilderAction {
    BeanDataValue bean;

    public TABLEBuilder() {

    }
    
    @Override
    public ActionEventSender createAction(ActionDictionary dict, Element elem) {
        RefDataValue ref = new RefDataValue();
        String refName = elem.getAttributeValue("ref");
        ref.setSourceName(refName);
        RefDataValueTableWrapper wrapper = new RefDataValueTableWrapper(
                elem.getAttributeValue("name"), dict, ref);
        
        for (Element column : elem.getChildren()) {
            if ("COLUMN".equals(column.getName())) {
                wrapper.addColumn(column.getAttributeValue("name"));
            }
        }
        return wrapper.getTable();
    }

}
