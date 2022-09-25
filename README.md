[![CircleCI branch](https://img.shields.io/circleci/project/github/leftshiftone/protoscript/master.svg?style=flat-square)](https://circleci.com/gh/leftshiftone/protoscript)
[![GitHub tag (latest SemVer)](https://img.shields.io/github/tag/leftshiftone/protoscript.svg?style=flat-square)](https://github.com/leftshiftone/protoscript/tags)
[![Maven Central](https://img.shields.io/maven-central/v/one.leftshift.protoscript/protoscript-smtp?style=flat-square)](https://mvnrepository.com/artifact/one.leftshift.protoscript/protoscript-smtp)


# Protocol Scripts (a.k.a. protoscript)

## How to send an email via SMTP (BasicAuth)

```

        val sessionConfig = { config: SmtpConfig ->
            config.host(host)
            config.port(587)
            config.username(username)
            config.password(XXXXX)
        }

        val spec = smtp(sessionConfig) {
            header(username, "A subject") {
                to("xxxx@leftshift.one")
            }
            content {
                headline("Example smtp")
                text("this is an example smtp string")
            }
            attachment {
                pdf("".toByteArray(), "test")
            }
        }

        val executorService = ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, LinkedBlockingQueue())
        SmtpScript.send(spec, executorService).get()

```

## How to send an email via SMTP (OAuth: _Password Flow_)

At this moment only the OAuth flow "password" is implemented. 


```

        val sessionConfig = { config: SmtpConfig ->
            config.host(host)
            config.port(587)
            config.authenticator {
                clientId(clientId)
                clientSecret(clientSecret)
                username(username)
                password(password)
                scope(scopes)
                grantType(grantType)
                url(url)
            }
        }


        val spec = smtp(sessionConfig) {
            header(username, "Patricio Trabajador Bearer") {
                to("xxxxxx@leftshift.one")
            }
            content {
                headline("Example smtp")
                text("this is an example smtp string")
            }
            attachment {
                pdf("".toByteArray(), "test")
            }
        }

        val executorService = ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, LinkedBlockingQueue())
        SmtpScript.send(spec, executorService).get()

```



## Development

### Release
Releases are triggered locally. Just a tag will be pushed and CI pipelines take care of the rest.

#### Major
Run `./gradlew final -x sendReleaseEmail -Prelease.scope=major` locally.

#### Minor
Run `./gradlew final -x sendReleaseEmail -Prelease.scope=minor` locally.

#### Patch
Run `./gradlew final -x sendReleaseEmail -Prelease.scope=patch` locally.
