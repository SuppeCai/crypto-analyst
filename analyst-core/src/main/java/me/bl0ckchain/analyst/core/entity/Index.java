package me.bl0ckchain.analyst.core.entity;

import me.bl0ckchain.sdk.mybatis.entity.UpdatableEntity;

public class Index extends UpdatableEntity<Long> {

    private String name;

    private String code;

    private String description;

    private Integer weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}