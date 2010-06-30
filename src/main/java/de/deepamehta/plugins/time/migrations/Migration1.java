package de.deepamehta.plugins.time.migrations;

import de.deepamehta.core.model.DataField;
import de.deepamehta.core.model.TopicType;
import de.deepamehta.core.service.Migration;



public class Migration1 extends Migration {

    @Override
    public void run() {
        // Add "Date Created" and "Date Modified" data fields to all existing topic types.
        // Note: Topic types created after the time plugin is activated get these fields through the preCreateHook().
        // See de.deepamehta.plugins.time.TimePlugin
        //
        // TODO: Avoid this code doubling by providing a "update type definition" facility.
        //
        DataField timeCreatedField = new DataField("Date Created");
        timeCreatedField.setUri("http://www.deepamehta.de/core/property/DateCreated");
        timeCreatedField.setDataType("number");
        timeCreatedField.setIndexingMode("FULLTEXT_KEY");
        //
        DataField timeModifiedField = new DataField("Date Modified");
        timeModifiedField.setUri("http://www.deepamehta.de/core/property/DateModified");
        timeModifiedField.setDataType("number");
        timeModifiedField.setIndexingMode("FULLTEXT_KEY");
        //
        for (String typeUri : dms.getTopicTypeUris()) {
            dms.addDataField(typeUri, timeCreatedField);
            dms.addDataField(typeUri, timeModifiedField);
        }
    }
}
