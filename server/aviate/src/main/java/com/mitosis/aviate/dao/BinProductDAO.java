package com.mitosis.aviate.dao;

import java.util.List;

import org.codehaus.jettison.json.JSONObject;

import com.mitosis.aviate.model.BinProductModel;

public interface BinProductDAO {

	public List<BinProductModel> getBinProducts(Long storeId, Long productId);
	public List<BinProductModel> getBinProducts(Long storeId);
	public List<BinProductModel> getBinProducts(JSONObject merchantId);
	
}
