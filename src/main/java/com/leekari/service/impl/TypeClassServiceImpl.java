package com.leekari.service.impl;

import com.leekari.dao.TypeClassDao;
import com.leekari.entity.TypeClass;
import com.leekari.service.TypeClassService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author litao
 * @date 2020/8/19 13:33
 * @description
 */
@Service
public class TypeClassServiceImpl implements TypeClassService {
    @Resource
    private TypeClassDao typeClassDao;

    @Override
    public List<TypeClass> getAll() {
        return typeClassDao.getAllTypeClass();
    }
}
