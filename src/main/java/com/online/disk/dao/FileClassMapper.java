package com.online.disk.dao;

import com.online.disk.model.FileClass;

public interface FileClassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FileClass record);

    int insertSelective(FileClass record);

    FileClass selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileClass record);

    int updateByPrimaryKey(FileClass record);
}