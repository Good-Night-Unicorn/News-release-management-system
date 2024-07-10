package com.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.FavDAO;
import com.entity.Fav;
import com.service.FavService;

@Service("favService") //
public class FavServiceImpl implements FavService {
	@Autowired // 它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作
	private FavDAO favDAO;
	@Override // 继承接口的新增用户收藏表数据 返回值0(失败),1(成功)
	public int insertFav(Fav fav) {
		return this.favDAO.insertFav(fav);
	}

	@Override // 继承接口的更新用户收藏表数据 返回值0(失败),1(成功)
	public int updateFav(Fav fav) {
		return this.favDAO.updateFav(fav);
	}

	@Override // 继承接口的按主键删除用户收藏表数据 返回值0(失败),1(成功)
	public int deleteFav(String favid) {
		return this.favDAO.deleteFav(favid);
	}

	@Override // 继承接口的批量删除用户收藏表数据 返回值0(失败),大于0(成功)
	public int deleteFavByIds(String[] ids) {
		return this.favDAO.deleteFavByIds(ids);
	}

	@Override // 继承接口的查询用户收藏表全部数据
	public List<Fav> getAllFav() {
		return this.favDAO.getAllFav();
	}

	@Override // 继承接口的按条件精确查询用户收藏表数据
	public List<Fav> getFavByCond(Fav fav) {
		return this.favDAO.getFavByCond(fav);
	}

	@Override // 继承接口的按条件模糊查询用户收藏表数据
	public List<Fav> getFavByLike(Fav fav) {
		return this.favDAO.getFavByLike(fav);
	}

	@Override // 继承接口的按主键查询用户收藏表数据 返回Entity实例
	public Fav getFavById(String favid) {
		return this.favDAO.getFavById(favid);
	}

}

