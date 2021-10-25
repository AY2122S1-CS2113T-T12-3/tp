# StonksXD User Guide

![](https://melmagazine.com/wp-content/uploads/2019/07/Stonks_Meme.jpg)

## Introduction

StonksXD is your go-to smart money management application that is able to: 
- track your daily expenses 
- sets/adjusts your spending limits
- provide you with helpful financial tips and insights

It is optimised as a daily journal, so you can key in your entries while you unwind at the end of the day. It also allows you to 
view your financial records through both text and graphs, in different currencies, so you can use this application anywhere around the world!

Using this guide, you will be able to learn how to use all the functionalities of this application through step-by-step instructions 
without having to learn how to write a single line of code.

Before you learn how to use the app, take a look at the [quickstart](#quick-start) guide on how to install and prepare the application for use
## Table of Contents

- [Quick Start](#quick-start)
- [Features](#features)
    - [View all possible commands: `help`](#view-all-possible-commands-help)
    - [Create expense entry: `add_ex`](#create-expense-entry-add_ex)
    - [Delete expense entry: `del_ex`](#delete-expense-entry-del_ex)
    - [List all expense entries: `list_ex`](#list-all-expense-entries-list_ex)
    - [View total expense: `total_ex`](#view-total-expense-total_ex)
    - [Create income entry: `add_in`](#create-income-entry-add_in)
    - [Delete income entry: `del_in`](#delete-income-entry-del_in)
    - [List all income entries: `list_in`](#list-all-income-entries-list_in)
    - [View total income: `total_in`](#view-total-income-total_in)
    - [Find entry using date: `find YYYY-MM-DD`](#find-entry-using-date-find-yyyy-mm-dd)
    - [Find entry using keyword: `find KEYWORD`](#find-entry-using-keyword-find-keyword)
    - [View total balance: `balance`](#view-total-balance-balance)
    - [Show total expense between 2 dates: `btw_ex s/START_DATE e/END_DATE`](#show-total-expense-between-2-dates-btw_ex)
    - [Show total income between 2 dates: `btw_in s/START_DATE e/END_DATE`](#show-total-income-between-2-dates-btw_in)
    - [Clear all entries: `clear_all_entries`](#clear-all-entries-clear_all_entries)
    - [Set budget: `set_budget`](#set-budget-set_budget)
    - [Check budget: `check_budget`](#check-budget-check_budget)
    - [Set_threshold: `set_threshold`](#set-threshold-set_threshold)
    - [View Yearly Report: `show_graph`](#view-yearly-report-show_graph)
    - [Terminate program: `end`](#terminate-program-end)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `StonksXD.jar` from [here](https://github.com/AY2122S1-CS2113T-T12-3/tp/releases).
3. Copy the file to the folder you want to use as the home folder for your `StonksXD`.
4. Open the Command-Line interface (CLI) and navigate to the directory where you saved the `.jar` file and run `java -jar tp.java` in the command line. `StonksXD` will start up.
5. In `StonksXD`, type the command in the CLI and press `Enter` on your keyboard to execute it. (Tip: type `help` to show all available commands and their format)
6. Use the format `add_ex d/DESCRIPTION a/AMOUNT c/CATEGORY` to add expense entries to `StonksXD`.
7. Use the format `add_in d/DESCRIPTION a/AMOUNT c/CATEGORY` to add income entries to `StonksXD`.
8. Type `balance` to view your net saving.
9. Refer to the [Features](#features) below for details of each command.

## Features 

### Notes

- Words in `UPPER_CASE` are the parameters to be supplied by the user.
  e.g. in add `a/AMOUNT`, `AMOUNT` is a parameter which can be used as `a/12.30`.

### View all possible commands: `help`
Shows a list of all possible commands.

Format: `help`

<font size=1><i>Below is a collapsible section that allows you to see the run time output. Do check them if you want to visualize what the product looks like!</i></font>

<details>
<summary> Expected output in run window (Click to expand!)</summary>
<pre>
-----------------------------------------------------------------------------------------------------
This is a list of commands and their format!
-----------------------------------------------------------------------------------------------------
List Out All Commands: help
Adding Expense: add_ex d/DESCRIPTION a/AMOUNT c/CATEGORY
Deleting Expense: del_ex i/INDEX
Listing Expense: list_ex
Show Total Expense: total_ex
Show Total Expense between 2 dates: btw_ex s/START_DATE e/END_DATE
Adding Income: add_in d/DESCRIPTION a/AMOUNT c/CATEGORY
Deleting Income: del_in i/INDEX
Listing Income: list_in
Show Total Income: total_in
To Find Using Date: find YYYY-MM-DD
To Find Based On Keyword: find KEYWORD
To Display Total Balance: balance
Show Total Income between 2 dates: btw_in s/START_DATE e/END_DATE
To Set Budgets: set_budget c/CATEGORY a/AMOUNT
To Check Budgets: check_budget c/CATEGORY
To Set Threshold Value for Reminders: set_threshold t/THRESHOLD
To View Your Yearly Report: show_graph
To Terminate The Program: end
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

### Create expense entry: `add_ex`
Adds an expense entry.

Format: `add_ex d/DESCRIPTION a/AMOUNT c/CATEGORY`

- `DESCRIPTION` has to be non-empty.
- `AMOUNT` has to be a positive amount.
- `CATEGORY` has to be either `food`, `transport`, `bills`, `medical`, `entertainment`, `overall` or `misc`.

Examples:

- `add_ex d/KFC lunch a/10.20 c/food` Adds an expense entry regarding lunch that costs $10.20.

<details>
<summary> Expected output in run window </summary>
<pre>
-----------------------------------------------------------------------------------------------------
Your most recent spending: 
[E] KFC lunch - $10.20 (19/10/2021)
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

### Delete expense entry: `del_ex`

Delete unwanted expense entries by providing the index of said entry.

Format: `del_ex i/INDEX`

- `INDEX` has to be non-empty.
- `INDEX` has to be a valid non-negative integer.

Examples:

- `del_ex i/1` Deletes the 1st entry from the expense list.

<details>
<summary> Expected output in run window </summary>
<br>
Before deletion the expense list is as follows:
<pre>
-----------------------------------------------------------------------------------------------------
Below is a list of all of your recent spending!
-----------------------------------------------------------------------------------------------------
1: [E] pillow - $500.00 (18/10/2021)
2: [E] bought cookies - $500.00 (18/01/2021)
3: [E] bought home - $555.00 (18/07/2021)
-----------------------------------------------------------------------------------------------------
</pre>
<br>
When command `del_ex i/1` is given, you get the following message:
<pre>
-----------------------------------------------------------------------------------------------------
You removed this: 
[E] pillow - $500.00 (18/10/2021)
-----------------------------------------------------------------------------------------------------
</pre>
<br>
After deletion, we see that the list has removed the previous first entry!
<pre>
-----------------------------------------------------------------------------------------------------
1: [E] bought cookies - $500.00 (18/01/2021)
2: [E] bought home - $555.00 (18/07/2021)
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

### List all expense entries: `list_ex`

Displays the previously added expense entries in the form of a list.

Format: `list_ex`

<details>
<summary> Expected output in run window </summary>
<pre>
-----------------------------------------------------------------------------------------------------
Below is a list of all of your recent spending!
-----------------------------------------------------------------------------------------------------
1: [E] bought cookies - $500.00 (18/01/2021)
2: [E] bought home - $555.00 (18/07/2021)
3: [E] bought car - $4777.00 (18/06/2021)
4: [E] bought condo - $87654888878.00 (18/05/2021)
5: [E] KFC lunch - $10.20 (19/10/2021)
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

### View total expense: `total_ex`

Displays the total amount of all expenses in your list.

Format: `total_ex`

<details>
<summary> Expected output in run window </summary>
<br>
Here we have a simple expense list with three items:
<pre>
-----------------------------------------------------------------------------------------------------
1: [E] bought cookies - $100.00 (18/01/2021)
2: [E] bought home - $200.50 (18/07/2021)
3: [E] bought car - $300.00 (18/06/2021)
-----------------------------------------------------------------------------------------------------
</pre>
<br>
If we give the command `total_ex`, it will display the total expenditure:
<pre>
-----------------------------------------------------------------------------------------------------
Your total expense is: $600.50
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

### Create income entry: `add_in`

Adds an income entry.

Format: `add_in d/DESCRIPTION a/AMOUNT c/CATEGORY`

- `DESCRIPTION` has to be non-empty.
- `AMOUNT` has to be a positive amount.
- `CATEGORY` has to be either `salary`, `allowance`, `others` or `adhoc`.

Examples:

- `add_in d/lunch money a/1000 c/allowance` Adds an income entry regarding an allowance of $1000.

<details>
  <summary> Expected output in run window </summary>
<pre>
-----------------------------------------------------------------------------------------------------
Your most recent earning: 
[I] lunch money - $1000.00 (19/10/2021)
-----------------------------------------------------------------------------------------------------
</pre>

</details>
<br>

### Delete income entry: `del_in`

Delete unwanted income entries by providing the index of said entry.

Format: `del_in i/INDEX`

- `INDEX` has to be non-empty.
- `INDEX` has to be a valid non-negative integer.

Examples:

- `del_in i/1` Deletes the 1st entry from the income list.

<details>
<summary> Expected output in run window </summary>
<br>
Before deletion the income list is as follows:
<pre>
-----------------------------------------------------------------------------------------------------
Below is a list of all of your recent earnings!
-----------------------------------------------------------------------------------------------------
1: [I] rob a bank - $800.00 (18/10/2021)
2: [I] rob a church - $300.00 (18/11/2021)
3: [I] rob a car - $400.00 (18/12/2021)
-----------------------------------------------------------------------------------------------------
</pre>
<br>
When command `del_in i/1` is given, you get the following message:
<pre>
-----------------------------------------------------------------------------------------------------
You removed this: 
[I] rob a bank - $800.00 (18/10/2021)
-----------------------------------------------------------------------------------------------------
</pre>
<br>
After deletion, we see that the list has removed the previous first entry!
<pre>
-----------------------------------------------------------------------------------------------------
1: [I] rob a church - $300.00 (18/11/2021)
2: [I] rob a car - $400.00 (18/12/2021)
-----------------------------------------------------------------------------------------------------
</pre>

</details>
<br>

### List all income entries: `list_in`

Displays the previously added income entries in the form of a list.

Format: `list_in`

<details>
<summary> Expected output in run window </summary>
<pre>
-----------------------------------------------------------------------------------------------------
Below is a list of all of your recent earnings!
-----------------------------------------------------------------------------------------------------
1: [I] rob a church - $300.00 (18/11/2021)
2: [I] rob a car - $400.00 (18/12/2021)
3: [I] rob a home - $500.00 (18/09/2021)
4: [I] rob a child - $600.00 (18/08/2021)
5: [I] lunch money - $1000.00 (19/10/2021)
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

### View total income: `total_in`

Displays the total amount of all income entries in your list.

Format: `total_in`
<details>
<summary> Expected output in run window </summary>
<br>
Here we have a simple income list with three items:
<pre>
-----------------------------------------------------------------------------------------------------
1: [I] rob a church - $300.00 (18/11/2021)
2: [I] rob a car - $400.00 (18/12/2021)
3: [I] rob a home - $500.00 (18/09/2021)
-----------------------------------------------------------------------------------------------------
</pre>
<br>
If we give the command `total_in`, it will display the total income:
<pre>
-----------------------------------------------------------------------------------------------------
Your total income is: $2800.00
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

### Find entry using date: `find DD/MM/YYYY`

Finds and displays the income or expense entries recorded on the given date.

Format: `find DD/MM/YYYY`

- If the date given is not in the recognised format, it will be treated as a String and be passed into keyword search.

Examples:

- `find 19/10/2021` returns income and/or expense entries recorded on the given date.

<details>
<summary> Expected output in run window </summary>
If you enter `find 19/10/2021`, it will find the entry recorded on that date:
<pre>
-----------------------------------------------------------------------------------------------------
Your most recent earning: 
[I] Birthday Money! - $200.00 (19/10/2021)
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

### Find entry using keyword: `find KEYWORD`

Finds and displays the income or expense entries that contain the given keyword.

Format: `find KEYWORD`

- `KEYWORD` has to be a non-empty
- `KEYWORD` can be any alpha-numeric string

Examples:

- `find FOOD` returns income and/or expense entries that contain the keyword `FOOD` in their description or categories.

<details>
<summary> Expected output in run window </summary>
<br>
If you wish to search based on category, for e.g. all `food` expenses:
- Give the command `find food` and it will return the following:
<pre>
-----------------------------------------------------------------------------------------------------
Below is a list of all your findings!
-----------------------------------------------------------------------------------------------------
1: [E] KFC lunch - $10.20 (19/10/2021)
-----------------------------------------------------------------------------------------------------
</pre>
If you wish to search based on description, for e.g. all entries that contain the keyword `bought`:
- Give the command `find bought` and it will return the following:
<pre>
-----------------------------------------------------------------------------------------------------
Below is a list of all your findings!
-----------------------------------------------------------------------------------------------------
1: [E] bought cookies - $500.00 (18/01/2021)
2: [E] bought home - $555.00 (18/07/2021)
3: [E] bought car - $4777.00 (18/06/2021)
4: [E] bought condo - $87654888878.00 (18/05/2021)
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

### View total balance: `balance`

Shows the net balance you have leftover from your expenses and incomes.

Format: `balance`
<details>
  <summary> Expected output in run window </summary>
<pre>
-----------------------------------------------------------------------------------------------------
Your current balance is: $-87654891720.20
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

### Show total expense between 2 dates: `btw_ex`

Shows the total expense you had accumulated during a given time period.

Format: `btw_ex s/START_DATE e/END_DATE`

Examples:
- <code>btw_ex s/06/12/1987 e/21/11/1999</code> will return the total income of all entries between the given dates.
<details>
<summary> Expected output in run window </summary>
<br>
If you wish to find your income between 10th Aug 2021 and 23rd Oct 2021
<br>
<ul><li>Give the command <code>btw_ex s/10/07/2021 e/23/10/2021</code></li></ul>
<pre>
-----------------------------------------------------------------------------------------------------
Your total expense between 10 Jul 2021 and 23 Oct 2021 is : $2300.00
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

### Show total income between 2 dates: `btw_in`

Shows the total income you had accumulated during a given time period.

Format: `btw_in s/START_DATE e/END_DATE`

- `START_DATE` & `END_DATE` are of the form YYYY-MM-DD
- `START_DATE` & `END_DATE` have to be valid and non-empty

Examples: 
<ul><li><code>btw_in s/06/12/1987 e/21/11/1999</code> will return the total income of all entries between the given dates.</li></ul>
<details>
<summary> Expected output in run window </summary>
<br>
If you wish to find your income between 10th Aug 2021 and 23rd Oct 2021
<ul><li>Give the command <code>btw_in s/10/07/2021 e/23/10/2021</code></li></ul>
<pre>
-----------------------------------------------------------------------------------------------------
Your total income between 10 Jul 2021 and 23 Oct 2021 is : $2300.00
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

### Clear all entries: `clear_all_entries`

Clears all the income and expense entries StonksXD is currently keeping track of.

Format: `clear_all_entries`

<details>
<summary> Expected output in run window </summary>
<br>
If you wish to clear all your entries and start afresh:
<br>
<ul><li> Give the command <code>clear_all_entries</code></li></ul>
<pre>
-----------------------------------------------------------------------------------------------------
All your entries have been cleared!
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>

### Set budget: `set_budget`

- TODO

### Check budget: `check_budget`

- TODO

### Set threshold: `set_threshold`

- TODO

### View Yearly Report: `show_graph`

Shows the monthly breakdown of the finances in a Yearly Report which also
includes current month spending and earnings.

Format: `show_graph`

<details>
<summary> Expected output in run window </summary>
<pre>
show_graph
-----------------------------------------------------------------------------------------------------
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
x                                                                                                  x
x   Account Balance: $-398.2                                               Legend:                 x
x   Current month (OCTOBER) total expense: $410.20                               # is Expense      x
x   Current month (OCTOBER) total income: $0.00                                  o is Income       x
x   Your Yearly Report                                                                             x
x ------------------------------------------------------------------------------------------------ x
x                                                                                                  x
x                                                                                                  x
x                                                                                                  x
x                                                                                                  x
x                                                                                                  x
x                                                                                                  x
x                                                                                                  x
x                                                                                                  x
x                                                                           #                      x
x                                                                           #                      x
x ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ x
x   Jan     Feb     Mar     Apr     May     Jun     Jul     Aug     Sept    Oct     Nov     Dec    x
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>


### Terminate program: `end`

Exits the program when the user is done.

Format: `end`
<details>
<summary> Expected output in run window </summary>
<pre>
-----------------------------------------------------------------------------------------------------
██████  ██    ██ ███████        ██  
██   ██  ██  ██  ██          ██  ██ 
██████    ████   █████           ██ 
██   ██    ██    ██          ██  ██ 
██████     ██    ███████        ██ 
-----------------------------------------------------------------------------------------------------
</pre>
</details>
<br>


### Saving of Data

StonksXD will save / load data from `StonksXD_Entries.csv` and `StonksXD_Budget.csv`. 

- `StonksXD_Entries.csv` will store all the expense and income entries StonksXD is currently tracking.
- `StonksXD_Budget.csv` will store all the budget values.

The reason for data files to be in `.csv` format is so that you can have an easier time editing those data in Excel 
when not using the program.

#### Note

StonksXD expects the dates in `StonksXD_Entries.csv` to be in `dd/MM/yyyy` format i.e., `11/12/2021` when loading data. 
When opening `StonksXD_Entries.csv` in Excel, Excel might change the format of the dates. Do ensure Excel's date format 
is in `dd/MM/yyyy` when dealing with `StonksXD_Entries.csv`. Entries with a different date format will be considered 
corrupted and not be loaded into StonksXD. 

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Your data is saved in 2 `.csv` files called `StonksXD_Entries.csv` and `StonksXD_Budget.csv`. To transfer the data, make a copy of these files and paste them in the same directory as the `.jar` file on your new machine. 

**Q**: What if my program terminates unexpectedly?

**A**: All data will be stored inside the respective `.csv` files.

## Command Summary

| Action | Format | Examples |
| ------------ | ------------- | ------------- |
| View all possible commands | `help` | - |
| Create expense entry | `add_ex d/DESCRIPTION a/AMOUNT c/CATEGORY`  |  `add_ex d/KFC lunch a/10.20 c/food` |
| Delete expense entry | `del_ex i/INDEX` | `del_ex i/3` |
| List all expense entries | `list_ex` | - |
| View total expense | `total_ex` | - |
| Create income entry | `add_in d/DESCRIPTION a/AMOUNT c/CATEGORY`  | `add_in d/work a/3200 c/salary` |
| Delete income entry | `del_in i/INDEX` | `del_in i/2` |
| List all income entries | `list_in` | - |
| View total income | `total_in` | - |
| Find entry using date | `find YYYY-MM-DD` | `find 19/10/2021` |
| Find entry using keyword | `find KEYWORD` | `find food` |
| View total balance | `balance` | - |
| Show total expense between 2 dates | `btw_ex s/START_DATE e/END_DATE` | `btw_ex s/10/07/2021 e/23/10/2021` |
| Show total income between 2 dates | `btw_in s/START_DATE e/END_DATE` | `btw_in s/10/07/2021 e/23/10/2021`  |
| Clear all entries | `clear_all_entries` | - |
| View Yearly Report | `show_graph` | - |
| To terminate program | `end` | - |


