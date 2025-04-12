# Selenium Practice Lesson

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![GitHub last commit](https://img.shields.io/github/last-commit/cihat-kose/selenium-practice-lesson?style=for-the-badge)

## 💡 Introduction
Welcome to the **selenium-practice-lesson** repository! This project is designed for practical learning and hands-on exercises in UI test automation using Selenium. It’s ideal for those who want to strengthen their Selenium skills through real-world test scenarios.

## Table of Contents
- [Introduction](#-introduction)
- [Installation](#-installation)
- [Usage](#usage)
- [Features](#-features)
- [Dependencies](#-dependencies)
- [Configuration](#-configuration)
- [Documentation](#-documentation)
- [Examples](#-examples)
- [Troubleshooting](#troubleshooting)
- [Contributors](#-contributors)
- [Contributing](#-contributing)
- [License](#-license)

## 📥 Installation
1. Open IntelliJ IDEA.
2. Select **File > New > Project from Version Control**.
3. Enter the repository URL: `https://github.com/cihat-kose/selenium-practice-lesson.git` and click **Clone**.
4. Make sure you have JDK installed:
   - Go to **File > Project Structure > Project**, and set the correct JDK version.
5. Add dependencies using Maven:
   - Go to **File > Project Structure > Libraries**.
   - Add the following Maven artifacts:
     - `org.seleniumhq.selenium:selenium-java:latest`
     - `commons-io:commons-io:latest`
     - `org.slf4j:slf4j-nop:latest`

## ⚙️ Usage
1. Open the project in IntelliJ IDEA.
2. Navigate to the test directories containing practice lessons.
3. Run test classes or methods using IntelliJ’s test runner.
4. Modify or extend test cases as needed to enhance your understanding.

## ✨ Features
- Focused practice modules on essential Selenium skills.
- Hands-on test cases covering real-world UI elements and flows.
- Easy-to-understand structure for beginners and intermediate learners.

## 📦 Dependencies
- Java JDK 11 or newer
- Selenium WebDriver
- JUnit 5
- Commons IO
- SLF4J NOP

## 🔧 Configuration
All dependencies are managed using Maven. Ensure `pom.xml` includes the necessary libraries, and that your IDE recognizes the Maven configuration.

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

- Verify browser drivers (e.g. ChromeDriver) are installed and up-to-date.
- Check your Maven dependencies are correctly configured.
- Confirm your Java SDK and IDE settings are compatible with the project.
- Review console logs for stack traces and failure points.
- Open an issue on the GitHub repository with detailed information.

## 👥 Contributors
- [cihat-kose](https://github.com/cihat-kose) - Cihat Köse  
- [SefaKahramann](https://github.com/SefaKahramann) - Sefa Kahraman

## 🤝 Contributing
Want to improve the project? Great! Follow these steps:
1. Fork the repository.
2. Create a branch (`git checkout -b your-feature`).
3. Commit your changes (`git commit -m 'Add something awesome'`).
4. Push the branch (`git push origin your-feature`).
5. Submit a Pull Request.

## 📜 License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for full details.

For more information or to contribute, check out the [selenium-practice-lesson GitHub repository](https://github.com/cihat-kose/selenium-practice-lesson).
