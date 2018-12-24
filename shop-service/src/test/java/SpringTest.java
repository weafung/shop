import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.service.WebsiteConfigService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring-cache.xml", "classpath*:/spring-mybatis.xml", "classpath*:/application-context.xml" })
public class SpringTest {
    @Autowired
    WebsiteConfigService websiteConfigService;

    @Test
    public void getConfigByKey() {
        String key = "service_phone";
        ResponseDTO<String> responseDTO = websiteConfigService.getConfigValue(key);
        Assert.assertNotNull(responseDTO);
    }
}