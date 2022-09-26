package protoscript.smtp

import javax.mail.Authenticator
import javax.mail.PasswordAuthentication

class OAuthAuthenticator(private val config: MutableMap<String, String>) : Authenticator() {

    override fun getPasswordAuthentication(): PasswordAuthentication {
        val response = khttp.post(url = config["url"]!!, data = config)
        try {
            val token = response.jsonObject.get("access_token") as String
            return PasswordAuthentication(config["username"], token)
        } catch (ex: Exception) {
            throw RuntimeException("Bearer token could not be retrieved", ex)
        }
    }
}