package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.vo.SubjectNestedVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Ay
 * @since 2022-03-04
 */
public interface EduSubjectService extends IService<EduSubject> {
    /**
     * 添加课程分类
     * @param file
     * @param eduSubjectService
     */
    void saveSubject(MultipartFile file,EduSubjectService eduSubjectService);

    /**
     * 得到整个课程分类列表
     * @return
     */
    List<SubjectNestedVo> nestedList();
}
