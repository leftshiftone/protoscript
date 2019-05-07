package protoscript.smtp.spec

import javax.mail.Message
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class SmtpHeaderSpec(private val message:MimeMessage) {

    fun to(address:String) = this.message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(address))
    fun cc(address:String) = this.message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(address))
    fun bcc(address:String) = this.message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(address))

}
