package de.deepamehta.plugins.time;

import de.deepamehta.core.model.Topic;
import de.deepamehta.core.plugin.DeepaMehtaPlugin;

import java.util.Date;
import java.util.logging.Logger;



public class TimePlugin extends DeepaMehtaPlugin {

    private Logger logger = Logger.getLogger(getClass().getName());

    // --- Client-Side ---

    @Override
    public int getCodeModelVersion() {
        return 1;
    }

    @Override
    public String getClientPlugin() {
        return "dm3_time.js";
    }

    // --- Server-Side ---

    @Override
    public void preCreateHook(Topic topic) {
        logger.info("preCreateHook invoked!");
        String time = new Date().toString();
        topic.setProperty("time_created", time);
        topic.setProperty("time_modified", time);
    }

    @Override
    public void preUpdateHook(Topic topic) {
        String time = new Date().toString();
        topic.setProperty("time_modified", time);
    }
}
