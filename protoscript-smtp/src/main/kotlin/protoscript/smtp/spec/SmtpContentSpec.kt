package protoscript.smtp.spec

class SmtpContentSpec(private val builder:StringBuilder) {

    fun headline(text:String) = builder.append("<h1>$text</h1>")
    fun bold(text:String) = builder.append("<b>$text</b>")
    fun text(text:String) = builder.append("<span>$text</span>")
    fun linebreak() = builder.append("<br/>")
    fun paragraph(config: SmtpContentSpec.() -> Unit) {
        builder.append("<p>")
        SmtpContentSpec(builder).apply(config)
        builder.append("</p>")
    }

}
