package ribbonconfiguration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RibbonConfiguration
 *
 * @author Sonic
 * @since 2020/4/6
 */
@Configuration
public class RibbonConfiguration {

    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }

//    @Bean
//    public IPing ping() {
//        return new PingUrl();
//    }


}
