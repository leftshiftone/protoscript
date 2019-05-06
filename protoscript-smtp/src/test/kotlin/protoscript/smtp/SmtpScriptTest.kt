package protoscript.smtp

import protoscript.smtp.SmtpScript.Companion.smtp
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import protoscript.smtp.SmtpScript.Companion.send
import javax.mail.internet.InternetAddress

class SmtpScriptTest {

    @Test
    fun `test smtp script`() {
        val sessionConfig = SmtpConfig()
        sessionConfig.host("localhost")
        sessionConfig.port(2525)

        val message = smtp(sessionConfig) {
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

        Assertions.assertEquals((message.from[0] as InternetAddress).address, "gaia@leftshift.one")
        Assertions.assertEquals(message.subject, "subject")
        Assertions.assertEquals((message.allRecipients[0] as InternetAddress).address, "test@leftshift.one")
    }

}
