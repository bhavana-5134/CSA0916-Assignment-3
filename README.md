# Q1 - Handling Failure in a Bank ATM Simulation

## Requirements
- JDK 17 or later (JDK 11+ should also work)
- VS Code with the Extension Pack for Java
- `ATMWithdrawal.java` and `card.txt` in the same folder

## Run in VS Code
1. Open this folder (`Q1_ATM_Exception`) in VS Code.
2. Open `ATMWithdrawal.java`.
3. Click **Run** above `main`, or use the terminal commands below.

## Run in the VS Code terminal
```bash
javac ATMWithdrawal.java
java ATMWithdrawal
```
Keep `card.txt` in the current working directory when running the program.

## Suggested test cases
| Test | Account number | Amount | Expected path |
|---|---|---:|---|
| 1 | 123456 | 500 | Successful withdrawal |
| 2 | 123456 | abc | NumberFormatException catch |
| 3 | 123456 | -100 | IllegalArgumentException catch |
| 4 | 123456 | 6000 | InsufficientFundsException catch |
| 5 | 12A456 | 100 | IllegalArgumentException catch |
| 6 | 123456 | 100 | Delete/rename card.txt first; FileNotFoundException catch |

For Test 6, restore `card.txt` after the test.

## GitHub upload
From the parent folder of `Q1_ATM_Exception`, run:
```bash
git init
git add .
git commit -m "Add Q1 ATM exception handling program"
git branch -M main
git remote add origin https://github.com/YOUR-USERNAME/CSA09-YOUR-REGISTER-NUMBER-Assignment3.git
git push -u origin main
```
Replace the URL with your own repository URL. If you already initialized Git in the repository root, do not run `git init` again; use `git add`, `git commit`, and `git push`.

## Important
This is a Q1 starter implementation. Run every test yourself and capture your own screenshots. Add your own name, register number, Java version, test observations, and stack-trace evidence to the report. The assignment requires each student to execute all tasks in their own repository.
