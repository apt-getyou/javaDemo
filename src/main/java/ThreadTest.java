public class ThreadTest {

	private Integer b = 0;

	public static void main(String[] args) throws InterruptedException {
		ThreadTest test = new ThreadTest();
		ThreadTest1 t1 = test.new ThreadTest1();
		ThreadTest1 t2 = test.new ThreadTest1();
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(test.b);
	}
	
	private class ThreadTest1 extends Thread{

		@Override
		public void run() {
			synchronized (b) {
				for(int i = 0; i < 10000; i ++){
					b++; 
				}
			}
		}
		
	}
}
/**
 * Result: 11659, not expect 20000
 * 
 * */
