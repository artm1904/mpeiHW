package ru.mpei.LR3.Model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ListData {

	//Огромный массив где содержатся ВСЕ-ВСЕ пара тысяч возможных путей
	private final List<NodeData> nodeDataAgree;

	public ListData() {
		nodeDataAgree = new ArrayList<>();
	}

	public void addDataAgree(NodeData nodeData) {
		nodeDataAgree.add(nodeData);
	}


	public List<NodeData> getMinDataAgreeList() {
		int minLength = 0;
		List<NodeData> minDataList = new ArrayList<>();
		for (NodeData nd : nodeDataAgree) {
			if (minDataList.isEmpty() || nd.getTotalLength() < minLength) {
				minDataList.clear();
				minDataList.add(nd);
				minLength = nd.getTotalLength();

			} else if (nd.getTotalLength() == minLength) {
				minDataList.add(nd);
			}
		}
		return minDataList;
	}


	public int getNodeDataAgreeSize() {
		return nodeDataAgree.size();
	}

}
