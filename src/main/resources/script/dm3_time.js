function dm3_time() {

    doctype_implementation("/de.deepamehta.3-time/script/time_search_result.js")
    css_stylesheet("/de.deepamehta.3-time/style/dm3-time.css")



    /**************************************************************************************************/
    /**************************************** Overriding Hooks ****************************************/
    /**************************************************************************************************/



    this.init = function() {
        $("#searchmode_select").append($("<option>").text("By Time"))
    }

    this.search_widget = function(searchmode) {
        if (searchmode == "By Time") {
            return ui.menu("time_select", undefined, [
                {label: "Last week"},
                {label: "Last month"}
            ]).dom
        }
    }

    this.search = function(searchmode) {
        if (searchmode == "By Time") {
            // 1) perform time search
            var time_mode = ui.menu_item("time_select").label
            var result = db.view("deepamehta3/dm3-time", {descending: true})
            // 2) create result topic
            return create_result_topic(time_mode, result.rows, "TimeSearchResult", function(row) {
                return {
                    id:            row.id,
                    type:          row.value.topic_type,
                    label:         row.value.topic_label,
                    time_modified: row.key
                }
            })
        }
    }
}
