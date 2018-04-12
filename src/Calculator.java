import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class Calculator
{
    private ExecutorService exec;

    private CompletionService<Integer> completionService;


    private int cpuCoreNumber;

    // 内部类
    class SumCalculator implements Callable<Integer>
    {
        private int numbers;
        private int start;
        private int end;

        public SumCalculator(int numbers, int start, int end)
        {
            this.numbers = numbers;
            this.start = start;
            this.end = end;
        }

        @Override
        public Integer call() throws Exception
        {
            // TODO Auto-generated method stub
            Random rand = new Random();
            TimeUnit.SECONDS.sleep(rand.nextInt(7));
            return rand.nextInt();

        }

    }

    public Calculator()
    {
        cpuCoreNumber = Runtime.getRuntime().availableProcessors();
        exec = Executors.newFixedThreadPool(cpuCoreNumber);
        completionService = new ExecutorCompletionService<Integer>(exec);


    }

    public Long sum()
    {
        // 根据CPU核心个数拆分任务，创建FutureTask并提交到Executor
        for (int i = 0; i < cpuCoreNumber; i++)
        {
            SumCalculator subCalc = new SumCalculator(1, 1, 1);
            if (!exec.isShutdown())
            {
                completionService.submit(subCalc);
            }
        }

        return getResult();
    }

    public Long getResult()
    {
        Long result = 0l;
        for (int i = 0; i < cpuCoreNumber; i++)
        {
            try
            {
                Integer f = completionService.take().get();
                if (f == null)
                {

                }
                else
                {
                    Integer kuu = f;
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void close()
    {
        exec.shutdown();

    }
}
