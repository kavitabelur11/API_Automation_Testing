API_Automation_Testing/
│
├── pom.xml                         # Maven build file – manages dependencies & plugins
│
├── src/
│   ├── main/java/
│   │   └── base/                   # Base classes & utility methods
│   │       ├── BaseClass.java      # Common setup (base URI, headers, request specs)
│   │       └── Utils.java          # Helper methods (token parsing, logging, etc.)
│   │
│   └── test/
│       ├── java/
│       │   ├── stepDefinations/    # Step Definitions for Cucumber feature steps
│       │   ├── runners/            # TestRunner classes (Cucumber + JUnit)
│       │   └── apiTests/           # API logic classes and response validations
│       │
│       └── resources/
│           ├── features/           # Cucumber Gherkin feature files
│           │   └── placeValidations.feature
│           └── extent-config.xml   # ExtentReport configuration file
│
├── target/                         # Compiled classes, test reports & logs
│
├── logging.txt                     # Custom log file generated during test runs
│
└── .idea/, .git/, .mvn/            # IntelliJ & Git configuration folders

