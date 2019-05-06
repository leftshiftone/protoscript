package protoscript.smtp.spec

import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart

class SmtpSpec(val message: MimeMessage) {

    private val multipart = MimeMultipart()

    init {
        message.setContent(multipart)
    }

    fun header(from: String, subject: String, config: SmtpHeaderSpec.() -> Unit) {
        message.setFrom(from)
        message.setSubject(subject)
        SmtpHeaderSpec(message).apply(config)
    }

    fun content(config: SmtpContentSpec.() -> Unit) {
        val builder = StringBuilder()
        SmtpContentSpec(builder).apply(config)

        val bodyPart = MimeBodyPart()
        bodyPart.setContent(builder, "text/html")

        multipart.addBodyPart(bodyPart, 0)
    }

    fun attachment(config: SmtpAttachmentSpec.() -> Unit) {
        SmtpAttachmentSpec(multipart).apply(config)
    }

}
