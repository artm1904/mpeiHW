package lec15;

import lombok.SneakyThrows;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Main {

	@SneakyThrows
	public static void main(String[] args) {

		File f = new File("src/main/resources/tt.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(CommonConfigure.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		CommonConfigure cfg = (CommonConfigure) unmarshaller.unmarshal(f);
		System.out.println(cfg);
	}
}
