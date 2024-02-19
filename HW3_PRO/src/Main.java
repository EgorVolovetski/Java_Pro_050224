import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME) // Вказуємо, що анотація буде доступна в час виконання
@Target(ElementType.METHOD)
@interface SaveTo{
    String path();
}
@interface Saver{}
class Something {
    public static void print (int a, int b){
        System.out.println(a);
        System.out.println(b);
    }
    @test (a = 2, b = 5)
    public void test1(int a, int b) {
        print(a, b);
    }
}


class TextContainer{
    private String content = "Good job";
    @Saver
    @SaveTo(path = "C:\\Users\\Egor\\IdeaProjects\\HW3_PRO\\text.txt")
    public void Saver(String path){
    String content = this.content;
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.close(); 

            System.out.println("Текст успішно збережено у файл: " + path);
        } catch (IOException e) {
            System.out.println("Сталася помилка при спробі записати у файл: " + e.getMessage());
        }


    }


}



public class Main {
    public static void main(String[] args) throws Exception{
            Something obj = new Something();
            Method method = obj.getClass().getMethod("test1", int.class, int.class);
            test t = method.getAnnotation(test.class);
            if (t != null) {
                int a = t.a();
                int b = t.b();
                obj.test1(a, b);
            }
            else {
                System.out.println("Annotation is null");
            }

            TextContainer tc = new TextContainer();
            Method method1 = tc.getClass().getMethod("Saver", String.class);
            SaveTo st = method1.getAnnotation(SaveTo.class);
            String path = st.path();
            tc.Saver(path);
    }
}