import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // Вказуємо, що анотація буде доступна в час виконання
@Target(ElementType.METHOD)
public @interface test {
    int a() default 0;
    int b() default 0;
}
