package com.loast.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.loast.entity.User;
import org.apache.ibatis.annotations.Update;

/**
 * 用户(User)表数据库访问层
 *
 * @author Arman
 * @since 2021-11-03 21:14:16
 */
public interface UserMapper extends BaseMapper<User> {

}

