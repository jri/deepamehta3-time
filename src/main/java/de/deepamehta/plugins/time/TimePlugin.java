package de.deepamehta.plugins.time;

import de.deepamehta.core.model.DataField;
import de.deepamehta.core.model.Topic;
import de.deepamehta.core.model.TopicType;
import de.deepamehta.core.service.Plugin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;



public class TimePlugin extends Plugin {

    private Logger logger = Logger.getLogger(getClass().getName());



    // ************************
    // *** Overriding Hooks ***
    // ************************



    @Override
    public void preCreateHook(Topic topic, Map<String, String> clientContext) {
        //
        if (topic.typeUri.equals("de/deepamehta/core/topictype/TopicType")) {
            // Add "Date Created" and "Date Modified" data fields to the topic type being created.
            // Note: Topic types created before the time plugin was activated get these fields through the initial
            // migration. See de.deepamehta.plugins.time.migrations.Migration1
            //
            // TODO: Avoid this code doubling by providing a "update type definition" facility.
            //
            DataField timeCreatedField = new DataField("Date Created");
            timeCreatedField.setUri("de/deepamehta/core/property/DateCreated");
            timeCreatedField.setDataType("number");
            timeCreatedField.setIndexingMode("FULLTEXT_KEY");
            //
            DataField timeModifiedField = new DataField("Date Modified");
            timeModifiedField.setUri("de/deepamehta/core/property/DateModified");
            timeModifiedField.setDataType("number");
            timeModifiedField.setIndexingMode("FULLTEXT_KEY");
            //
            ((TopicType) topic).addDataField(timeCreatedField);
            ((TopicType) topic).addDataField(timeModifiedField);
        }
        //
        // add a timestamp to the topic being created
        logger.info("Adding timestamp to " + topic);
        long time = System.currentTimeMillis();
        topic.setProperty("de/deepamehta/core/property/DateCreated", time);
        topic.setProperty("de/deepamehta/core/property/DateModified", time);
    }

    @Override
    public void preUpdateHook(Topic topic, Map<String, Object> newProperties) {
        long time = System.currentTimeMillis();
        topic.setProperty("de/deepamehta/core/property/DateModified", time);
    }

    // ---

    @Override
    public void providePropertiesHook(Topic topic) {
        topic.setProperty("de/deepamehta/core/property/DateModified",
            dms.getTopicProperty(topic.id, "de/deepamehta/core/property/DateModified"));
    }
}
