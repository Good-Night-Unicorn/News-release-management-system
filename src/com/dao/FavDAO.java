package com.dao;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.entity.Fav;

@Repository("favDAO") // Repository标签定义数据库连接的访问 Spring中直接扫描加载
@Mapper // 不需要在spring配置中设置扫描地址 spring将动态的生成Bean后注入到FavServiceImpl中
public interface FavDAO {

	/**
	* FavDAO 接口 可以按名称直接调用fav.xml配置文件的SQL语句
	*/

	// 插入用户收藏表数据 调用mapper包fav.xml里的insertFav配置 返回值0(失败),1(成功)
	public int insertFav(Fav fav);

	// 更新用户收藏表数据 调用mapper包fav.xml里的updateFav配置 返回值0(失败),1(成功)
	public int updateFav(Fav fav);

	// 按主键删除用户收藏表数据 调用mapper包fav.xml里的deleteFav配置 返回值0(失败),1(成功)
	public int deleteFav(String favid);

	// 批量删除用户收藏表数据 调用mapper包fav.xml里的deleteFavByIds配置 返回值0(失败),大于0(成功)
	public int deleteFavByIds(String[] ids);

	// 查询用户收藏表全部数据 调用mapper包fav.xml里的getAllFav配置 返回List<Fav>类型的数据
	public List<Fav> getAllFav();

	// 按照Fav类里面的值精确查询 调用mapper包fav.xml里的getFavByCond配置 返回List<Fav>类型的数据
	public List<Fav> getFavByCond(Fav fav);

	// 按照Fav类里面的值模糊查询 调用mapper包fav.xml里的getFavByLike配置 返回List<Fav>类型的数据
	public List<Fav> getFavByLike(Fav fav);

	// 按主键查询用户收藏表返回单一的Fav实例 调用mapper包fav.xml里的getFavById配置
	public Fav getFavById(String favid);

}


