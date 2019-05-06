package protoscript.smtp

import io.reactivex.Flowable
import org.reactivestreams.Publisher
import protoscript.smtp.spec.SmtpSpec
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future
import javax.mail.Message
import javax.mail.Transport
import javax.mail.internet.MimeMessage

class SmtpScript {

    companion object {
        @JvmStatic
        fun send(message: Message, executorService: ExecutorService): Future<Void> {
            @Suppress("UNCHECKED_CAST")
            return executorService.submit { Transport.send(message) } as Future<Void>
        }

        fun stream(message:Message):Publisher<Unit> {
            return Flowable.fromCallable { Transport.send(message) }
        }

        @JvmStatic
        fun smtp(sessionConfig: SmtpConfig, spec: SmtpSpec.() -> Unit): Message {
            val message = MimeMessage(sessionConfig.openSession())
            SmtpSpec(message).apply(spec)
            return message
        }

        @JvmStatic
        fun smtp(sessionConfig: (SmtpConfig) -> Unit, spec: SmtpSpec.() -> Unit): Message {
            return smtp(SmtpConfig().apply(sessionConfig), spec)
        }
    }

}
