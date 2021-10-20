package seedu.utility;

import seedu.budget.BillsBudget;
import seedu.budget.Budget;
import seedu.budget.EntertainmentBudget;
import seedu.budget.FoodBudget;
import seedu.budget.MedicalBudget;
import seedu.budget.MiscBudget;
import seedu.budget.OverallBudget;
import seedu.budget.TransportBudget;
import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;

import java.time.LocalDate;
import java.util.ArrayList;

public class BudgetManager {
    private double threshold;
    OverallBudget overallBudget = new OverallBudget(0);
    FoodBudget foodBudget = new FoodBudget(0);
    TransportBudget transportBudget = new TransportBudget(0);
    MedicalBudget medicalBudget = new MedicalBudget(0);
    BillsBudget billsBudget = new BillsBudget(0);
    EntertainmentBudget entertainmentBudget = new EntertainmentBudget(0);
    MiscBudget miscBudget = new MiscBudget(0);

    public BudgetManager() {
        this.threshold = 0.1;
    }

    public void handleBudget(Expense expense, ArrayList<Expense> expenses, Ui ui) {
        checkBudget(expense, expenses, overallBudget, ui);
        Budget budget = expenseCategoryToBudget(expense.getCategory());
        if (budget != overallBudget) {
            checkBudget(expense, expenses, budget, ui);
        }
    }

    private void checkBudget(Expense expense, ArrayList<Expense> expenses, Budget budget, Ui ui) {
        if (budget.getLimit() != 0) {
            String month = LocalDate.now().getMonth().toString();
            double currAmount = budget.calAmount(expenses);
            double limit = budget.getLimit();
            double diff = limit - currAmount;
            if ((diff < threshold*limit) & (diff > 0)) {
                ui.printBudgetWarning(month, budget.getName(), currAmount, limit);
            } else if (diff < 0) {
                ui.printBudgetExceeded(month, budget.getName(), currAmount, limit);
            }
        }
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public void setBudget(double amount, ExpenseCategory category) {
        Budget budget = expenseCategoryToBudget(category);
        budget.setLimit(amount);
    }

    public double getBudget(ExpenseCategory category) {
        Budget budget = expenseCategoryToBudget(category);
        return budget.getLimit();
    }

    private Budget expenseCategoryToBudget(ExpenseCategory category) {
        Budget budget;
        switch (category) {
        case FOOD:
            budget = foodBudget;
            break;
        case TRANSPORT:
            budget = transportBudget;
            break;
        case MEDICAL:
            budget = medicalBudget;
            break;
        case BILLS:
            budget = billsBudget;
            break;
        case ENTERTAINMENT:
            budget = entertainmentBudget;
            break;
        case MISC:
            budget = miscBudget;
            break;
        default:
            budget = overallBudget;
            break;
        }
        return budget;
    }
}
