import Annotations.AfterSuite;
import Annotations.BeforeSuit;
import Annotations.Test;

public class Tests {
    @BeforeSuit
    public static void beforeSuit() {
        System.out.println();
        System.out.println("№1. Метод с аннотацией @BeforeSuit");
    }

    @Test(priority = 1)
    public static void test1() {
        System.out.println();
        System.out.println("№2. Тест с приоритетом 1");
    }

    @Test(priority = 2)
    public static void test2() {
        System.out.println("№3. Тест с приоритетом 2");
    }

    @Test(priority = 3)
    public static void test3() {
        System.out.println("№4. Тест с приоритетом 3");
    }

    @Test(priority = 4)
    public static void test4() {
        System.out.println("№5. Тест с приоритетом 4");
    }

    @AfterSuite
    public static void afterSuite() {
        System.out.println();
        System.out.println("№6. Метод с аннотацией @AfterSuite");
    }

}
