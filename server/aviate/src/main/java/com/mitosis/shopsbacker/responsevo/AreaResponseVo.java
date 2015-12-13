/**
 * 
 */
package com.mitosis.shopsbacker.responsevo;

import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.common.AreaVo;

/**
 * @author Anbukkani Gajendiran
 *
 */
public class AreaResponseVo extends ResponseModel {

	List<AreaVo> areas=new ArrayList<AreaVo>();

	public List<AreaVo> getAreas() {
		return areas;
	}

	public void setAreas(List<AreaVo> areas) {
		this.areas = areas;
	}
	
	
}
