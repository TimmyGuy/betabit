package com.example.betabit;

import com.example.betabit.container.ProfileContainer;
import com.example.betabit.store.Store;
import com.example.betabit.transport.TravelPreference;
import com.example.betabit.user.User;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {

    @Test
    void buy_userClicksBuyButtonOnStorePage_shouldBuyIfEnoughPointsAndBadgesOrActivateIfUserAlreadyHasIt() {
        // Arrange
        ProfileContainer container = new ProfileContainer(1, "Container", Color.BLACK, 1, 100, 5);
        User testcase1 = new User("John Doe",
                "john.doe@gmail.com",
                "test123",
                "Admin",
                150,
                150,
                0,
                0,
                null,
                null,
                new TravelPreference(-1, null));
        User testcase2 = new User("John Doe",
                "john.doe@gmail.com",
                "test123",
                "Admin",
                50,
                50,
                0,
                0,
                null,
                container,
                new TravelPreference(-1, null));
        testcase2.addContainer(container);
        User testcase3 = new User("John Doe",
                "john.doe@gmail.com",
                "test123",
                "Admin",
                50,
                50,
                10,
                10,
                null,
                container,
                new TravelPreference(-1, null));
        testcase3.addContainer(container);
        User testcase4 = new User("John Doe",
                "john.doe@gmail.com",
                "test123",
                "Admin",
                150,
                150,
                10,
                10,
                null,
                null,
                new TravelPreference(-1, null));
        Store store = Store.getInstance();

        // Act
        HelloApplication.user = testcase1;
        boolean result1 = store.handleTransaction(testcase1, container);
        HelloApplication.user = testcase2;
        boolean result2 = store.handleTransaction(testcase2, container);
        HelloApplication.user = testcase3;
        boolean result3 = store.handleTransaction(testcase3, container);
        HelloApplication.user = testcase4;
        boolean result4 = store.handleTransaction(testcase4, container);

        // Assert
        assertFalse(result1);
        assertTrue(result2);
        assertTrue(result3);
        assertTrue(result4);
    }
}