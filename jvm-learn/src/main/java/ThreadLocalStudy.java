/**
 * @Author guxiang02
 * @Date 2020/12/15
 **/
public class ThreadLocalStudy {

    ThreadLocal<String> t1 = new ThreadLocal<>();

    private String content;

    public String getContent() {
        return t1.get();
    }

    public void setContent(String content) {
        t1.set(content);

    }

    public static void main(String[] args) {
        ThreadLocalStudy threadLocalStudy = new ThreadLocalStudy();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    threadLocalStudy.setContent(Thread.currentThread().getName() + "的数据");
                    System.out.println("==============");
                    System.out.println(threadLocalStudy.getContent() +">>>>>>" + Thread.currentThread().getName() );
                    try {
                        Thread.sleep(100000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.setName("线程 " + i);
            thread.start();
        }

    }
}
