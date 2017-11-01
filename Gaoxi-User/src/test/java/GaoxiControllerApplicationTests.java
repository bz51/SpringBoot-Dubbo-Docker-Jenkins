import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.facade.user.UserService;
import com.gaoxi.req.user.UserQueryReq;
import com.gaoxi.user.dao.UserDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GaoxiControllerApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	public void contextLoads() {
		List<UserEntity> userEntityList = userService.findUsers(new UserQueryReq());
		System.out.println(userEntityList);
	}

}
