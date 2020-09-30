package me.bl0ckchain.sdk.log;

import java.lang.reflect.Method;

import me.bl0ckchain.sdk.constant.Characters;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * @author caisupeng
 * @version $Id: ControllerAspect.java, v 0.1 2018-12-06 3:17 PM caisupeng Exp $$
 */
@Aspect
public class MethodLogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodLogAspect.class);

    public static final String PARAMS = "params";

    public static final String RESULT = "result";

    public static final String ERROR = "error";

    public static final String NULL = "null";

    public static final int MAX_LENGTH = 500;

    public static final long MILLIS_PER_SECOND = 1000;

    public static final String COST = "cost";

    public static final String COST_UNIT = "ms";

    @Pointcut("@within(me.bl0ckchain.sdk.log.Log)||@annotation(me.bl0ckchain.sdk.log.Log)")
    public void cutRequest() {
    }

    @Around("cutRequest()")
    public Object aroundRequest(ProceedingJoinPoint point) throws Throwable {

        long startAt = System.currentTimeMillis();
        Signature signature = point.getSignature();

        Class clazz = signature.getDeclaringType();
        String clazzName = clazz.getSimpleName();
        String methodName = signature.getName();
        MethodSignature methodSignature = (MethodSignature) signature;
        Class<?>[] paramTypes = methodSignature.getParameterTypes();
        Method method = clazz.getMethod(methodName, paramTypes);

        Log classLog = AnnotationUtils.findAnnotation(clazz, Log.class);
        Log methodLog = AnnotationUtils.findAnnotation(method, Log.class);

        boolean isComplete = classLog == null ? false : classLog.isComplete();
        isComplete = methodLog == null ? isComplete : methodLog.isComplete();

        String params = params(methodSignature.getParameterNames(), point.getArgs(), isComplete);
        log(StageEnum.START, clazzName, methodName, params, null);

        Object result;
        try {
            result = point.proceed();
        } catch (Exception e) {
            log(StageEnum.ERROR, clazzName, methodName, error(e), cost(startAt));
            throw e;
        }

        String resultStr = null;
        if (result != null) {
            resultStr = params(new String[]{result.getClass().getSimpleName()}, new Object[]{result}, isComplete);
        }

        log(StageEnum.END, clazzName, methodName, resultStr, cost(startAt));
        return result;
    }

    private String params(String[] paramNames, Object[] params, boolean isComplete) {
        StringBuilder builder = new StringBuilder();
        int paramSize = params.length;
        if (paramSize != 0) {
            for (int i = 0; i < paramSize; i++) {
                String paramName = paramNames[i];
                Object param = params[i];
                builder.append(paramName);
                builder.append(Characters.EQUAL);
                String argStr = NULL;
                if (param != null) {
                    argStr = objectToString(param);
                }
                if (!isComplete && argStr.length() > MAX_LENGTH) {
                    argStr = argStr.substring(0, MAX_LENGTH);
                }
                builder.append(argStr);
                if (i < paramSize - 1) {
                    builder.append(Characters.COMMA);
                    builder.append(Characters.SPACE);
                }
            }
        }
        return builder.toString();
    }

    private String error(Exception e) {
        StringBuilder builder = new StringBuilder();
        builder.append(e.getClass().getSimpleName());
        String message = e.getMessage();
        if (message.length() > MAX_LENGTH) {
            message = message.substring(0, MAX_LENGTH);
        }
        builder.append(Characters.SPACE);
        builder.append(message);
        return builder.toString();
    }

    private void log(StageEnum stageEnum, String clazzName, String methodName, String params, Long cost) {
        StringBuilder builder = new StringBuilder();
        builder.append(Characters.LEFT_SQUARE_BRACKET);
        builder.append(stageEnum);
        builder.append(Characters.RIGHT_SQUARE_BRACKET);
        builder.append(Characters.SPACE);
        builder.append(clazzName);
        builder.append(Characters.POUND);
        builder.append(methodName);
        builder.append(Characters.SPACE);

        if (StringUtils.isNotBlank(params)) {
            switch (stageEnum) {
                case START:
                    builder.append(PARAMS);
                    break;
                case END:
                    builder.append(RESULT);
                    break;
                case ERROR:
                    builder.append(ERROR);
                    break;
                default:
                    break;
            }
            builder.append(Characters.COLON);
            builder.append(Characters.SPACE);
            builder.append(params);
            builder.append(Characters.SPACE);
        }

        if (cost != null) {
            builder.append(COST);
            builder.append(Characters.COLON);
            builder.append(Characters.SPACE);
            builder.append(cost);
            builder.append(COST_UNIT);
        }

        LOGGER.info(builder.toString());
    }

    private String objectToString(Object obj) {
        String str;
        if (obj instanceof Integer || obj instanceof Double || obj instanceof String) {
            str = obj.toString();
        } else {
            str = ToStringBuilder.reflectionToString(obj, ToStringStyle.SHORT_PREFIX_STYLE);
        }
        return str;
    }

    private long cost(long startAt) {
        return (System.currentTimeMillis() - startAt) / MILLIS_PER_SECOND;
    }
}