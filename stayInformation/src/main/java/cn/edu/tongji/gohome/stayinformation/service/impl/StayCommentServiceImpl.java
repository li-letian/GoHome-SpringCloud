package cn.edu.tongji.gohome.stayinformation.service.impl;

import cn.edu.tongji.gohome.stayinformation.dto.CommentDto;
import cn.edu.tongji.gohome.stayinformation.dto.StayCommentInfoDto;
import cn.edu.tongji.gohome.stayinformation.dto.mapper.CommentMapper;
import cn.edu.tongji.gohome.stayinformation.model.*;
import cn.edu.tongji.gohome.stayinformation.repository.*;
import cn.edu.tongji.gohome.stayinformation.service.StayCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * StayCommentServiceImpl类
 *
 * @author 汪明杰
 * @date 2021/11/22 18:47
 */
@Service
public class StayCommentServiceImpl implements StayCommentService {

    @Resource
    private StayRepository stayRepository;

    @Resource
    private OrderStayRepository orderStayRepository;

    @Resource
    private CustomerCommentRepository customerCommentRepository;

    @Resource
    private CustomerRepository customerRepository;

    @Resource
    private OrderRepository orderRepository;

    /**
     * 获取某一个stayId的评论集
     * @param stayId
     * @return
     */
    @Override
    public StayCommentInfoDto searchCommentInfoForStayId(long stayId){

        // 评论集合
        List<CommentDto> commentDtoList = new ArrayList<>();

        List<OrderStayEntity> orderStayEntityList =
                orderStayRepository.findAllByStayId(stayId);

        double aveRatings = 0;
        int sumComment = 0;

        Set<Long> orderIdSet = new HashSet<>();

        for(int i = 0; i<orderStayEntityList.size(); ++i){
            long orderId = orderStayEntityList.get(i).getOrderId();

            if (orderIdSet.contains(orderId)){
                continue;
            }
            else{
                orderIdSet.add(orderId);
            }

            // 根据orderId找到对应的评论实体
            CustomerCommentEntity customerCommentEntity =
                    customerCommentRepository.findFirstByOrderId(orderId);

            // 用户无评论
            if (customerCommentEntity == null){
                continue;
            }


            // 根据orderId找customerId
            OrderEntity orderEntity =
                orderRepository.findById(
                        customerCommentEntity.getOrderId())
                        .orElse(null);

            if (orderEntity == null){
                continue;
            }

            CustomerEntity customerEntity =
                    customerRepository.
                            findCustomerEntityByCustomerId(
                                    orderEntity.getCustomerId()
                            );

            // 新增CommentDto
            CommentDto commentDto = CommentMapper.getInstance().
                    toDto(customerEntity, customerCommentEntity);

            commentDtoList.add(commentDto);

            aveRatings += customerCommentEntity.getStayScore();
            sumComment += 1;
        }

        StayCommentInfoDto stayCommentInfoDto = new StayCommentInfoDto();

        if (sumComment == 0){
            stayCommentInfoDto.setRatings(0.0);
            stayCommentInfoDto.setCommentNum(0);
        }
        else{
            stayCommentInfoDto.setRatings(aveRatings / sumComment);
            stayCommentInfoDto.setCommentNum(commentDtoList.size());
        }
        stayCommentInfoDto.setComments(commentDtoList);

        return stayCommentInfoDto;

    }

    /**
     * 获取hostId所拥有房源收到的全部评价
     * @param hostId
     * @return
     */
    @Override
    public int getCommentNumberForHostId(int hostId) {
        List<StayEntity> stayEntityList = stayRepository.findAllByHostId(hostId);
        int sumNum = 0;
        for(int i = 0; i < stayEntityList.size(); ++i){
            sumNum += searchCommentInfoForStayId(stayEntityList.get(i).getStayId())
                    .getCommentNum();
        }
        return sumNum;
    }
}
