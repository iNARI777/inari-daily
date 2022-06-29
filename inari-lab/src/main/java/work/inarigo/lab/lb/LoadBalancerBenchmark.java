package work.inarigo.lab.lb;

import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class LoadBalancerBenchmark {
    public static void main(String[] args) throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(RouterPlayGround.class.getSimpleName())
                .result("result.json")
                .resultFormat(ResultFormatType.JSON)
                .build();
        new Runner(opts).run();
    }
}
