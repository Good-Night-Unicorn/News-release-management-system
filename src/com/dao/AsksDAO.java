package com.dao;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.entity.Asks;

@Repository("asksDAO") // Repository标签定义数据库连接的访问 Spring中直接扫描加载
@Mapper // 不需要在spring配置中设置扫描地址 spring将动态的生成Bean后注入到AsksServiceImpl中
public interface AsksDAO {

	/**
	* AsksDAO 接口 可以按名称直接调用asks.xml配置文件的SQL语句
	*/

	// 插入提示问题表数据 调用mapper包asks.xml里的insertAsks配置 返回值0(失败),1(成功)
	public int insertAsks(Asks asks);

	// 更新提示问题表数据 调用mapper包asks.xml里的updateAsks配置 返回值0(失败),1(成功)
	public int updateAsks(Asks asks);

	// 按主键删除提示问题表数据 调用mapper包asks.xml里的deleteAsks配置 返回值0(失败),1(成功)
	public int deleteAsks(String asksid);

	// 批量删除提示问题表数据 调用mapper包asks.xml里的deleteAsksByIds配置 返回值0(失败),大于0(成功)
	public int deleteAsksByIds(String[] ids);

	// 查询提示问题表全部数据 调用mapper包asks.xml里的getAllAsks配置 返回List<Asks>类型的数据
	public List<Asks> getAllAsks();

	// 按照Asks类里面的值精确查询 调用mapper包asks.xml里的getAsksByCond配置 返回List<Asks>类型的数据
	public List<Asks> getAsksByCond(Asks asks);

	// 按照Asks类里面的值模糊查询 调用mapper包asks.xml里的getAsksByLike配置 返回List<Asks>类型的数据
	public List<Asks> getAsksByLike(Asks asks);

	// 按主键查询提示问题表返回单一的Asks实例 调用mapper包asks.xml里的getAsksById配置
	public Asks getAsksById(String asksid);

}


