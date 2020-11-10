package MultiThreading.ThreadCommunication;
// 测试生产者消费者模式——管程法
public class WithBuffer {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        Productor productor = new Productor(buffer);
        Customer customer = new Customer(buffer);

        // 启动
        productor.start();
        customer.start();

    }
}

// 生产者
class Productor extends Thread{
    Buffer buffer = new Buffer();  // 需要一个缓冲池
    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        // 生产鸡
        for (int i = 0; i < 100; i++) {
            buffer.push(new Chicken(i));
            System.out.println("生产了第"+i+"只鸡");
        }
    }
}

// 消费者
class Customer extends Thread{
    Buffer buffer = new Buffer();

    public Customer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        // 消费鸡
        for (int i = 0; i < 100; i++) {
            Chicken chicken = buffer.pop();
            System.out.println("消费了第"+chicken.id+"只鸡");
        }
    }
}

// 产品
class Chicken{
    int id;  // 鸡的编号

    public Chicken(int id) {
        this.id = id;
    }
}

// 缓存区
class Buffer{
    // 缓冲区大小
    Chicken[] bufferSize = new Chicken[10];
    // 记录长度
    int count = 0;
    // 生产者生产的鸡丢进缓冲区
    public synchronized void push(Chicken chicken){
        if(count >= 9){
            // 此时缓冲区放不下更多的鸡，通知生产暂停
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        bufferSize[count] = chicken;
        count += 1;
        this.notifyAll();

    }

    // 消费者消费
    public synchronized Chicken pop(){
        Chicken chicken;
        if(count == 0){
            // 此时没有鸡可以消费
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count -= 1;
        chicken = bufferSize[count];

        this.notifyAll();
        return chicken;
    }


}
