package com.hncu.dao.mapper;

import com.hncu.common.BaseMapper;
import com.hncu.entity.Notice;
import org.springframework.stereotype.Repository;

/**
 * 通知公告数据持久化操作接口层
 */
@Repository
public interface NoticeMapper extends BaseMapper<Notice> {

    int insertNoticeByRole(Notice notice);

    int updateNotice(Notice notice);

    int deleteNotice(Notice notice);
}
