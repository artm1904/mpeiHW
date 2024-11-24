package ru.mpei.LR3.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
public class NodeData {

	//Содержит перечисление всех узлов, которые были посещены
	private List<String> nodeNames = new ArrayList<>();
	private int totalLength = 0;
	private String findNodeName;

	public NodeData(String firstNodeName, String findNodeName) {
		this.nodeNames.add(firstNodeName);
		this.findNodeName = findNodeName;
	}


	//Нужно для того, чтобы иметь возможность отправить сообщение
	// СТАРТОВОМУ узлу после достижения максимальной глубины
	public String firstNodeName() {
		return nodeNames.get(0);
	}

	//Добавляем узел в путь и суммируем вес до узлов
	public void addData(String nodeName, int pathLength) {
		this.nodeNames.add(nodeName);
		totalLength = totalLength + pathLength;
	}

	//deserialization String (json) to clas
	public static NodeData parseData(String dataString) {
		return JsonParser.parseData(dataString, NodeData.class);
	}

	//serialization clas obj to String
	public static String dataToString(NodeData nodeData) {
		return JsonParser.dataToString(nodeData);
	}


}
