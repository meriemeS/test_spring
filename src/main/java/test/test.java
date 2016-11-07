package test;

import org.gestion.bp.entities.Client;
import org.gestion.bp.service.IbanqueService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		IbanqueService service = (IbanqueService) context.getBean("service");
		service.addclient(new Client("C1","AD1"));
		service.addclient(new Client("C2","AD2"));


	}

}
