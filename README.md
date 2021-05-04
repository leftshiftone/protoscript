[![CircleCI branch](https://img.shields.io/circleci/project/github/leftshiftone/protoscript/master.svg?style=flat-square)](https://circleci.com/gh/leftshiftone/protoscript)
[![GitHub tag (latest SemVer)](https://img.shields.io/github/tag/leftshiftone/protoscript.svg?style=flat-square)](https://github.com/leftshiftone/protoscript/tags)
[![Maven Central](https://img.shields.io/maven-central/v/one.leftshift.protoscript/protoscript-smtp?style=flat-square)](https://mvnrepository.com/artifact/one.leftshift.protoscript/protoscript-smtp)


# Protocol Scripts (a.k.a. protoscript)

## Development

### Release
Releases are triggered locally. Just a tag will be pushed and CI pipelines take care of the rest.

#### Major
Run `./gradlew final -x sendReleaseEmail -Prelease.scope=major` locally.

#### Minor
Run `./gradlew final -x sendReleaseEmail -Prelease.scope=minor` locally.

#### Patch
Run `./gradlew final -x sendReleaseEmail -Prelease.scope=patch` locally.
