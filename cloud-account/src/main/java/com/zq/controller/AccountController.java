package com.zq.controller;


import com.zq.controller.request.AccountRegisterRequest;
import com.zq.enums.BizCodeEnum;
import com.zq.service.AccountService;
import com.zq.service.FileService;
import com.zq.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zq
 * @since 2023-01-15
 */
@RestController
@RequestMapping("/accountDO")
public class AccountController {

    @Autowired
    private FileService fileService;


    @Autowired
    private AccountService accountService;

    /**
     * 文件上传 最大默认1M
     *  文件格式、拓展名等判断
     * @param file
     * @return
     */
    @PostMapping("upload")
    public JsonData uploadUserImg(@RequestPart("file") MultipartFile file){

        String result = fileService.uploadUserImg(file);

        return result !=null ? JsonData.buildSuccess(result):JsonData.buildResult(BizCodeEnum.FILE_UPLOAD_USER_IMG_FAIL);

    }


    /**
     * 用户注册
     * @param registerRequest
     * @return
     * 核⼼逻辑
     * 通过phone找数据库记录
     * 获取盐，和当前传递的密码就⾏加密后匹配
     * ⽣成token令牌
     */
    @PostMapping("register")
    public JsonData register(@RequestBody AccountRegisterRequest registerRequest){

        JsonData jsonData = accountService.register(registerRequest);
        return jsonData;
    }


}

