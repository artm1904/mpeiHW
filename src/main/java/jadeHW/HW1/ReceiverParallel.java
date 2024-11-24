package jadeHW.HW1;

import jade.core.behaviours.ParallelBehaviour;

public class ReceiverParallel extends ParallelBehaviour {

	public ReceiverParallel() {


		this.addSubBehaviour(new ReceiveSpam());
		this.addSubBehaviour(new ReceiveNotSpam());

	}
}
