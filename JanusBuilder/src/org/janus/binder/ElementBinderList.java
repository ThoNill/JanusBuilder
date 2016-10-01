package org.janus.binder;

import java.util.ArrayList;

import org.jdom2.Element;

public class ElementBinderList extends ArrayList<ElementBinder> {

    public ElementBinderList() {
    }

    public void addBindAdvices(Element listener, Element sender) {
        for (ElementBinder binder : this) {
            binder.addBindAdvices(sender);
            binder.addBindAdvices(listener, sender);
        }
    }

    public void setList(BindAdviceList list) {
        for (ElementBinder binder : this) {
            binder.setList(list);
        }
    }

    public void add(String listenerElement, String listenerAttribut,
            String senderElement, String senderAttribut) {
        add(new ElementBinder(listenerElement, listenerAttribut, senderElement,
                senderAttribut));
    }

    public void add(String element, String listenerAttribut,
            String senderAttribut) {
        add(new ElementBinder(element, listenerAttribut, senderAttribut));
    }

}
