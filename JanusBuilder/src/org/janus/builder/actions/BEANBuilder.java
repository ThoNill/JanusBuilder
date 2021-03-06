package org.janus.builder.actions;

import org.janus.value.RefDataValue;
import org.janus.actions.Action;
import org.janus.bean.BeanDataValue;
import org.janus.dict.actions.ActionDictionary;
import org.janus.dict.actions.ActionEventSender;
import org.jdom2.Element;

public class BEANBuilder extends DefaultBuilderAction {
    BeanDataValue bean;

    public BEANBuilder() {

    }
    
 

    @Override
    protected Action createAction(Element elem) {
        String className = elem.getAttributeValue("class");

        bean = new BeanDataValue();
        bean.setClassname(className);
        
        return bean;
    }

    public BeanDataValue getBean() {
        return bean;
    }
}
