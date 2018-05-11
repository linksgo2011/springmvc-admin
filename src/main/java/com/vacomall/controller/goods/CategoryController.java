package com.vacomall.controller.goods;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.vacomall.common.anno.Log;
import com.vacomall.common.bean.Rest;
import com.vacomall.common.controller.SuperController;
import com.vacomall.entity.SysCategory;
import com.vacomall.entity.SysDept;
import com.vacomall.entity.SysUser;
import com.vacomall.service.ISysCategoryService;
import com.vacomall.service.ISysDeptService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 分类控制器
 */
@Controller
@RequestMapping("/goods/category")
public class CategoryController extends SuperController{

	@Autowired private ISysCategoryService sysCategoryService;
	
	/**
	 * 分页查询部门
	 */
	@RequiresPermissions("listCategory")
    @RequestMapping("/list/{pageNumber}")
    public  String list(@PathVariable Integer pageNumber,@RequestParam(defaultValue="15") Integer pageSize, String search,Model model){
    	
		Page<SysCategory> page = getPage(pageNumber,pageSize);
		model.addAttribute("pageSize", pageSize);
		// 查询分页
		EntityWrapper<SysCategory> ew = new EntityWrapper<SysCategory>();
		if(StringUtils.isNotBlank(search)){
			ew.like("name",search);
			model.addAttribute("search",search);
		}
		Page<SysCategory> pageData = sysCategoryService.selectPage(page, ew);
		model.addAttribute("pageData", pageData);
		return "goods/category/list";
    }

	/**
	 * 新增分类
	 */
	@RequiresPermissions("addCategory")
	@RequestMapping(value = "/add")
	public  String add(){
		return "goods/category/add";
	}

	/**
	 * 编辑分类
	 */
	@RequiresPermissions("editCategory")
	@RequestMapping(value = "/edit/{id}")
	public  String edit(@PathVariable String id,Model model){

		SysCategory sysCategory = sysCategoryService.selectById(id);

		model.addAttribute("sysCategory",sysCategory);
		return "goods/category/edit";
	}

	/**
	 * 执行编辑分类
	 */
	@Log("编辑分类")
	@RequiresPermissions("editCategory")
	@RequestMapping(value = "/testEdit")
	@ResponseBody
	public  Rest doEdit(
			SysCategory sysCategory){

		sysCategoryService.updateById(sysCategory);
		return Rest.ok();
	}


	/**
	 * 执行新增
	 */
	@Log("创建分类")
	@RequiresPermissions("addCategory")
	@RequestMapping("/doAdd")
	@ResponseBody
	public  Rest doAdd(
			SysCategory sysCategory){

		sysCategoryService.insert(sysCategory);
		return Rest.ok();
	}

	/**
	 * 删除分类
	 */
	@Log("删除分类")
	@RequiresPermissions("deleteCategory")
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public  Rest delete(@PathVariable String id){
		sysCategoryService.deleteById(id);
		return Rest.ok();
	}
}
