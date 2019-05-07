package protoscript.smtp

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import protoscript.smtp.SmtpScript.Companion.smtp
import javax.mail.internet.InternetAddress

class SmtpScriptTest {

    @Test
    fun `test smtp script`() {
        val sessionConfig = { config:SmtpConfig ->
            config.host("localhost")
            config.port(2525)
            Unit
        }

        val spec = smtp(sessionConfig) {
            header("gaia@leftshift.one", "subject") {
                to("test@leftshift.one")
            }
            content {
                headline("Example smtp")
                text("this is an example smtp string")
            }
            attachment {
                pdf("".toByteArray(), "test")
            }
        }

        Assertions.assertEquals((spec.message.from[0] as InternetAddress).address, "gaia@leftshift.one")
        Assertions.assertEquals(spec.message.subject, "subject")
        Assertions.assertEquals((spec.message.allRecipients[0] as InternetAddress).address, "test@leftshift.one")
    }

}
