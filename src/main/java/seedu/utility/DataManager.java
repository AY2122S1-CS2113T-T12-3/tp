package seedu.utility;

import seedu.entry.Expense;
import seedu.entry.Income;
import seedu.exceptions.InputException;
import seedu.exceptions.InvalidExpenseDataFormatException;
import seedu.exceptions.InvalidIncomeDataFormatException;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Saves entries that StonksXD is currently tracking into a csv file so that entries can be saved and be easily
 * manipulated by user.
 * Also loads all saved entries when a new instance of StonksXD starts.
 */
public class DataManager {

    private static final String NEWLINE = System.lineSeparator();
    private static final String ENTRIES_FILE_NAME = "./StonksXD_Entries.csv";
    private static final String ENTRIES_CSV_HEADER = "entry_type,entry_description,amount,category,date";
    private static final String BUDGET_FILE_NAME = "./StonksXD_Budget.csv";
    private static final String BUDGET_CSV_HEADER = "overallBudget,foodBudget,transportBudget,medicalBudget,"
            + "billsBudget,entertainmentBudget,miscBudget";
    private final Parser parser;
    private final FinancialTracker financialTracker;
    private final Ui ui;
    private final BudgetManager budgetManager;

    public DataManager(Parser parser, FinancialTracker financialTracker, Ui ui, BudgetManager budgetManager) {
        this.parser = parser;
        this.financialTracker = financialTracker;
        this.ui = ui;
        this.budgetManager = budgetManager;
    }

    /**
     * Saves all entries and budget settings.
     * This method will be used more frequently as we typically want to save both entries and budget settings together.
     */
    public void saveAll() {
        saveEntries();
        //saveBudgetSettings();
    }

    /**
     * Loads all entries and budget settings.
     * This method will be used more frequently as we typically want to load both entries and budget settings together.
     */
    public void loadAll() {
        loadEntries();
        //loadBudgetSettings();
    }

    /**
     * Saves all entries StonksXD is currently tracking into a csv file StonksXD_Data.csv.
     * This allows users to not lose all their entries when program closes.
     */
    public void saveEntries() {
        try {
            FileWriter writer = new FileWriter(ENTRIES_FILE_NAME);
            BufferedWriter buffer = new BufferedWriter(writer);
            String data;

            // Categories header for the CSV file
            buffer.write(ENTRIES_CSV_HEADER);
            buffer.write(NEWLINE);
            ArrayList<Expense> expenses = financialTracker.getExpenses();
            for (Expense expense : expenses) {
                data = parser.convertExpenseToData(expense);
                buffer.write(data);
                buffer.write(NEWLINE);
            }
            ArrayList<Income> incomes = financialTracker.getIncomes();
            for (Income income : incomes) {
                data = parser.convertIncomeToData(income);
                buffer.write(data);
                buffer.write(NEWLINE);
            }

            buffer.close();
        } catch (IOException e) {
            ui.printError(Messages.ERROR_SAVING_ENTRIES);
        }
    }

    /**
     * Loads all entries from StonksXD_Data.csv into StonksXD.
     * This allows users to not lose all their entries when the previous instance of StonksXD closed.
     */
    public void loadEntries() {
        boolean hasCorruptedLines = false;
        FileInputStream fis;
        try {
            fis = new FileInputStream(ENTRIES_FILE_NAME);
        } catch (FileNotFoundException e) {
            ui.printError(Messages.UNABLE_TO_FIND_DATA_FILE);
            return;
        }
        Scanner sc = new Scanner(fis);
        sc.nextLine();

        while (sc.hasNextLine()) {
            String data = sc.nextLine();
            try {
                Expense expense = parser.convertDataToExpense(data);
                financialTracker.addExpense(expense);
            } catch (InputException | InvalidExpenseDataFormatException | DateTimeParseException e) {
                try {
                    Income income = parser.convertDataToIncome(data);
                    financialTracker.addIncome(income);
                } catch (InputException | InvalidIncomeDataFormatException | DateTimeParseException ie) {
                    hasCorruptedLines = true;
                }
            }
        }

        if (hasCorruptedLines) {
            ui.printError(Messages.HAS_CORRUPTED_DATA_ENTRIES);
        }
    }

    /**
     * Saves all budget settings into a csv file StonksXD_Budget.csv.
     * This allows users to not lose all their budget settings when program closes.
     */
    public void saveBudgetSettings() {
        try {
            FileWriter writer = new FileWriter(BUDGET_FILE_NAME);
            BufferedWriter buffer = new BufferedWriter(writer);
            String data;

            // Categories header for the CSV file
            buffer.write(BUDGET_CSV_HEADER);
            buffer.write(NEWLINE);
            data = parser.convertBudgetSettingsToString(budgetManager);
            buffer.write(data);
            buffer.write(NEWLINE);
            buffer.close();
        } catch (IOException e) {
            ui.printError(Messages.ERROR_SAVING_BUDGET_SETTINGS);
        }
    }

    /**
     * Loads all settings from StonksXD_Budget.csv into StonksXD.
     * This allows users to not lose all their budget settings when the previous instance of StonksXD closed.
     */
    public void loadBudgetSettings() {
        boolean hasCorruptedLines = false;
        FileInputStream fis;
        try {
            fis = new FileInputStream(BUDGET_FILE_NAME);
        } catch (FileNotFoundException e) {
            ui.printError(Messages.UNABLE_TO_FIND_BUDGET_FILE);
            return;
        }
        Scanner sc = new Scanner(fis);
        sc.nextLine();

        while (sc.hasNextLine()) {
            // To be updated
        }

        //if (hasCorruptedLines) {
        //   ui.printError(Messages.HAS_CORRUPTED_BUDGET_SETTINGS);
        //}
    }
}
