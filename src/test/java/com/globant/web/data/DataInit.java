package com.globant.web.data;

import com.globant.web.utils.CruisesParameters;
import com.globant.web.utils.FlightsParameters;
import com.globant.web.utils.HotelsParameters;
import com.globant.web.utils.PackagesParameters;
import org.testng.annotations.DataProvider;

/**
 * This class defines the Data Provider that will be used for the test
 *
 * @author: july.moreno
 * @version:
 */

public class DataInit {

    // Parameters to book a flight:
    String[] childrenAge = {"3"};

    FlightsParameters searchParameters = new FlightsParameters(
            "LAS",
            "LAX",
            "1",
            "1",
            childrenAge,
            60,
            73,
            "Duration (Shortest)",
            1,
            3,
            "July",
            "Moreno");

    // Parameters to book a Package:
    PackagesParameters packagesParameters = new PackagesParameters(
            "LAS",
            "LAX",
            5,
            10,
            11,
            20);

    // Parameters to book a Hotel:
    HotelsParameters hotelsParameters = new HotelsParameters(
            "Montevideo, Uruguay",
            5,
            10,
            "Radisson",
            "email4@email.com");

    // Parameters to book a Cruise:
    CruisesParameters cruisesParameters = new CruisesParameters(
            "europe",
            20,
            50);

    /**
     * Data Provider for Flights Test
     */
    @DataProvider(name = "InputFlightsData")
    public Object[][] inputFlightsData() {
        return new Object[][]{{searchParameters}};

    }

    /**
     * Data Provider for Cruises Test
     */
    @DataProvider(name = "InputCruisesData")
    public Object[][] inputCruisesData() {
        return new Object[][]{{cruisesParameters}};

    }

    /**
     * Data Provider for Package (Flight + Hotel) Test
     */
    @DataProvider(name = "InputPackageData")
    public Object[][] inputPackageData() {
        return new Object[][]{{packagesParameters}};

    }


    /**
     * Data Provider for Hotel Test
     */
    @DataProvider(name = "InputHotelData")
    public Object[][] inputHotelData() {
        return new Object[][]{{hotelsParameters}};

    }
}