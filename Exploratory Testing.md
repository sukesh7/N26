# Moneyf Exploratory Testing

## Table of Contents
* [Intro](#intro)
* [Environment Context](#environment-context)
* [Charters](#charters)
* [Prioritization](#prioritization)
* [Observations](#observations)
* [Non Functional Requirements](#nfr)
* [Risks And Mitigation](#risks-and-mitigation)

## Intro
This is a typical money management app which is used to track the user expense and income and there by helps the user for better manage their money.


## Environment Context
* Environment - Mobile
* Platform - iOS


## Charters
* [Expense and Income](#expenseandincome)
* [Filters](#filters)
* [Records](#records)
* [Search](#search)
* [Accounts](#accounts)
* [Currencies](#currencies)
* [Settings](#settings)
* [Categories](#categories)


## Prioritization
* Expense and Income - HIGH
* Records - HIGH
* Filters - MEDIUM
* Search - MEDIUM
* Accounts - MEDIUM
* Settings - MEDIUM
* Categories - MEDIUM
* NFR - MEDIUM
* Currencies - LOW

### Reason
- This is a typical money management app. The core of this app would be the ability to add or edit the income/expense and viewing the data. Hence it gets the highest priority w.r.t the functionality. 
- The rest of the functionalities are essential in enhancing the user experience which can be defined with medium priority.


### Expense and Income(30 mins)

1. User should be able to add Income sucessfully
2. User should be able to add expense sucessfully by choosing category
3. User should be able to edit expense sucessfully by chossing different category
4. User should be able to edit expense succesfully by updating the notes
5. User should be able to edit expense sucessfully by updating expense value
6. User should be able to delete expense sucessfully
7. User should be able to view the expense chart based on the category choosen
8. User should be able to view the record details when clicked on the donut
9. User should be able to input notes (like characters, number, special characters and smileys) on the expense
10. User should not be able to choose the category before entering the expense value
11. User is able to cancel the new expense entry by clicking cancel button on the top right corner
12. User should be displayed with the toast message with undo button once expenses added
13. User should be able to undo the changes made on the expense or transfer using undo button on the toast message
14. User should be able to change the date by selecting the calendar displayed above the expense value input 

Note: Schedule the expense functionality is not able to test because of premium feature.


#### Observations 
1. If there are 2 category entries, if one category amount is greater than 99%, then the category represented in the chart as 0% instead of 0.X%.
2. In the donut chart, in certain cases, the line indication directing to the expenses category is not displayed.
3. In the expense addition page, if the result of the calculation exceeds 9 digits by performing additionor multiplication operation, then the value resets to the 0 value
4. In the expense addition page, paste from clipboard is not supported for the amount to be entered.
5. In the new expense page, the user is able to enter negative values (ex: -900) because the intial zero value is considered as a digit upon which any opeartion performed is calculated (Ex 0 - 900 = -900)
6. When the timeline is changed to future or past month while adding expense, then the home page redirects to the future or past month based on the selected month. Those months are displayed in the home display even though the timeline period changed to week, day or month instead of the current month. When the app is closed and opened again, the home display page resets to the current month.
7. The balance display page always redirects to the last expense entered timeline. If the latest entry is entered for the past date(2 months before), now if the page is navigatd to the current month manually and on selection of a different display(like day, month), the page goes back to the last entered timeline instead of the current timeline
8. There is no way to reset to original state while editing the expense


### Filters(10 mins)

1. The user should be able to select the different time range (day, week, month, year and all) to view records sucessfully
2. The user should be able to select the time interval on the calendar based on his choice by clicking select interval button
3. The user should be able to choose date from the calendar sucessfully
4. The user should be able to select all accounts on the profile to see the records
5. The user should be able to choose either cash or payment card on the accounts
6. The user should be able to view the records on the chart based on the filters applied
7. The user should be able to navigate to the new transfer window when the filter menu is open
8. The user should be able to navigateto the search window when the filter menu is open

#### Observations:

1. When more than two intervals are selected, then interval display list getting misaligned on subsequent addition of filters


### Records(20 mins)

1. The user should be able to view the records on clicking the donut charts
2. The user should be able to sort by the currency in decending order
3. The user should be able to sort the records by the dates
4. The user should be able to view the details of the each records by expand button
5. The user should be able to swipe left or right to see the record details for the past or future dates respectively
6. The user should redirect to the edit expense page when clicked on the expenses from the record list
7. The user should be able to add expense or income from the records page
8. The expenses should be grouped by the categories when sorted by the currency decending order and should be able to view details by expanding the categories options


### Search(10 mins)

1. The user should be able to search the records sucessfully by typing the characters
2. The user should be able to search the records sucessfully by typing the expense value
3. The user should be displayed with the "No result found" message when the search is not found 
4. The user should be able to cancel the search by clicking on the cancel button

#### Observations:

1. The partial search match is not working for the decimal values (Only after entering 99.65 the matching records are found instead of displaying records related 99.65 when 99 entered on the search bar)
2. If we search for 0.05 values, the records related to 5 are displayed instead of records related to the 0.05 value
3. Too many characters on the notes disrupts the search result UI
4. Search result is sorted based on the dates rather than relevance (Ex: if we search for sport, search sorted based on the recent entry transport instead of first search result of sport category)

### Accounts(10 mins)

1. The user should be able to add new account sucessfully by chossing the category of account
2. The user should be able to set an initial balance on the new account
3. The user should be able to edit an initial balance on the created account
4. The user should be able to exclude the amount on the balance by using the toggle button
5. The user should be able to delete the account on the balance and the relavent expense records should be deleted
6. The user should be able to edit the category of the account 
7. The user should be displayed with the toast message with undo button once new account added
8. The user should be able to undo/delete account created using undo button on the toast message
9. The user should be able to disable the account by clicking on the setting option while editin the account
10. The user should be able to merge the account to the other account sucessfully by clicking on the setting option while editin the account
11. The user should be able to enable the account sucessfully which was disabled


#### Observations:

1. Merge not happening properly when the user merge account with the payment card. The expense value is not merged to the account.


### Currencies(10 mins)

The functionality is not able to test because of premium feature.

### Settings(20 mins)

1. The user is able to set different language
2. The language changed should reflect across all other devices on the profile.
3. The user is able to set different day for the first day of month
4. The user is able to set different day of week as the first day of week
5. The user is able to change the currency on the profile
6. The user is able to export the data into the csv file
7. The user is able to sync the data on the google drive or dropbox
8. The data should be properly synced on the google drive or dropbox
9. The user should be able to delete the data by  clicking ok button on the clear data action
10. The data should not be deleted on the profile if the user clicks on the cancel button while clearing the data
11. The user is able to activate the budget mode by setting the value
12. The budget value should be split across the day, week or month based on the timeline period selected
13. The user should be able to activate the carry over option
14. The user should be able to create data backup sucessfully
15. The user should be able to restore data sucessfully

#### Observations:
1. Back up file is not availabel while trying to restore data after app re-installation 


### Categories(10 mins)
1. The user should be able to delete the category and the related expense should be deleted
2. The user should be able to disable the category sucessfully
3. The user should be able to merge the categories together sucessfully


## Non Functional

1. Security 
	* Other applications should not be able to steal the data from the application.
	* Backup files should be properly encrypted.
	* User should have the control whether or not to preserve the back up data in local while uninstalling the app.
2. Data
	* On app version upgrade, data should be properly restored.
	* Data should be restored from google drive/drop box after re-installing the app.
	* Local Data and state of the app should be properly restored after the IOS version upgrade.
3. Stability
	* The app should not get crashed when interrupted by the other third party or Native apps action(ex- incoming call, notification).
4. Performance
	* The app should be evaluated for seamless performance with different cpu loads, battery consumption, battery levels, different device specs etc.


## Risks And Mitigation

- Possible Risks
	* Data Security.
	* Data Loss.
	* Service Stability.

- Mitigation
	* Prioritize, Track and implement the possible solutions based on the risk assessment. Also monitoring should be in place for alerting in terms of issues or risks.
