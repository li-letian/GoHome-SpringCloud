package cn.edu.tongji.gohome.login.service;

import org.springframework.stereotype.Service;

/**
 * LoginService
 *
 * @author 卓正一
 * @since 2021/11/22 8:56 PM
 */
@Service
public interface LoginService {
    Boolean checkUserLogin(String userPhoneCode, String userPhone, String password);
    Boolean checkAdminLogin(String adminName, String password);
    Long getCustomerIdByPhone(String phoneCode, String phone);
    Integer getAdminIdByName(String adminName);
}