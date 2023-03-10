package com.zq.manager.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zq.manager.AccountManager;
import com.zq.mapper.AccountMapper;
import com.zq.model.AccountDO;
import com.zq.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zq
 * @since 2023-01-15
 */
@Component
@Slf4j
public class AccountManagerImpl implements AccountManager {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public int insert(AccountDO accountDO) {
        return accountMapper.insert(accountDO);
    }

    @Override
    public List<AccountDO> findByPhone(String phone) {

        List<AccountDO> accountDOList = accountMapper
                .selectList(new QueryWrapper<AccountDO>().eq("phone", phone));

        return accountDOList;
    }
}
