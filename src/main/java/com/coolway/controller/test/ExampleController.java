package com.coolway.controller.test;


import com.alibaba.excel.metadata.Sheet;
import com.coolway.controller.common.ResponseResult;
import com.coolway.controller.common.page.PageParam;
import com.coolway.controller.common.page.PageService;
import com.coolway.controller.common.quartzjob.DemoScheduledExecutorService;
import com.coolway.controller.common.quartzjob.DemoTimerTask;
import com.coolway.controller.common.utils.ExcelUtils;
import com.coolway.mapper.student.StudentDO;
import com.coolway.service.student.IStudentService;
import com.coolway.service.thread.AsyncTaskTestService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/example")
@Slf4j
public class ExampleController {

    @Autowired
    private AsyncTaskTestService asyncTaskTestService;
    @Autowired
    private IStudentService studentService;

//    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @GetMapping("/freemakerExample")
    @ApiOperation(value = "freemaker测试", httpMethod = "GET", notes = "跳转到example.ftl")
    public String getFtlExample(Map<String, Object> map) {
        map.put("name", "zhangsan");
        return "exampleController";
    }

    @GetMapping("/timer")
    public void timer() {
        TimerTask timerTask = new DemoTimerTask();
        Timer timer = new Timer();
        //指定任务开始延迟时间和执行频率，单位毫秒
        timer.schedule(timerTask, 5000, 10000);
    }

    @GetMapping("/scheduledExecutorService")
    public void scheduledExecutorService() {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new DemoScheduledExecutorService(), 5, 10, TimeUnit.SECONDS);
    }

    @GetMapping("/log4j2Test")
    @ApiOperation(value = "log4j2测试")
    public void log4j2Test() {
//        logger.trace("trace111");
//        logger.debug("debug111");
//        logger.info("info111");
//        logger.warn("warn111");
//        logger.error("error111");
//        logger.fatal("fatal111");
        log.info("info222");
    }

    /**
     * Excel文件导入
     */
    @PostMapping("/importExcel")
    @ApiOperation(value = "Excel文件导入")
    public void importExcel(HttpServletRequest request, MultipartFile file, HttpServletResponse response) throws Exception {
        List<Object> objects = ExcelUtils.readLessThan1000RowBySheet(file.getInputStream(), new Sheet(1, 1));
        List list = new ArrayList<>();
        for (Object o : objects) {
            List<String> strList = (List<String>) o;
            strList.get(0);
            strList.get(1);
            strList.get(2);
            for (String str : strList) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
    }

    @PostMapping("/testLocalDate")
    @ApiOperation(value = "LocalDate,LocalTime,LocalDateTime")
    public void testLocalDate() {
        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = LocalDate.of(2020, 12, 29);
        localDate.getYear();
        localDate.getMonthValue();
        localDate.getDayOfWeek().getValue();
        localDate.getDayOfMonth();
        localDate.getDayOfYear();
        localDate.isLeapYear();
        localDate.plusDays(1);
        localDate.plusDays(-1);
        localDate.minusDays(1);
        localDate.lengthOfMonth();
        localDate.lengthOfYear();
        localDate.compareTo(localDate1);
        localDate.isEqual(localDate1);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateStr = localDate.format(df);
        LocalDate localDate2 = LocalDate.parse(dateStr, df);
        System.out.println();

        LocalTime localTime = LocalTime.now();
        LocalTime localTime1 = LocalTime.of(20, 48, 00);
        localTime.getHour();
        localTime.getMinute();
        localTime.getSecond();
        localTime.compareTo(localTime1);
    }

    @PostMapping("/asyncTest")
    @ApiOperation(value = "多线程测试")
    public void asyncTest() {
        for (int i = 0; i < 5; i++) {
            asyncTaskTestService.asyncTaskTest();
        }
    }

    /**
     * http测试
     */
    @PostMapping("/crosTest")
    @ApiModelProperty(value = "http请求测试")
    public void crosTest() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://192.168.31.206:8081/institutionCheck/doneForAbolish";
        String response = restTemplate.postForObject(url, null, String.class);
        System.out.println(response);
    }

    @GetMapping("/pageTest/page/{page}/size/{size}")
    @ApiOperation(value = "分页查询测试")
    public ResponseResult pageTest(PageParam<StudentDO> pageParam) {
        return new ResponseResult().resultFlag(true).data(studentService.page(pageParam));
    }

}
