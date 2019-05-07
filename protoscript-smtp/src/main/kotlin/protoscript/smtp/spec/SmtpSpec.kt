package protoscript.smtp.spec

import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart

class SmtpSpec(val message: MimeMessage) {

    private val multipart = MimeMultipart()

    init {
        message.setContent(multipart)
    }

    fun header(from: String, subject: String, config: SmtpHeaderSpec.() -> Unit):SmtpSpec {
        message.setFrom(from)
        message.setSubject(subject)
        SmtpHeaderSpec(message).apply(config)
        return this
    }

    fun content(config: SmtpContentSpec.() -> Unit):SmtpSpec {
        val builder = StringBuilder()
        SmtpContentSpec(builder).apply(config)

        val bodyPart = MimeBodyPart()
        bodyPart.setContent(builder.toString(), "text/html")

        multipart.addBodyPart(bodyPart, 0)
        return this
    }

    fun attachment(config: SmtpAttachmentSpec.() -> Unit):SmtpSpec {
        SmtpAttachmentSpec(multipart).apply(config)
        return this
    }

}
