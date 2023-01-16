package com.zq.service.impl;

import com.zq.controller.request.AccountLoginRequest;
import com.zq.controller.request.AccountRegisterRequest;
import com.zq.enums.AuthTypeEnum;
import com.zq.enums.BizCodeEnum;
import com.zq.enums.SendCodeEnum;
import com.zq.manager.AccountManager;
import com.zq.model.AccountDO;
import com.zq.mapper.AccountMapper;
import com.zq.model.LoginUser;
import com.zq.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zq.service.NotifyService;
import com.zq.utils.CommonUtil;
import com.zq.utils.JWTUtil;
import com.zq.utils.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@Service
@Slf4j
public class AccountServiceImpl extends ServiceImpl<AccountMapper, AccountDO> implements AccountService {

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private AccountManager accountManager;


    /**
     * 手机验证码验证
     * 密码加密（TODO）
     * 账号唯一性检查(TODO)
     * 插入数据库
     * 新注册用户福利发放(TODO)
     *
     * @param registerRequest
     * @return
     */
    @Override
    public JsonData register(AccountRegisterRequest registerRequest) {

        boolean checkCode =false;
        //判断验证码
        if(StringUtils.isNotBlank(registerRequest.getPhone())){

            checkCode = notifyService.checkCode(SendCodeEnum.USER_REGISTER,registerRequest.getPhone(),registerRequest.getCode());

        }
        //验证码错误
        if(!checkCode){
            return JsonData.buildResult(BizCodeEnum.CODE_ERROR);
        }


        AccountDO accountDO = new AccountDO();

        BeanUtils.copyProperties(registerRequest,accountDO);
        //认证级别
        accountDO.setAuth(AuthTypeEnum.DEFAULT.name());


        //生成唯一的账号  TODO
        accountDO.setAccountNo(CommonUtil.getCurrentTimestamp());

        //设置密码 秘钥 盐
        accountDO.setSecret("$1$"+CommonUtil.getStringNumRandom(8));
        String cryptPwd = Md5Crypt.md5Crypt(registerRequest.getPwd().getBytes(),accountDO.getSecret());
        accountDO.setPwd(cryptPwd);

        int rows = accountManager.insert(accountDO);
        log.info("rows:{},注册成功:{}",rows,accountDO);

        //用户注册成功，发放福利 TODO
        userRegisterInitTask(accountDO);

        return JsonData.buildSuccess();

    }


    /**
     * 1、根据手机号去找
     * 2、有的话，则用秘钥+用户传递的明文密码，进行加密，再和数据库的密文进行匹配
     *
     * @param request
     * @return
     */
    @Override
    public JsonData login(AccountLoginRequest request) {

        List<AccountDO> accountDOList = accountManager.findByPhone(request.getPhone());

        if(accountDOList!=null && accountDOList.size() ==1){

            AccountDO accountDO = accountDOList.get(0);

            String md5Crypt = Md5Crypt.md5Crypt(request.getPwd().getBytes(), accountDO.getSecret());
            if(md5Crypt.equalsIgnoreCase(accountDO.getPwd())){

                LoginUser loginUser = LoginUser.builder().build();
                BeanUtils.copyProperties(accountDO,loginUser);


                String token = JWTUtil.geneJsonWebTokne(loginUser);

                return JsonData.buildSuccess(token);

            }else {
                return JsonData.buildResult(BizCodeEnum.ACCOUNT_PWD_ERROR);
            }


        }else {
            return JsonData.buildResult(BizCodeEnum.ACCOUNT_UNREGISTER);
        }


    }

    /**
     * 用户初始化，发放福利：流量包 TODO
     * @param accountDO
     */
    private void userRegisterInitTask(AccountDO accountDO) {

    }
}
