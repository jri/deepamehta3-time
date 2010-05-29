package de.deepamehta.plugins.time.migrations;

import de.deepamehta.core.model.DataField;
import de.deepamehta.core.model.TopicType;
import de.deepamehta.core.plugin.Migration;



public class Migration1 extends Migration {

    public void run() {
        for (TopicType topicType : dms.getTopicTypes()) {
            String typeId = topicType.getProperty("type_id");
            //
            DataField timeCreatedField = new DataField();
            timeCreatedField.setId("time_created");
            DataField timeModifiedField = new DataField();
            timeModifiedField.setId("time_modified");
            //
            dms.addDataField(typeId, timeCreatedField);
            dms.addDataField(typeId, timeModifiedField);
        }
    }
}
