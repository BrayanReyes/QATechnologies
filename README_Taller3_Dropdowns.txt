****************************   READ ME  **************************** 

----------------------------------------------------------------------
1. suiteTest
----------------------------------------------------------------------
The test execution will recreate a Flight Search. 

Due to a business restriction the expected result is to get this alert
message.
 
"We are only able to book between 1 and 6 travellers. Please adjust
 the number of travellers for your search."

Expected result:
BaseTest:28 - Open Browser
BasePage:69 - Click on Flights Tab
Listener:49 - THE TEST STARTED
BasePage:78 - Select Roundtrip Option
BasePage:89 - City Flying From typed
BasePage:100 - City Flying To typed
BasePage:112 - Selecting Departing Date through the Calendar
BasePage:123 - Selecting Returning Date through the Calendar
BasePage:132 - Selecting Adults quantity in the dropdown list
BasePage:146 - Selecting Children quantity in the dropdown list
BasePage:160 - Setting Children Ages in the dropdown list
BasePage:179 - Searching Flights
Listener:55 - THE TEST PASSED
BaseTest:47 - Reload Home Page
BaseTest:34 - Close Browser
Listener:20 - THE TEST FINISHED