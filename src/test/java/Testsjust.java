import listeners.ListenerForAPI;
import listeners.ListenerProperty;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Log4j
@Listeners({ListenerProperty.class, ListenerForAPI.class})
public class Testsjust {
    @Test
    public void test1() {
        System.out.println(1);
    }

    @Test
    public void test2() {
        System.out.println(1);
    }

    @Test
    public void test3() {
        System.out.println(1);
    }

    @Test
    public void test4() {
        System.out.println(1);
    }

    @Test
    public void test5() {
        System.out.println(1);
    }

    @Test
    public void test6() {
        System.out.println(1);
    }

    @Test
    public void test7() {
        System.out.println(1);
    }

    @Test
    public void test8() {
        System.out.println(1);
    }

    @Test
    public void test9() {
        System.out.println(1);
    }

    @Test
    public void test10() {
        System.out.println(1);
    }
}
