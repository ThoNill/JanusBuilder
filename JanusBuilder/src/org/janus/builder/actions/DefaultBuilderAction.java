package org.janus.builder.actions;

import org.janus.actions.Action;
import org.janus.builder.BuilderAction;
import org.janus.dict.actions.ActionDictionary;
import org.janus.dict.actions.ActionEventSender;
import org.jdom2.Element;

public class DefaultBuilderAction implements BuilderAction {

	String idAttribut = null;

	public DefaultBuilderAction(String idAttribut) {
		super();
		this.idAttribut = idAttribut;
	}

	public DefaultBuilderAction() {
		this("name");
	}

	public String getId(Element elem) {
		return elem.getAttributeValue(idAttribut);
	}
	
	@Override
	public ActionEventSender createAction(ActionDictionary dict, Element elem) {
		Action action = createAction(elem);
		if (action != null) {
			return dict.addAction(getId(elem), action);
		}
		return null;
	}
	


	protected Action createAction(Element elem) {
		return null;
	}



}
