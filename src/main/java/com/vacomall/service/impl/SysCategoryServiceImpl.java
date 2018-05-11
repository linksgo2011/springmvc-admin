package com.vacomall.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.vacomall.entity.SysCategory;
import com.vacomall.entity.SysDept;
import com.vacomall.mapper.SysCategoryMapper;
import com.vacomall.mapper.SysDeptMapper;
import com.vacomall.service.ISysCategoryService;
import com.vacomall.service.ISysDeptService;
import org.springframework.stereotype.Service;

/**
 *
 * sys_category 实现类
 *
 */
@Service
public class SysCategoryServiceImpl extends ServiceImpl<SysCategoryMapper, SysCategory> implements ISysCategoryService {

}