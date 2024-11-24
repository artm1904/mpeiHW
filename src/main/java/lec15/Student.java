package lec15;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {
	@XmlElement
	private String name;
	@XmlElement
	private String surname;
	@XmlElement
	private int age;
	@XmlElement
	private GroupCfg count;
}
