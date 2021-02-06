package TestHandler;

import Annotations.AfterSuite;
import Annotations.BeforeSuit;
import Annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

public class TestHandler {

    public static void start(Class<?> Test) { // Класс, запускающий тесты, должен иметь статический метод start(Class testClass), которому в качестве аргумента передается объект типа Class
        Method[] declaredMethods = Test.getDeclaredMethods();
        Method beforeMethod = getSuitMethod(BeforeSuit.class, declaredMethods);
        Method afterMethod = getSuitMethod(AfterSuite.class, declaredMethods);

        runTests(declaredMethods, beforeMethod, afterMethod); // запускаем тесты
    }

    private static Method getSuitMethod(Class<? extends Annotation> suitAnnotationClass, Method[] declaredMethods) {
        Method suitMethod = null;

        for (Method declaredMethod : declaredMethods){
            if(declaredMethod.isAnnotationPresent(suitAnnotationClass)) {
                if(suitMethod != null) {
                    throw new RuntimeException(suitAnnotationClass.getName() + " Методов с аннотациями @BeforeSuite или @AfterSuite больше одного"); // Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном экземпляре.
                    // Если это не так – необходимо бросить RuntimeException при запуске «тестирования»
                } else {
                    suitMethod = declaredMethod;
                }
            }
        }
        return suitMethod;
    }

    private static void runTests(Method[] declaredMethods, Method beforeMethod, Method afterMethod) {
        startStaticMethod(beforeMethod); //вначале должен быть запущен метод с аннотацией @BeforeSuite
        callTestMethods(declaredMethods); //Далее запускаются методы с аннотациями @Test
        startStaticMethod(afterMethod); //по завершении всех тестов – метод с аннотацией @AfterSuite
    }

    private  static void callTestMethods (Method[] declaredMethods) {
        Arrays.stream(declaredMethods) // запускаем тесты в стриме
                .filter(method -> method.isAnnotationPresent(Test.class)) // фильтр методов на наличие аннотации @Test
                .sorted(Comparator.comparing(method -> method.getAnnotation(Test.class).priority())) // сортировка методов по приоритету
                .forEach(method1 -> startStaticMethod(method1));
    }

    private static void startStaticMethod(Method method) { // запускаем метод
        try {
            method.invoke(null);
        } catch (IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
        }
    }
}
