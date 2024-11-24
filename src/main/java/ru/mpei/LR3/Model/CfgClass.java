package ru.mpei.LR3.Model;

import lombok.Getter;

import javax.xml.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;



// Как сформировать XML https://howtodoinjava.com/jaxb/jaxb-example-marshalling-and-unmarshalling-hashmap-in-java/

/**
 * <cfg>
 *     <neighbours>
 *         <neighbour key="N2" value="15"/>
 *         <neighbour key="N3" value="26"/>
 *         <neighbour key="N4" value="150"/>
 *         <neighbour key="N5" value="25"/>
 *     </neighbours>
 *     <start>true</start>
 *     <findNodeName>N12</findNodeName>
 * </cfg>
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "cfg")
@Getter
public class CfgClass {
    @XmlElement
    private boolean start = false;

    @XmlElement
    private String findNodeName;

	@XmlElementWrapper(name = "neighbours")
    private Map<String, Integer> neighbours = new HashMap<>();



}
