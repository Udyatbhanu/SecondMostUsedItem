import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.example.Main.getSecondMostUsed;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    static Map<String, Set<String>> recipeMap = new HashMap<>();

    @BeforeAll
    static void initAll() {
        recipeMap.put("pie", Set.of("flour", "pepperoni", "tomato", "cheese", "salt", "pepper"));
        recipeMap.put("cake", Set.of("flour", "milk", "sugar", "cheese" ));
        recipeMap.put("icecream", Set.of("milk", "sugar", "nuts"));
        recipeMap.put("soup", Set.of("water", "meat", "salt",  "pepper","potatoes"));
        recipeMap.put("fries", Set.of("potatoes", "salt"));
    }

    @Test
    public void testResultsForSecondMostUsedItem(){

        List<String> result = getSecondMostUsed(recipeMap);
        assertFalse(result.isEmpty());
        assertEquals(6, result.size());
        assertTrue(result.contains("milk"));
    }


    @AfterAll
    static void tearDownAll() {
        recipeMap  = null;
    }
}
