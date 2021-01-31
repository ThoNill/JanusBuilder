package org.janus.binder.gui;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.apache.log4j.Logger;
import org.janus.actions.Action;
import org.janus.dict.actions.ActionDictionary;
import org.janus.gui.basis.GuiComponent;
import org.janus.gui.basis.RootGuiComponent;
import org.janus.gui.builder.GuiElementBuilder;
import org.janus.gui.enums.GuiType;
import org.jdom2.Document;
import org.jdom2.Element;

import toni.druck.xml.TreeWalker;

public class GuiBuilderWalker extends TreeWalker {
    private static final Logger LOG = Logger.getLogger(GuiBuilderWalker.class);

    private ActionDictionary dict;
    private Deque<GuiComponent> elementStack = new ArrayDeque<>();
    private GuiComponent aktualComponent;
    private GuiElementBuilder elementBuilder;
    private RootGuiComponent root;
    private List<GuiComponent> components = new ArrayList<>();

    public GuiBuilderWalker(GuiElementBuilder elementBuilder) {
        super();
        this.elementBuilder = elementBuilder;
    }

    @Override
    protected void bearbeite(Element elem) {
        if (relevant(elem)) {
            String id = getId(elem);
            Action a = dict.getAction(id);
            aktualComponent = elementBuilder.createGuiElement(elem, a, dict);

            components.add(aktualComponent);
            if (!elementStack.isEmpty()) {
                if (!(aktualComponent instanceof NeedLaterGuiBuild)) {
                    addToParentComponent();
                }
            } else {
                if ("GUI".equals(elem.getName())) {
                    root = (RootGuiComponent) aktualComponent;
                }
            }
        }
    }

    protected void addToParentComponent() {
        if (!elementStack.isEmpty()) {
            GuiComponent parent = elementStack.peek();
            parent.addComponent(aktualComponent);
        } else {
            root.addComponent(aktualComponent);
        }
    }

    private boolean relevant(Element elem) {
        try {
            GuiType.valueOf(elem.getName());
            return true;
        } catch (IllegalArgumentException e) {
            LOG.debug("Test ob ein relevantes Element vorliegt", e);
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
        if (!elementStack.isEmpty()) {
            aktualComponent = elementStack.pop();
            if (aktualComponent != null
                    && aktualComponent instanceof NeedLaterGuiBuild) {
                ((NeedLaterGuiBuild) aktualComponent).buildGui();
                addToParentComponent();
            }
        }
    }

    public RootGuiComponent getRoot() {
        return root;
    }

    public List<GuiComponent> getComponents() {
        return components;
    }

    @Override
    public void walkAlong(Document doc) {
        super.walkAlong(doc);
        if (root != null) {
            root.setAllComponents(components);
        }
    }
}
