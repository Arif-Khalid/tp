package seedu.duke.commands;

import seedu.duke.Account;
import seedu.duke.AccountList;
import seedu.duke.Currency;
import seedu.duke.constants.ErrorMessage;
import seedu.duke.constants.Message;
import seedu.duke.exceptions.InvalidBalanceCommandException;
import seedu.duke.exceptions.NoAccountException;
import seedu.duke.ui.Ui;

import java.util.HashMap;

/**
 * BalanceCommand is a subclass of the Command class that is used to 
 * handle the getBalance command by the user.
 */
public class BalanceCommand extends Command {
    private final String command;
    private final String ALL = "ALL";

    /**
     * @param command The full user input including the command word {@code balance SGD}.
     */
    public BalanceCommand(String command) {
        super(false, command);
        this.command = command.trim();
    }

    private String processCommand() throws InvalidBalanceCommandException {
        String[] words = command.split(" ");
        return switch (words.length) {
        case 1 -> ALL;
        case 2 -> words[1];
        default -> throw new InvalidBalanceCommandException();
        };
    }

    private Currency convertStringToEnum(String currency) throws IllegalArgumentException {
        return Currency.valueOf(currency);
    }

    private HashMap<Currency, Account> getBalance(String currencyString, AccountList account)
            throws NoAccountException {
        if (currencyString.equals(ALL)) {
            return account.getAccountHashMap();
        }
        Currency currency = convertStringToEnum(currencyString);
        return account.getBalance(currency);
    }

    private void printCurrencies(HashMap<Currency, Account> balances, Ui ui) {
        ui.printMessage(Message.BALANCE.getMessage());
        balances.forEach((currency, account) -> ui.printf("%s: %f\n", currency.name(), account.getBalance()));
    }

    /**
     * Gets the currencies from the AccountList and displays it onto the screen.
     */
    @Override
    public void execute(Ui ui, AccountList account) {
        try {
            String currencyString = processCommand();
            HashMap<Currency, Account> balances = getBalance(currencyString, account);
            printCurrencies(balances, ui);
        } catch (InvalidBalanceCommandException e) {
            System.out.println(ErrorMessage.MORE_THAN_ONE_CURRENCY_PROVIDED);
        } catch (IllegalArgumentException e) {
            System.out.println(ErrorMessage.INVALID_CURRENCY);
        } catch (NoAccountException e) {
            System.out.println(ErrorMessage.NO_SUCH_ACCOUNT);
        }
    }
}