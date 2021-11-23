package cn.edu.tongji.gohome.login.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.edu.tongji.gohome.login.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * LoginController
 *
 * @author 卓正一
 * @since 2021/11/22 8:47 PM
 */
@RestController
@RequestMapping("api/v1/login/")
public class LoginController {

    @Resource
    LoginService loginService;

    @GetMapping("customer")
    public ResponseEntity<String> userCustomerLogin(
            @RequestParam String phoneCode,
            @RequestParam String phone,
            @RequestParam String password) {
        if (loginService.checkUserLogin(phoneCode, phone, password)) {
            Long id = loginService.getCustomerIdByPhone(phoneCode, phone);
            StpUtil.login(id);
            return ResponseEntity.ok("success! role: " + StpUtil.getPermissionList());
        } else {
            return ResponseEntity.status(401).body("Wrong password or user not exist");
        }
    }

    @GetMapping("host")
    public ResponseEntity<String> userHostLogin(
            @RequestParam String phoneCode,
            @RequestParam String phone,
            @RequestParam String password) {
        //TODO: Test this
        // Duplicate code for stputil to recognize session
        if (loginService.checkUserLogin(phoneCode, phone, password)) {
            StpUtil.login(loginService.getCustomerIdByPhone(phoneCode, phone));

            return ResponseEntity.ok("success! role: " + StpUtil.getPermissionList());
        } else {
            return ResponseEntity.status(401).body("Wrong password or user not exist");
        }
    }

    @GetMapping("administrator")
    public ResponseEntity<String> userAdminLogin(
            @RequestParam String adminName,
            @RequestParam String password) {
        if (loginService.checkAdminLogin(adminName, password)) {
            //TODO: Test this
            StpUtil.login((long) loginService.getAdminIdByName(adminName));

            return ResponseEntity.ok("success! role: " + StpUtil.getPermissionList());
        } else {
            return ResponseEntity.status(401).body("Wrong password or admin not exist");
        }
    }

    //TODO: Write API to get all permissions

    @GetMapping("permission")
    public ResponseEntity<List<String>> getUserPermissions() {
        return ResponseEntity.ok(StpUtil.getPermissionList());
    }

    @GetMapping("userRole")
    public ResponseEntity<List<String>> getUserRoles() {
        return ResponseEntity.ok(StpUtil.getRoleList());
    }

}