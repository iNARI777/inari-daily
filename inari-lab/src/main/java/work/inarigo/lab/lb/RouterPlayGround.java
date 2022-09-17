package work.inarigo.lab.lb;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 5)
@Threads(4)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class RouterPlayGround {

    private SimpleWeightedLoadBalancer randomLbr;
    private NginxLoadBalancer roundRobinLbr;

    public RouterPlayGround() {
        List<WeightedServer> servers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            WeightedServer server = new WeightedServer();
            server.setId(Integer.toString(i));
            server.setWeight(10 * (i + 1));
            servers.add(server);
        }
        randomLbr = new SimpleWeightedLoadBalancer(servers);
        roundRobinLbr = new NginxLoadBalancer(servers);
    }

    @Benchmark
    public String selectRandom() {
        return randomLbr.select();
    }

    @Benchmark
    public String selectRoundRobin() {
        return roundRobinLbr.select();
    }
}
