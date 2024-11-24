package ru.mpei.LR3.Agents;

import jade.core.Agent;
import ru.mpei.LR3.Behaviour.InitBehaviour;
import ru.mpei.LR3.Behaviour.RequestBehaviour;
import ru.mpei.LR3.Model.CfgClass;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;


public class NodeAgent extends Agent {

	@Override
	protected void setup() {

		//Процесс по deserialization (из XML получить объект класса CfgClass) нода, заданного в cfg
		CfgClass cfg;
		try {
			JAXBContext context = JAXBContext.newInstance(CfgClass.class);
			Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
			cfg = (CfgClass) jaxbUnmarshaller.unmarshal(
					new File("C:\\ForMe\\Proger\\Java\\jProgram\\mpeiJavaHW\\src\\main\\resources\\Node\\"
							+ getLocalName() + ".xml"));

		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}

		//Если в конфиге указано что это стартовый узел, то для него поведение ИНИЦИАЛИЗАЦИИ
		if (cfg.isStart()) {

			addBehaviour(new InitBehaviour(cfg.getNeighbours(), cfg.getFindNodeName()));
		}

		//Узел принимает сообщения и передает их дальше своим соседям, в конструкторе передаем саму Map с соседями
		addBehaviour(new RequestBehaviour(cfg.getNeighbours()));

	}

}
