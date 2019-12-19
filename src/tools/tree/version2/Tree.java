package tools.tree.version2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

public class Tree extends BaseTree{
	private Integer id;
	private Integer pid;
	private String name;
	
	public Tree(Integer id,Integer pid,String name) {
		this.id=id;
		this.pid=pid;
		this.name=name;
	}

	@Override
	public String init_TN() {
		return id.toString();
	}
	@Override
	public String init_PN() {
		return pid.toString();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static void main(String[] args) {
		Tree tree2=new Tree(2,1,"我是子级");
		Tree tree3=new Tree(3,1,"我是子级");
		Tree tree=new Tree(1,0,"我是父级");
		Tree tree4=new Tree(4,2,"我是子级");
		Tree tree5=new Tree(5,2,"我是子级");
		Tree tree6=new Tree(6,2,"我是子级");
		Tree tree7=new Tree(7,2,"我是子级");
		Tree tree8=new Tree(8,2,"我是子级");
		Tree tree9=new Tree(9,2,"我是子级");
		List<Tree> list =new ArrayList<>();
		list.add(tree);
		list.add(tree2);
		list.add(tree3);
		list.add(tree4);
		list.add(tree5);
		list.add(tree6);
		list.add(tree7);
		list.add(tree8);
		list.add(tree9);
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,3,"我是3的子级"));
		list.add(new Tree(9,4,"我是4的子级"));
		list.add(new Tree(9,4,"我是4的子级"));
		list.add(new Tree(9,4,"我是4的子级"));
		list.add(new Tree(9,4,"我是4的子级"));
		list.add(new Tree(9,4,"我是4的子级"));
		list.add(new Tree(9,4,"我是4的子级"));
		list.add(new Tree(9,4,"我是4的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,5,"我是5的子级"));
		list.add(new Tree(9,6,"我是6的子级"));
		list.add(new Tree(9,6,"我是6的子级"));
		list.add(new Tree(9,6,"我是6的子级"));
		list.add(new Tree(9,6,"我是6的子级"));
		list.add(new Tree(9,6,"我是6的子级"));
		list.add(new Tree(9,6,"我是6的子级"));
		list.add(new Tree(9,6,"我是6的子级"));
		list.add(new Tree(9,6,"我是6的子级"));
		list.add(new Tree(9,6,"我是6的子级"));
		list.add(new Tree(9,6,"我是6的子级"));
		list.add(new Tree(9,6,"我是6的子级"));
		list.add(new Tree(9,6,"我是6的子级"));
		list.add(new Tree(9,6,"我是6的子级"));
		list.add(new Tree(9,6,"我是6的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,8,"我是8的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,8,"我是8的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,8,"我是8的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,7,"我是7的子级"));
		list.add(new Tree(9,8,"我是8的子级"));
		list.add(new Tree(9,8,"我是8的子级"));
		list.add(new Tree(9,8,"我是8的子级"));
		list.add(new Tree(9,8,"我是8的子级"));
		list.add(new Tree(9,8,"我是8的子级"));
		list.add(new Tree(9,8,"我是8的子级"));
		list.add(new Tree(9,8,"我是8的子级"));
		list.add(new Tree(9,8,"我是8的子级"));
		list.add(new Tree(9,8,"我是8的子级"));
		list.add(new Tree(9,8,"我是8的子级"));
		list.add(new Tree(9,8,"我是8的子级"));
		list.add(new Tree(9,8,"我是8的子级"));
		list.add(new Tree(9,8,"我是8的子级"));
		for (int i = 0; i < 10000; i++) {
			list.add(new Tree(9,7,"我是7的子级"));
			list.add(new Tree(9,7,"我是7的子级"));
			list.add(new Tree(9,7,"我是7的子级"));
			list.add(new Tree(9,7,"我是7的子级"));
			list.add(new Tree(9,7,"我是7的子级"));
			list.add(new Tree(9,7,"我是7的子级"));
			list.add(new Tree(9,8,"我是8的子级"));
			list.add(new Tree(9,8,"我是8的子级"));
			list.add(new Tree(9,8,"我是8的子级"));
			list.add(new Tree(9,8,"我是8的子级"));
			list.add(new Tree(9,8,"我是8的子级"));
			list.add(new Tree(9,8,"我是8的子级"));
			list.add(new Tree(9,8,"我是8的子级"));
			list.add(new Tree(9,8,"我是8的子级"));
			list.add(new Tree(9,8,"我是8的子级"));
			list.add(new Tree(9,8,"我是8的子级"));
			list.add(new Tree(9,8,"我是8的子级"));
			list.add(new Tree(9,8,"我是8的子级"));
			list.add(new Tree(9,8,"我是8的子级"));
		}
		
		Date time1=new Date();
		List list2=BaseTree.sortForTree2(list);
		Date time2=new Date();
		System.out.println( "sortForTree use time:"+(time2.getTime()-time1.getTime())  );
		
		Date time3=new Date();
		List list3=BaseTree.sortForTree2(list);
		Date time4=new Date();
		System.out.println( "sortForTree2 use time:"+(time4.getTime()-time3.getTime())  );
		
//		System.out.println(  JSONArray.fromObject(list2) );
//		System.out.println(  "size:"+list.size() );
	}

}
