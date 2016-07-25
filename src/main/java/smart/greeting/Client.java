package smart.greeting;

/**
 * @author 刘博文
 * @date 2016/7/25 0025 21:58
 */
public class Client {
    public static void main(String[] args){

        //============================
        //静态代理方法，导致XxxProxy类会越来越多

        Greeting greetingProxy = new GreetingProxy(new GreetingImpl());
        greetingProxy.sayHello("liu");

        //=====================================


        //====================================
        //JDK 动态代理
        Greeting greeting = new JdkDynamicProxy(new GreetingImpl()).getProxy();
        greeting.sayHello("JDK");
    }


}
