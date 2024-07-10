package com.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.entity.Asks;
@Service("asksService") // 自动注册到Spring容器，不需要再在xml文件定义bean
public interface AsksService {
	// 插入提示问题表数据 调用asksDAO里的insertAsks配置
	public int insertAsks(Asks asks);

	// 更新提示问题表数据 调用asksDAO里的updateAsks配置
	public int updateAsks(Asks asks);

	// 按主键删除提示问题表数据 调用asksDAO里的deleteAsks配置
	public int deleteAsks(String asksid);

	// 批量删除提示问题表数据 调用mapper包asks.xml里的deleteAsksByIds配置 返回值0(失败),大于0(成功)
	public int deleteAsksByIds(String[] ids);

	// 查询全部数据 调用asksDAO里的getAllAsks配置
	public List<Asks> getAllAsks();

	// 按照Asks类里面的字段名称精确查询 调用asksDAO里的getAsksByCond配置
	public List<Asks> getAsksByCond(Asks asks);

	// 按照Asks类里面的字段名称模糊查询 调用asksDAO里的getAsksByLike配置
	public List<Asks> getAsksByLike(Asks asks);

	// 按主键查询表返回单一的Asks实例 调用asksDAO里的getAsksById配置
	public Asks getAsksById(String asksid);

}

