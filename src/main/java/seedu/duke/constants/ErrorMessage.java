package seedu.duke.constants;

/**
 * A public interface that is used to contain all the error messages throughout the application.
 */
public interface ErrorMessage {
    String INVALID_ADD_COMMAND = "Please check that you have correctly provided the currency and amount";
    String INVALID_AMOUNT_TO_ADD = "Please provide a value greater than 0";
    String INVALID_CURRENCY = "An invalid currency has been provided.";
    String INVALID_NUMERICAL_AMOUNT = "Please provide a an integer for the amount to update";
    String MORE_THAN_ONE_CURRENCY_PROVIDED = "Please do not provide more than one currency.";
    String NO_SUCH_ACCOUNT = "You do not have an account for the currency.";
}
