package org.janus.builder;

import org.janus.actions.Action;
import org.janus.dict.actions.ActionDictionary;
import org.janus.dict.actions.ActionEventSender;
import org.jdom2.Element;

public interface BuilderAction {

	ActionEventSender createAction(ActionDictionary dict,Element elem);
		
}
