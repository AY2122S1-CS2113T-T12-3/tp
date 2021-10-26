package seedu.utility;

/**
 * Contains all the warning messages to be sent to the user when an invalid input is given.
 */
public class Messages {
    public static final String INVALID_COMMAND_MESSAGE =
            "Invalid command. Use \"help\" to show the list of possible commands.";
    public static final String NON_NUMERIC_AMOUNT_MESSAGE = "Only numeric inputs are allowed for amount.";
    public static final String N0N_NUMERIC_INDEX_MESSAGE = "Only numeric inputs are allowed for index.";
    public static final String NON_POSITIVE_AMOUNT_MESSAGE = "Only positive values are allowed for amount.";
    public static final String NON_POSITIVE_INDEX_MESSAGE = "Only positive values are allowed for index.";
    public static final String BLANK_AMOUNT_MESSAGE = "No amount inputted!";
    public static final String UNABLE_TO_DELETE_MESSAGE = "Entry not deleted because entry not found!";
    public static final String BLANK_DESCRIPTION_MESSAGE = "Your description is empty!";
    public static final String HAS_CORRUPTED_DATA_ENTRIES = "StonksXD_Entries.csv has corrupted entries, " 
            + "some or all data maybe lost.";
    public static final String HAS_CORRUPTED_BUDGET_SETTINGS = "StonksXD_Budget.csv has corrupted settings, "
            + "some or all settings maybe lost.";
    public static final String UNABLE_TO_FIND_DATA_FILE = "Unable to find StonksXD_Entries.csv, a new one " 
            + "has been made.";
    public static final String UNABLE_TO_FIND_BUDGET_FILE = "Unable to find StonksXD_Budget.csv, a new one " 
            + "has been made.";
    public static final String ERROR_SAVING_ENTRIES = "Unable to save entries into StonksXD_Data.csv, " 
            + "some or all data maybe lost.";
    public static final String ERROR_SAVING_BUDGET_SETTINGS = "Unable to save budget settings into " 
            + "StonksXD_Budget.csv, some or all data maybe lost.";
    public static final String SEARCH_NO_MATCH_MESSAGE = "Your search did not match any of the entries!";
    public static final String TYPE_SOMETHING_MESSAGE = "Type something!";
    public static final String HELP_COMMAND_MESSAGE = "This is a list of commands and their format!";
    public static final String LISTING_EXPENSE_MESSAGE = "Below is a list of all of your recent spending!";
    public static final String LISTING_INCOME_MESSAGE = "Below is a list of all of your recent earnings!";
    public static final String EMPTY_INCOME_MESSAGE = "You have not entered any income!";
    public static final String EMPTY_EXPENSE_MESSAGE = "You have not spent anything!";
    public static final String FOUND_LIST_MESSAGE = "Below is a list of all your findings!";
    public static final String BLANK_CATEGORY_MESSAGE = "Your category is empty!";
    public static final String DATE_FORMAT_MESSAGE = "Your start and end dates must be in a DD/MM/YYYY format!";
    public static final String ALL_DATA_CLEARED = "All your entries have been cleared!";
    public static final String INVALID_EXPENSE_CATEGORY_MESSAGE = "Input only 1 of these 5 categories: Food, " 
            + "Transport, Bills, Medical or Entertainment.";
    public static final String INVALID_INCOME_CATEGORY_MESSAGE = "Input only 1 of these 3 categories: Salary, " 
            + "Allowance or Adhoc.";
    public static final String INVALID_BUDGET_CATEGORY_MESSAGE = "Input only 1 of these 6 categories: Food, "
            + "Transport, Bills, Medical, Entertainment or Overall";
    public static final String INVALID_THRESHOLD_MESSAGE = "Threshold value should between 0 and 1.";


    public static final String SEPARATOR_MESSAGE = "----------------------------------------------------------------"
            + "-------------------------------------";

    public static final String BYE_MESSAGE = "██████  ██    ██ ███████        ██  \n"
            + "██   ██  ██  ██  ██          ██  ██ \n"
            + "██████    ████   █████           ██ \n"
            + "██   ██    ██    ██          ██  ██ \n"
            + "██████     ██    ███████        ██ ";

    public static final String LOGO_MESSAGE = "███████ ████████  ██████  ███    ██ ██   ██ ███████"
            + "     ██   ██ ██████  \n██         ██    ██    ██ ████   ██ ██  ██  ██           ██ ██  ██   ██ \n"
            + "███████    ██    ██    ██ ██ ██  ██ █████   ███████       ███   ██   ██ \n"
            + "     ██    ██    ██    ██ ██  ██ ██ ██  ██       ██      ██ ██  ██   ██ \n"
            + "███████    ██     ██████  ██   ████ ██   ██ ███████     ██   ██ ██████  ";

    public static final String TIP_HEADER = "Here's our tip for the day: ";
    public static final String DISPLAY_ADVICE_ERROR = "Sorry there is no advice for you at this moment >.<";
    public static final String ERROR_LOADING_ADVICE = "./FinancialAdvice.txt cannot be loaded";
    public static final String UNABLE_TO_FIND_ADVICE_FILE = "FinancialAdvice.txt cannot be found";
}
