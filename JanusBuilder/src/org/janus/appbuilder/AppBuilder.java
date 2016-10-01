package org.janus.appbuilder;

import org.janus.binder.BindWalker;
import org.janus.binder.gui.GuiBuilderWalker;
import org.janus.builder.BuilderWalker;
import org.janus.gui.basis.GuiComponent;
import org.janus.gui.basis.JanusApplication;
import org.janus.gui.basis.JanusPage;
import org.janus.gui.builder.GuiElementBuilder;

import toni.druck.xml.XMLDocumentLoader;

public class AppBuilder {
    public static final String PAGEDIR = "/pages/";
    JanusApplication app;
    GuiElementBuilder elementBuilder;

    public AppBuilder(GuiElementBuilder elementBuilder) {
        this.elementBuilder = elementBuilder;
    }

    private String pageListe;

    public String[] getPageListe() {
        return pageListe.split(" +");
    }

    public void setPageListe(String pageListe) {
        this.pageListe = pageListe;
    }

    public void addDataModel(JanusApplication app, String filename) {
        JanusPage m = createDataModelFromFile(app.getName() + PAGEDIR, filename);
        app.addPage(m);
    }

    private JanusPage createDataModelFromFile(String dir, String pagename) {

        org.jdom2.Document page = new XMLDocumentLoader().createDocument(dir
                + pagename + ".xml");
        BuilderWalker walker = new AppBuilderWalker(pagename);
        JanusPage dict = new JanusPage(pagename);
        walker.setDict(dict);
        walker.walkAlong(page);

        dict.completeObject();
        dict.configure();

        BindWalker bindWalker = new AppBinderWalker();
        bindWalker.walkAlong(page);
        bindWalker.bind(dict);

        GuiBuilderWalker guiWalker = new GuiBuilderWalker(elementBuilder);
        guiWalker.setDict(dict);
        guiWalker.walkAlong(page);

        GuiComponent comp = guiWalker.getRoot();

        dict.setGui(comp);
        return dict;
    }

    public void createDataModels(JanusApplication app) throws Exception {
        String[] list = getPageListe();

        for (String fname : list) {
            if (!("application".equals(fname) || "global".equals(fname) || "login"
                    .equals(fname))) {
                addDataModel(app, fname);
            }
        }
    }

    public JanusApplication getApplication(String name) throws Exception {

        JanusApplication app = new JanusApplication(name);
        addDataModel(app, "global");
        addDataModel(app, "login");
        createDataModels(app);

        return app;
    }
}
