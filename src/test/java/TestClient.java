import com.dao.ClientDao;
import com.entity.Client;
import org.junit.Assert;
import com.service.ClientService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestClient {
	@Autowired
	ClientDao clientDao;
	@Autowired
	ClientService clientService;
	@Test
	@Ignore
	public void testAddClientByDao(){

		Client client = new Client();
		client.setName("hello");
		client.setPassword("123456");
		client.setClientId(-100);
		boolean result = clientDao.addClient(client);
		System.out.println(client.getClientId());
		Assert.assertTrue(result);
	}
//	@Test
//	public void testAddClientByService(){
//		Assert.assertTrue(clientService.loginRegister("hello","123456"));
//	}
}
