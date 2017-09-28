package smart.greeting;

/**
 * @author banhujiu
 * @date 2016/7/25 0025 21:53
 */
public class GreetingImpl implements Greeting {

    @Override
    public void sayHello(String name) {
        before();
        System.out.print("Hello  !  "+ name);
        after();
    }

    private void before(){
        System.out.print("Before\n");
    }

    private void after(){
        System.out.print("\nAfter");
    }
}
