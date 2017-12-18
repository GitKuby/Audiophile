package pl.tau.restdemo.logging;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerProducer {

    @Produces
    public Logger produceLogger(InjectionPoint ip) {
        return LogManager.getLogger(ip.getMember().getDeclaringClass().getSimpleName());
    }

}
