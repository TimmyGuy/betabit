package com.example.betabit.log;

import com.example.betabit.transport.Transport;
import com.example.betabit.transport.TravelPreference;
import com.example.betabit.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogTest {

    @Test
    void calculatePoints_userSubmitsTypeOfTransportAndAmountOfKilometers_shouldReturnLessPointsIfMoreEmission() {
        // Arrange
        Transport bigEmission = new Transport(1, "Benzine auto", 192);
        Transport mediumEmission = new Transport(3, "Bus (OV)", 89);
        Transport noEmission = new Transport(6, "Lopen", 0);
        User user = new User("John Doe",
                "john.doe@gmail.com",
                "test123",
                "Admin",
                0,
                0,
                0,
                0,
                null,
                null,
                new TravelPreference(-1, null));
        Log bigEmissionLog = new Log(1, null, 100, bigEmission);
        Log mediumEmissionLog = new Log(1, null, 100, mediumEmission);
        Log noEmissionLog = new Log(1, null, 100, noEmission);

        int bigEmissionPoints = 0;
        int mediumEmissionPoints = 0;
        int noEmissionPoints = 0;

        // Act
        try {
            bigEmissionPoints = bigEmissionLog.calculatePoints();
            mediumEmissionPoints = mediumEmissionLog.calculatePoints();
            noEmissionPoints = noEmissionLog.calculatePoints();
        } catch (Exception e) {
            fail("Exception thrown");
        }


        // Assert
        assertTrue((bigEmissionPoints < mediumEmissionPoints && bigEmissionPoints < noEmissionPoints));
        assertTrue(mediumEmissionPoints < noEmissionPoints);
    }

    @Test
    void calculatePoints_userSubmitsNoTransportation_shouldThrowException() {
        // Arrange
        User user = new User("John Doe",
                "john.doe@gmail.com",
                "test123",
                "Admin",
                0,
                0,
                0,
                0,
                null,
                null,
                new TravelPreference(-1, null));
        Log log = new Log(1, user, 100, null);

        // Act

        // Assert
        assertThrows(Exception.class, log::calculatePoints);
    }
}