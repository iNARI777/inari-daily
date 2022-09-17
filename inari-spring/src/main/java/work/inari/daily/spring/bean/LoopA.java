package work.inari.daily.spring.bean;

import org.springframework.stereotype.Component;

@Component
public class LoopA {
    private LoopB loopB;

    public LoopB getLoopB() {
        return loopB;
    }

    public void setLoopB(LoopB loopB) {
        this.loopB = loopB;
    }
}
