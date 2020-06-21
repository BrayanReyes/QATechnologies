#ESPN RESULTS

suiteESPN includes Log In, Log Out and Deactivate Account.  
The execution will show a result like this:


------------------------------------------------------------------------------------------------------------------------------
LOG IN
------------------------------------------------------------------------------------------------------------------------------
2020-06-07 17:18:05 INFO  Listener:46 - THE TEST GROUP STARTED
2020-06-07 17:18:27 INFO  BaseTest:35 - Log Info: Opening Browser ESPN: Serving sports fans. Anytime. Anywhere.
2020-06-07 17:18:27 INFO  Listener:21 - THE TEST STARTED
2020-06-07 17:18:27 INFO  BasePage:85 - Log Info:  Log In Option clicked - Switch to the iframe
2020-06-07 17:18:31 INFO  BasePage:139 - Log Info: User Request to Log In
2020-06-07 17:18:52 INFO  BasePage:110 - Log info: Validating the label Welcome crheqf!
2020-06-07 17:18:52 INFO  Listener:26 - THE TEST PASSED

2020-06-07 17:18:53 INFO  BasePage:62 - Log Info: Log Out Option clicked
2020-06-07 17:19:03 INFO  BasePage:129 - Log Info: Validating de label Welcome!

2020-06-07 17:19:33 INFO  BaseTest:46 - Log Info: Closing Browser
2020-06-07 17:19:33 INFO  Listener:51 - THE TEST GROUP FINISHED


------------------------------------------------------------------------------------------------------------------------------
LOG OUT
------------------------------------------------------------------------------------------------------------------------------
2020-06-07 17:19:33 INFO  Listener:46 - THE TEST GROUP STARTED

2020-06-07 17:19:43 INFO  BaseTest:35 - Log Info: Opening Browser ESPN: Serving sports fans. Anytime. Anywhere.
2020-06-07 17:19:44 INFO  BasePage:85 - Log Info:  Log In Option clicked - Switch to the iframe
2020-06-07 17:19:53 INFO  BaseTest:67 - UserDataESPN: {firstName='kdpedh', lastName='vkxqxw', email='kdpedh.vkxqxw@espn.com', password='kdpedh123*', statusAccount=NEW}
2020-06-07 17:19:53 INFO  BasePage:89 - Log Info: Creating an ESPN Account
2020-06-07 17:19:56 INFO  BasePage:97 - Log Info: ESPN Account created
2020-06-07 17:19:56 INFO  BaseTest:69 - Log Info: Validating errors in Sign Up process
2020-06-07 17:20:43 INFO  BasePage:110 - Log info: Validating the label Welcome kdpedh!
2020-06-07 17:20:43 INFO  Listener:21 - THE TEST STARTED
2020-06-07 17:20:44 INFO  BasePage:62 - Log Info: Log Out Option clicked
2020-06-07 17:20:54 INFO  BasePage:129 - Log Info: Validating de label Welcome!
2020-06-07 17:20:55 INFO  Listener:26 - THE TEST PASSED

2020-06-07 17:20:56 INFO  BaseTest:46 - Log Info: Closing Browser
2020-06-07 17:20:56 INFO  Listener:51 - THE TEST GROUP FINISHED

------------------------------------------------------------------------------------------------------------------------------
DEACTIVATE ACCOUNT
------------------------------------------------------------------------------------------------------------------------------
2020-06-07 17:20:56 INFO  Listener:46 - THE TEST GROUP STARTED

2020-06-07 17:21:04 INFO  BaseTest:35 - Log Info: Opening Browser ESPN: Serving sports fans. Anytime. Anywhere.
2020-06-07 17:21:04 INFO  BasePage:85 - Log Info:  Log In Option clicked - Switch to the iframe
2020-06-07 17:21:12 INFO  BaseTest:67 - UserDataESPN: {firstName='fxdcie', lastName='kbpmwp', email='fxdcie.kbpmwp@espn.com', password='fxdcie123*', statusAccount=NEW}
2020-06-07 17:21:12 INFO  BasePage:89 - Log Info: Creating an ESPN Account
2020-06-07 17:21:14 INFO  BasePage:97 - Log Info: ESPN Account created
2020-06-07 17:21:14 INFO  BaseTest:69 - Log Info: Validating errors in Sign Up process
2020-06-07 17:22:06 INFO  BasePage:110 - Log info: Validating the label Welcome fxdcie!
2020-06-07 17:22:06 INFO  Listener:21 - THE TEST STARTED
2020-06-07 17:22:06 INFO  BasePage:71 - Log Info:  ESPN Profile Option clicked - Switch to the iframe
2020-06-07 17:22:09 INFO  BasePage:149 - Log Info: User request to delete the Account
2020-06-07 17:22:12 INFO  BasePage:163 - Log Info: User accept to delete the account
2020-06-07 17:22:12 INFO  BasePage:85 - Log Info:  Log In Option clicked - Switch to the iframe
2020-06-07 17:22:18 INFO  BasePage:139 - Log Info: User Request to Log In
2020-06-07 17:22:18 INFO  BasePage:171 - Log Info: Checking if the account was deactivated
2020-06-07 17:22:18 INFO  Listener:26 - THE TEST PASSED

2020-06-07 17:22:21 INFO  BaseTest:46 - Log Info: Closing Browser
2020-06-07 17:22:21 INFO  Listener:51 - THE TEST GROUP FINISHED


===============================================
Test ESPN
Total tests run: 3, Failures: 0, Skips: 0
===============================================
