package me.bl0ckchain.analyst.core.monitoring;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 22/06/2018
 */
public interface IComponent {

    /**
     * Get name of the component.
     *
     * @return
     */
    String getName();

    /**
     * Get code of the component.
     *
     * @return
     */
    String getCode();

    /**
     * Get description of the component.
     *
     * @return
     */
    String getDescription();
}
