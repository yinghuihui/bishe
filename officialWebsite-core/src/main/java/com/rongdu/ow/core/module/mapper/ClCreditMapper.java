package com.rongdu.ow.core.module.mapper;


import java.util.Map;

import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.mapper.RDBatisDao;
import com.rongdu.ow.core.module.domain.ClCredit;

/**
 * 授信额度表Dao
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-25 17:43:39
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface ClCreditMapper extends BaseMapper<ClCredit, Long> {
   /**
    * 更新额度
    * @param param
    */
	int updateAmount(Map<String,Object> param);

}
