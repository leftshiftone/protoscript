package protoscript.smtp

import org.junit.jupiter.api.Test
import protoscript.smtp.SmtpScript.Companion.smtp
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class ManualE2ESmtpScriptTest {

    private val smtpBasicAuth = SMTPConfig(
        host = "smtp.office365.com",
        username = "XXXXXX",
        password = "XXXXXX"
    )


    private val smtpOAuth = SMTPConfig(
        host = "smtp.office365.com",
        username = "XXXXXX",
        password = "XXXXXX",
        authenticator = OAuthConfig(
            grantType = "password",
            username = "XXXXXX",
            password = "XXXXXX",
            clientId = "XXXXXX",
            clientSecret = "XXXXXX",
            scopes = "https://outlook.office365.com/SMTP.Send",
            url = "https://login.microsoftonline.com/XXXXXX/oauth2/v2.0/token"
        )
    )


    @Test
    fun `test smtp script`() {
        val sessionConfig = { config: SmtpConfig ->
            config.host(smtpBasicAuth.host)
            config.port(587)
            config.username(smtpBasicAuth.username)
            config.password(smtpBasicAuth.password!!)
        }

        val spec = smtp(sessionConfig) {
            header(smtpBasicAuth.username, "A subject") {
                to("XXXXX@leftshift.one")
            }
            content {
                headline("Example smtp")
                text("this is an example smtp string")
            }
            attachment {
                pdf("".toByteArray(), "test")
            }
        }

        val executorService = ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, LinkedBlockingQueue())
        SmtpScript.send(spec, executorService).get()
    }


    @Test
    fun `test with OAuth Authenticator `() {
        val sessionConfig = { config: SmtpConfig ->
            config.host(smtpOAuth.host)
            config.port(587)
            config.authenticator {
                clientId(smtpOAuth.authenticator!!.clientId)
                clientSecret(smtpOAuth.authenticator.clientSecret)
                username(smtpOAuth.authenticator.username)
                password(smtpOAuth.authenticator.password)
                scope(smtpOAuth.authenticator.scopes)
                grantType(smtpOAuth.authenticator.grantType)
                url(smtpOAuth.authenticator.url)
            }
        }


        val spec = smtp(sessionConfig) {
            header(smtpOAuth.username, "Patricio Trabajador Bearer") {
                to("xxxxxx@leftshift.one")
            }
            content {
                headline("Example smtp")
                text("this is an example smtp string")
            }
            attachment {
                pdf("".toByteArray(), "test")
            }
        }

        val executorService = ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, LinkedBlockingQueue())
        SmtpScript.send(spec, executorService).get()
    }

}

data class SMTPConfig(
    val host: String,
    val username: String,
    val password: String?,
    val authenticator: OAuthConfig?=null
)

data class OAuthConfig(
    val grantType: String,
    val url: String,
    val scopes: String,
    val username: String,
    val password: String,
    val clientId: String,
    val clientSecret: String
)
