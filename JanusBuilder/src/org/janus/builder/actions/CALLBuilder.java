package org.janus.builder.actions;

import org.janus.actions.Action;
import org.janus.bean.CallDataValue;
import org.jdom2.Element;

public class CALLBuilder extends DefaultBuilderAction {
	BEANBuilder builder;

	public CALLBuilder(BEANBuilder builder) {
		super();
		this.builder = builder;
	}

	@Override
	protected Action createAction(Element elem) {
		String command = elem.getAttributeValue("command");
		String name = elem.getAttributeValue("name");
		
		CallDataValue call =  new CallDataValue(builder.getBean(),command);
			
		return call;
	}
}
