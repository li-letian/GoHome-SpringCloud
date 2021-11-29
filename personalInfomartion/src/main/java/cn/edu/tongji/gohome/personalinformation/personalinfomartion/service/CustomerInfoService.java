package cn.edu.tongji.gohome.personalinformation.personalinfomartion.service;

import cn.edu.tongji.gohome.personalinformation.personalinfomartion.dto.CustomerInfoDto;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashMap;

/**
 * CustomerInfoService类，待实现的接口
 * @author 梁乔
 * @since 2021/11/23 11:34
 */
@Service
public interface CustomerInfoService {
    HashMap<String,Object> searchCustomerInfoByCustomerId(Long customerId);

    void updateAvatar(Long customerId, String base64File);

    void updateUserInfo(CustomerInfoDto customerInfoDto, Long customerId)throws ParseException;

    HashMap<String,Object> insertNewFavorite(String favoriteName, Long customerId);

    HashMap<String,Object> getFavoriteImage(Integer favoriteId);

    void deleteFavoriteById(Integer favoriteId);

    HashMap<String,Object> getFavoriteStayInfo(Integer favoriteId);

    void addStayToFavorite(Integer favoriteId, Long stayId);

    void deleteStayFromFavorite(Integer favoriteId, Long stayId);

    HashMap<String,Object> getHostInfoByCustomerId(Long customerId);
}