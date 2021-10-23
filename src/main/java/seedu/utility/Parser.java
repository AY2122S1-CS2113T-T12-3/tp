package seedu.utility;

import seedu.commands.AddExpenseCommand;
import seedu.commands.AddIncomeCommand;
import seedu.commands.CheckBudgetCommand;
import seedu.commands.ClearAllEntriesCommand;
import seedu.commands.Command;
import seedu.commands.DeleteExpenseCommand;
import seedu.commands.DeleteIncomeCommand;
import seedu.commands.ExitCommand;
import seedu.commands.HelpCommand;
import seedu.commands.InvalidCommand;
import seedu.commands.ListExpenseCommand;
import seedu.commands.ListIncomeCommand;
import seedu.commands.SetBudgetCommand;
import seedu.commands.SetThresholdCommand;
import seedu.commands.ShowGraphCommand;
import seedu.commands.TotalExpenseBetweenCommand;
import seedu.commands.TotalExpenseCommand;
import seedu.commands.TotalIncomeBetweenCommand;
import seedu.commands.TotalIncomeCommand;

import seedu.commands.FindCommand;
import seedu.commands.BalanceCommand;


import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;
import seedu.entry.Income;

import seedu.entry.IncomeCategory;
import seedu.exceptions.InvalidExpenseAmountException;
import seedu.exceptions.InvalidExpenseDataFormatException;
import seedu.exceptions.InvalidExpenseIndexException;
import seedu.exceptions.InvalidIncomeAmountException;
import seedu.exceptions.InvalidIncomeDataFormatException;
import seedu.exceptions.InvalidIncomeIndexException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    /**
     * Used for initial separation of command word and args.
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     */
    private static final Pattern ADD_EXPENSE_ARGUMENT_FORMAT =
            Pattern.compile("d/(?<description>[^/]+)"
                    + " a/(?<amount>[^/]+)"
                    + " c/(?<category>[^/]+)");

    /**
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     */
    private static final Pattern ADD_INCOME_ARGUMENT_FORMAT =
            Pattern.compile("d/(?<description>[^/]+)"
                    + " a/(?<amount>[^/]+)"
                    + " c/(?<category>[^/]+)");

    /**
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     */
    private static final Pattern DELETE_EXPENSE_ARGUMENT_FORMAT =
            Pattern.compile("i/(?<index>[^/]+)");

    /**
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     */
    private static final Pattern DELETE_INCOME_ARGUMENT_FORMAT =
            Pattern.compile("i/(?<index>[^/]+)");

    private static final Pattern DATE_RANGE_ARGUMENT_FORMAT =
            Pattern.compile("s/(?<start>[^/]+)"
                    + "e/(?<end>[^/]+)");

    private static final Pattern SET_BUDGET_ARGUMENT_FORMAT =
            Pattern.compile("c/(?<category>[^/]+)"
                    + "a/(?<amount>[^/]+)");

    private static final Pattern CHECK_BUDGET_ARGUMENT_FORMAT =
            Pattern.compile("c/(?<category>[^/]+)");

    private static final Pattern SET_THRESHOLD_ARGUMENT_FORMAT =
            Pattern.compile("t/(?<threshold>[^/]+)");
    
    private static final String HELP_COMMAND_KEYWORD = "help";
    private static final String ADD_EXPENSE_KEYWORD = "add_ex";
    private static final String ADD_INCOME_KEYWORD = "add_in";
    private static final String DELETE_EXPENSE_KEYWORD = "del_ex";
    private static final String DELETE_INCOME_KEYWORD = "del_in";
    private static final String LIST_EXPENSE_KEYWORD = "list_ex";
    private static final String LIST_INCOME_KEYWORD = "list_in";
    private static final String TOTAL_EXPENSE_KEYWORD = "total_ex";
    private static final String TOTAL_INCOME_KEYWORD = "total_in";
    private static final String FIND_KEYWORD = "find";
    private static final String BALANCE_KEYWORD = "balance";
    private static final String EXIT_KEYWORD = "end";
    private static final String EXPENSE_RANGE_KEYWORD = "btw_ex";
    private static final String INCOME_RANGE_KEYWORD = "btw_in";
    private static final String CLEAR_ALL_ENTRIES_KEYWORD = "clear_all_entries";
    private static final String SET_BUDGET_KEYWORD = "set_budget";
    private static final String CHECK_BUDGET_KEYWORD = "check_budget";
    private static final String SET_THRESHOLD_KEYWORD = "set_threshold";
    
    private static final String DATA_SEPARATOR = ",";

    private static final Pattern EXPENSE_DATA_FORMAT
            = Pattern.compile("E" + DATA_SEPARATOR + "(?<description>.+)" + DATA_SEPARATOR
            + "(?<amount>.+)" + DATA_SEPARATOR + "(?<category>.+)" + DATA_SEPARATOR + "(?<date>.+)");
    private static final Pattern INCOME_DATA_FORMAT
            = Pattern.compile("I" + DATA_SEPARATOR + "(?<description>.+)" + DATA_SEPARATOR
            + "(?<amount>.+)" + DATA_SEPARATOR + "(?<category>.+)" + DATA_SEPARATOR + "(?<date>.+)");
    private static final String SHOW_GRAPH_KEYWORD = "show_graph";
    private static final String DATE_FORMAT = "dd/MM/yyyy";


    /**
     * Parses user input into command for execution.
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments").trim();

        switch (commandWord) {
        case HELP_COMMAND_KEYWORD:
            return prepareHelp(arguments);
        case ADD_EXPENSE_KEYWORD:
            return prepareAddExpense(arguments);
        case ADD_INCOME_KEYWORD:
            return prepareAddIncome(arguments);
        case DELETE_EXPENSE_KEYWORD:
            return prepareDeleteExpense(arguments);
        case DELETE_INCOME_KEYWORD:
            return prepareDeleteIncome(arguments);
        case LIST_EXPENSE_KEYWORD:
            return prepareListExpense(arguments);
        case LIST_INCOME_KEYWORD:
            return prepareListIncome(arguments);
        case TOTAL_EXPENSE_KEYWORD:
            return prepareTotalExpense(arguments);
        case TOTAL_INCOME_KEYWORD:
            return prepareTotalIncome(arguments); 
        case FIND_KEYWORD:
            return prepareFind(arguments);
        case BALANCE_KEYWORD:
            return prepareBalance(arguments);
        case EXIT_KEYWORD:
            return prepareExit(arguments);
        case EXPENSE_RANGE_KEYWORD:
            return prepareExpenseRange(arguments);
        case INCOME_RANGE_KEYWORD:
            return prepareIncomeRange(arguments);
        case CLEAR_ALL_ENTRIES_KEYWORD:
            return prepareClearAllEntries(arguments);
        case SHOW_GRAPH_KEYWORD:
            return prepareShowGraph(arguments);
        case SET_BUDGET_KEYWORD:
            return prepareSetBudget(arguments);
        case CHECK_BUDGET_KEYWORD:
            return prepareCheckBudget(arguments);
        case SET_THRESHOLD_KEYWORD:
            return prepareSetThreshold(arguments);
        default:
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }
    }

    private Command prepareIncomeRange(String arguments) {
        final Matcher matcher = DATE_RANGE_ARGUMENT_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }
        try {
            String start = matcher.group("start").trim();
            LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern(DATE_FORMAT));
            String end = matcher.group("end").trim();
            LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.ofPattern(DATE_FORMAT));
            return new TotalIncomeBetweenCommand(startDate,endDate);
        } catch (DateTimeParseException e) {
            return new InvalidCommand(Messages.DATE_FORMAT_MESSAGE);
        }
    }
    
    private Command prepareExpenseRange(String arguments) {
        final Matcher matcher = DATE_RANGE_ARGUMENT_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }
        try {
            String start = matcher.group("start").trim();
            LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern(DATE_FORMAT));
            String end = matcher.group("end").trim();
            LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.ofPattern(DATE_FORMAT));
            return new TotalExpenseBetweenCommand(startDate,endDate);
        } catch (DateTimeParseException e) {
            return new InvalidCommand(Messages.DATE_FORMAT_MESSAGE);
        }
    }
    
    private Command prepareBalance(String arguments) {
        if (arguments.isBlank()) {
            return new BalanceCommand();
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }
    
    private Command prepareFind(String arguments) {
        if (arguments.isBlank()) {
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }
        return new FindCommand(arguments);
    }
    
    
    private Command prepareHelp(String arguments) {
        if (arguments.isBlank()) {
            return new HelpCommand();
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }

    /**
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     */
    private Command prepareAddExpense(String arguments) {
        final Matcher matcher = ADD_EXPENSE_ARGUMENT_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }

        String userGivenAmount = matcher.group("amount").trim();
        double expenseAmount;
        try {
            expenseAmount = parseExpenseAmount(userGivenAmount);
        } catch (InvalidExpenseAmountException e) {
            return new InvalidCommand(e.getMessage());
        }
        assert expenseAmount > 0;

        String expenseDescription = matcher.group("description").trim();
        if (expenseDescription.isBlank()) {
            return new InvalidCommand(Messages.BLANK_DESCRIPTION_MESSAGE);
        }
        
        String expenseCategory = matcher.group("category").trim().toUpperCase();
        if (expenseCategory.isBlank()) {
            return new InvalidCommand(Messages.BLANK_CATEGORY_MESSAGE);
        }
        Expense expense;
        switch (expenseCategory) {
        case "FOOD":
            expense = new Expense(expenseDescription, expenseAmount, ExpenseCategory.FOOD);
            break;
        case "TRANSPORT":
            expense = new Expense(expenseDescription, expenseAmount, ExpenseCategory.TRANSPORT);
            break;
        case "MEDICAL":
            expense = new Expense(expenseDescription, expenseAmount, ExpenseCategory.MEDICAL);
            break;
        case "BILLS":
            expense = new Expense(expenseDescription, expenseAmount, ExpenseCategory.BILLS);
            break;
        case "ENTERTAINMENT":
            expense = new Expense(expenseDescription, expenseAmount, ExpenseCategory.ENTERTAINMENT);
            break;
        case "MISC":
            expense = new Expense(expenseDescription, expenseAmount, ExpenseCategory.MISC);
            break;
        default:
            return new InvalidCommand(Messages.INVALID_EXPENSE_CATEGORY_MESSAGE);
        }
        return new AddExpenseCommand(expense);
    }

    /**
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     */
    private Command prepareAddIncome(String arguments) {
        final Matcher matcher = ADD_INCOME_ARGUMENT_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }

        String userGivenAmount = matcher.group("amount").trim();
        double incomeAmount;
        try {
            incomeAmount = parseIncomeAmount(userGivenAmount);
        } catch (InvalidIncomeAmountException e) {
            return new InvalidCommand(e.getMessage());
        }
        assert incomeAmount > 0;

        String incomeDescription = matcher.group("description").trim();
        if (incomeDescription.isBlank()) {
            return new InvalidCommand(Messages.BLANK_DESCRIPTION_MESSAGE);
        }

        String incomeCategory = matcher.group("category").trim().toUpperCase();
        if (incomeCategory.isBlank()) {
            return new InvalidCommand(Messages.BLANK_CATEGORY_MESSAGE);
        }
        Income income;
        switch (incomeCategory) {
        case "ALLOWANCE":
            income = new Income(incomeDescription, incomeAmount, IncomeCategory.ALLOWANCE);
            break;
        case "SALARY":
            income = new Income(incomeDescription, incomeAmount, IncomeCategory.SALARY);
            break;
        case "ADHOC":
            income = new Income(incomeDescription, incomeAmount, IncomeCategory.ADHOC);
            break;
        case "OTHERS":
            income = new Income(incomeDescription, incomeAmount, IncomeCategory.OTHERS);
            break;
        default:
            return new InvalidCommand(Messages.INVALID_INCOME_CATEGORY_MESSAGE);
        }
        return new AddIncomeCommand(income);
    }

    /**
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     */
    private Command prepareDeleteExpense(String arguments) {
        final Matcher matcher = DELETE_EXPENSE_ARGUMENT_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }

        String userGivenIndex = matcher.group("index").trim();
        int deleteExpenseIndex;
        try {
            deleteExpenseIndex = parseExpenseIndex(userGivenIndex);
        } catch (InvalidExpenseIndexException e) {
            return new InvalidCommand(e.getMessage());
        }
        assert deleteExpenseIndex >= 1;

        return new DeleteExpenseCommand(deleteExpenseIndex);
    }

    /**
     * This was adapted from addressbook-level2 source code here:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     */
    private Command prepareDeleteIncome(String arguments) {
        final Matcher matcher = DELETE_INCOME_ARGUMENT_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }

        String userGivenIndex = matcher.group("index").trim();
        int deleteIncomeIndex;
        try {
            deleteIncomeIndex = parseIncomeIndex(userGivenIndex);
        } catch (InvalidIncomeIndexException e) {
            return new InvalidCommand(e.getMessage());
        }
        assert deleteIncomeIndex >= 1;

        return new DeleteIncomeCommand(deleteIncomeIndex);
    }

    private Command prepareListExpense(String arguments) {
        if (arguments.isBlank()) {
            return new ListExpenseCommand();
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }

    private Command prepareListIncome(String arguments) {
        if (arguments.isBlank()) {
            return new ListIncomeCommand();
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }

    private Command prepareTotalExpense(String arguments) {
        if (arguments.isBlank()) {
            return new TotalExpenseCommand();
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }

    private Command prepareTotalIncome(String arguments) {
        if (arguments.isBlank()) {
            return new TotalIncomeCommand();
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }
    
    private Command prepareClearAllEntries(String arguments) {
        if (arguments.isBlank()) {
            return new ClearAllEntriesCommand();
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }

    private Command prepareShowGraph(String arguments) {
        if (arguments.isBlank()) {
            return new ShowGraphCommand();
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }

    private Command prepareExit(String arguments) {
        if (arguments.isBlank()) {
            return new ExitCommand();
        }
        return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
    }

    private double parseExpenseAmount(String userGivenAmount) throws InvalidExpenseAmountException {
        double expenseAmount;
        try {
            expenseAmount = Double.parseDouble(userGivenAmount);
        } catch (NumberFormatException e) {
            throw new InvalidExpenseAmountException(Messages.NON_NUMERIC_AMOUNT_MESSAGE);
        }
        if (expenseAmount <= 0) {
            throw new InvalidExpenseAmountException(Messages.NON_POSITIVE_AMOUNT_MESSAGE);
        }
        return expenseAmount;
    }

    private double parseIncomeAmount(String userGivenAmount) throws InvalidIncomeAmountException {
        double incomeAmount;
        try {
            incomeAmount = Double.parseDouble(userGivenAmount);
        } catch (NumberFormatException e) {
            throw new InvalidIncomeAmountException(Messages.NON_NUMERIC_AMOUNT_MESSAGE);
        }
        if (incomeAmount <= 0) {
            throw new InvalidIncomeAmountException(Messages.NON_POSITIVE_AMOUNT_MESSAGE);
        }
        return incomeAmount;
    }

    private int parseExpenseIndex(String userGivenIndex) throws InvalidExpenseIndexException {
        int deleteExpenseIndex;
        try {
            deleteExpenseIndex = Integer.parseInt(userGivenIndex);
        } catch (NumberFormatException e) {
            throw new InvalidExpenseIndexException(Messages.N0N_NUMERIC_INDEX_MESSAGE);
        }
        if (deleteExpenseIndex <= 0) {
            throw new InvalidExpenseIndexException(Messages.NON_POSITIVE_INDEX_MESSAGE);
        }
        return deleteExpenseIndex;
    }

    private int parseIncomeIndex(String userGivenIndex) throws InvalidIncomeIndexException {
        int deleteIncomeIndex;
        try {
            deleteIncomeIndex = Integer.parseInt(userGivenIndex);
        } catch (NumberFormatException e) {
            throw new InvalidIncomeIndexException(Messages.N0N_NUMERIC_INDEX_MESSAGE);
        }
        if (deleteIncomeIndex <= 0) {
            throw new InvalidIncomeIndexException(Messages.NON_POSITIVE_INDEX_MESSAGE);
        }
        return deleteIncomeIndex;
    }

    public String convertExpenseToData(Expense expense) {
        return "E" + DATA_SEPARATOR + expense.getDescription() + DATA_SEPARATOR + expense.getValue() + DATA_SEPARATOR 
                + expense.getCategory() + DATA_SEPARATOR 
                + expense.getDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public String convertIncomeToData(Income income) {
        return "I" + DATA_SEPARATOR + income.getDescription() + DATA_SEPARATOR + income.getValue() + DATA_SEPARATOR 
                + income.getCategory() + DATA_SEPARATOR 
                + income.getDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
    
    public Expense convertDataToExpense(String data) throws InvalidExpenseAmountException,
            InvalidExpenseDataFormatException, DateTimeException {
        final Matcher matcher = EXPENSE_DATA_FORMAT.matcher(data);
        if (!matcher.matches()) {
            throw new InvalidExpenseDataFormatException();
        }
        
        String expenseDescription = matcher.group("description").trim();
        if (expenseDescription.isBlank()) {
            throw new InvalidExpenseDataFormatException();
        }
        String dataAmount = matcher.group("amount").trim();
        double expenseAmount = parseExpenseAmount(dataAmount);
        String expenseCategory = matcher.group("category").trim().toUpperCase();
        if (expenseCategory.isBlank()) {
            throw new InvalidExpenseDataFormatException();
        }
        String date = matcher.group("date").trim();
        LocalDate expenseDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
        Expense expense;
        switch (expenseCategory) {
        case "FOOD":
            expense = new Expense(expenseDescription, expenseAmount, ExpenseCategory.FOOD, expenseDate);
            break;
        case "TRANSPORT":
            expense = new Expense(expenseDescription, expenseAmount, ExpenseCategory.TRANSPORT, expenseDate);
            break;
        case "MEDICAL":
            expense = new Expense(expenseDescription, expenseAmount, ExpenseCategory.MEDICAL, expenseDate);
            break;
        case "BILLS":
            expense = new Expense(expenseDescription, expenseAmount, ExpenseCategory.BILLS, expenseDate);
            break;
        case "ENTERTAINMENT":
            expense = new Expense(expenseDescription, expenseAmount, ExpenseCategory.ENTERTAINMENT, expenseDate);
            break;
        case "MISC":
            expense = new Expense(expenseDescription, expenseAmount, ExpenseCategory.MISC, expenseDate);
            break;
        //this is the fail case. Not sure how we wna implement this
        default:
            expense = new Expense("FAIL EXPENSE", 9999999, ExpenseCategory.NULL, expenseDate);
        }
        assert expenseAmount > 0;
        assert !expenseDescription.isBlank();
        return expense;
    }

    public Income convertDataToIncome(String data) throws InvalidIncomeAmountException, 
            InvalidIncomeDataFormatException, DateTimeException {
        final Matcher matcher = INCOME_DATA_FORMAT.matcher(data);
        if (!matcher.matches()) {
            throw new InvalidIncomeDataFormatException();
        }
        
        String incomeDescription = matcher.group("description").trim();
        if (incomeDescription.isBlank()) {
            throw new InvalidIncomeDataFormatException();
        }
        String dataAmount = matcher.group("amount").trim();
        double incomeAmount = parseIncomeAmount(dataAmount);
        String incomeCategory = matcher.group("category").trim();
        if (incomeCategory.isBlank()) {
            throw new InvalidIncomeDataFormatException();
        }
        String date = matcher.group("date").trim().toUpperCase();
        LocalDate incomeDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
        Income income;
        switch (incomeCategory) {
        case "ALLOWANCE":
            income = new Income(incomeDescription, incomeAmount, IncomeCategory.ALLOWANCE, incomeDate);
            break;
        case "SALARY":
            income = new Income(incomeDescription, incomeAmount, IncomeCategory.SALARY, incomeDate);
            break;
        case "ADHOC":
            income = new Income(incomeDescription, incomeAmount, IncomeCategory.ADHOC, incomeDate);
            break;
        case "OTHERS":
            income = new Income(incomeDescription, incomeAmount, IncomeCategory.OTHERS, incomeDate);
            break;
        //this is the fail case. Not sure how we wna implement this
        default:
            income = new Income("FAIL INCOME", 999999, IncomeCategory.NULL, incomeDate);
        }
        assert incomeAmount > 0;
        assert !incomeDescription.isBlank();
        return income;
    }

    private Command prepareSetBudget(String arguments) {
        final Matcher matcher = SET_BUDGET_ARGUMENT_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }

        String dataAmount = matcher.group("amount").trim();
        if (dataAmount.isBlank()) {
            return new InvalidCommand(Messages.BLANK_AMOUNT_MESSAGE);
        }
        double budgetAmount;
        try {
            budgetAmount = Double.parseDouble(dataAmount);
        } catch (NumberFormatException e) {
            return new InvalidCommand(Messages.NON_NUMERIC_AMOUNT_MESSAGE);
        }
        if (budgetAmount < 0) {
            return new InvalidCommand(Messages.NON_POSITIVE_AMOUNT_MESSAGE);
        }

        String expenseCategory = matcher.group("category").trim().toUpperCase();
        switch (expenseCategory) {
        case "FOOD":
            return new SetBudgetCommand(ExpenseCategory.FOOD, budgetAmount);
        case "TRANSPORT":
            return new SetBudgetCommand(ExpenseCategory.TRANSPORT, budgetAmount);
        case "MEDICAL":
            return new SetBudgetCommand(ExpenseCategory.MEDICAL, budgetAmount);
        case "BILLS":
            return new SetBudgetCommand(ExpenseCategory.BILLS, budgetAmount);
        case "ENTERTAINMENT":
            return new SetBudgetCommand(ExpenseCategory.ENTERTAINMENT, budgetAmount);
        case "MISC":
            return new SetBudgetCommand(ExpenseCategory.MISC, budgetAmount);
        case "OVERALL":
            return new SetBudgetCommand(ExpenseCategory.OVERALL, budgetAmount);
        default:
            return new InvalidCommand(Messages.INVALID_BUDGET_CATEGORY_MESSAGE);
        }
    }

    private Command prepareCheckBudget(String arguments) {
        final Matcher matcher = CHECK_BUDGET_ARGUMENT_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }

        String expenseCategory = matcher.group("category").trim().toUpperCase();
        if (expenseCategory.isBlank()) {
            return new InvalidCommand(Messages.BLANK_CATEGORY_MESSAGE);
        }

        switch (expenseCategory) {
        case "FOOD":
            return new CheckBudgetCommand(ExpenseCategory.FOOD);
        case "TRANSPORT":
            return new CheckBudgetCommand(ExpenseCategory.TRANSPORT);
        case "MEDICAL":
            return new CheckBudgetCommand(ExpenseCategory.MEDICAL);
        case "BILLS":
            return new CheckBudgetCommand(ExpenseCategory.BILLS);
        case "ENTERTAINMENT":
            return new CheckBudgetCommand(ExpenseCategory.ENTERTAINMENT);
        case "MISC":
            return new CheckBudgetCommand(ExpenseCategory.MISC);
        case "OVERALL":
            return new CheckBudgetCommand(ExpenseCategory.OVERALL);
        default:
            return new InvalidCommand(Messages.INVALID_BUDGET_CATEGORY_MESSAGE);
        }
    }

    private Command prepareSetThreshold(String arguments) {
        final Matcher matcher = SET_THRESHOLD_ARGUMENT_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.INVALID_COMMAND_MESSAGE);
        }

        String thresholdString = matcher.group("threshold").trim();
        double thresholdValue;
        try {
            thresholdValue = Double.parseDouble(thresholdString);
        } catch (NumberFormatException e) {
            return new InvalidCommand(Messages.NON_NUMERIC_AMOUNT_MESSAGE);
        }
        if ((thresholdValue < 0) | (thresholdValue > 1)) {
            return new InvalidCommand(Messages.INVALID_THRESHOLD_MESSAGE);
        }

        return new SetThresholdCommand(thresholdValue);
    }
}
