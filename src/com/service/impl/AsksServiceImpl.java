package com.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.AsksDAO;
import com.entity.Asks;
import com.service.AsksService;

@Service("asksService") //
public class AsksServiceImpl implements AsksService {
	@Autowired // 它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作
	private AsksDAO asksDAO;
	@Override // 继承接口的新增提示问题表数据 返回值0(失败),1(成功)
	public int insertAsks(Asks asks) {
		return this.asksDAO.insertAsks(asks);
	}

	@Override // 继承接口的更新提示问题表数据 返回值0(失败),1(成功)
	public int updateAsks(Asks asks) {
		return this.asksDAO.updateAsks(asks);
	}

	@Override // 继承接口的按主键删除提示问题表数据 返回值0(失败),1(成功)
	public int deleteAsks(String asksid) {
		return this.asksDAO.deleteAsks(asksid);
	}

	@Override // 继承接口的批量删除提示问题表数据 返回值0(失败),大于0(成功)
	public int deleteAsksByIds(String[] ids) {
		return this.asksDAO.deleteAsksByIds(ids);
	}

	@Override // 继承接口的查询提示问题表全部数据
	public List<Asks> getAllAsks() {
		return this.asksDAO.getAllAsks();
	}

	@Override // 继承接口的按条件精确查询提示问题表数据
	public List<Asks> getAsksByCond(Asks asks) {
		return this.asksDAO.getAsksByCond(asks);
	}

	@Override // 继承接口的按条件模糊查询提示问题表数据
	public List<Asks> getAsksByLike(Asks asks) {
		return this.asksDAO.getAsksByLike(asks);
	}

	@Override // 继承接口的按主键查询提示问题表数据 返回Entity实例
	public Asks getAsksById(String asksid) {
		return this.asksDAO.getAsksById(asksid);
	}

}

