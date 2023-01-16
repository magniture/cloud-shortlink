package com.zq.service;

import com.zq.controller.request.AccountLoginRequest;
import com.zq.controller.request.AccountRegisterRequest;
import com.zq.model.AccountDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zq.utils.JsonData;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zq
 * @since 2023-01-15
 */
public interface AccountService extends IService<AccountDO> {
    /**
     * 用户注册
     * @param registerRequest
     * @return
     */
    JsonData register(AccountRegisterRequest registerRequest);

    /**
     * 登录
     * @param request
     * @return
     */
    JsonData login(AccountLoginRequest request);
}
