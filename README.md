## What's inside the repository?

## 1st task:
- merge_sort.py -> contains all the functions and small examples in the end. Combines two lists and puts them in ascending order.

### Instructions to run the sort locally:
- Through terminal cd in to the directory where the merge_sort.py is
- Use command: "python3 (or python) merge_sort.py"

## 2nd task -> networking-tests directory:
- src/test/features -> for .feature files
- src/test/java/homework/RunCucumberTest.java -> Uses Cucumber engine and looks for feature files
- src/test/java/homework/StepDefinitions.java -> Code that executes the .feature steps

### Instruction to run tests locally through terminal:
- Maven must be installed. I used Homebrew to install: brew install maven
- Through terminal cd into the homework/ directory
- Run all tests: mvn clean test
- Run specific test based on the tag used in feature (given example): mvn clean test -Dcucumber.filter.tags="{Tag_from_feature}"
