package org.janus.binder;

import java.util.Stack;

import org.janus.dict.actions.ActionDictionary;
import org.jdom2.Element;

import toni.druck.xml.TreeWalker;

public class BindWalker extends TreeWalker {
	private BindAdviceList adviceList = new BindAdviceList();
	private ElementBinderList binderList = new ElementBinderList();
	private Stack<Element> elementStack = new Stack<>();

	public BindWalker() {
		init();
	}

	
	public void bind(ActionDictionary dict) {
		adviceList.bind(dict);
	}
	
	protected void init() {
		binderList.setList(adviceList);
	}

	@Override
	protected void bearbeite(Element element) {
		Element parent = elementStack.peek();
		binderList.addBindAdvices(parent, element);

	}

	@Override
	protected void goUp(Element elem) {
		elementStack.push(elem);
	}

	@Override
	protected void goDown(Element elem) {
		elementStack.pop();
	}
	protected void add(String listenerElement, String listenerAttribut,
			String senderElement, String senderAttribut) {
		binderList.add(listenerElement, listenerAttribut, senderElement,
				senderAttribut);
	}

	protected void add(String element, String listenerAttribut,
			String senderAttribut) {
		binderList.add(element, listenerAttribut, senderAttribut);
	}
}
