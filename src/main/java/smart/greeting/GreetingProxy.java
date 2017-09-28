package smart.greeting;

/**
 * @author banhujiu
 * @date 2016/7/25 0025 21:55
 */
public class GreetingProxy implements Greeting {

    private GreetingImpl greetingImpl;

    public GreetingProxy(GreetingImpl greetingImpl) {
        this.greetingImpl = greetingImpl;
    }

    @Override
    public void sayHello(String name) {
        before();
        greetingImpl.sayHello(name);
        after();
    }

    private void after() {
        System.out.print("\nAfter");
    }

    private void before() {
        System.out.print("Before\n");
    }
}
