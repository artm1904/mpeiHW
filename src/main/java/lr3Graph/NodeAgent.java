package lr3Graph;
import jade.core.Agent;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class NodeAgent extends Agent {


	@Override
	protected void setup() {

//		System.out.println(this.getLocalName());

		SerialConfig serialConfig = null;
		try {
			JAXBContext context = JAXBContext.newInstance(SerialConfig.class);
			Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
			serialConfig = (SerialConfig) jaxbUnmarshaller.unmarshal(new File("src/main/resources/graph/" + this.getLocalName()));

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		this.addBehaviour(new AgentFSM(this, serialConfig));

	}
}