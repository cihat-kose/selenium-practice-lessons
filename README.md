# Selenium Practice Lessons

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-25A162?style=for-the-badge&logo=junit&logoColor=white)
![GitHub last commit](https://img.shields.io/github/last-commit/cihat-kose/selenium-practice-lessons?style=for-the-badge)

## 💡 Introduction
Welcome to the **selenium-practice-lessons** repository! This project is designed for practical learning and hands-on exercises in UI test automation using Selenium. It’s ideal for those who want to strengthen their Selenium skills through real-world test scenarios.

## Table of Contents
- [Introduction](#-introduction)
- [Installation](#-installation)
- [Usage](#-usage)
- [Features](#-features)
- [Dependencies](#-dependencies)
- [Documentation](#-documentation)
- [Examples](#-examples)
- [Troubleshooting](#-troubleshooting)
- [Contributors](#-contributors)
- [Contributing](#-contributing)
- [License](#-license)

## 📥 Installation

1. Open IntelliJ IDEA.
2. Select **File > New > Project from Version Control**.
3. Enter the repository URL: `https://github.com/cihat-kose/selenium-practice-lesson.git` and click **Clone**.
4. Wait for Maven to automatically download all dependencies.
5. Verify that **Project SDK** is set to **Java 21** (or newer):
- Go to **File → Project Structure → Project**
- Set **Project SDK** and **Language level** appropriately.
6. To confirm everything works, open the Maven panel and run: 
    ```bash
    mvn clean test
    ```
💡 *No manual JAR management is needed anymore — all dependencies are managed via Maven.*

## ⚙️ Usage
1. Open the project in IntelliJ IDEA.
2. Navigate to the `src/test/java` directory.
3. Run individual test classes or the entire test suite using:
    - Right-click on a test class and select **Run 'ClassName'**.
    - Or use the Maven command: 
    ```bash
    mvn clean test
    ```
4. Modify or add new tests to explore different Selenium use cases.


## ✨ Features
- Structured **Maven** project for easy dependency management.
- Focused practice modules on essential Selenium skills.
- Hands-on test cases covering real-world UI elements and flows.
- Easy-to-understand structure for beginners and intermediate learners.
- Demonstrates common **UI automation techniques** and patterns.

## 📦 Dependencies
Managed automatically through `pom.xml`:

| Dependency | Version | Purpose |
|-------------|----------|----------|
| `org.seleniumhq.selenium:selenium-java` | 4.35.0 | Core Selenium WebDriver |
| `junit:junit` | 4.13.2 | Test framework |
| `com.fasterxml.jackson.core:jackson-databind` | 2.20.0 | JSON processing |
| `com.fasterxml.jackson.datatype:jackson-datatype-jsr310` | 2.20.0 | Java 8+ date/time JSON support |
| `io.github.testervippro:record-video` | 2.1 | Record test execution videos |

## 📚 Documentation
Each test file includes inline documentation explaining the scenario and Selenium functions being used.

## 💡 Examples

### Core Selenium Techniques
- **Locating Elements**: Practice using ID, name, class, CSS Selector, and XPath.
- **Interaction Methods**: Perform clicks, typing, selections, and submissions.

### User Interaction
- **Action Class Examples**: Hovering, drag-and-drop, and composite actions.
- **Keyboard/Mouse Control**: Using Robot class for OS-level control.

### Wait Strategies
- **Explicit Waits**: Synchronize your tests with slow-loading elements.
- **Implicit Waits**: Set global wait defaults for driver operations.

### Multi-Context Handling
- **iFrames**: Switch to frames and interact with inner elements.
- **Window/Tab Management**: Handle multiple browser tabs or pop-ups.

### Extra Scenarios
- **File Upload**: Handle file dialogs using both Selenium and Robot.
- **Scrolling Techniques**: Scroll into view or to the bottom of the page.

## 🛠️ Troubleshooting
If you experience any issues:

- Make sure Maven dependencies are correctly downloaded (mvn clean install -U).
- Check that your browser drivers (e.g., ChromeDriver) are up-to-date.
- Ensure that your Project SDK is set correctly in IntelliJ.
- Review test logs and console output to identify failures.
- Still stuck? [Open an issue on GitHub](https://github.com/cihat-kose/selenium-practice-lessons/issues) and describe your problem in detail.

## 👥 Contributors
- [cihat-kose](https://github.com/cihat-kose) – Cihat Köse  
- [SefaKahramann](https://github.com/SefaKahramann) – Sefa Kahraman

## 🤝 Contributing
Contributions are welcome! Please follow these steps:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Create a Pull Request.

## 📜 License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

For more info, visit the [selenium-practice-lessons GitHub page](https://github.com/cihat-kose/selenium-practice-lessons).
