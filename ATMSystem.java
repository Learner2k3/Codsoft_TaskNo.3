import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds. Withdrawal failed.");
            return false;
        }
        balance -= amount;
        return true;
    }
}

class ATM {
    private BankAccount userAccount;

    public ATM(BankAccount account) {
        this.userAccount = account;
    }

    public void displayMenu() {
        System.out.println("ATM Menu:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("0. Exit");
    }

    public void processTransaction(int option) {
        Scanner scanner = new Scanner(System.in);

        switch (option) {
            case 1:
                handleWithdrawal(scanner);
                break;

            case 2:
                handleDeposit(scanner);
                break;

            case 3:
                checkBalance();
                break;

            case 0:
                System.out.println("Exiting. Thank you!");
                System.exit(0);
                break;

            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private void handleWithdrawal(Scanner scanner) {
        System.out.print("Enter withdrawal amount: ");
        double withdrawAmount = scanner.nextDouble();
        if (withdrawAmount > 0) {
            if (userAccount.withdraw(withdrawAmount)) {
                System.out.println("Withdrawal successful. Remaining balance: " + userAccount.getBalance());
            }
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    private void handleDeposit(Scanner scanner) {
        System.out.print("Enter deposit amount: ");
        double depositAmount = scanner.nextDouble();
        if (depositAmount > 0) {
            userAccount.deposit(depositAmount);
            System.out.println("Deposit successful. Updated balance: " + userAccount.getBalance());
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    private void checkBalance() {
        System.out.println("Current balance: " + userAccount.getBalance());
    }
}

public class ATMSystem {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0);
        ATM atm = new ATM(userAccount);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            atm.displayMenu();
            System.out.print("Enter your choice: ");
            int option = scanner.nextInt();
            atm.processTransaction(option);
        }
    }
}
