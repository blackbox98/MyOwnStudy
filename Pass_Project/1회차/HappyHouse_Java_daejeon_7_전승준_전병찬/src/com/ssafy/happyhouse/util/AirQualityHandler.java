package com.ssafy.happyhouse.util;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.helpers.DefaultHandler;

import com.ssafy.happyhouse.model.dto.HouseDeal;

public class AirQualityHandler extends DefaultHandler {
	/**아파트 거래 정보를 담는다 */
	private List<HouseDeal> houses;
	/**파상힌 아파트 거래 정보*/
	private HouseDeal house;
	/**태그 바디 정보를 임시로 저장*/
	private String temp;
	
	public AirQualityHandler(){
		houses = new LinkedList<HouseDeal>();
	}
	
	
	
	public void characters(char[]ch, int start, int length){
		temp = new String(ch, start, length);
	}
	public List<HouseDeal> getHouses() {
		return houses;
	}
	public void setHouses(List<HouseDeal> houses) {
		this.houses = houses;
	}
}