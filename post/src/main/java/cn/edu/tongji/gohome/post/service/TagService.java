package cn.edu.tongji.gohome.post.service;


import cn.edu.tongji.gohome.post.model.PostTagEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface TagService {
    HashMap<String, Object> searchDefaultTagList(int currentPage, int pageSize);

    HashMap<String, Object> searchPostListForTag(String tag, int currentPage, int pageSize);

    List<String> searchTagListForPostId(long postId);
}
