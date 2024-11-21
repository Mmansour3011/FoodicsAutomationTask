# FoodicsAutomationTask

**A comprehensive automation framework for testing the checkout flow on Foodics (or similar e-commerce platforms).**

## Features
* **Login:** Authenticates users to the platform.
* **Product Browsing:** Navigates to product categories and applies filters.
* **Cart Management:** Adds items to the cart and verifies their quantity and total price.
* **Checkout:** Proceeds to checkout, updates delivery address, and selects payment methods.
* **Cart Removal:** Removes items from the cart.

## Technologies
* **Cucumber:** Behavior-Driven Development (BDD) framework
* **Selenium WebDriver:** Web automation library
* **TestNG:** Testing framework
* **Allure:** Reporting framework

## Prerequisites
* **Java 11+**
* **Maven**
* **Web Browser** (Chrome, Firefox, etc.)

## Test Report
![1](https://github.com/user-attachments/assets/1cb52319-bb19-42cf-85a6-ec1b0c499a2c)
![2](https://github.com/user-attachments/assets/9090c569-424d-4658-aab0-3f296e23f20a)
![3](https://github.com/user-attachments/assets/521ab0ad-3a7c-4b7d-9871-93e8220cc2de)
![4](https://github.com/user-attachments/assets/a56a71a6-eec0-4187-b115-dad2033f82b5)

## Test logs
[logs.txt](https://github.com/user-attachments/files/17849325/logs.txt)

## Installation
1. **Clone the repository:**
   ```bash
   git clone [https://github.com/your-username/FoodicsAutomationTask.git](https://github.com/your-username/FoodicsAutomationTask.git)

2. **Navigate to the project directory:**
   ```bash
   cd FoodicsAutomationTask
3. **Install dependencies:**
   ```bash
   mvn install
## Usage

**Configure Login Credentials:**
* Update the `username` and `password` in `login.feature`.

**Run Tests:**
* **Maven:**
   ```bash
   mvn test
**IDE:** Run the `TestRunner` class.

# Steps to Install Allure

## Prerequisites
1. **Ensure Java is installed**:
   - Verify Java installation by running:  
     ```bash
     java -version
     ```
   - If not installed, download and install it from [Java Downloads](https://www.oracle.com/java/technologies/javase-downloads.html).

2. **Ensure Maven is installed**:
   - Verify Maven installation by running:  
     ```bash
     mvn -version
     ```
   - If not installed, download and install it from [Maven Downloads](https://maven.apache.org/download.cgi).

3. **Ensure Node.js is installed** (optional for specific plugins):
   - Verify Node.js installation by running:  
     ```bash
     node -v
     ```
   - If not installed, download and install it from [Node.js Downloads](https://nodejs.org/).

---

## Installation Steps

### Step 1: Install Allure Command-Line Tool
1. **Download Allure**:
   - Visit the [Allure GitHub Releases](https://github.com/allure-framework/allure2/releases) page and download the latest version.

2. **Extract Allure**:
   - Extract the downloaded archive to a preferred directory (e.g., `C:\allure` or `/opt/allure`).

3. **Add Allure to System PATH**:
   - Add the `bin` folder from the extracted directory to your system's PATH:
     - **Windows**:
       1. Open *Environment Variables* settings.
       2. Add the path to the `bin` folder in the *System Variables* section.
     - **Linux/Mac**:
       ```bash
       export PATH=$PATH:/path/to/allure/bin
       ```
       Add this line to your shell profile file (e.g., `~/.bashrc`, `~/.zshrc`).

4. **Verify Installation**:
   - Run the following command to ensure Allure is installed correctly:
     ```bash
     allure --version
     ```

---

### Step 2: Integrate Allure with Your Testing Framework
1. **Add Allure Dependencies**:
   - Add the appropriate Allure dependency to your `pom.xml` (for Maven).

     **Example for Maven**:
     ```xml
     <dependency>
         <groupId>io.qameta.allure</groupId>
         <artifactId>allure-testng</artifactId>
         <version>2.20.0</version>
     </dependency>
     ```

2. **Configure Allure**:
   - Ensure the test results are generated in a compatible format (e.g., XML or JSON).

3. **Run Tests**:
   - Execute your tests to generate Allure result files.

---

### Step 3: Generate and View Reports
1. **Generate Allure Report**:
   - Run the following command in your project directory where the results are stored:
     ```bash
     allure serve <path-to-results-directory>
     ```

2. **View Report**:
   - This will automatically open the Allure report in your default browser.

---


**Generating Allure Report**
1. **Run Tests:** Execute the tests using Maven or your IDE.
2. **Open Allure Report:** Once the tests finish, open the generated Allure report in your browser. The default location is `target/allure-results`.
3. **View Report:** Explore the detailed test results, including test cases, steps, attachments, and statistics.
