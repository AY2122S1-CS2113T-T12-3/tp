package seedu.entry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Income extends Entry {
    IncomeCategory category;
    
    public Income(String description, double value, IncomeCategory category) {
        this.description = description;
        this.value = value;
        this.date = LocalDate.now();
        this.category = category;
    }

    public Income(String description, double value, IncomeCategory category, LocalDate date) {
        this.description = description;
        this.value = value;
        this.date = date;
        this.category = category;
    }

    @Override
    public IncomeCategory getCategory() {
        return category;
    }

    @Override
    public String toString() {
        String valueTwoDecimalPoint = String.format("%.2f",value);
        return "[I] " + description + " - $" + valueTwoDecimalPoint + " ("
                + date.format(DateTimeFormatter.ofPattern("dd MMM yyy")) + ")";
    }
}
