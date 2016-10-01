package org.janus.builder.actions;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.janus.dict.actions.ActionDictionary;
import org.janus.dict.actions.ActionEventSender;
import org.janus.table.DelegatingTableWrapper;
import org.jdom2.Element;

public class MAPTABLEBuilder extends DefaultBuilderAction {
    private static String[] columns = new String[] { "value", "text" };

    public MAPTABLEBuilder() {
    }

    @Override
    public ActionEventSender createAction(ActionDictionary dict, Element elem) {

        DefaultTableModel tm = createDefaultTableModel(elem);

        DelegatingTableWrapper wrapper = new DelegatingTableWrapper(
                elem.getAttributeValue("name"), dict, tm);
        wrapper.addColumn("value");
        wrapper.addColumn("text");
        return wrapper.getTable();
    }

    protected DefaultTableModel createDefaultTableModel(Element elem) {
        List<Element> childs = elem.getChildren();
        DefaultTableModel tm = new DefaultTableModel(columns, childs.size());

        int row = 0;
        for (Element e : childs) {
            tm.setValueAt(e.getAttributeValue("value"), row, 0);
            tm.setValueAt(e.getAttributeValue("text"), row, 1);
            row++;
        }
        return tm;
    }

}
