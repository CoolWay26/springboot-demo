package com.coolway.controller.test;


import com.alibaba.excel.metadata.Sheet;
import com.coolway.controller.common.ResponseResult;
import com.coolway.controller.common.page.PageParam;
import com.coolway.controller.common.quartzjob.DemoScheduledExecutorService;
import com.coolway.controller.common.quartzjob.DemoTimerTask;
import com.coolway.controller.common.utils.ExcelUtils;
import com.coolway.controller.student.StudentVO;
import com.coolway.mapper.student.StudentDO;
import com.coolway.service.student.IStudentService;
import com.coolway.service.thread.AsyncTaskTestService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
import java.util.function.*;

import java.util.stream.Stream;

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

    @PostMapping("/jdk8TestSupplier")
    @ApiOperation(value = "jdk8TestSupplier")
    public ResponseResult jdk8TestSupplier() {
        System.out.println(testSupplier(() -> 1 + 2));
        return new ResponseResult().resultFlag(true);
    }

    @PostMapping("/jdk8TestConsumer")
    @ApiOperation(value = "jdk8TestConsumer")
    public ResponseResult jdk8TestConsumer() {
        testConsumer(message -> System.out.println(message.toUpperCase()), "Hello World!");

        testConsumer2(message -> System.out.println(message.toUpperCase()), message -> System.out.println(message.toLowerCase()), "Hello World!");
        return new ResponseResult().resultFlag(true);
    }

    @PostMapping("/jdk8TestFunction")
    @ApiOperation(value = "jdk8TestFunction")
    public ResponseResult jdk8TestFunction() {
        Integer integer = testFunction((msg) -> msg.length(), (intValue -> intValue * 2), "function");
        System.out.println(integer);
        return new ResponseResult().resultFlag(true);
    }

    @PostMapping("/jdk8TestPredicate")
    @ApiOperation(value = "jdk8TestPredicate")
    public ResponseResult jdk8TestPredicate() {
        testPredicate((msg) -> msg.startsWith("pre"),
                (msg) -> msg.endsWith("hh"),
                "predicate");
        return new ResponseResult().resultFlag(true);
    }

    @PostMapping("/jdk8TestMethods")
    @ApiOperation(value = "方法引用测试")
    public ResponseResult jdk8TestMethods() {
        //类名::普通方法
        Function<String, Integer> function = String::length;
        //类名::静态方法
        Function<Integer, String> function1 = String::valueOf;
        //类名::new
        BiFunction<String, Integer, StudentVO> function2 = StudentVO::new;
        //[]::new
        Function<Integer, String[]> function3 = String[]::new;

        System.out.println(function.apply("function"));
        System.out.println(function1.apply(211).substring(0, 1));
        System.out.println(function2.apply("张三", 25));
        System.out.println(function3.apply(3).length);


        return new ResponseResult().resultFlag(true);
    }

    @PostMapping("/testStreamCreate")
    @ApiOperation(value = "获取Stream流")
    public ResponseResult testStreamCreate() {
        ArrayList<String> arrayList = new ArrayList<>();
        Stream<String> streamList = arrayList.stream();

        Map<String, String> map = new HashMap<>();
        Stream<String> stream1 = map.keySet().stream();
        Stream<String> stream2 = map.values().stream();
        Stream<Map.Entry<String, String>> stream3 = map.entrySet().stream();

        String[] arr = new String[10];
        Stream<String> streamArr = Stream.of(arr);
        Stream<String> streamArr2 = Arrays.stream(arr);

        return new ResponseResult().resultFlag(true);
    }

    @PostMapping("testStreamAPI")
    @ApiOperation(value = "测试StreamAPI")
    public ResponseResult testStreamAPI() {
        String[] arr1 = {"1", "1", "3", "4"};
        String[] arr2 = {"1", "1", "3", "4"};
        ArrayList<StudentVO> studentList = new ArrayList<>();
        studentList.add(new StudentVO("zhangsan", 23));
        studentList.add(new StudentVO("lisi", 22));
        studentList.add(new StudentVO("wangwu", 21));
        studentList.add(new StudentVO("zhaoliu", 24));

        Stream<String> stream = Stream.of(arr1);
        Stream<String> stream1 = Stream.of(arr1);
        Stream<String> stream2 = Stream.of(arr2);
        Stream<StudentVO> studentSteam = studentList.stream();


        //forEach
//        stream.forEach(System.out::println);

        //count
//        long streamCount = stream.count();
//        System.out.println(streamCount);

        //filter
//        stream.filter(str -> str.length() > 2).forEach(System.out::println);

        //limit
//        stream.limit(3).forEach(System.out::println);

        //skip
//        stream.skip(3).forEach(System.out::println);

        //map
//        stream.map(String::toUpperCase).forEach(System.out::println);

        //sort
//        stream.sorted().forEach(System.out::println);
//        stream.map(Integer::parseInt).sorted((inte1, inte2) -> inte2 - inte1).forEach(System.out::println);

        //distinct
//        stream.distinct().forEach(System.out::println);

        //match
//        Boolean b1 = stream.allMatch(str -> str.length() == 1);
//        System.out.println(b1);
//        Boolean b2 = stream.anyMatch(str -> str.length() == 1);
//        System.out.println(b2);
//        Boolean b3 = stream.noneMatch(str -> str.length() == 1);
//        System.out.println(b3);

        //find
//        Optional<String> optional1 = stream.findFirst();
//        System.out.println(optional1.get());
//        Optional<String> optional2 = stream.findAny();
//        System.out.println(optional2.get());

        //min,max
//        Optional<Integer> optional1 = stream.map(Integer::parseInt).min(Comparator.comparingInt(i -> i));
//        Optional<Integer> optional2 = stream.map(Integer::parseInt).max(Comparator.comparingInt(i -> i));

        //reduce
//        Integer integer = stream.map(Integer::parseInt).reduce(0, Integer::sum);
//        Integer integer = stream.map(Integer::parseInt).reduce(0, Integer::max);
//        Integer integer = studentSteam.map(StudentVO::getAge).reduce(0, Integer::max);
//        System.out.println(integer);

        //mapToInt
//        IntStream intStream = stream.map(Integer::parseInt).mapToInt(Integer::intValue);
//        intStream.forEach(System.out::println);

        //concat
//        Stream<String> newStream = Stream.concat(stream1, stream2);
//        newStream.forEach(System.out::println);

        //用集合接收流中数据
//        List<String> list = stream.collect(Collectors.toList());
//        Set<String> set = stream.collect(Collectors.toSet());
//        ArrayList<String> list1 = stream.collect(Collectors.toCollection(ArrayList::new));
//        HashSet<String> set1 = stream.collect(Collectors.toCollection(HashSet::new));
//
//        String[] strArr = stream1.toArray(String[]::new);

        //聚合
//        Optional<StudentVO> maxAgeStudent = studentSteam.collect(Collectors.maxBy(Comparator.comparingInt(StudentVO::getAge)));
//        Integer sumAge = studentSteam.collect(Collectors.summingInt(StudentVO::getAge));
//
//        Integer[] integers = {1, 2, 3, 4};
//        Integer sumVal = Stream.of(integers).max(Comparator.comparingInt(i -> i)).get();

        //分组
//        Map<Integer, Map<String, List<StudentVO>>> studentMap = studentSteam.collect(Collectors.groupingBy(
//                StudentVO::getAge,
//                Collectors.groupingBy(StudentVO::getName)
//        ));
//
//        studentMap.forEach((k, v) -> {
//            System.out.println(k);
//            v.forEach((k1, v1) -> {
//                System.out.println("\t" + k1);
//                System.out.println("\t\t" + v1);
//            });
//        });

        //分区
//        Map<Boolean, List<StudentVO>> studentMap = studentSteam.collect(Collectors.partitioningBy((student) -> student.getAge() > 22));
//        studentMap.forEach((k, v) -> {
//            System.out.print(k + ":");
//            System.out.println(v);
//        });
        //拼接
//        String res = stream.collect(Collectors.joining());
//        System.out.println(res);
//        String res1 = stream.collect(Collectors.joining(","));
//        System.out.println(res1);
//        String res2 = stream.collect(Collectors.joining(",", "前缀", "后缀"));
//        System.out.println(res2);

        //并行流
//        Stream<StudentVO> streamParallel = studentList.parallelStream();
//        Stream<String> streamArr = Stream.of(arr1).parallel();
        return new ResponseResult().resultFlag(true);
    }

    @PostMapping("testOptional")
    @ApiOperation(value = "测试testOptional")
    public ResponseResult testOptional() throws Exception {
        Optional<String> optional1 = Optional.of("name");
//        Optional<String> optional2 = Optional.ofNullable(null);
//        Optional<Object> optional3 = Optional.empty();

//        System.out.println(optional1.isPresent() ? optional1.get() : "000");
//        System.out.println(optional1.orElse("111"));
//        System.out.println(optional1.orElseGet(() -> {
//            return "222";
//        }));
//        System.out.println(optional1.orElseThrow(Exception::new));

        optional1.ifPresent(System.out::println);
        return new ResponseResult().resultFlag(true);
    }

    private void testPredicate(Predicate<String> predicate1, Predicate<String> predicate2, String str) {
        //and
        Boolean boolean1 = predicate1.and(predicate2).test(str);
        //or
        Boolean boolean2 = predicate1.or(predicate2).test(str);
        //negate
        Boolean boolean3 = predicate1.negate().test(str);
        System.out.println(boolean1);
        System.out.println(boolean2);
        System.out.println(boolean3);
    }

    private Integer testFunction(Function<String, Integer> function1, Function<Integer, Integer> function2, String str) {
        return function1.andThen(function2).apply(str);
    }

    private Integer testSupplier(Supplier<Integer> supplier) {
        return supplier.get();
    }

    private void testConsumer(Consumer<String> consumer, String str) {
        consumer.accept(str);
    }

    private void testConsumer2(Consumer<String> consumer1, Consumer<String> consumer2, String str) {
        consumer1.andThen(consumer2).accept(str);
    }

}
