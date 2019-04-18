package util.system;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import page.MainLocale;
import util.stage.MapColc;
import util.stage.Stage;
import util.stage.StageMap;
import util.unit.Form;

public class MultiLangCont<I,T> {

	public static final MultiLangCont<MapColc,String> MCNAME=new MultiLangCont<>();
	public static final MultiLangCont<StageMap,String> SMNAME=new MultiLangCont<>();
	public static final MultiLangCont<Stage,String> STNAME=new MultiLangCont<>();
	public static final MultiLangCont<Form,String> FNAME=new MultiLangCont<>();
	
	public static String get(Object o) {
		String loc=MainLocale.RENN[MainLocale.lang];
		if(o instanceof MapColc)
			return MCNAME.get(loc,(MapColc) o);
		if(o instanceof StageMap)
			return SMNAME.get(loc,(StageMap) o);
		if(o instanceof Stage)
			return STNAME.get(loc,(Stage) o);
		if(o instanceof Form)
			return FNAME.get(loc,(Form) o);
		return null;
	}
	
	private final Map<String,HashMap<I,T>> map = new TreeMap<>();
	
	public MultiLangCont() {
		
	}
	
	public void put(String loc,I x,T t) {
		if(x==null||t==null)
			return;
		getSub(loc).put(x, t);
	}
	
	public T get(String loc,I x) {
		T ans=getSub(loc).get(x);
		if(ans==null)
			ans=getSub(MainLocale.LOC_CODE[0]).get(x);
		return ans;
	}
	
	private HashMap<I,T> getSub(String loc){
		HashMap<I,T> ans=map.get(loc);
		if(ans==null)
			map.put(loc,ans=new HashMap<>());
		return ans;
	}
	
}


