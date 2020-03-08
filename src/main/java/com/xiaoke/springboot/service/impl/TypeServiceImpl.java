package com.xiaoke.springboot.service.impl;

import com.xiaoke.springboot.entity.Type;
import com.xiaoke.springboot.dao.TypeDao;
import com.xiaoke.springboot.service.TypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * (Type)表服务实现类
 *
 * @author makejava
 * @since 2020-03-05 16:50:32
 */
@Service("typeService")
public class TypeServiceImpl implements TypeService {
    @Resource
    private TypeDao typeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param typeId 主键
     * @return 实例对象
     */
    @Override
    public Type queryById(Integer typeId) {
        return this.typeDao.queryById(typeId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Type> queryAllByLimit(int offset, int limit) {
        return this.typeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param type 实例对象
     * @return 实例对象
     */
    @Override
    public Type insert(Type type) {
        this.typeDao.insert(type);
        return type;
    }

    /**
     * 修改数据
     *
     * @param type 实例对象
     * @return 实例对象
     */
    @Override
    public Type update(Type type) {
        this.typeDao.update(type);
        return this.queryById(type.getTypeId());
    }

    /**
     * 通过主键删除数据
     *
     * @param typeId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer typeId) {
        return this.typeDao.deleteById(typeId) > 0;
    }

    @Override
    public Type queryTypeByName(String typename){
        return this.typeDao.queryTypeByName(typename);
    }

    @Override
    public List<Type> queryTypeByPid(Integer parentId){
        List<Type> parentTypes=typeDao.queryTypeByPid(parentId);
        Iterator<Type> iterator=parentTypes.iterator();
        while (iterator.hasNext()){
            Type it=iterator.next();
            List<Type> childrenTypes=typeDao.queryTypeByPid(it.getTypeId());
            Map<Integer,List<Type>> collect =childrenTypes.stream().collect(Collectors.groupingBy(Type::getParentId));
            List<Type> children=collect.get(it.getTypeId());
            it.setChildrenTypes(children);
        }
        return parentTypes;
    }
}