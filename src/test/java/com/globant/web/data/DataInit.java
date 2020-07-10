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


    String[] childrenAge = { "3" };

    // Parameters to search a flight:

    FlightsParameters searchParameters = new FlightsParameters("LAS","LAX","1","1",childrenAge,60,73,"Duration (Shortest)",1,3,"July","Moreno");

    //PackagesParameters searchFlightHotelCar = new PackagesParameters("LAS","LAX",60,73,"1","July","Moreno");

    PackagesParameters packagesParameters = new PackagesParameters("LAS","LAX",5,10,11,20);

    HotelsParameters hotelsParameters = new HotelsParameters("Montevideo, Uruguay",5,10,"Radisson","email4@email.com");

    CruisesParameters cruisesParameters = new CruisesParameters("europe",30,45);

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
        return new Object[][]{{ packagesParameters }};

    }


    /**
     * Data Provider for Hotel Test
     */
    @DataProvider(name = "InputHotelData")
    public Object[][] inputHotelData() {
        return new Object[][]{{ hotelsParameters }};

    }


//    /**
//     * Data Provider for Package (Flight + Hotel + Car) Test
//     */
//    @DataProvider(name = "InputFlightHotelCarData")
//    public Object[][] inputFlightHotelCarData() {
//        return new Object[][]{{searchFlightHotelCar}};
//
//    }
}