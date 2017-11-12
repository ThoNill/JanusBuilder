package org.janus.appbuilder;

import org.janus.binder.BindWalker;

public class AppBinderWalker extends BindWalker {

    public AppBinderWalker() {
        init();
    }

    @Override
    protected void init() {
        add("STRING", "name", "source");
        add("BEAN", "name", "CALL", "name");
        add("CALL", "name", "SET", "to");
        add("TABLE","name","ref");
        super.init();
    }

}
