package ru.mpei.LR2;

import jade.core.Agent;

public class FunctionAgent extends Agent {


    @Override
    protected void setup(){

		//Первый агант является особым - он инициатор и у его есть доп-ое поведение (однократное)
        if(this.getLocalName().equals("first")){
            String name = this.getLocalName();

			//Для первого агента (инициатора) нужно задать однократное поведение - инициацию расчетов
			//Внутри будет логика отправки сообщения, получаетем будет этот же агент
            this.addBehaviour(new StartCount(name));
			this.addBehaviour(new SendMsgAll());

			//Агент-инициатор определяет экстремум суммарного значения
			//функций на основе полученных ответов
			this.addBehaviour(new RecieveMsgInit());
        }
		/**
		 * Данное поведение отвечает за принятие запроса от другого агентов на расчет значения
		 * функции, заданной агенту;
		 */
        this.addBehaviour(new ReceiveMsg());
    }
}
