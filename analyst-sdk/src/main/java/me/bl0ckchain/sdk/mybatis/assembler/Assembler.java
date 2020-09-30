package me.bl0ckchain.sdk.mybatis.assembler;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 26/06/2018
 */
public interface Assembler<T> {

    /**
     * Check if target class match the assembler.
     *
     * @param target
     * @return
     */
    boolean match(Class<?> target);

    /**
     * Assemble bean.
     *
     * @param bean
     */
    void assemble(T bean);
}
