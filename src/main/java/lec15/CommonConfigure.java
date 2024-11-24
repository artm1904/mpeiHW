package lec15;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlRootElement(name = "cfg")
@XmlAccessorType(XmlAccessType.FIELD)
public class CommonConfigure {
	@XmlElementWrapper(name = "students")
	@XmlElement(name = "student")
	private List<Student> students;
}
