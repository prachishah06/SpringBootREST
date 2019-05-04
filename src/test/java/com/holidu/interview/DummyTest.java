package com.holidu.interview;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.holidu.interview.assignment.entity.SearchTreeParam;
import com.holidu.interview.assignment.entity.StreetTree;
import com.holidu.interview.assignment.support.HealthCheckController;

public class DummyTest {

	HealthCheckController test;

	@Before
	public void initData() {

		test = new HealthCheckController();

		List<StreetTree> trees = new ArrayList<StreetTree>();

		StreetTree tree = new StreetTree();
		tree.setX_sp("70.55");
		tree.setY_sp("120.00");
		tree.setSpc_common("Valid_Tree_1");//1268=r2

		trees.add(tree);

		tree = new StreetTree();
		tree.setX_sp("150.67");
		tree.setY_sp("87");
		tree.setSpc_common("Valid_Tree_2");//2737=r2 

		trees.add(tree);

		tree = new StreetTree();
		tree.setX_sp("-45.72");
		tree.setY_sp("200");
		tree.setSpc_common("InValid_Tree");//31235=r2

		trees.add(tree);

		test.listStreetTree = trees;

	}

	@Test
	public void TestTreeSearch() {

		SearchTreeParam searchParam = new SearchTreeParam();
		searchParam.setCenterPoint_X(100d);
		searchParam.setCenterPoint_Y(100d);
		searchParam.setSearchRadius(40d);

		List<StreetTree> filteredTreeList = test.getStreetTrees(searchParam);
		Assert.assertEquals(1, filteredTreeList.size());
		StreetTree tree = filteredTreeList.get(0);
		Assert.assertEquals("Valid_Tree_1" , tree.getSpc_common() );

		searchParam = new SearchTreeParam();
		searchParam.setCenterPoint_X(100d);
		searchParam.setCenterPoint_Y(100d);
		searchParam.setSearchRadius(60d);

		filteredTreeList = test.getStreetTrees(searchParam);
		Assert.assertEquals(2, filteredTreeList.size());
		for (StreetTree streetTree : filteredTreeList) {
			Assert.assertNotEquals("InValid_Tree", streetTree.getSpc_common());
		}

	}
}
