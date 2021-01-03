package hellcoding_Java.exam01;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/* 리스트 내에서 키워드 검색하기 
 * https://hellcoding.tistory.com/10
 * 2021.01.02 작성
 * */
public class ListSearch {
	public static String sampleStr = "[{'no':'1','city':'서울','name':'김철수','gender':'남자'},"
			+ "{'no':'2','city':'서울','name':'김영희','gender':'여자'},"
			+ "{'no':'3','city':'서울','name':'김민수','gender':'남자'},"
			+ "{'no':'4','city':'대전','name':'이광수','gender':'남자'},"
			+ "{'no':'5','city':'대전','name':'이미자','gender':'여자'},"
			+ "{'no':'6','city':'대전','name':'진지희','gender':'여자'},"
			+ "{'no':'7','city':'대구','name':'김철수','gender':'남자'},"
			+ "{'no':'8','city':'대구','name':'김영희','gender':'여자'},"
			+ "{'no':'9','city':'부산','name':'김철수','gender':'남자'},"
			+ "{'no':'10','city':'부산','name':'이미자','gender':'여자'}]";
	
	public static void getListElement(List<Map<String, Object>> list) {
		/* 1. 리스트 출력 */
		System.out.println("--------------- getListElement ---------------");
		for(int i=0; i<list.size(); i++) {
			Map<String, Object> innerListElement = list.get(i);
			System.out.println("["+(i+1)+"] " + innerListElement);
		}
		System.out.println("총 " +list.size()+"개 항목");
		System.out.println("-------------------------------------------------");
	}
	
	public static void searchList1(List<Map<String, Object>> list, String city, String gender) {
		/* 2-1. 리스트 내부 검색 method1 */
		System.out.println("============== searchList1 ==============");
		System.out.println("search >> 도시: "+city+" , 성별: "+gender);
		
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		for(int i=0; i<list.size(); i++) {
			Map<String, Object> tempMap = list.get(i);
			if( "".equals(city) && "".equals(gender) ) {
				// 1) 검색조건 없음
				resultList = list;
			}else if( "".equals(city) && "".equals(gender) ) {
				// 2) 도시만 검색
				if( city.equals(tempMap.get("city")) ) {
					resultList.add(tempMap);
				}
			}else if( "".equals(city) && "".equals(gender) ) {
				// 3) 성별만 검색
				if( gender.equals(tempMap.get("gender")) ) {
					resultList.add(tempMap);
				}
			}else if( !("".equals(city) && "".equals(gender)) ) {
				// 4) 도시,성별 검색
				if( city.equals(tempMap.get("city")) && gender.equals(tempMap.get("gender")) ) {
					resultList.add(tempMap);
				}
			}
		}
		
		int resultCnt = resultList.size();
		if(resultCnt>0) {
			getListElement(resultList);
		}else {
			System.out.println("일치하는 항목이 존재하지 않습니다.");
		}
		System.out.println("===================================");
	}
	
	public static void searchList2(List<Map<String, Object>> list, String city, String gender) {
		/* 2-1. 리스트 내부 검색 method2 */
		System.out.println("============== searchList2 ==============");
		System.out.println("search >> 도시: "+city+" , 성별: "+gender);
		
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		for(int i=0; i<list.size(); i++) {
			Map<String, Object> tempMap = list.get(i);
			if( (!"".equals(city) ? city.equals(tempMap.get("city")) : true ) && 
					(!"".equals(gender) ? gender.equals(tempMap.get("gender")) : true ) ) {
				resultList.add(tempMap);
			}
		}
		
		int resultCnt = resultList.size();
		if(resultCnt>0) {
			getListElement(resultList);
		}else {
			System.out.println("일치하는 항목이 존재하지 않습니다.");
		}
		System.out.println("===================================");
	}
	
	public static void main(String[] args) {
		Gson gson = new Gson();
		List<Map<String, Object>> memberList = null;
		memberList = gson.fromJson(sampleStr, new TypeToken<List<Map<String, Object>>>() {}.getType());
		
		boolean run = true;
		while(run) {
			int order;
			String city;
			String gender;
			Scanner sc = new Scanner(System.in);
			System.out.println("검색조건(1.없음, 2.도시, 3.성별, 4.도시+성별)");
			order = sc.nextInt();
			switch(order) {
				case 1 : 
					city = "";
					gender="";
					searchList1(memberList, city, gender);
					searchList2(memberList, city, gender);
					break;
				case 2 : 
					System.out.println("도시를 입력하세요");
					city = sc.next();
					gender="";
					searchList1(memberList, city, gender);
					searchList2(memberList, city, gender);
					break;
				case 3 : 
					System.out.println("성별을 입력하세요");
					gender = sc.next();
					city = "";
					searchList1(memberList, city, gender);
					searchList2(memberList, city, gender);
					break;
				case 4 : 
					System.out.println("도시를 입력하세요>");
					city = sc.next();
					System.out.println("성별을 입력하세요>");
					gender = sc.next();
					searchList1(memberList, city, gender);
					searchList2(memberList, city, gender);
					break;
				default : 
					System.out.println("잘못된 조건번호입니다.");
					break;	
			}
			System.out.println("종료하시겠습니까?(y/n)");
			String checkEnd = sc.next();
			if("y".equals(checkEnd)) {
				System.out.println("프로그램을 종료합니다.");
				run = false;
			}
		}
	}

}
