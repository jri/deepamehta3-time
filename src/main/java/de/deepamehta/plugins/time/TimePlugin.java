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
        if (topic.typeId.equals("Topic Type")) {
            // Add "time_created" and "time_modified" data fields to the topic type being created.
            // Note: Topic types created before the time plugin was activated get these fields through the initial
            // migration. See de.deepamehta.plugins.time.migrations.Migration1
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
            ((TopicType) topic).addDataField(timeCreatedField);
            ((TopicType) topic).addDataField(timeModifiedField);
        }
        //
        // add a timestamp to the topic being created
        logger.info("Adding timestamp to " + topic);
        long time = System.currentTimeMillis();
        topic.setProperty("time_created", time);
        topic.setProperty("time_modified", time);
    }

    @Override
    public void preUpdateHook(Topic topic) {
        long time = System.currentTimeMillis();
        topic.setProperty("time_modified", time);
    }

    // ---

    @Override
    public void providePropertiesHook(Topic topic) {
        topic.setProperty("time_modified", dms.getTopicProperty(topic.id, "time_modified"));
    }
}
