package org.janus.builder.actions;

import org.janus.actions.Action;
import org.janus.bean.BeanDataValue;
import org.janus.data.DefaultClassFactory;
import org.janus.dict.actions.NamedActionReferenceList;
import org.janus.gui.actions.ElementConfigurable;
import org.jdom2.Element;

public class ACTIONBuilder extends DefaultBuilderAction {
	BeanDataValue bean;

	public ACTIONBuilder() {

	}

	@Override
	protected Action createAction(Element elem) {

		Action action = actionFromClassname(elem);
		if (action==null) {
			action = actionList(elem);
		}
		return action;

	}

	protected Action actionFromClassname(Element elem) {
		Action action=null;
		String className = elem.getAttributeValue("class");
		if (className != null) {
			action = (Action) DefaultClassFactory.FACTORY
					.createAndCheckInstance(className, Action.class);

			if (action instanceof ElementConfigurable) {
				((ElementConfigurable) action).configure(elem);
			}
		}
		return action;
	}
	
	protected Action actionList(Element elem) {
		Action action=null;
		String actions = elem.getAttributeValue("actions");
		if (actions!=null) {
			action = new NamedActionReferenceList(actions);
		}
		return action;
	}

}
