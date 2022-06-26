package work.inarigo.lab.lb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RouterPlayGround {

    public static void main(String[] args) {
        List<WeightedServer> servers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            WeightedServer server = new WeightedServer();
            server.setId(Integer.toString(i));
            server.setWeight(10 * (i + 1));
            servers.add(server);
        }

        Map<String, Integer> statistics = new HashMap<>();

        ILoadBalancer lb = new SimpleWeightedLoadBalancer(servers);
//        ILoadBalancer lb = new NginxLoadBalancer(servers);

        for (int i = 0; i < 10000; i++) {
            String target = lb.select();
            Integer count = statistics.getOrDefault(target, 0);
            statistics.put(target, ++count);
        }

        for (Map.Entry<String, Integer> entry : statistics.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }
}
