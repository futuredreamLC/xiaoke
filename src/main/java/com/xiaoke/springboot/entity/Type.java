package com.xiaoke.springboot.entity;

import java.io.Serializable;

/**
 * (Type)实体类
 *
 * @author makejava
 * @since 2020-03-05 16:50:32
 */
public class Type implements Serializable {
    private static final long serialVersionUID = -33771953276644376L;
    
    private Integer typeId;
    
    private Integer parentId;
    
    private String typename;


    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

}