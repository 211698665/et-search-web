package cn.henu.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.henu.common.pojo.SearchResult;
import cn.henu.search.service.SearchService;

@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;
	@Value("${SEARCH_RESULT_ROWS}")
	private Integer rows;
	@RequestMapping("/search")
	public String searchItemList(String keyword,@RequestParam(defaultValue="1")Integer page,Model model) throws Exception {
		//第一次搜索可能没有页码所有加上默认值为1,
		//把keyword进行转码，否则会出现乱码
		keyword=new String(keyword.getBytes("iso-8859-1"),"utf-8");
		//查询商品列表
		SearchResult searchResult = searchService.search(keyword, page, rows);
		//把结果传递给页面
		model.addAttribute("query",keyword);
		model.addAttribute("totalPages", searchResult.getTotalPages());
		model.addAttribute("page", page);
		model.addAttribute("recourdCount", searchResult.getRecordCount());
		model.addAttribute("itemList", searchResult.getItemList());
		
		//测试全局异常处理器
		//int a=1/0;
		//返回逻辑视图
		return "search";
	}
}
