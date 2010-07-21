package de.deepamehta.plugins.time.migrations;

import de.deepamehta.plugins.time.TimePlugin;

import de.deepamehta.core.model.DataField;
import de.deepamehta.core.model.TopicType;
import de.deepamehta.core.service.Migration;



public class Migration1 extends Migration {

    @Override
    public void run() {
        // Add "Date Created" and "Date Modified" data fields to all existing topic types.
        //
        // Note: Topic types created after the time plugin is activated get these fields through the preCreateHook().
        // See de.deepamehta.plugins.time.TimePlugin
        //
        // TODO: Avoid this code doubling by providing a "update type definition" facility.
        //
        for (String typeUri : dms.getTopicTypeUris()) {
            dms.addDataField(typeUri, TimePlugin.createDateCreatedField());
            dms.addDataField(typeUri, TimePlugin.createDateModifiedField());
        }
    }
}
