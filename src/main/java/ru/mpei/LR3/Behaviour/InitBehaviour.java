package ru.mpei.LR3.Behaviour;

import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.WakerBehaviour;
import ru.mpei.LR3.Model.ListData;
import ru.mpei.LR3.Model.NodeData;


import java.util.List;
import java.util.Map;

/**
 * На вход получает СОСЕДЕЙ и ЦЕЛЬ поиска
 */
public class InitBehaviour extends ParallelBehaviour {

	//Объект класса, где есть массив всех путей и методы по работе с ним
	private final ListData data = new ListData();
	private final Map<String, Integer> neighbours;
	private final String findNodeName;

	public InitBehaviour(Map<String, Integer> neighbours, String findNode) {
		//Прекращение логики параллельного поведения, когда любое поведение
		//завершается - в данной случае WakerBehaviour
		super(ParallelBehaviour.WHEN_ANY);
		this.neighbours = neighbours;
		this.findNodeName = findNode;
	}

	//Добавили СТАРТОВОУ узлу поведение по отправке СОСЕДЯМ сообщений, ждем 2000мс пока не прекратим поиск
	@Override
	public void onStart() {
		addSubBehaviour(new InitRequestBehaviour(data, neighbours, findNodeName));
		addSubBehaviour(new WakerBehaviour(myAgent, 2000) {
			@Override
			protected void onWake() {
			}
		});
	}


	@Override
	public int onEnd() {
		System.out.println();
		System.out.println();
		System.out.println("-----------------------------------------------------");

		List<NodeData> minDataList = data.getMinDataAgreeList();

		for (NodeData minData : minDataList) {
			System.out.println("Минимальный путь, найденный за отведённое время: "
					+ minData.getNodeNames().toString() + ", его длина = " + minData.getTotalLength());
		}
		System.out.println("Всего алгоритм нашел возможный путей  " + data.getNodeDataAgreeSize());
		return 1;
	}
}
