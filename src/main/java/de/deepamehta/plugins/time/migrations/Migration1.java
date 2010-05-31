package de.deepamehta.plugins.time.migrations;

import de.deepamehta.core.model.DataField;
import de.deepamehta.core.model.TopicType;
import de.deepamehta.core.service.Migration;



public class Migration1 extends Migration {

    public void run() {
        //
        DataField timeCreatedField = new DataField("time_created");
        DataField timeModifiedField = new DataField("time_modified");
        //
        for (String typeId : dms.getTopicTypeIds()) {
            dms.addDataField(typeId, timeCreatedField);
            dms.addDataField(typeId, timeModifiedField);
        }
    }
}
