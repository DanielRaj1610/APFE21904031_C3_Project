import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class RestaurantTest {
    Restaurant restaurant;
    int initialMenuSize;
    LocalTime currentMockInsideTime = LocalTime.parse("21:00:00");
    LocalTime currentMockOutsideTime = LocalTime.parse("23:00:00");
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");

    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void setUp() {
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        initialMenuSize = restaurant.getMenu().size();
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {

        Restaurant spyRes = Mockito.spy(restaurant);
        Mockito.when(spyRes.getCurrentTime()).thenReturn(currentMockInsideTime);
        assertTrue(spyRes.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
        Restaurant spyRes = Mockito.spy(restaurant);
        Mockito.when(spyRes.getCurrentTime()).thenReturn(currentMockOutsideTime);
        assertFalse(spyRes.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>>Price<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Test
    public void addSelectedItems_that_does_not_exist_in_menu_should_throw_exception(){
        Item item = new Item("briyani",250);
        assertThrows(itemNotFoundException.class,
                () -> restaurant.addSelectedItems(item));

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<Price>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1() {

        restaurant.addToMenu("Sizzling brownie", 319);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                () -> restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


}