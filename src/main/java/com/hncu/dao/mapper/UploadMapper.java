package com.hncu.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 上传数据持久化操作层
 */
@Repository
public interface UploadMapper {
    int uploadDate(@Param("title") String title,@Param("issuer") String issuer,
                   @Param("description") String description,@Param("receive") String receive,
                   @Param("uploadFile") String uploadFile,@Param("uploadPath") String uploadPath,
                   @Param("uploadFileOldName") String uploadFileOldName);
}
