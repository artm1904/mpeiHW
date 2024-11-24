package lr3Graph;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "root")
@Data
public class SerialConfig {


	@XmlElement
	private int Initiator;

	@XmlElement
	private String Find;

	@XmlElement()
	private List<String> Neighbours;

	@XmlElement()
	private List<Integer> pathWeight;


}
