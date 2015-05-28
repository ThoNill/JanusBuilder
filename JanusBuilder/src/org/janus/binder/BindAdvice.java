package org.janus.binder;


import org.janus.dict.actions.ActionDictionary;
import org.janus.dict.actions.ActionEventSender;
import org.janus.dict.actions.NamedActionValue;

public class BindAdvice {
	private String listener;
	private String sender;
	

	public BindAdvice(String listener, String sender) {
		super();
		this.listener = listener;
		this.sender = sender;
	}

	public void bind(ActionDictionary dict) {
		NamedActionValue l = dict.getAction(listener);
		notNull(l,listener,dict);
		ActionEventSender s = dict.getAction(sender);
		notNull(s,sender,dict);
		s.addActionListener(l);
	}

	private void notNull(Object l, String name,ActionDictionary dict) {
		if (l == null) {
			throw new RuntimeException(String.format("Das Directory %1$s enthält kein %2$s ",dict.getName(),name));
		}
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((listener == null) ? 0 : listener.hashCode());
		result = prime * result + ((sender == null) ? 0 : sender.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BindAdvice other = (BindAdvice) obj;
		if (listener == null) {
			if (other.listener != null)
				return false;
		} else if (!listener.equals(other.listener))
			return false;
		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;
		return true;
	}

}
