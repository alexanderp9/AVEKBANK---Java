package AccountCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Customer extends Person{

    private long bankId;
    private List<BankAccount> accounts;

    public Customer(long bankId) {
        this.bankId = bankId;
        this.accounts = new ArrayList<>();
    }


    //Creating an account with createAccount() from class AccountFactory and adding to Customer List<BankAccount> accounts
    public void addAccountToList(AccountType accountType) {
        BankAccount newAccount = AccountFactory.createAccount(accountType);
        newAccount.setAccountNumber(getRandomizedAccountNr());
        accounts.add(newAccount);
    }

    public int getRandomizedAccountNr(){
        Random random = new Random();
        return random.nextInt(100000,999999);
    }

    public void deleteAccountInList(BankAccount bankAccount) {
        accounts.remove(0);  //More logic need for removing the correct account. Maybe with account.getAccountNumber
    }

    public long getBankId() {
        return bankId;
    }
    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public String printoutAccountsWithIndex() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < accounts.size(); i++) {
            BankAccount account = accounts.get(i);
            sb.append("Account choice: ").append(i+1).append(" - ")
                    .append(account.getAccountType()).append(" Account-number: ").append(account.getAccountNumber())
                    .append(", Balance: ").append(account.getBalance());
            if (account instanceof CreditCardAccount) {
                CreditCardAccount creditCardAccount = (CreditCardAccount) account;
                sb.append(", Kortnummer: ").append(creditCardAccount.getCardNumber()).append(", PIN-kod: ").append(creditCardAccount.getCardPIN());
            } else if (account instanceof SavingBankAccount) {
                SavingBankAccount savingBankAccount = (SavingBankAccount) account;
                sb.append(", Amount of years locked: ").append(savingBankAccount.getLockPeriod()).append(", Interest rate: ").append(savingBankAccount.getInterestRate()*100).append("%");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String printOutActualAccountBalance(int AccountIndex) {
        return "Balance: " + String.valueOf(accounts.get(AccountIndex - 1).getBalance());
    }
}
