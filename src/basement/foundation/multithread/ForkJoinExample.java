package basement.foundation.multithread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkJoinExample  extends RecursiveTask<Integer> {
    private final int threshold = 5;
    private int first;
    private int last;

    public ForkJoinExample(int first,int last){
        this.first = first;
        this.last = last;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        // directly
        if(last-first <= threshold){
            for(int i=first;i<=last;++i)
                result += i;
        }else {
            int middle = first+(last-first)/2;

            ForkJoinExample left = new ForkJoinExample(first, middle);
            ForkJoinExample right = new ForkJoinExample(middle+1, last);

            left.fork();
            right.fork();

            // waiting for completing
            result = left.join()+right.join();
        }

        return result;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinExample example = new ForkJoinExample(1, 1000000);

        // need special threadPool
        ForkJoinPool pool = new ForkJoinPool();
        Future res = pool.submit(example);

        System.out.println(res.get());

    }
}
