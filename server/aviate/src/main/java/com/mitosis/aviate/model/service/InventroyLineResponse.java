package com.mitosis.aviate.model.service;

import java.util.List;

import com.mitosis.aviate.model.InventoryLineModel;

public class InventroyLineResponse extends ResponseModel{
	public List<InventoryLineModel> listOfInventroyLine;

	public List<InventoryLineModel> getListOfInventroyLine() {
		return listOfInventroyLine;
	}

	public void setListOfInventroyLine(List<InventoryLineModel> listOfInventroyLine) {
		this.listOfInventroyLine = listOfInventroyLine;
	}
}
