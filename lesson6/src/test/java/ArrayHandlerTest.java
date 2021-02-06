import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ArrayHandlerTest {
    public final ArrayHandler arrayHandler = new ArrayHandler();

    @ParameterizedTest
    @MethodSource("dataForSuccessGettingValuesAfterLastFour")
    void intsAfterLastFour(int[] originalArray, int[] expectedResultArray) {
        int[] actualResultArray = arrayHandler.intsAfterLastFour(originalArray);

        Assertions.assertArrayEquals(expectedResultArray, actualResultArray);
    }

    private static Stream<Arguments> dataForSuccessGettingValuesAfterLastFour() {
        List<Arguments> arguments = new ArrayList<>() {{
            add(Arguments.arguments(new int[]{1, 2, 3, 4, 5}, new int[]{5}));
            add(Arguments.arguments(new int[]{1, 2, 3, 4}, new int[]{}));
            add(Arguments.arguments(new int[]{4, 1, 2, 4, 3, 4, 5}, new int[]{5}));
            add(Arguments.arguments(new int[]{4, 1, 2, 3, 5}, new int[]{1, 2, 3, 5}));
        }};

        return arguments.stream();
    }

    @Test
    void intsAfterLastFourWaitExcepyion() {
        int[] originalArray = {1, 2, 3};
        Assertions.assertThrows(RuntimeException.class,
                () -> arrayHandler.intsAfterLastFour(originalArray));

        int[] empty = {};
        Assertions.assertThrows(RuntimeException.class,
                () -> arrayHandler.intsAfterLastFour(empty));
    }

    @ParameterizedTest
    @MethodSource("dataForFindFourOrOne")
    void findFourOrOne(int[] array, boolean expectedResult) {
        boolean actualResult = arrayHandler.findFourOrOne(array);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> dataForFindFourOrOne() {
        List<Arguments> arguments = new ArrayList<>() {{
            add(Arguments.arguments(new int[]{1, 1, 1, 4, 4, 1, 4, 4,}, true));
            add(Arguments.arguments(new int[]{1, 1, 1, 1, 1, 1,}, false));
            add(Arguments.arguments(new int[]{4, 4, 4, 4,}, false));
            add(Arguments.arguments(new int[]{1, 4, 4, 1, 1, 4, 3}, false));
        }};

        return arguments.stream();
    }
}

