## What's inside the repository?


### 2nd task -> networking-tests directory:
- src/test/features -> for .feature files
- src/test/java/homework/RunCucumberTest.java -> Uses Cucumber engine and looks for feature files
- src/test/java/homework/StepDefinitions.java -> Code that executes the .feature steps

## Instruction to run tests
- Maven must be installed. I used Homebrew to install: brew install maven
- Run all tests: mvn test
- Run specific test based on the tag used in feature (given example): mvn test -Dcucumber.filter.tags="@ip_check"
