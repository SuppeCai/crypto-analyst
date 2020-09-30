package me.bl0ckchain.sdk.mybatis;

import me.bl0ckchain.sdk.mybatis.assembler.AbstractAssembler;
import me.bl0ckchain.sdk.mybatis.assembler.AbstractManyAssembler;
import me.bl0ckchain.sdk.mybatis.assembler.Assembler;
import me.bl0ckchain.sdk.mybatis.assembler.ManyToManyAssembler;
import me.bl0ckchain.sdk.mybatis.repository.AbstractRepository;
import me.bl0ckchain.sdk.mybatis.repository.BaseRepository;
import me.bl0ckchain.sdk.mybatis.repository.CacheRepository;
import me.bl0ckchain.sdk.utils.ClassUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 26/06/2018
 */
@Order(1)
public class RepositoryAssembler implements ApplicationListener<ContextRefreshedEvent> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Map<Class<?>, BaseRepository> repositoryMap = new HashMap<>();

    private Map<Class<?>, List<Assembler>> assemblerMap = new HashMap<>();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();

        Map<String, AbstractRepository> repositoryMap = context.getBeansOfType(AbstractRepository.class);
        for (AbstractRepository repository : repositoryMap.values()) {
            putRepository(repository);
        }

        Map<String, Object> assemblerMap = context.getBeansWithAnnotation(me.bl0ckchain.sdk.mybatis.annotation.Assembler.class);
        for (Object obj : assemblerMap.values()) {
            if (!(obj instanceof AbstractAssembler)) {
                throw new IllegalArgumentException("Assembler class must extend from AbstractAssembler.");
            }
            AbstractAssembler assembler = (AbstractAssembler) obj;
            int vIndex = assembler instanceof AbstractManyAssembler ? 3 : 2;
            assembler.setValueRepo(getRepository(ClassUtils.getGenericClass(assembler.getClass(), vIndex)));
            if (assembler instanceof ManyToManyAssembler) {
                ManyToManyAssembler manyToMany = ((ManyToManyAssembler) assembler);
                manyToMany.setAssociationRepo(getRepository(manyToMany.getAssociatedClasses()));
            }
            putAssembler(assembler);
        }

        for (BaseRepository repository : this.repositoryMap.values()) {
            AbstractRepository repo = ((AbstractRepository) repository);
            Class<?> targetClass = ClassUtils.getGenericClass(repo.getClass(), 1);
            List<Assembler> assemblers = this.assemblerMap.get(targetClass);
            if (CollectionUtils.isNotEmpty(assemblers)) {
                repo.setAssemblers(assemblers);
            }
            RedisTemplate template = (RedisTemplate) context.getBean("redisTemplate");
            if (repo instanceof CacheRepository) {
                ((CacheRepository) repo).setRedisTemplate(template);
            }
        }
    }

    private void putRepository(AbstractRepository repository) {
        this.repositoryMap.put(ClassUtils.getGenericClass(repository.getClass(), 1), repository);
    }

    private void putAssembler(AbstractAssembler assembler) {
        Class<?> clazz = assembler.getTClass();
        List<Assembler> assemblers = this.assemblerMap.get(clazz);
        if (assemblers == null) {
            assemblers = new ArrayList<>();
            assemblerMap.put(clazz, assemblers);
        }
        assemblers.add(assembler);
    }

    public BaseRepository getRepository(Class<?> clazz) {
        return this.repositoryMap.get(clazz);
    }

}
