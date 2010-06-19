package de.deepamehta.plugins.time;

import de.deepamehta.core.model.DataField;
import de.deepamehta.core.model.Topic;
import de.deepamehta.core.model.TopicType;
import de.deepamehta.core.plugin.DeepaMehtaPlugin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;



public class TimePlugin extends DeepaMehtaPlugin {

    private Logger logger = Logger.getLogger(getClass().getName());



    // ************************
    // *** Overriding Hooks ***
    // ************************



    @Override
    public void preCreateHook(Topic topic, Map<String, String> clientContext) {
        logger.info("Adding timestamp to " + topic);
        //
        if (topic.typeId.equals("Topic Type")) {
            DataField timeCreatedField = new DataField("time_created").setIndexingMode("FULLTEXT_KEY");
            DataField timeModifiedField = new DataField("time_modified").setIndexingMode("FULLTEXT_KEY");
            ((TopicType) topic).addDataField(timeCreatedField);
            ((TopicType) topic).addDataField(timeModifiedField);
        }
        //
        String time = String.valueOf(System.currentTimeMillis());
        topic.setProperty("time_created", time);
        topic.setProperty("time_modified", time);
    }

    @Override
    public void preUpdateHook(Topic topic) {
        String time = String.valueOf(System.currentTimeMillis());
        topic.setProperty("time_modified", time);
    }

    // ---

    @Override
    public void providePropertiesHook(Topic topic) {
        topic.setProperty("time_modified", dms.getTopicProperty(topic.id, "time_modified"));
    }
}
