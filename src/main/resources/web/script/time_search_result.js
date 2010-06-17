function TimeSearchResult() {
}

TimeSearchResult.prototype = {

    __proto__: SearchResult.prototype,

    // override
    render_function: function() {
        return this.render_topic_with_timestamp
    },

    render_topic_with_timestamp: function(topic) {
        // call default render function
        var topic_div = render_topic(topic)
        // append timestamp
        var time = TimeSearchResult.prototype.format_time(topic.time_modified)
        var time_div = $("<div>").addClass("result-item-time").append(time)
        return topic_div.append(time_div)
    },

    format_time: function(rfc3339_timestamp) {
        if (rfc3339_timestamp) {
            var date = rfc3339_timestamp.substr(0, 10).replace(/-/g, "/")
            var time = rfc3339_timestamp.substr(11, 5)
            return date + "&nbsp;&nbsp;&nbsp;" + time
        } else {
            return "?"
        }
    }
}
