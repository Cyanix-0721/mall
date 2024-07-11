/**
 * 首页广告管理Service接口
 * 提供首页广告的增删改查等操作
 */
package com.mole.marketing.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mole.marketing.entity.SmsHomeAdvertise;

/**
 * 首页广告管理Service
 */
public interface SmsHomeAdvertiseService {
	/**
	 * 添加广告
	 * @param advertise 需要添加的广告信息
	 * @return 添加成功的广告条数
	 */
	/**
	 * 添加广告
	 */
	int create(SmsHomeAdvertise advertise);

	/**
	 * 根据ID删除广告
	 * @param id 广告的ID
	 * @return 删除操作是否成功
	 */
	/**
	 * 删除广告
	 */
	boolean deteleById(Long id);

	/**
	 * 更新广告的状态
	 * @param id 广告的ID
	 * @param status 新的状态值
	 * @return 更新操作影响的行数
	 */
	/**
	 * 修改上、下线状态
	 */
	int updateStatus(Long id, Integer status);

	/**
	 * 根据ID获取广告详情
	 * @param id 广告的ID
	 * @return 广告的详细信息
	 */
	/**
	 * 获取广告详情
	 */
	SmsHomeAdvertise gotById(Long id);
	/**
	 * 根据ID更新广告信息
	 * @param advertise 需要更新的广告信息
	 * @return 更新操作是否成功
	 */
	/**
	 * 更新广告
	 */
	boolean updateById(SmsHomeAdvertise advertise);

	/**
	 * 分页查询广告列表
	 * @param name 广告名称，可选查询条件
	 * @param type 广告类型，可选查询条件
	 * @param endTime 广告结束时间，可选查询条件
	 * @param pageSize 每页的广告数量
	 * @param pageNum 当前页码
	 * @return 包含广告信息的分页对象
	 */
	/**
	 * 分页查询广告
	 */
	IPage<SmsHomeAdvertise> list(String name, Integer type, String endTime, Integer pageSize, Integer pageNum);
}
