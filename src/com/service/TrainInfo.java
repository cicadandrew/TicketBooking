package com.service;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TrainInfo {

	private String trainID = null;
	private List<TimeInfo> tables = new ArrayList<TimeInfo>();
	private String        trainType = null;

	public TrainInfo(Node item) {

		Element eElement = (Element) item;
		trainID = eElement.getAttribute("CarClass");
		NodeList timeinfos = item.getChildNodes();

		for (int temp = 0; temp < timeinfos.getLength(); temp++) {

			Node nNode = timeinfos.item(temp);

			tables.add(new TimeInfo(nNode));

		}
		
		
	}
	
	public String getTrainID() {
		return trainID;
	}
	

	public List<TimeInfo> getTables() {
		return this.tables;
	}

	public static class TimeInfo {

		private static String arrtime = null;
		private static String deptime = null;
		private int order = 0;
		private String stationID = null;

		public TimeInfo(Node item) {
			Element eElement = (Element) item;
			this.arrtime = eElement.getAttribute("ARRTime");
			this.deptime = eElement.getAttribute("DEPTime");
			this.order = Integer.parseInt(eElement.getAttribute("Order"));
			this.stationID = eElement.getAttribute("Station");
		}

		// TODO Any method you'd like to

		public static String getArrtime() {
			return arrtime;
		}

		public static String getDeptime() {
			return deptime;
		}

		public String getStationID() {
			return stationID;
		}

	}
}
