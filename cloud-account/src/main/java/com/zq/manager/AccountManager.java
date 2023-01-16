package com.zq.manager;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zq.model.AccountDO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zq
 * @since 2023-01-15
 */
public interface AccountManager  {

    int insert(AccountDO accountDO);


    List<AccountDO> findByPhone(String phone);

}
