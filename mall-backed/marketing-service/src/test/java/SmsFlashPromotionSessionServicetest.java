import com.mole.marketing.MarketingServiceApplication;
import com.mole.common.dto.marketing.SmsFlashPromotionSessionDetail;
import com.mole.common.entity.marketing.SmsFlashPromotionSession;
import com.mole.marketing.mapper.SmsFlashPromotionSessionMapper;
import com.mole.marketing.service.SmsFlashPromotionSessionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {MarketingServiceApplication.class})
public class SmsFlashPromotionSessionServicetest {

    @Autowired
    private SmsFlashPromotionSessionService promotionSessionService;

    @MockBean
    private SmsFlashPromotionSessionMapper smsFlashPromotionSessionMapper;

    @BeforeEach
    public void setUp() {
        // 准备测试数据
        SmsFlashPromotionSession session1 = new SmsFlashPromotionSession();
        session1.setId(1L);
        session1.setName("Session 1");

        SmsFlashPromotionSession session2 = new SmsFlashPromotionSession();
        session2.setId(4L);
        session2.setName("Session 4");

        when(smsFlashPromotionSessionMapper.getSmsFlashPromotionSessionDetailsByPromotionId(1L))
                .thenReturn(Arrays.asList(session1, session2));
    }

    @Test
    public void testSelectList() {
        // 调用服务层方法
        List<SmsFlashPromotionSessionDetail> result = promotionSessionService.selectList(1L);

        // 验证结果大小
        assertEquals(2, result.size());

        // 简单验证转换逻辑，具体验证内容根据实际转换逻辑调整
        assertEquals("Session 1", result.get(0).getName());
        assertEquals("Session 4", result.get(1).getName());
    }
}
