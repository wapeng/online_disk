package com.online.disk.dao;

import java.util.List;
import java.util.Map;

import com.online.disk.model.OnlineFile;

public interface OnlineFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OnlineFile record);

    int insertSelective(OnlineFile record);

    OnlineFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OnlineFile record);

    int updateByPrimaryKey(OnlineFile record);
    
    List<OnlineFile> querys(Map<String, Object> param);
}