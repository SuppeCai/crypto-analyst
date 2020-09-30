package me.bl0ckchain.analyst.core.entity;

import me.bl0ckchain.analyst.core.monitoring.IComponent;
import me.bl0ckchain.sdk.mybatis.entity.UpdatableEntity;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 06/06/2018
 */
public class Analysis extends UpdatableEntity<Long> implements IComponent {

    private String name;

    private String code;

    private String description;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

}