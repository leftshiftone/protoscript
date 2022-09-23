package protoscript.smtp

import javax.mail.Authenticator
import javax.mail.PasswordAuthentication

class OAuthAuthenticator(private val config: MutableMap<String, String>) : Authenticator() {

    override fun getPasswordAuthentication(): PasswordAuthentication {
        val response = khttp.post(
            url = config["url"]!!,
            data = config
        )
        val token = response.jsonObject.get("access_token") as String //TODO check cast
        return PasswordAuthentication(config["username"], token)
    }
}