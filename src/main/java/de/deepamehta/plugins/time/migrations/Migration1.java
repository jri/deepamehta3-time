package de.deepamehta.plugins.time.migrations;

import de.deepamehta.core.model.DataField;
import de.deepamehta.core.model.TopicType;
import de.deepamehta.core.service.Migration;



public class Migration1 extends Migration {

    @Override
    public void run() {
        //
        DataField timeCreatedField = new DataField("time_created").setIndexingMode("FULLTEXT_KEY");
        DataField timeModifiedField = new DataField("time_modified").setIndexingMode("FULLTEXT_KEY");
        //
        for (String typeId : dms.getTopicTypeIds()) {
            dms.addDataField(typeId, timeCreatedField);
            dms.addDataField(typeId, timeModifiedField);
        }
    }
}
