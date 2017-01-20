package com.hncu.dao.mapper;

import com.hncu.common.BaseMapper;
import com.hncu.entity.Upload;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 上传数据持久化操作层
 */
@Repository
public interface UploadMapper extends BaseMapper<Upload> {
    /**
     * 资料上传
     * @param upload
     * @return
     */
    int uploadDate(Upload upload);
}
