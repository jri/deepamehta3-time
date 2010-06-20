package de.deepamehta.plugins.time.migrations;

import de.deepamehta.core.model.DataField;
import de.deepamehta.core.model.TopicType;
import de.deepamehta.core.service.Migration;



public class Migration1 extends Migration {

    @Override
    public void run() {
        // Add "time_created" and "time_modified" data fields to all existing topic types.
        // Note: Topic types created after the time plugin is activated get these fields through the preCreateHook().
        // See de.deepamehta.plugins.time.TimePlugin
        //
        // TODO: Avoid this code doubling by providing a "update type definition" facility.
        //
        DataField timeCreatedField = new DataField("time_created");
        timeCreatedField.setDataType("number");
        timeCreatedField.setIndexingMode("FULLTEXT_KEY");
        //
        DataField timeModifiedField = new DataField("time_modified");
        timeModifiedField.setDataType("number");
        timeModifiedField.setIndexingMode("FULLTEXT_KEY");
        //
        for (String typeId : dms.getTopicTypeIds()) {
            dms.addDataField(typeId, timeCreatedField);
            dms.addDataField(typeId, timeModifiedField);
        }
    }
}
