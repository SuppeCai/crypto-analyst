package me.bl0ckchain.analyst.endpoint;

import me.bl0ckchain.analyst.cache.HeartBeatCache;
import me.bl0ckchain.analyst.endpoint.encode.MessageDecoder;
import me.bl0ckchain.analyst.endpoint.encode.MessageEncoder;
import me.bl0ckchain.analyst.core.model.Message;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 09/07/2018
 */
@Component
@ServerEndpoint(value = "/analysis", encoders = {MessageEncoder.class}, decoders = {MessageDecoder.class})
public class AnalysisEndPoint implements ApplicationContextAware {

    private final static Logger logger = LoggerFactory.getLogger(AnalysisEndPoint.class);

    private static CopyOnWriteArraySet<Session> SESSIONS = new CopyOnWriteArraySet<>();

    private static HeartBeatCache CACHE;

    @OnOpen
    public void onOpen(Session session) {
        SESSIONS.add(session);
        CACHE.put(session.getId());
        System.out.println("open");
    }

    @OnClose
    public void onClose(Session session) {
        SESSIONS.remove(session);
        System.out.println("close");
    }

    @OnMessage
    public void onMessage(Session session, Message message) {
        if (Message.Type.PING == message.getType()) {
            CACHE.put(session.getId());
            message.setType(Message.Type.PONG);
            try {
                session.getBasicRemote().sendObject(message);
            } catch (Exception e) {
                logger.error("pong error:", e);
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("onError:", error);
    }

    public void broadcast(Message message) {
        try {
            for (Session s : SESSIONS) {
                s.getBasicRemote().sendObject(message);
            }
        } catch (Exception e) {
            logger.error("broadcast error:", e);
        }
    }

    @Scheduled(fixedRate = DateUtils.MILLIS_PER_MINUTE)
    public void checkHeartBeat() {
        for (Session session : SESSIONS) {
            if (!CACHE.exist(session.getId())) {
                SESSIONS.remove(session);
                if (session.isOpen()) {
                    try {
                        session.close();
                    } catch (IOException e) {
                        logger.error("close session error:", e);
                    }
                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CACHE = applicationContext.getBean(HeartBeatCache.class);
    }
}