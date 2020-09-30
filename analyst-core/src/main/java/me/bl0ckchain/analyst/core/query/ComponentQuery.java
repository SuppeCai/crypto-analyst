package me.bl0ckchain.analyst.core.query;

import me.bl0ckchain.analyst.core.monitoring.ComponentType;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 06/08/2018
 */
public class ComponentQuery {

    private ComponentType componentType;

    private Long componentId;

    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    public Long getComponentId() {
        return componentId;
    }

    public void setComponentId(Long componentId) {
        this.componentId = componentId;
    }
}
