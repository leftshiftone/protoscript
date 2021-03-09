[![CircleCI branch](https://img.shields.io/circleci/project/github/leftshiftone/protoscript/master.svg?style=flat-square)](https://circleci.com/gh/leftshiftone/protoscript)
[![GitHub tag (latest SemVer)](https://img.shields.io/github/tag/leftshiftone/protoscript.svg?style=flat-square)](https://github.com/leftshiftone/protoscript/tags)
[![Bintray](https://img.shields.io/badge/dynamic/json.svg?label=bintray&query=name&style=flat-square&url=https%3A%2F%2Fapi.bintray.com%2Fpackages%2Fleftshiftone%2Fprotoscript%2Fone.leftshift.protoscript.protoscript-smtp%2Fversions%2F_latest)](https://bintray.com/leftshiftone/protoscript/one.leftshift.protoscript.protoscript-smtp/_latestVersion)

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
