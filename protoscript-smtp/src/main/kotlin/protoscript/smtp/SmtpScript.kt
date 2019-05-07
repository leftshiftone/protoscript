package protoscript.smtp

import io.reactivex.Flowable
import org.reactivestreams.Publisher
import protoscript.smtp.spec.SmtpSpec
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future
import javax.mail.Transport
import javax.mail.internet.MimeMessage

class SmtpScript {

    companion object {
        @JvmStatic
        fun send(message: SmtpSpec, executorService: ExecutorService): Future<Void> {
            @Suppress("UNCHECKED_CAST")
            return executorService.submit { Transport.send(message.message) } as Future<Void>
        }

        @JvmStatic
        fun stream(message: SmtpSpec): Publisher<Unit> {
            return Flowable.fromCallable { Transport.send(message.message) }
        }

        // @JvmStatic
        // fun smtp(config: SmtpConfig, spec: SmtpSpec.() -> Unit): Message {
        //     val message = MimeMessage(config.openSession())
        //     SmtpSpec(message).apply(spec)
        //     return message
        // }

        @JvmStatic
        fun smtp(configurer: (SmtpConfig) -> Unit, spec: SmtpSpec.() -> Unit): SmtpSpec {
            val message = MimeMessage(SmtpConfig().apply(configurer).openSession())
            return SmtpSpec(message).apply(spec)
        }

        // @JvmStatic
        // fun smtp(config: Consumer<SmtpConfig>, spec: Consumer<SmtpSpec>): Message {
        //     val cfg = SmtpConfig()
        //     config.accept(cfg)
        //     return smtp(cfg, spec::accept)
        // }
    }

}
