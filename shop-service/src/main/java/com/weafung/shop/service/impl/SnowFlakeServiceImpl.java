package com.weafung.shop.service.impl;

import com.google.common.collect.Maps;
import com.weafung.shop.common.util.SnowFlake;
import com.weafung.shop.service.*;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author weifengshih
 */
@Service
public class SnowFlakeServiceImpl implements SnowFlakeService {

    private Map<Class<?>, SnowFlake> map;

    public SnowFlakeServiceImpl() {
        map = Maps.newHashMap();
        map.put(AddressService.class, new SnowFlake(1L, 1L));
        map.put(GoodsService.class, new SnowFlake(1L, 2L));
        map.put(GorderService.class, new SnowFlake(1L, 3L));
        map.put(OrderService.class, new SnowFlake(1L, 4L));
        map.put(CategoryService.class, new SnowFlake(1L, 5L));
        map.put(SkuAttributeNameService.class, new SnowFlake(1L, 6L));
        map.put(SkuAttributeValueService.class, new SnowFlake(1L, 7L));

        // dataCenterId maxValue : 31
        map.put(this.getClass(), new SnowFlake(1L, 31L));
    }

    @Override
    public Long nextId(Class<?> clazz) {
        if (map.get(clazz) == null) {
            return map.get(this.getClass()).nextId();
        }
        return map.get(clazz).nextId();
    }
}
