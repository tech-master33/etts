# Contributing to ETTS

First off, thank you for considering contributing to ETTS! It's people like you that make ETTS such a great tool for blind users.

## Code of Conduct

This project and everyone participating in it is governed by our [Code of Conduct](CODE_OF_CONDUCT.md). By participating, you are expected to uphold this code.

## How Can I Contribute?

### Reporting Bugs

Before creating bug reports, please check the issue list as you might find out that you don't need to create one. When you are creating a bug report, please include as many details as possible:

* **Use a clear and descriptive title**
* **Describe the exact steps which reproduce the problem**
* **Provide specific examples to demonstrate the steps**
* **Describe the behavior you observed after following the steps**
* **Explain which behavior you expected to see instead and why**
* **Include screenshots and animated GIFs if possible**
* **Include your Android version and device model**
* **Include relevant logcat output**

### Suggesting Enhancements

Enhancement suggestions are tracked as GitHub issues. When creating an enhancement suggestion, please include:

* **Use a clear and descriptive title**
* **Provide a step-by-step description of the suggested enhancement**
* **Provide specific examples to demonstrate the steps**
* **Describe the current behavior and the proposed behavior**
* **Explain why this enhancement would be useful to blind users**

### Pull Requests

* Fill in the required template
* Follow the Kotlin/Java style guides
* Include appropriate test cases
* Update documentation as needed
* End all files with a newline

## Development Setup

1. **Fork and clone the repository**
```bash
git clone https://github.com/YOUR_USERNAME/etts.git
cd etts
git remote add upstream https://github.com/tech-master33/etts.git
```

2. **Create a branch for your feature**
```bash
git checkout -b feature/your-feature-name
```

3. **Set up development environment**
```bash
# Install Android Studio
# Install Android NDK via SDK Manager
# Install Java 11
```

4. **Make your changes**
```bash
# Edit files
./gradlew ktlint  # Check Kotlin style
./gradlew test    # Run tests
./gradlew build   # Build project
```

5. **Test on device or emulator**
```bash
./gradlew installDebug
```

## Testing Guidelines

### Unit Tests
```bash
./gradlew test
```

### Manual Testing
1. Install debug APK on device
2. Enable TalkBack (Settings > Accessibility > TalkBack)
3. Test basic TTS functionality
4. Test speech rate adjustment
5. Test pitch adjustment
6. Test with multiple languages

### Accessibility Testing
- [ ] Tested with TalkBack enabled
- [ ] Tested keyboard-only navigation
- [ ] Tested with high contrast mode
- [ ] Tested with minimum font size

## Style Guides

### Git Commit Messages

* Use the present tense ("Add feature" not "Added feature")
* Use the imperative mood ("Move cursor to..." not "Moves cursor to...")
* Limit the first line to 72 characters or less
* Reference issues and pull requests liberally after the first line
* Example:
```
Add TTS engine initialization

- Initialize eSpeak NG with default settings
- Load English language support
- Set default speech rate to 175 WPM

Closes #42
```

### Kotlin/Java Style Guide

* Follow [Google's Kotlin Style Guide](https://developer.android.com/kotlin/style-guide)
* Use `ktlint` for formatting: `./gradlew ktlint -F`
* Maximum line length: 120 characters
* Use meaningful variable names
* Add comments for complex logic

### Documentation

* Use Markdown for all documentation
* Include code examples where helpful
* Keep documentation up-to-date with code changes
* Document accessibility features and considerations

## Build Process

1. **Clean build**
```bash
./gradlew clean
```

2. **Build debug APK**
```bash
./gradlew assembleDebug
```

3. **Build release APK**
```bash
./gradlew assembleRelease
```

## Continuous Integration

When you submit a pull request, several automated checks will run:

* **Build**: Gradle build check
* **Lint**: Code quality checks
* **Tests**: Unit test execution
* **Security**: Vulnerability scanning

All checks must pass before merging.

## Release Process

1. Update version in `build.gradle`
2. Update `README.md` with changes
3. Create git tag: `git tag -a v1.0.0 -m "Version 1.0.0"`
4. Push tag: `git push upstream v1.0.0`
5. GitHub Actions will automatically create a release

## Questions?

Feel free to open an issue with the `question` label or contact the maintainers.

## Recognition

Contributors will be recognized in:
* README.md Contributors section
* Release notes
* GitHub contributors page

Thank you for contributing to make ETTS better for blind users everywhere! 🎉
