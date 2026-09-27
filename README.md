# Selenium Practice Lessons

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-4.49.0-43B02A?style=for-the-badge&logo=selenium&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-4.13.2-25A162?style=for-the-badge&logo=junit&logoColor=white)
![GitHub last commit](https://img.shields.io/github/last-commit/cihat-kose/selenium-practice-lessons?style=for-the-badge)

## Introduction

This repository contains hands-on Selenium WebDriver practice examples written in
Java and JUnit 4. The lessons demonstrate common UI automation techniques through
small, focused examples using public and demo websites.

This is an **educational practice repository**, not a production-ready automation
framework. The examples intentionally favor clarity and experimentation over
enterprise framework architecture.

## Technology Stack

| Technology | Version |
| --- | --- |
| Java | 21 |
| Maven Wrapper | 3.3.4 |
| Maven | 3.9.11 |
| Selenium Java | 4.49.0 |
| JUnit | 4.13.2 |
| Maven Compiler Plugin | 3.16.0 |
| Maven Surefire Plugin | 3.6.0 |
| Maven Compiler Plugin | 3.13.0 |
| Maven Surefire Plugin | 3.2.5 |

## Repository Structure

The project uses the standard Maven test-source layout:

```text
src/test/java/
├── _01_SeleniumIntro/
├── _02_Locators/
├── _03_CssSelector/
├── _04_XPath/
├── _05_Select_ElementInStatus/
├── _06_Actions/
├── _07_Alerts/
├── _08_Waits/
├── _09_IFrames/
├── _10_Scroll/
├── _11_Windows/
├── _12_RobotClass/
├── _13_ShadowDom/
├── _13_WebDriverBiDi/
└── utility/
```

`Task` and `Summary` files are educational notes that accompany the lesson
examples. Local HTML and upload fixtures are kept in `src/test/resources/`; the
existing iframe and file-upload fixtures remain part of the project.

## Lessons and Topics

- Selenium introduction and registration flow
- Locator strategies, CSS selectors, and XPath
- Select/dropdown handling
- Actions, keyboard, and mouse interactions
- JavaScript alerts
- Implicit, explicit, and fluent waits
- iFrames
- Scrolling
- Windows and tabs
- File uploads
- OS-level interaction with the Java `Robot` class
- Selenium 4 Shadow DOM access through `WebElement.getShadowRoot()`
- WebDriver BiDi console-event listening over the browser WebSocket connection

## Setup

Prerequisites:

- Java 21
- A local Maven-compatible environment using the included Maven Wrapper
- Google Chrome for browser examples
- Internet access for the public/demo websites used by the lessons

Clone the repository and use the Maven Wrapper as the preferred build entry point.
No manual JAR management is required.

### Compile the lesson sources

Windows PowerShell:

```powershell
.\mvnw.cmd test-compile
```

macOS/Linux:

```bash
./mvnw test-compile
```

`test-compile` is the safe build validation command. It compiles the Java test
sources but does not launch browsers or execute Selenium tests.

### Run one lesson

Windows PowerShell:

```powershell
.\mvnw.cmd "-Dtest=_02_Locators.LocatorStrategiesExample" test
```

This starts a browser and runs one lesson class. Browser examples may depend on
live/demo websites, network availability, and the current behavior of those sites.
Running the complete suite is therefore not equivalent to a stable CI test suite.

The Shadow DOM and WebDriver BiDi lessons use local HTML fixtures. The BiDi
lesson enables Selenium's `webSocketUrl` capability and requires a compatible
local Chrome and ChromeDriver.

### Run every lesson test

Windows PowerShell:

```powershell
.\mvnw.cmd "-Dtest=*" test
```

The wildcard includes lesson classes whose names do not match Maven Surefire's
default `*Test` naming pattern. The full run includes Robot lessons that open
desktop windows and interact with the system clipboard. Both file-upload lessons
use the checked-in `src/test/resources/upload-sample.txt` fixture, so no personal
desktop path needs to be edited. The search lessons use DuckDuckGo because Google
may present an automated-traffic check that blocks the exercise.

The native file-picker lesson (`FileUploadWithRobot`) needs an interactive
desktop session because Java's `Robot` sends keys to the operating system's file
dialog. In headless or remote test sessions, use `FileUploadWithWebDriverLetcode`
to see the reliable `input[type=file].sendKeys(...)` approach. Run the Robot
example on a desktop with the command:

```powershell
.\mvnw.cmd "-Dtest=_12_RobotClass.FileUploadWithRobot" test
```

## Browser Driver and Lifecycle

The current Selenium setup does not require a manually configured ChromeDriver
path. Selenium Manager handles driver resolution for the local browser
environment. Browser compatibility is not universal, and startup can still be
affected by the installed browser, operating system, or local environment.

`utility.BaseDriver` provides the educational browser lifecycle:

- `@Before` creates a fresh browser for each test.
- `@After` guarantees cleanup when a test fails or does not close the browser itself.
- `waitAndClose()` is intentionally retained in lesson examples.
- Its short three-second delay lets students observe the result before the browser
  closes.

These mechanisms serve different educational purposes and intentionally coexist.

## GitHub Actions

GitHub Actions provides build validation through
`.github/workflows/build.yml`. The workflow:

- uses Java 21,
- uses the repository Maven Wrapper,
- runs `./mvnw -B -ntp test-compile`, and
- validates test-source compilation only.

The CI workflow does not run the live Selenium suite, launch browsers, or execute
the desktop/Robot examples.

## Known Limitations

- External and demo websites can change or become unavailable.
- Browser examples require network access and a compatible local browser setup.
- Examples use visible browser interaction rather than a CI-oriented headless design.
- Robot examples depend on desktop and operating-system behavior.
- WebDriver BiDi support depends on the installed browser and driver versions.
- Some examples intentionally use simple or hard-coded educational data.

## Contributors

- [cihat-kose](https://github.com/cihat-kose) – Cihat Köse
- [SefaKahramann](https://github.com/SefaKahramann) – Sefa Kahraman

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Create a Pull Request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file
for details.

For more information, visit the
[selenium-practice-lessons GitHub page](https://github.com/cihat-kose/selenium-practice-lessons).
