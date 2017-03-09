package com.ebao.convert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 第一个是core，第二个是DC
 * @author brandon.he
 *
 */
public class Convert {
	private static final Logger logger = LoggerFactory.getLogger(Convert.class);
	
	
	private static JsonObject getJson(String fileName) throws Exception{
		String path = System.getProperty("user.dir");

		BufferedReader br = new BufferedReader(new FileReader(path + File.separator + fileName));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}

		System.out.println("============get json from :" + fileName);
		System.out.println(sb.toString());
		
		JsonObject jsonobj = new JsonParser().parse(sb.toString()).getAsJsonObject();
		return jsonobj;
	}
	
	private static Map<String, List<String>> getMapData(JsonObject jsonObj){
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		analysisJson(jsonObj, map, "Policy");
		Gson gson = new Gson();
		System.out.println("json1:==================");
		System.out.println(gson.toJson(map));
		
		Map<String, List<String>> mapDc = new HashMap<String, List<String>>();
		return map;
	}
	
	public static void main(String[] args) throws Exception {
		

		
		//root ele
//		loop(jsonobj, sheet, "Policy", 0);
		
		Map<String, List<String>> mapCore = getMapData(getJson("DEPG17000070_Request.json"));
		Map<String, List<String>> mapDc = getMapData(getJson("dc.json"));

		Set<String> keyCore = mapCore.keySet();
		Set<String> keyDc = mapDc.keySet();
		
		List<String> keyLst = new ArrayList<String>();
		keyLst.addAll(keyCore);
		keyLst.addAll(keyDc);
		
		//<"Policy", <"POI", {"POI", Y, Y}>>
		Map<String, Map<String, KeyResult>> result = new HashMap<String, Map<String, KeyResult>>();
		
		for(Entry<String, List<String>> entry : mapCore.entrySet()){
			Map<String, KeyResult> rsMap =  new HashMap<String, KeyResult>();
			
			String parentName = entry.getKey();
			List<String> childNames = entry.getValue();
			for(String name : childNames){
				KeyResult ks = new KeyResult(name,true, false);
				rsMap.put(name, ks);
			}
			result.put(parentName, rsMap);
		}
		
		for(Entry<String, List<String>> entry : mapDc.entrySet()){
			
			String parentName = entry.getKey();
			List<String> childNames = entry.getValue();
			
			if(result.containsKey(parentName)){
				
				Map<String, KeyResult> rsMapOld = result.get(parentName);
				for(String name : childNames){
					if(rsMapOld.containsKey(name)){
						rsMapOld.get(name).setDc(true);
					}
				}
				
			}else{
				Map<String, KeyResult> rsMapNew =  new HashMap<String, KeyResult>();
				for(String name : childNames){
					KeyResult ks = new KeyResult(name,false, true);
					rsMapNew.put(name, ks);
				}
				result.put(parentName, rsMapNew);
			}
		}
		
		
		System.out.println("==========result==========");
//		System.out.println(result);
//		printResult(result);
		System.out.println("==========end==========");
		
		export(result);
		
		
    	logger.info("execute end");
	}
	
	private static void export(Map<String, Map<String, KeyResult>> result){
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("analysis sheet");
		
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("Node Name");;
		row.createCell(1).setCellValue("Key Name");;
		row.createCell(2).setCellValue("isCore");;
		row.createCell(3).setCellValue("isDc");;
		
		int rowIdx = 1;
		//export data
		for(Entry<String, Map<String, KeyResult>> entry : result.entrySet()){
			String nodeName = entry.getKey();
			System.out.println("root name : " + nodeName);
			Row rowForNode = sheet.createRow(rowIdx);
			rowIdx++;
			rowForNode.createCell(0).setCellValue(nodeName);
			
			Map<String, KeyResult> rsMap = entry.getValue();
			for(KeyResult kr: rsMap.values()){
				Row rowForKey = sheet.createRow(rowIdx);
				rowIdx++;
				rowForKey.createCell(1).setCellValue(kr.getKey());;
				rowForKey.createCell(2).setCellValue(kr.isCore());
				rowForKey.createCell(3).setCellValue(kr.isDc());
				logger.info("{},\t\t {},\t {}", kr.getKey(), kr.isCore(), kr.isDc());
			}
		}
		
		
		String path = System.getProperty("user.dir");
		File convertFile = new File(path + File.separator + System.currentTimeMillis() + ".xlsx");
		
    	FileOutputStream fileOut = null;
    	try {
    		fileOut = new FileOutputStream(convertFile);
    		wb.write(fileOut);
    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
    	} catch (IOException e) {
    		e.printStackTrace();
    	} finally{
    		try {
    			fileOut.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
	}
	
	
	private static void printResult(Map<String, Map<String, KeyResult>> result){
		for(Entry<String, Map<String, KeyResult>> entry : result.entrySet()){
			System.out.println("root name : " + entry.getKey());
			Map<String, KeyResult> rsMap = entry.getValue();
			for(KeyResult kr: rsMap.values()){
				logger.info("{},\t\t {},\t {}", kr.getKey(), kr.isCore(), kr.isDc());
			}
		}
	}
	
	private static void analysisJson(JsonObject jsonobj, Map<String, List<String>> mapCore, String parentKey){
		Map<String, JsonObject> objMap =  new HashMap<String, JsonObject>();
		for(Map.Entry<String,JsonElement> entry: jsonobj.entrySet()){
			
			String key = entry.getKey();
			JsonElement value = entry.getValue();					
			
			if(value.isJsonObject()){
				//直接进行递归, 增加一列
				objMap.put(parentKey+"."+key, (JsonObject)value);
			}else if(value.isJsonArray()){
				//默认选择第一个进行递归， 增加一列
				objMap.put(parentKey+"."+key, value.getAsJsonArray().get(0).getAsJsonObject());
			}else{
				//直接写入excel当前列，不改变列
				logger.info("{}, {}, {}, {}", parentKey, itemNum, key);
				handleMap(mapCore, parentKey, key);
			}
		}
		
		//handle other list
		for(Entry<String, JsonObject> entry : objMap.entrySet()){
			itemNum++;
			logger.info("hanling the tag :{}", entry.getKey());
			analysisJson(entry.getValue(), mapCore, entry.getKey());
		}
	}
	
	private static void handleMap(Map<String, List<String>> mapCore, String key, String lstValue){
		if(mapCore.containsKey(key)){
			List lst = mapCore.get(key);
			lst.add(lstValue);
		}else{
			List lst = new ArrayList<String>();
			lst.add(lstValue);
			mapCore.put(key, lst);
		}
	}
	
	
	
	//第几列，当前列的外部索引
	private static int itemNum = 0;
	
	/**
	 * 
	 * @param jsonobj
	 * @param currentIdx 当前列内部的索引
	 * @param sheet
	 * @param parentKey 父节点名称
	 */
	private static void loop(JsonObject jsonobj, Sheet sheet, String parentKey, int currentIdx){
		
		Map<String, JsonObject> objMap =  new HashMap<String, JsonObject>();
		
		for(Map.Entry<String,JsonElement> entry: jsonobj.entrySet()){
			
			String key = entry.getKey();
			JsonElement value = entry.getValue();					
			
			if(value.isJsonObject()){
				//直接进行递归, 增加一列
				//itemNum++;
				objMap.put(parentKey+"."+key, (JsonObject)value);
			}else if(value.isJsonArray()){
				//默认选择第一个进行递归， 增加一列
				//itemNum++;
				objMap.put(parentKey+"."+key, value.getAsJsonArray().get(0).getAsJsonObject());
			}else{
				//直接写入excel当前列，不改变列
				if(currentIdx == 0){
					Row row = getOrCreateRow(sheet, currentIdx);
					getOrCreateCell(row, itemNum).setCellValue(parentKey);
					currentIdx++;
				}
				Row row = getOrCreateRow(sheet, currentIdx);
				getOrCreateCell(row, itemNum).setCellValue(key);
				
				logger.info("{}, {}, {}, {}", parentKey, itemNum, currentIdx, key);
				
				currentIdx++;
			}
		}
		
		//handle other list
		for(Entry<String, JsonObject> entry : objMap.entrySet()){
			itemNum++;
			logger.info("hanling the tag :{}", entry.getKey());
			loop(entry.getValue(), sheet, entry.getKey(), 0 );
		}
	}
	
	
	
	private static Row getOrCreateRow(Sheet sheet, int idx){
		return sheet.getRow(idx)!= null?sheet.getRow(idx):sheet.createRow(idx);
	}
	
	private static Cell getOrCreateCell(Row row, int idx){
		return row.getCell(idx)!= null?row.getCell(idx):row.createCell(idx);
	}
	
	public static void generateRootEleType() throws Exception{
		String path = System.getProperty("user.dir");

		BufferedReader br = new BufferedReader(new FileReader(path + File.separator + "source.json"));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}

		System.out.println(sb.toString());
		
		JsonObject jsonobj = new JsonParser().parse(sb.toString()).getAsJsonObject();
		System.out.println(jsonobj.get("GrossPremiumDifference"));
		
		
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("covert sheet");
		
		//root ele
		Row row = sheet.createRow((short)0);
		Cell c00 = row.createCell(0);
		c00.setCellValue("Policy");
		
		//row 1 and 2 for source and target tag
		row = sheet.createRow((short)1);
		Cell c10 = row.createCell(0);
		c10.setCellValue("key");
		
		Cell c11 = row.createCell(1);
		c11.setCellValue("isJsonArray");
		
		c11 = row.createCell(2);
		c11.setCellValue("isJsonObject");
		
		c11 = row.createCell(3);
		c11.setCellValue("isJsonNull");
		
		c11 = row.createCell(4);
		c11.setCellValue("isJsonPrimitive");
		
		//key start from index 2
		int sourceIndex = 2;
		
		logger.info("key \t isJsonArray \t isJsonObject \t isJsonNull \t isJsonPrimitive");
		
		Row sourceRow = null;
		for(Map.Entry<String,JsonElement> entry: jsonobj.entrySet()){
			sourceRow = sheet.createRow((short)sourceIndex);
			
			int j =0;
			Cell sourceCell = sourceRow.createCell((short)j);
			String key = entry.getKey();
			JsonElement value = entry.getValue();
			sourceCell.setCellValue(key);
			
			j++;
			sourceCell = sourceRow.createCell((short)j);
			sourceCell.setCellValue(value.isJsonArray());
			j++;
			sourceCell = sourceRow.createCell((short)j);
			sourceCell.setCellValue(value.isJsonObject());
			j++;
			sourceCell = sourceRow.createCell((short)j);
			sourceCell.setCellValue(value.isJsonNull());
			j++;
			sourceCell = sourceRow.createCell((short)j);
			sourceCell.setCellValue(value.isJsonPrimitive());
			
			logger.info("{} \t {} \t {} \t {} \t {}", 
					key, value.isJsonArray(), value.isJsonObject(), value.isJsonNull(), value.isJsonPrimitive());
			sourceIndex ++;
		}
		
		File convertFile = new File(path + File.separator + System.currentTimeMillis() + ".xlsx");
		
    	FileOutputStream fileOut = null;
    	try {
    		fileOut = new FileOutputStream(convertFile);
    		wb.write(fileOut);
    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
    	} catch (IOException e) {
    		e.printStackTrace();
    	} finally{
    		try {
    			fileOut.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
	
	}
	
	
	
}
