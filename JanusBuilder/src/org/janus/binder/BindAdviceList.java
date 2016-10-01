package org.janus.binder;

import java.util.ArrayList;

import org.janus.dict.actions.ActionDictionary;

public class BindAdviceList extends ArrayList<BindAdvice> {

    public void bind(ActionDictionary dict) {
        for (BindAdvice advice : this) {
            advice.bind(dict);
        }
    }

    public void addAdviceKommaList(String listener, String manySender) {
        String sender[] = manySender.split(" *, *");
        addAdvices(listener, sender);
    }

    public void addAdviceQList(String listener, String manySender) {
        String[] sender = manySender.split(" *\\? *");
        String[] hsender = new String[sender.length / 2];
        for (int i = 0; i < sender.length / 2; i++) {
            hsender[i] = sender[2 * i + 1];
        }
        addAdvices(listener, hsender);
    }

    private String beforePointPart(String text) {
        int i = text.indexOf('.');
        if (i >= 0) {
            return text.substring(0, i).trim();
        }
        return text.trim();
    }

    private void addAdvices(String listener, String[] manySender) {
        for (String sender : manySender) {
            addAdvice(listener, sender);
        }

    }

    public void addAdvice(String listener, String sender) {
        String listenerBeforePoint = beforePointPart(listener);
        String senderBeforePoint = beforePointPart(sender);
        BindAdvice newAdvice = new BindAdvice(listenerBeforePoint,
                senderBeforePoint);
        if (!this.contains(newAdvice)) {
            add(newAdvice);
        }

    }
}
