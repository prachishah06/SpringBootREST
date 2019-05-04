package com.holidu.interview.assignment.support;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.holidu.interview.assignment.entity.SearchTreeParam;
import com.holidu.interview.assignment.entity.StreetTree;
import com.holidu.interview.assignment.exception.TreeNotFoundException;

@RestController
public class HealthCheckController {

	@RequestMapping(name = "healthCheckEndpoint", method = RequestMethod.GET, value = "/")
	public String healthCheck() {
		return "Greetings from the Holidu interview assignment!";
	}

	/* create list of StreetTree */
	public List<StreetTree> listStreetTree;

	/*
	 * load all StreetTree Data
	 */
	@PostConstruct
	public void loadData() {

		listStreetTree = new ArrayList<StreetTree>();

		try {
			// create object mapper
			ObjectMapper mapper = new ObjectMapper();

			// read JSON file and convert to Java POJO
			listStreetTree = mapper.readValue(new URL("https://data.cityofnewyork.us/resource/nwxe-4ae8.json"),
					new TypeReference<List<StreetTree>>() {
					});

			System.out.println("Done!");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * add mapping to retrieve all StreetTreeData endpoint :
	 * http://localhost:8080/streetTreeData
	 */
	@GetMapping("/streetTreeData")
	public List<StreetTree> listStreetTreeData() {
		return listStreetTree;
	}

	/*
	 * add mapping to retrieve input parameters from URL and search species endpoint:
	 * http://localhost:8080/searchTreesv2/?centerPoint_X=1027420&centerPoint_Y=202750&searchRadius=1700
	 */
	@GetMapping("/searchTreesV2")
	public Map<String, Integer> searchTreesInRadiusV2(@RequestParam double centerPoint_X,
			@RequestParam double centerPoint_Y, @RequestParam double searchRadius) {
		SearchTreeParam searchTreeParam = new SearchTreeParam(centerPoint_X, centerPoint_Y, searchRadius);

		return searchTreesInRadius(searchTreeParam);
	}

	/*
	 * add mapping to retrieve input parameters JSON body and search species endpoint:
	 * http://localhost:8080/searchTrees { "centerPoint_X": "1027420",
	 * "centerPoint_Y": "202750", "searchRadius": "1509"}
	 */
	@GetMapping("/searchTrees")
	public Map<String, Integer> searchTreesInRadius(@RequestBody SearchTreeParam searchTreeParam) {
		List<StreetTree> tempResultList = new ArrayList<StreetTree>();
		// query and get result
		tempResultList = getStreetTrees(searchTreeParam);

		if (tempResultList.isEmpty())
			throw new TreeNotFoundException(
					"No trees found in the given radius of " + searchTreeParam.getSearchRadius() + "m");

		Map<String, Integer> countOfTrees = new HashMap<String, Integer>();
		for (StreetTree streetTree : tempResultList) {

			if (streetTree.getSpc_common() == null)
				streetTree.setSpc_common("Unknown");

			Integer j = countOfTrees.get(streetTree.getSpc_common());
			countOfTrees.put(streetTree.getSpc_common(), (j == null) ? 1 : j + 1);
		}
		return countOfTrees;
	}

	/* query data to search trees */
	public List<StreetTree> getStreetTrees(SearchTreeParam searchTreeParam) {
		List<StreetTree> tempResultList = new ArrayList<StreetTree>();
		double x;
		double y;
		double h = searchTreeParam.getCenterPoint_X();
		double k = searchTreeParam.getCenterPoint_Y();
		Double searchRadius = new Double(Math.pow(searchTreeParam.getSearchRadius(), 2));
		Double calcRadius;

		/* center:(h, k) coordinates of tree: (x,y) radius: r (x−h)2+(y−k)2=r2 */
		for (StreetTree streetTree : listStreetTree) {
			x = Double.parseDouble(streetTree.getX_sp());
			y = Double.parseDouble(streetTree.getY_sp());

			calcRadius = new Double(Math.pow((x - h), 2) + Math.pow((y - k), 2));

			if (calcRadius.compareTo(searchRadius) <= 0)
				tempResultList.add(streetTree);
		}

		return tempResultList;
	}
}
