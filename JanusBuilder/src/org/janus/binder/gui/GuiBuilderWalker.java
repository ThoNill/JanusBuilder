package org.janus.binder.gui;

import java.util.Stack;
import java.util.Vector;

import org.janus.actions.Action;
import org.janus.dict.actions.ActionDictionary;
import org.janus.gui.basis.GuiComponent;
import org.janus.gui.builder.GuiElementBuilder;
import org.janus.gui.enums.GuiType;
import org.jdom2.Element;

import toni.druck.xml.TreeWalker;

public class GuiBuilderWalker extends TreeWalker {

	private ActionDictionary dict;
	private Stack<GuiComponent> elementStack = new Stack<>();
	private GuiComponent aktualComponent;
	private GuiElementBuilder elementBuilder;
	private GuiComponent root;
	private Vector<GuiComponent> components = new Vector<>();

	public GuiBuilderWalker(GuiElementBuilder elementBuilder) {
		super();
		this.elementBuilder = elementBuilder;
	}

	@Override
	protected void bearbeite(Element elem) {
		if (relevant(elem)) {
			Action a = dict.getAction(getId(elem));
			aktualComponent = elementBuilder.createGuiElement(elem, a,dict);
						
			components.add(aktualComponent);
			if (!elementStack.isEmpty()) {
				GuiComponent parent = elementStack.peek();
				parent.addComponent(aktualComponent);
			} else {
				if ("GUI".equals(elem.getName())) {
					root = aktualComponent;
				}
			}
		}
	}

	private boolean relevant(Element elem) {
		try {
			GuiType.valueOf(elem.getName());
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	private String getId(Element elem) {
		String idName = "name";
		return elem.getAttributeValue(idName);
	}

	public ActionDictionary getDict() {
		return dict;
	}

	public void setDict(ActionDictionary dict) {
		this.dict = dict;
	}

	@Override
	protected void goUp(Element elem) {
		if (aktualComponent != null) {
			elementStack.push(aktualComponent);
		}
	}

	@Override
	protected void goDown(Element elem) {
		if (!elementStack.empty()) {
			elementStack.pop();
		}
	}

	public GuiComponent getRoot() {
		return root;
	}

	public Vector<GuiComponent> getComponents() {
		return components;
	}
}
