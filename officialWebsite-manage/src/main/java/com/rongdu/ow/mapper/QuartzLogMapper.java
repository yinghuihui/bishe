package com.rongdu.ow.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.mapper.RDBatisDao;
import com.rongdu.ow.domain.QuartzLog;
import com.rongdu.ow.model.QuartzLogModel;

/**
 * 定时任务记录Dao
 * 
 */
@RDBatisDao
public interface QuartzLogMapper extends BaseMapper<QuartzLog,Long> {

	/**
	 * 据quartId查询最后执行时间
	 * @param id
	 * @return
	 */
	String findLastTimeByQzInfoId(@Param("quartzId") Long quartId);

	/**
	 * 日志查询
	 * @param searchMap
	 * @return
	 */
	List<QuartzLogModel> page(Map<String, Object> searchMap);

    

}
