package org.janus.builder;

import java.util.HashMap;

import org.janus.dict.actions.ActionDictionary;
import org.jdom2.Element;

import toni.druck.xml.TreeWalker;

public class BuilderWalker extends TreeWalker {

	private ActionDictionary dict;
	
	private HashMap<String,BuilderAction> actions = null;
	
	public BuilderWalker(String name) {
		actions = new HashMap<>();
		init(actions);
	}
	

	protected void init(HashMap<String, BuilderAction> actions2) {
	}

	
	@Override
	protected void bearbeite(Element elem) {
		BuilderAction action = actions.get(elem.getName());
		if (action != null) {
			action.createAction(dict, elem);
		}
	}

	public BuilderAction put(String key, BuilderAction value) {
		return actions.put(key, value);
	}



	public ActionDictionary getDict() {
		return dict;
	}



	public void setDict(ActionDictionary dict) {
		this.dict = dict;
	}

}
