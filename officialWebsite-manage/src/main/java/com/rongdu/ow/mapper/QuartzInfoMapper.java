package com.rongdu.ow.mapper;

import java.util.List;
import java.util.Map;

import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.mapper.RDBatisDao;
import com.rongdu.ow.domain.QuartzInfo;
import com.rongdu.ow.model.QuartzInfoModel;

/**
 * 定时任务详情Dao
 * 
 
 */
@RDBatisDao
public interface QuartzInfoMapper extends BaseMapper<QuartzInfo, Long> {

	/**
	 * 定时任务查询
	 * 
	 * @param map
	 * @return
	 */
	List<QuartzInfoModel> page(Map<String, Object> map);
}
