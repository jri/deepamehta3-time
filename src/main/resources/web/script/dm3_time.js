function dm3_time() {

    register_field_renderer("/de.deepamehta.3-time/script/timestamp_field_renderer.js")
    css_stylesheet("/de.deepamehta.3-time/style/dm3-time.css")



    /**************************************************************************************************/
    /**************************************** Overriding Hooks ****************************************/
    /**************************************************************************************************/



    this.init = function() {
        $("#searchmode-select").append($("<option>").text("By Time"))
    }

    this.search_widget = function(searchmode) {
        if (searchmode == "By Time") {
            return ui.menu("time_select", undefined, [
                {label: "Last week",  value:  7},
                {label: "Last month", value: 30}
            ]).dom
        }
    }

    this.search = function(searchmode) {
        if (searchmode == "By Time") {
            // 1) perform time search
            var mode_item = ui.menu_item("time_select")
            var upper_date = new Date().getTime()
            var lower_date = upper_date - mode_item.value * 24 * 60 * 60 * 1000
            var query = "[" + lower_date + " TO " + upper_date + "]"
            return dmc.search_topics(undefined, query, "de/deepamehta/core/property/DateModified", true)
        }
    }

    this.render_topic_list_item = function(topic, list_item) {
        var timestamp = get_value(topic, "de/deepamehta/core/property/DateModified")
        // alert(topic.properties.time_modified + " (" + typeof(topic.properties.time_modified) + ") => " + time)
        var time_div = $("<div>").addClass("result-item-time").append(format_timestamp(timestamp))
        return list_item.append(time_div)
    }
} 
