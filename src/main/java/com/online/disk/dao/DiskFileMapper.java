package com.online.disk.dao;

import com.online.disk.model.DiskFile;

public interface DiskFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiskFile record);

    int insertSelective(DiskFile record);

    DiskFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiskFile record);

    int updateByPrimaryKey(DiskFile record);
}