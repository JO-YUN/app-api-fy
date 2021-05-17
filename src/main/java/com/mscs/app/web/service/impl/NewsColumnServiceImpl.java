package com.mscs.app.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mscs.app.common.dto.req.ReqNewsColumn;
import com.mscs.app.common.dto.resp.RespNewsColumn;
import com.mscs.app.web.dao.NewsColumnMapper;
import com.mscs.app.web.service.NewsColumnService;



@Service
public class NewsColumnServiceImpl implements NewsColumnService {
	@Autowired
	private NewsColumnMapper newsColumnMapper;

	
	@Override
	public List<RespNewsColumn> queryNewsColumnList(ReqNewsColumn obj) {
		    obj.setLevel("1");
		    obj.setPrentid("0");
		    List<RespNewsColumn> newslistAll=new ArrayList<RespNewsColumn>();
		    List<RespNewsColumn> listAll=new ArrayList<RespNewsColumn>();
			List<RespNewsColumn> list = newsColumnMapper.queryNewsColumnList(obj);
			for(RespNewsColumn column:list) {
				    RespNewsColumn newColumn=column;
					obj.setLevel("2");
					obj.setPrentid(column.getId());
					newColumn.setChildList(newsColumnMapper.queryNewsColumnList(obj));
				listAll.add(newColumn);
			}
			//给url增加栏目ID
			
			for (RespNewsColumn respNewsColumn : listAll) {
				RespNewsColumn newqespNewsColum=respNewsColumn;
				newqespNewsColum.setUrl(respNewsColumn.getUrl()+"?columnid="+respNewsColumn.getId());
				List<RespNewsColumn> childs=respNewsColumn.getChildList();
				List<RespNewsColumn> newchilds=new  ArrayList<RespNewsColumn>();
				if(childs.size()>0) {
					for(RespNewsColumn child:childs) {
						RespNewsColumn newchild=child;
						//带有锚点的就不用拼接了
						if(child.getUrl().indexOf("#")==-1) {
							newchild.setUrl(child.getUrl()+"?lmid="+child.getId());
						}
						newchilds.add(newchild);
					}
				}
				newqespNewsColum.setChildList(newchilds);
				newslistAll.add(newqespNewsColum);
			}
		return list;
	}


	

	

 
}
