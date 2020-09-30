package me.bl0ckchain.analyst.config;

import me.bl0ckchain.sdk.cache.CacheAssembler;
import me.bl0ckchain.sdk.mybatis.RepositoryAssembler;
import me.bl0ckchain.sdk.mybatis.proxy.RepositoryAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import me.bl0ckchain.sdk.scheduler.SchedulerRegister;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 25/06/2018
 */
@Configuration
@Import({RepositoryAspect.class, RepositoryAssembler.class, CacheAssembler.class, SchedulerRegister.class})
public class Config {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
