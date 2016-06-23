package com.id.get.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.id.get.model.ModelView;
import com.id.get.model.Pager;
import com.id.get.model.SmsStatusInfo;
import com.id.get.service.ISmsService;
import com.id.tools.lucence.Article;
import com.id.tools.lucence.SearchManager;

/**
 *
 * <br>类 名: StatServlet
 * <br>描 述: 统计servlet
 * <br>作 者: 付强
 * <br>创 建： 2014-1-3
 * <br>版 本：v1.0
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
@Controller
@Scope("prototype")
public class DistServlet{
	/**log**/
	private static final Logger logger = LoggerFactory.getLogger(DistServlet.class);
	
	@Autowired
	private ISmsService smsService;
	
	@Autowired
	private SearchManager searchManager;

	/**
	 * 
	 * <br>描 述：跳转短信查询页面
	 * <br>作 者：yufenghe 
	 * <br>历 史: (版本) 作者 时间 注释
	 * @return
	 */
	@RequestMapping(value = "/show.uz")
	public String showSmsView() {
		return "distView";
	}
	
	@RequestMapping(value = "/websocket.uz")
	public String websocket() {
		return "websocket/websocket";
	}
	
	/**
	 * 
	 * <br>描 述：跳转短信查询页面
	 * <br>作 者：yufenghe 
	 * <br>历 史: (版本) 作者 时间 注释
	 * @return
	 */
	@RequestMapping(value = "/require.uz")
	public String requireView() {
		return "requireView";
	}
	/**
	 * 
	 * <br>描 述：跳转短信查询页面
	 * <br>作 者：yufenghe 
	 * <br>历 史: (版本) 作者 时间 注释
	 * @return
	 */
	@RequestMapping(value = "/test.uz")
	public String test() {
		return "index";
	}
	/**
	 * 
	 * <br>描 述：查看发送状态表
	 * <br>作 者：yufenghe 
	 * <br>历 史: (版本) 作者 时间 注释
	 * @return
	 */
	@RequestMapping(value = "/showStatusList.uz")
	public String showStatusList() {
		return "statusOverview";
	}
	
	/**
	 * 
	 * <br>描 述：查询短信状态信息
	 * <br>作 者：yufenghe 
	 * <br>历 史: (版本) 作者 时间 注释
	 * @param pager
	 * @return ModelView<SmsStatusInfo>
	 * @throws Exception
	 */
	@RequestMapping(value = "/query.uz", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ModelView<SmsStatusInfo> query(Pager<SmsStatusInfo> pager) throws Exception {
		ModelView<SmsStatusInfo> mv = new ModelView<SmsStatusInfo>();
		
		if(pager.getStart() == null || pager.getEnd() == null) {
			logger.info("未设置查询起始日期start={}或截止日期end={}", new Object[]{pager.getStart(), pager.getEnd()});
			return mv;
		}
		try {
			logger.info("查询参数为{}", new Object[]{pager.toString()});
			Long pageSize = pager.getPageSize();
			Map<String, Object> map = new HashMap<String, Object>();
			if(pageSize == null) {
				pageSize = (long) Pager.PAGE_SIZE;
				pager.setPageSize(pageSize);
			}
			
			map.put("phone", pager.getPhone()==null||StringUtils.isBlank(pager.getPhone()) ? null :pager.getPhone());
			map.put("pageSize", pageSize);
			long startIndex = (pager.getCurrentPage() - 1) * pageSize;
			map.put("startIndex", startIndex);
			map.put("start", pager.getStart());
			map.put("end", pager.getEnd());
			pager = smsService.querySmsStatusDetail(pager, map);
			
			mv.setTotal(pager.getTotalSize());
			mv.setRows(pager.getList());
		} catch(Exception e) {
			logger.error("查询数据异常{}", new Object[]{pager.toString()});
			logger.error("异常信息", e);
		}
		
		return mv;
	}
	
	/**
	 * 
	 * <br>描 述：跳转短信查询页面
	 * <br>作 者：yufenghe 
	 * <br>历 史: (版本) 作者 时间 注释
	 * @return
	 */
	@RequestMapping(value = "/index.uz")
	public String main() {
		return "index";
	}
	
	/**
	 * 
	 * <br>描 述：跳转短信查询页面
	 * <br>作 者：yufenghe 
	 * <br>历 史: (版本) 作者 时间 注释
	 * @return
	 */
	@RequestMapping(value = "/dashboard.uz")
	public String dashboard() {
		return "dashbord/index";
	}
	
	
	/**
	 * 
	 * <br>描 述：跳转短信查询页面
	 * <br>作 者：yufenghe 
	 * <br>历 史: (版本) 作者 时间 注释
	 * @return
	 */
	@RequestMapping(value = "/showSearch.uz")
	public String search() {
		return "search";
	}
	
	@RequestMapping(value = "/search.uz", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<Article> searchResult(String key) {
		return searchManager.searchIndex(key);
	}
	
	
	@RequestMapping(value = "/searchResult.uz", method={RequestMethod.POST, RequestMethod.GET})
	public String searchRe(String key, ModelMap model) {
		List<Article> list = searchManager.searchIndex(key);
		model.addAttribute("list", list);
		model.addAttribute("test", "testtesttesttest");
		model.addAttribute("CREATE_HTML", Boolean.FALSE);
		return "result";
	}
}
