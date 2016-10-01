package org.janus.builder.actions;

import java.util.ArrayList;
import java.util.HashMap;
import org.janus.actions.Action;
import org.janus.database.ResultSet2HashMap;
import org.janus.database.SqlResult;
import org.janus.database.SqlStatement;
import org.jdom2.Element;

public class SQLBuilder extends DefaultBuilderAction {

    public SQLBuilder() {

    }

    @Override
    protected Action createAction(Element elem) {
        String stmt = elem.getAttributeValue("stmt");
        String name = elem.getAttributeValue("name");
        boolean prepare = !"false".equals(elem.getAttributeValue("prepare"));

        ArrayList<String> columnNamen = new ArrayList<>();
        for (Element column : elem.getChildren()) {
            if ("COLUMN".equals(column.getName())) {
                columnNamen.add(column.getAttributeValue("name"));
            }
        }

        ResultSet2HashMap toHashMapConverter = new ResultSet2HashMap(
                columnNamen.toArray(new String[0]));

        SqlStatement sql = new SqlResult<HashMap<String, Object>>(name, stmt,
                toHashMapConverter);
        sql.prepare(prepare);
        return sql;

    }

}
