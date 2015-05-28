package org.janus.builder.actions;

import org.janus.actions.Action;
import org.janus.datatype.DefaultData;
import org.janus.dict.actions.ListeningValue;
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
		
		return  new ListeningValue(d);
	}

}
