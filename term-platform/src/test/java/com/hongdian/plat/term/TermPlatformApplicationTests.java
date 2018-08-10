package com.hongdian.plat.term;

import com.baomidou.mybatisplus.plugins.Page;
import com.hongdian.plat.term.dto.StationDto;
import com.hongdian.plat.term.exec.devenum.OnLineEnum;
import com.hongdian.plat.term.service.IBizStatBasicService;
import com.hongdian.plat.term.service.ITermHeaderService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TermPlatformApplicationTests {

    @Resource
    IBizStatBasicService stationService;

    @Resource
    ITermHeaderService termHeaderService;

    @Test
    public void testEnvironment() {
        System.out.println("testEnvironment is ok");
    }

    /* stationService test  */
    @Test
    public void stationServiceTest(){
        Map<String, Object> map = new HashMap<>();
        map.put("onlineState", OnLineEnum.OFF_LINE.getStatus());
        map.put("lastLogoutTime", new Date());
        termHeaderService.updateAllTermOffline(map);
    }
}
