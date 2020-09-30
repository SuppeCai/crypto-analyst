package me.bl0ckchain.sdk.mybatis.proxy;

import me.bl0ckchain.sdk.mybatis.entity.BaseEntity;
import me.bl0ckchain.sdk.mybatis.repository.AbstractRepository;
import me.bl0ckchain.sdk.utils.ClassUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 03/07/2018
 */
@Aspect
public class RepositoryAspect {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("target(me.bl0ckchain.sdk.mybatis.repository.BaseRepository)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        Object target = point.getTarget();
        Object result = point.proceed();

        if (result == null || result == target) {
            return result;
        }

        try {
            AbstractRepository repository = (AbstractRepository) target;

            if (!repository.autoAssemble()) {
                repository.cleanThreadLocal();
                return result;
            }

            Class<?> targetClass = ClassUtils.getGenericClass(repository.getClass(), 1);
            if (result.getClass().equals(targetClass)) {
                repository.assemble((BaseEntity) result);
            } else if (result instanceof Collection) {
                Collection c = (Collection<?>) result;
                if (CollectionUtils.isNotEmpty(c) && c.iterator().next().getClass().equals(targetClass)) {
                    repository.assemble(c);
                }
            } else if (result instanceof Map) {
                Map m = (Map) result;
                if (MapUtils.isNotEmpty(m)) {
                    Collection c = m.values();
                    if (c.iterator().next().getClass().equals(targetClass)) {
                        repository.assemble(c);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Repository AOP error:", e);
        }
        return result;
    }

}
