package seedu.utility;


import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class StonksGraph {
    private static final int ROWS = 20;
    private static final int COLS = 100;
    private static final int ROWS_OFFSET = ROWS - 1;
    private static final int COLS_OFFSET = COLS - 1;
    private static final int BAR_VALUE = 200;
    private final char[][] grid = new char [ROWS][COLS];
    private static final char BORDER_CHAR = 'x';
    private static final char NON_BORDER_CHAR = ' ';

    /**
     * It will call all the differnet methods here like balance, date(which mth), a bar in the middle(How many% full).
     * need to rmb to key in case where input is damm long(troll input).
     *
     */
    public StonksGraph(FinancialTracker finances) {
        setBorder();
        setBalance(finances.getBalance());
        setBar(finances);
    }
    
    private void setBorder() {
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLS; y++) {
                if (isBorder(x, y)) {
                    grid[x][y] = BORDER_CHAR;
                } else {
                    grid[x][y] = NON_BORDER_CHAR;
                }
            }
        }
    }

    private boolean isBorder(int x, int y) {
        return x == 0 || y == 0 || x == ROWS_OFFSET || y == COLS_OFFSET;
    }

    private void setBalance(double amount) {
        String stringAmount = String.format("%.2f", amount);
        writeToGraph(2,4,"Account Balance: $");
        writeToGraph(2,22, stringAmount);
    }


    private void writeToGraph(int rowCount, int colCount, String toAdd) {
        int stringLength = toAdd.length();
        int i = 0;
        while (i < stringLength) {
            grid[rowCount][colCount] = toAdd.charAt(i);
            colCount++;
            i++;
        }
    }
    
    
    public String convertGridToString() {
        StringBuilder convertedString = new StringBuilder();
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLS; y++) {
                convertedString.append(grid[x][y]);
            }
            convertedString.append(System.lineSeparator());
        }
        return convertedString.toString();
    }
    
    
    @Override
    public String toString() {
        return convertGridToString();
    }

    /**
     * Returns month as an int base on which columm it is at.
     *
     * @param colCount the columns of the grid
     * @return Returns an integer that represents the month
     */
    private int getMonth(int colCount) {
        if (colCount >= 4 && colCount <= 6) {
            return 1;
        } else if (colCount >= 12 && colCount <= 14) {
            return 2;
        }  else if (colCount >= 20 && colCount <= 22) {
            return 3;
        }  else if (colCount >= 28 && colCount <= 30) {
            return 4;
        }  else if (colCount >= 36 && colCount <= 38) {
            return 5;
        }  else if (colCount >= 44 && colCount <= 46) {
            return 6;
        }  else if (colCount >= 52 && colCount <= 54) {
            return 7;
        }  else if (colCount >= 60 && colCount <= 62) {
            return 8;
        }  else if (colCount >= 68 && colCount <= 70) {
            return 9;
        }  else if (colCount >= 76 && colCount <= 78) {
            return 10;
        }  else if (colCount >= 84 && colCount <= 86) {
            return 11;
        } else {
            return 12;
        }
    }


    private void setBar(FinancialTracker finances) {
        writeToGraph(5,4, "Your Yearly Report");
        drawSeparator();
        drawLegend();
        drawXAxisLabels();
        drawXAxis();
        ArrayList<Double> monthlyIncomeBreakdowns = finances.getMonthlyIncomeBreakdown(2021);
        ArrayList<Double> monthlyExpenseBreakdowns = finances.getMonthlyExpenseBreakdown(2021);
        drawCurrentMonth(monthlyIncomeBreakdowns, monthlyExpenseBreakdowns);

        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLS; y++) {
                int monthIndex = getMonth(y) - 1;
                int incomeBar = (int)(monthlyIncomeBreakdowns.get(monthIndex) / BAR_VALUE);
                int expenseBar = (int)(monthlyExpenseBreakdowns.get(monthIndex) / BAR_VALUE);

                if (isWithinRowsConsistingOfBarGraph(x)) {
                    if (isExpenseBar(y)) {
                        setExpenseBar(x, y, expenseBar);
                    }
                    if (isIncomeBar(y)) {
                        setIncomeBar(x, y, incomeBar);
                    }
                }
            }
        }
    }

    private void drawCurrentMonth(ArrayList<Double> monthIncomeBreakdowns, ArrayList<Double> monthExpenseBreakdowns) {
        Month currentMonth = currentMonth();
        int currentMonthInIndex = currentMonthInIndex();
        double currentMonthExpense = monthExpenseBreakdowns.get(currentMonthInIndex);
        double currentMonthIncome = monthIncomeBreakdowns.get(currentMonthInIndex);
        String currentMonthExpenseAsString =
                String.format("Current month (" + currentMonth + ") total expense: " + "$%.2f", currentMonthExpense);
        String currentMonthIncomeAsString =
                String.format("Current month (" + currentMonth + ") total income: " + "$%.2f", currentMonthIncome);
        writeToGraph(3,4, currentMonthExpenseAsString);
        writeToGraph(4,4, currentMonthIncomeAsString);
    }

    private boolean isWithinRowsConsistingOfBarGraph(int x) {
        return x >= 7 && x < 17;
    }

    private void drawLegend() {
        writeToGraph(2, 75, "Legend:");
        writeToGraph(3, 80, " # is Expense");
        writeToGraph(4, 80, " o is Income ");
    }

    private boolean isExpenseBar(int y) {
        return y == 4 || y == 12 || y == 20 || y == 28 || y == 36 || y == 44 || y == 52 || y == 60
                || y == 68 || y == 76 || y == 84 || y == 92;
    }

    private boolean isIncomeBar(int y) {
        return y == 5 || y == 13 || y == 21 || y == 29 || y == 37 || y == 45 || y == 53 || y == 61
                || y == 69 || y == 77 || y == 85 || y == 93;
    }

    private void drawXAxisLabels() {
        writeToGraph(18,4,"Jan");
        writeToGraph(18,12,"Feb");
        writeToGraph(18,20,"Mar");
        writeToGraph(18,28,"Apr");
        writeToGraph(18,36,"May");
        writeToGraph(18,44,"Jun");
        writeToGraph(18,52,"Jul");
        writeToGraph(18,60,"Aug");
        writeToGraph(18,68,"Sept");
        writeToGraph(18,76,"Oct");
        writeToGraph(18,84,"Nov");
        writeToGraph(18,92,"Dec");
    }

    private void drawXAxis() {
        for (int i = 2;i < 98; i++) {
            grid[17][i] = '~';
        }
    }

    private void drawSeparator() {
        for (int i = 2;i < 98; i++) {
            grid[6][i] = '-';
        }
    }

    private void setIncomeBar(int x, int y, int incomeBar) {
        if (x >= 17 - incomeBar && x < 17) {
            grid[x][y] = 'o';
        }
    }

    private void setExpenseBar(int x, int y, int expenseBar) {
        if (x >= 17 - expenseBar && x < 17) {
            grid[x][y] = '#';
        }
    }
    
    private int currentMonthInIndex() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getMonthValue() - 1;
    }
    
    private Month currentMonth() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getMonth();
    }
}
