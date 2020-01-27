package protoscript.smtp.spec

import javax.activation.DataHandler
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMultipart
import javax.mail.util.ByteArrayDataSource

class SmtpAttachmentSpec(private val multipart: MimeMultipart) {

    fun jpg(bytes: ByteArray, name: String) = multipart.addBodyPart(toMimeBodyPart(bytes, "image/jpg", "${name}.jpg"))
    fun xml(bytes: ByteArray, name: String) = multipart.addBodyPart(toMimeBodyPart(bytes, "application/xml", "${name}.xml"))
    fun svg(bytes: ByteArray, name: String) = multipart.addBodyPart(toMimeBodyPart(bytes, "image/svg+xml", "${name}.svg"))
    fun png(bytes: ByteArray, name: String) = multipart.addBodyPart(toMimeBodyPart(bytes, "image/png", "${name}.png"))
    fun pdf(bytes: ByteArray, name: String) = multipart.addBodyPart(toMimeBodyPart(bytes, "application/pdf", "${name}.pdf"))
    fun zip(bytes: ByteArray, name: String) = multipart.addBodyPart(toMimeBodyPart(bytes, "application/zip", "${name}.zip"))

    private fun toMimeBodyPart(bytes: ByteArray, mimeType: String, name: String): MimeBodyPart {
        val part = MimeBodyPart()
        part.dataHandler = DataHandler(ByteArrayDataSource(bytes, mimeType))
        part.fileName = name

        return part
    }

}
