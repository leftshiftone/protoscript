package protoscript.smtp.spec

import javax.activation.DataHandler
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMultipart
import javax.mail.util.ByteArrayDataSource

class SmtpAttachmentSpec(private val multipart: MimeMultipart) {

    companion object {
        const val MIMETYPE_JPG = "image/jpg"
        const val MIMETYPE_XML = "application/xml"
        const val MIMETYPE_SVG = "image/svg+xml"
        const val MIMETYPE_PNG = "image/png"
        const val MIMETYPE_PDF = "application/pdf"
        const val MIMETYPE_ZIP = "application/zip"
        const val MIMETYPE_DOC = "application/msword"
        const val MIMETYPE_DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
        const val MIMETYPE_XLS = "application/msexcel"
        const val MIMETYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        const val MIMETYPE_PPT = "application/mspowerpoint"
        const val MIMETYPE_PPTX = "application/vnd.openxmlformats-officedocument.presentationml.presentation"
    }

    fun jpg(bytes: ByteArray, name: String) = multipart.addBodyPart(toMimeBodyPart(bytes, MIMETYPE_JPG, "${name}.jpg"))
    fun xml(bytes: ByteArray, name: String) = multipart.addBodyPart(toMimeBodyPart(bytes, MIMETYPE_XML, "${name}.xml"))
    fun svg(bytes: ByteArray, name: String) = multipart.addBodyPart(toMimeBodyPart(bytes, MIMETYPE_SVG, "${name}.svg"))
    fun png(bytes: ByteArray, name: String) = multipart.addBodyPart(toMimeBodyPart(bytes, MIMETYPE_PNG, "${name}.png"))
    fun pdf(bytes: ByteArray, name: String) = multipart.addBodyPart(toMimeBodyPart(bytes, MIMETYPE_PDF, "${name}.pdf"))
    fun zip(bytes: ByteArray, name: String) = multipart.addBodyPart(toMimeBodyPart(bytes, MIMETYPE_ZIP, "${name}.zip"))
    fun doc(bytes: ByteArray, name: String) = multipart.addBodyPart(toMimeBodyPart(bytes, MIMETYPE_DOC, "${name}.doc"))
    fun docx(bytes: ByteArray, name: String) = multipart.addBodyPart(toMimeBodyPart(bytes, MIMETYPE_DOCX, "${name}.docx"))
    fun xls(bytes: ByteArray, name: String) = multipart.addBodyPart(toMimeBodyPart(bytes, MIMETYPE_XLS, "${name}.xls"))
    fun xlsx(bytes: ByteArray, name: String) = multipart.addBodyPart(toMimeBodyPart(bytes, MIMETYPE_XLSX, "${name}.xlsx"))
    fun ppt(bytes: ByteArray, name: String) = multipart.addBodyPart(toMimeBodyPart(bytes, MIMETYPE_PPT, "${name}.ppt"))
    fun pptx(bytes: ByteArray, name: String) = multipart.addBodyPart(toMimeBodyPart(bytes, MIMETYPE_PPTX, "${name}.pptx"))

    private fun toMimeBodyPart(bytes: ByteArray, mimeType: String, name: String): MimeBodyPart {
        val part = MimeBodyPart()
        part.dataHandler = DataHandler(ByteArrayDataSource(bytes, mimeType))
        part.fileName = name

        return part
    }

}
