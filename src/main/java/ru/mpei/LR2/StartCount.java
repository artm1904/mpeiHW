package ru.mpei.LR2;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

//Данное поведение работает один раз для первого агента, который должен вычислить начальную точку и дельту

/**
 * Первый агент производит выбор начальной точки (Х) случайным
 * образом в некотором диапазоне, а также задает первоначальное
 * значение параметра delta – сдвиг, который служит для
 * определения возрастания суммарного значения функций всех
 * агентов. Первый агент становится агентом-инициатором.
 */
public class StartCount extends OneShotBehaviour {

	private String name;

	public StartCount(String name) {
		this.name = name;
	}

	@Override
	public void action() {

		/**
		 * Random Float With Plain Java
		 * And a bounded random float
		 */
		float leftLimit = 1;
		float rightLimit = 100;
		float generatedInitX = leftLimit + (float) (Math.random() * (rightLimit - leftLimit));
		float delta = 10;
		System.out.println(name + " agent запустил процесс расчета общего max: init X=" + generatedInitX + " ,delta = " + delta);


		//Чтобы добавить начальные данные в payload msg нужно их преобразовать в String
		//Будем использовать строку в качестве DTO
		String initData = generatedInitX + ";" + delta;

		/**
		 * Агент инииатор отправляет сообщение всем известным агентам
		 * для выполнения расчета графика функций каждого из агентов для
		 * трех точек:
		 *  X – delta
		 *  X
		 *  X + delta
		 */
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		AID receiver = new AID(name, false);
		msg.addReceiver(receiver);
		msg.setContent(initData);
		myAgent.send(msg);

	}
}
