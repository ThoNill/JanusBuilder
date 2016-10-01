package org.janus.binder;

import org.jdom2.Element;

public class ElementBinder {
    private String listenerElement;
    private String listenerAttribut;
    private String senderElement;
    private String senderAttribut;

    private BindAdviceList list;

    public ElementBinder(String listenerElement, String listenerAttribut,
            String senderElement, String senderAttribut) {
        super();
        this.listenerElement = listenerElement;
        this.listenerAttribut = listenerAttribut;
        this.senderElement = senderElement;
        this.senderAttribut = senderAttribut;
    }

    public ElementBinder(String elementName, String listenerAttribut,
            String senderAttribut) {
        super();
        this.listenerElement = elementName;
        this.listenerAttribut = listenerAttribut;
        this.senderElement = elementName;
        this.senderAttribut = senderAttribut;
    }

    public boolean passt(Element listener, Element sender) {
        return (listenerElement.equals(listener.getName())
                && listener.getAttributeValue(listenerAttribut) != null
                && senderElement.equals(sender.getName()) && sender
                    .getAttributeValue(senderAttribut) != null

        );
    }

    public boolean passt(Element element) {
        return passt(element, element);
    }

    public boolean singleSource() {
        return listenerElement.equals(senderElement);
    }

    public void addBindAdvices(Element listener, Element sender) {
        if (passt(listener, sender)) {
            String lAttribut = listener.getAttributeValue(listenerAttribut);
            String sAttribut = sender.getAttributeValue(senderAttribut);
            if (lAttribut != null && sAttribut != null) {
                addBindAdvices(lAttribut, sAttribut);
            }
        }
    }

    public void addBindAdvices(Element element) {
        if (singleSource()) {
            addBindAdvices(element, element);
        }
    }

    public void addBindAdvices(String lAttribut, String sAttribut) {
        if (lAttribut != null && sAttribut != null) {
            if (sAttribut.indexOf('?') >= 0) {
                list.addAdviceQList(lAttribut, sAttribut);
            } else {
                list.addAdviceKommaList(lAttribut, sAttribut);
            }
        }
    }

    public void setList(BindAdviceList list) {
        this.list = list;
    }

}
