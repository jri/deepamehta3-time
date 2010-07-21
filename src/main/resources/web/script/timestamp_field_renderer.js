function TimestampFieldRenderer(doc, field, rel_topics) {

    this.render_field = function() {
        // field label
        render.field_label(field)
        // field value
        return format_timestamp(get_value(doc, field.uri))
    }
}
