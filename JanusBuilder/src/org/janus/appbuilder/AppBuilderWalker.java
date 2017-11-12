package org.janus.appbuilder;

import java.util.HashMap;

import org.janus.builder.BuilderAction;
import org.janus.builder.BuilderWalker;
import org.janus.builder.actions.ACTIONBuilder;
import org.janus.builder.actions.BEANBuilder;
import org.janus.builder.actions.CALLBuilder;
import org.janus.builder.actions.DefaultBuilderAction;
import org.janus.builder.actions.MAPTABLEBuilder;
import org.janus.builder.actions.SQLBuilder;
import org.janus.builder.actions.STRINGBuilder;
import org.janus.builder.actions.TABLEBuilder;

public class AppBuilderWalker extends BuilderWalker {

    public AppBuilderWalker(String name) {
        super(name);
    }

    @Override
    protected void init(HashMap<String, BuilderAction> actions2) {
        BEANBuilder bean = new BEANBuilder();

        put("TABLE", new TABLEBuilder());
        put("ACTION", new ACTIONBuilder());
        put("BATCH", new DefaultBuilderAction());
        put("BEAN", bean);
        put("CALL", new CALLBuilder(bean));
        put("GLOBAL", new DefaultBuilderAction());
        put("MAPTABLE", new MAPTABLEBuilder());
        put("STRING", new STRINGBuilder());
        put("SQL", new SQLBuilder());
    }

}
