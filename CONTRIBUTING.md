Thanks for your interest in contributing to Zekai.

Zekai is a fork of [Mihon](https://github.com/mihonapp/mihon). Most of the code in this repository is upstream work. Contributions that fix a bug in shared code are best sent upstream first, so the fix reaches both projects.

# Code contributions

Pull requests are welcome.

If you want to take on an [open issue](https://github.com/zeeks024/zekai/issues), comment on it first so others know it is being worked on. You do not need to ask for permission.

## Prerequisites

* Basic [Android development](https://developer.android.com/)
* [Kotlin](https://kotlinlang.org/)

### Tools

* [Android Studio](https://developer.android.com/studio)
* An emulator or a phone with developer options enabled, to test changes.

## Building

Debug build:

```bash
./gradlew assembleDebug
```

Release build (the same command the CI workflow uses):

```bash
./gradlew assembleRelease -Pdist=ci
```

Before opening a pull request, run the checks the CI workflow runs:

```bash
./gradlew spotlessCheck
./gradlew testDebugUnitTest
./gradlew verifySqlDelightMigration
```

## Getting help

Ask in the [issue tracker](https://github.com/zeeks024/zekai/issues). There is no Discord server or other chat channel for Zekai.

# Translations

Translations for strings shared with upstream are managed upstream via Weblate. Do not edit translated string files in this repository. Add a new string in English in `i18n/src/commonMain/moko-resources/base/strings.xml` only if it is specific to Zekai.

# Forks

Forks are allowed so long as they abide by the project's [LICENSE](LICENSE). The upstream fork checklist is in the upstream repository's contributing guide. In short: change the app name, icon, and application ID, point the update checker at your own releases, and do not reuse the upstream project's Firebase configuration.
