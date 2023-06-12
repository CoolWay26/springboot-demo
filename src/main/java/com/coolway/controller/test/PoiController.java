package com.coolway.controller.test;

import com.coolway.common.enums.ExcelEnum;
import com.coolway.common.utils.ExcelUtils;
import com.coolway.common.utils.PoiUtils;
import com.coolway.common.utils.PoiUtils2;
import com.coolway.entity.Student;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
public class PoiController {

    @GetMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) throws NoSuchFieldException, IllegalAccessException {
        LinkedList<Student> datas = new LinkedList<>();
        Student student = new Student();
        student.setName("zhangsan");
        student.setAge(15);
        datas.add(student);

        Student student1 = new Student();
        student1.setName("lisi");
        student1.setAge(16);
        datas.add(student1);


        HSSFWorkbook hssfWorkbookWithDatas = PoiUtils.getHSSFWorkbookWithDatas("测试", "学生表",
                ExcelEnum.STUDENT_ENUM.getColChineseName(),
                ExcelEnum.STUDENT_ENUM.getColEnglishName(),
                datas);

        PoiUtils.setResponse4Excel(response, "学生信息.xls", hssfWorkbookWithDatas);

    }

    @PostMapping("/importExcel")
    public void importExcel(MultipartFile file) throws IOException {
        List<Map<String, Object>> res = PoiUtils2.readExcel(file.getInputStream(), file.getOriginalFilename(),2, 0, ExcelEnum.STUDENT_ENUM.getColEnglishName());
        if (CollectionUtils.isNotEmpty(res)) {
            res.stream().forEach(e-> {
                System.out.println(e.toString());
            });
        }
    }
}
