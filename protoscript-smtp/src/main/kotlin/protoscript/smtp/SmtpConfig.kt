package protoscript.smtp

import protoscript.smtp.spec.OAuthSpec
import java.util.*
import java.util.concurrent.atomic.AtomicReference
import javax.mail.Authenticator
import javax.mail.PasswordAuthentication
import javax.mail.Session

class SmtpConfig : Properties() {

    private val username = AtomicReference<String>()
    private val password = AtomicReference<String>()
    private val authenticator = AtomicReference<Authenticator?>()

    init {
        auth(true)
        startTls(true)
        connectionTimeout(10000)
        timeout(10000)
        sslProtocols("TLSv1 TLSv1.1 TLSv1.2")
        put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
    }

    fun auth(auth: Boolean) = put("mail.smtp.auth", "$auth")
    fun startTls(startTls: Boolean) = put("mail.smtp.starttls.enable", "$startTls")
    fun sslProtocols(protocols: String) = put("mail.smtp.ssl.protocols", protocols)
    fun host(host:String) = put("mail.smtp.host", host)
    fun port(port:Int) = put("mail.smtp.port", port)
    fun sslTrust(sslTrust:String) = put("mail.smtp.ssl.trust", sslTrust)
    fun timeout(timeout:Int) = put("mail.smtp.timeout", "$timeout")
    fun connectionTimeout(timeout:Int) = put("mail.smtp.connectiontimeout", "$timeout")

    fun username(username:String) = this.username.set(username)
    fun username() = this.username.get()

    fun password(password:String) = this.password.set(password)
    fun password() = this.password.get()

    fun authenticator(configurer: OAuthSpec.() -> Unit){
        val mutableMap= mutableMapOf<String,String>()
        OAuthSpec(mutableMap).apply(configurer)
        put("mail.smtp.auth.mechanisms", "XOAUTH2")
        this.authenticator.set(OAuthAuthenticator(mutableMap))
    }

    fun openSession(): Session{
        if (this.authenticator.get() == null)
            return Session.getInstance(this, PasswordAuthenticator(this))
        return Session.getInstance(this, this.authenticator.get())
    }

    private class PasswordAuthenticator(val config: SmtpConfig) : Authenticator() {
        override fun getPasswordAuthentication(): PasswordAuthentication {
            return PasswordAuthentication(config.username(), config.password())
        }
    }

}
