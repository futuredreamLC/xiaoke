package com.xiaoke.springboot.dao;

import com.xiaoke.springboot.entity.Type;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Type)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-05 16:50:32
 */
public interface TypeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param typeId 主键
     * @return 实例对象
     */
    Type queryById(Integer typeId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Type> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param type 实例对象
     * @return 对象列表
     */
    List<Type> queryAll(Type type);

    /**
     * 新增数据
     *
     * @param type 实例对象
     * @return 影响行数
     */
    int insert(Type type);

    /**
     * 修改数据
     *
     * @param type 实例对象
     * @return 影响行数
     */
    int update(Type type);

    /**
     * 通过主键删除数据
     *
     * @param typeId 主键
     * @return 影响行数
     */
    int deleteById(Integer typeId);

    /**
     * 通过类别名查询类别
     * @param typename
     * @return
     */
    Type queryTypeByName(String typename);

    List<Type> queryTypeByPid(Integer parentId);
}