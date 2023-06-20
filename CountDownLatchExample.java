public class CountDownLatchExample  { 
    public static void main(String args[]) throws InterruptedException {  
        CountDownLatch latch = new CountDownLatch(4);  
  
        Worker thread1 = new Worker(2000, latch, "Worker Thread-1");  
        Worker thread2 = new Worker(3000, latch, "Worker Thread-2");  
        Worker thread3 = new Worker(1000, latch, "Worker Thread-3");  
        Worker thread4 = new Worker(4000, latch, "Worker Thread-4");  
       
        thread1.start();  
        thread2.start();  
        thread3.start();  
      
        thread4.start();  
        System.out.println("Main thread is here");
        latch.await();  
        System.out.println("Thread "+Thread.currentThread().getName()+" has finished");  
        
        /* <-------- Console -------->
            Main thread is here
            Worker Thread-3 has finished
            Worker Thread-1 has finished
            Worker Thread-2 has finished
            Worker Thread-4 has finished
            main has finished
        */
    }  
}  
 
class Worker extends Thread  { 
    private int delay;  
    private CountDownLatch latch; 
     
    public Worker(int delay, CountDownLatch latch, String name)  {  
        super(name);  
        this.delay = delay;  
        this.latch = latch;  
    }  
    @Override  
    public void run()  {   
        try {  
            Thread.sleep(delay);  
            latch.countDown();
            System.out.println(Thread.currentThread().getName() + " has finished");  
        }catch (InterruptedException e){  
            e.printStackTrace();  
        }  
    }  
}  
