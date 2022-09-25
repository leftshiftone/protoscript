package protoscript.smtp

import com.sun.org.slf4j.internal.LoggerFactory
import javax.mail.Authenticator
import javax.mail.PasswordAuthentication

class OAuthAuthenticator(private val config: MutableMap<String, String>) : Authenticator() {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java.enclosingClass)
    }

    override fun getPasswordAuthentication(): PasswordAuthentication {
        val response = khttp.post(url = config["url"]!!, data = config)
        try {
            val token = response.jsonObject.get("access_token") as String
            return PasswordAuthentication(config["username"], token)
        } catch (ex: Exception) {
            log.error("Bearer token could not be retrieved", ex)
            throw RuntimeException("Bearer token could not be retrieved", ex)
        }
    }
}