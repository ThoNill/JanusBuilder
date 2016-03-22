package org.janus.builder.actions;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.janus.actions.Action;
import org.janus.bean.CallDataValue;
import org.jdom2.Element;

public class CALLBuilder extends DefaultBuilderAction {
	private static final Logger LOG = LogManager.getLogger(CALLBuilder.class);
	
	BEANBuilder builder;

	public CALLBuilder(BEANBuilder builder) {
		super();
		this.builder = builder;
	}

	@Override
	protected Action createAction(Element elem) {
		LOG.debug("create Call Action");
		String command = elem.getAttributeValue("command");
		String name = elem.getAttributeValue("name");
		
		CallDataValue call =  new CallDataValue(builder.getBean(),command);
		
		for (Element set : elem.getChildren()) {
			if ("SET".equals(set.getName())) {
				String varAttribute = set.getAttributeValue("var");
				String toAttribute = set.getAttributeValue("to");
				String constAttribute = set.getAttributeValue("constant");
				if (toAttribute != null) {
					LOG.debug("add Var setter {} {}",varAttribute,toAttribute);
					call.addSetterValue(varAttribute,toAttribute);
				}
				if (constAttribute != null) {
					LOG.debug("add const setter {} {}",varAttribute,constAttribute);
					call.addSetterConstant(varAttribute,constAttribute);
				}
			}
		}
		LOG.debug("Action created");	
		return call;
	}
}
