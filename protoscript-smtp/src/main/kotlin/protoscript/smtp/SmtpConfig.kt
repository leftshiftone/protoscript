package protoscript.smtp

import java.util.*
import java.util.concurrent.atomic.AtomicReference
import javax.mail.Authenticator
import javax.mail.PasswordAuthentication
import javax.mail.Session

class SmtpConfig : Properties() {

    private val username = AtomicReference<String>()
    private val password = AtomicReference<String>()

    fun auth(auth:Boolean) = put("mail.smtp.auth", auth)
    fun startTls(startTls: Boolean) = put("mail.smtp.starttls.enable", startTls)
    fun host(host:String) = put("mail.smtp.host", host)
    fun port(port:Int) = put("mail.smtp.port", port)
    fun sslTrust(sslTrust:String) = put("mail.smtp.ssl.trust", sslTrust)

    fun username(username:String) = this.username.set(username)
    fun username() = this.username.get()

    fun password(password:String) = this.password.set(password)
    fun password() = this.password.get()

    fun openSession() = Session.getInstance(this, PasswordAuthenticator(this))

    private class PasswordAuthenticator(val config: SmtpConfig) : Authenticator() {
        override fun getPasswordAuthentication(): PasswordAuthentication {
            return PasswordAuthentication(config.username(), config.password())
        }
    }

}
