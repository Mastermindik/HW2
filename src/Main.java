import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        // Task 1
        Class <?> cls = Main.class;
        Method[] methods = cls.getMethods();
        for (Method method: methods) {
            if (method.isAnnotationPresent(MyAnotation.class)) {
                MyAnotation annotation = method.getAnnotation(MyAnotation.class);
                try {
                    method.invoke(null, annotation.a(), annotation.b());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        // Task 2
        saver(new TextContainer());
    }

    @MyAnotation(a = 2, b = 5)
    public static void test(int a, int b) {
        System.out.println(a + b);
    }

    public static void saver(Object obj) {
        Class <?> cls = obj.getClass();
        if (cls.isAnnotationPresent(SaveTo.class)) {
            Method[] methods = cls.getMethods();
            for (Method method: methods){
                if (method.isAnnotationPresent(Saver.class)) {
                    try {
                        method.invoke(obj, cls.getDeclaredField("text").get(obj), cls.getAnnotation(SaveTo.class).path());
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}