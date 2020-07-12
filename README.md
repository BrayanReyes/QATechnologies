# Web Automation Testing Basics Exam - Acamica

# Exercise 1
Begin the process of booking a flight till the complete credit card information page.

# Exercise 2
Begin the process of booking a flight with hotel and car.

# Exercise 3
Verify that search by hotel name works properly.

**[Note: ]** The steps described in the document are not clear enough.  Therefore, I created two different test for cover this point: 
One of them cover search hotel by name, and the other one cover the finding of Member Discount Banner.

# Exercise 4
Verify that the error message displayed when looking for a hotel in incorrect dates is
correct.

# Exercise 5
Cruises discount is displayed.

**[Note: ]** I couldn't find any label, advertisement or pop-up suggesting a discount for booking cruises.
Take this in account, I decided to sort cruises by price and select the cheapest cruise in the list after filtering by nights number.

**[Suite Tests Description]**

**suiteFlight.xml**

Calls "FlightsTest" class covering the Exercise 1.

**suitePackage.xml**

Calls "PackageFlightHotelCarTest" class covering the Exercise 2 and calls "PackageFlightPartialHotelTest" class covering the Exercise 4.

**suiteHotel.xml**

Calls "SearchHotelTest" class covering the Exercise 3 - Searching Hotel by name and calls "HotelsMemberDiscountTest" class covering the Exercise 3 - Find the Member Discount Banner.

**suiteCruise.xml**

Calls "CruisesTest" class covering the Exercise 5.

**suiteTravelocity.xml**

Compilation of all Test classes described previously in one suite.