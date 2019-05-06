package protoscript.smtp

import protoscript.smtp.spec.SmtpSpec
import javax.mail.Message
import javax.mail.Transport
import javax.mail.internet.MimeMessage

class SmtpScript {

    companion object {
        @JvmStatic
        fun send(message:Message) = Transport.send(message)

        @JvmStatic
        fun smtp(sessionConfig: SmtpConfig, spec: SmtpSpec.() -> Unit):Message {
            val message = MimeMessage(sessionConfig.openSession())
            SmtpSpec(message).apply(spec)
            return message
        }

        @JvmStatic
        fun smtp(sessionConfig: (SmtpConfig) -> Unit, spec: SmtpSpec.() -> Unit):Message {
            return smtp(SmtpConfig().apply(sessionConfig), spec)
        }
    }

}
