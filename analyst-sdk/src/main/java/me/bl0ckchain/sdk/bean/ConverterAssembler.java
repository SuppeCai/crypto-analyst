/*
 * Project:  msc-parent
 * Module:   msc-server
 * File:     ConvertAssembler.java
 * Modifier: xyang
 * Modified: 2014-08-21 08:49
 *
 * Copyright (c) 2014 Wisorg All Rights Reserved.
 *
 * Copying of this document or code and giving it to others and the
 * use or communication of the contents thereof, are forbidden without
 * expressed authority. Offenders are liable to the payment of damages.
 * All rights reserved in the event of the grant of a invention patent
 * or the registration of a utility model, design or code.
 */

package me.bl0ckchain.sdk.bean;

import java.util.Collection;
import java.util.Map;

/**
 * 对象转换组装器
 *
 * @param <D> 目标对象
 * @param <S> 源对象
 * @param <K> 获取值的key
 * @param <V> 值
 * @author <a href="mailto:xyang@wisorg.com">xyang</a>
 * @version V1.0, 14-8-19
 */
public interface ConverterAssembler<D, S, K, V> {

    /**
     * 根据传入的提示判断是否需要装配
     *
     * @param hints 提示条件
     * @return 是否装配
     */
    boolean accept(Map<String, Object> hints);

    /**
     * 从原对象中提取key
     *
     * @param s 源对象
     * @param d 目标对象
     * @return 键
     */
    K getKey(S s, D d);

    /**
     * 设置源对象值
     *
     * @param s     源对象
     * @param d     目标对象
     * @param value 值
     */
    void setValue(S s, D d, V value);

    /**
     * 根据键获取目标值
     *
     * @param key 键
     * @return value
     */
    V getValue(K key);

    /**
     * 批量获取值
     *
     * @param keys 键列表
     * @return 值map
     */
    Map<K, V> mgetValue(Collection<K> keys);
}
