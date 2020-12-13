package application;
import org.json.simple.JSONObject;
public class Filter implements IFilter{
	private String type;
	private String key;

	public Filter(String type, String key) {
		this.type = type;
		this.key = key;
	}
	public DList search(DList index){
		if (!type.equals("to") && !type.equals("year") && !type.equals("month") && !type.equals("day") && !type.equals("attachment")) {
DList d = new DList();
for(int i = 0; i < index.size(); i++) {
	if (((String)((JSONObject) index.get(i)).get(type)).equals(key)) {
		d.add((JSONObject) index.get(i));
	}
}
		return d;
	} else if (type.equals("attachment")) {
		return searchAttach(index);
	} else if (type.equals("to")) {
		return searchReceivers(index);
	} else {
		return searchDate(index);
	}
	}
	private DList searchReceivers(DList index) {
		// TODO Auto-generated method stub
		DList d = new DList();
		for(int i = 0; i < index.size(); i++) {
				String[] str = ((String)((JSONObject) index.get(i)).get(type)).split(",");
				for (int j = 0; j < str.length; j++) {
					if (str[j].equals(key)) {
						d.add((JSONObject) index.get(i));
						break;
					}
				}
		}
		return d;
	}
	private DList searchAttach(DList index) {
		// TODO Auto-generated method stub
		DList d = new DList();
		if (key == null) {
		for(int i = 0; i < index.size(); i++) {
			if (((JSONObject) index.get(i)).get(type).equals(key)) {
				d.add((JSONObject) index.get(i));
			}
			}
		} else {
			for(int i = 0; i < index.size(); i++) {
				if (!((JSONObject) index.get(i)).get(type).equals(key)) {
					d.add((JSONObject) index.get(i));
				}
				}
		}
		return d;
	}
	private DList searchDate(DList index) {
		int m;
		DList d = new DList();
		if (type.equals("year")) {
			m = 0;
		} else {
			if(type.equals("month")) {
			m = 1;
			} else {
				m = 2;
			}
			for(int i = 0; i < index.size(); i++) {
				String str = (String) ((JSONObject) index.get(i)).get("date");
				String[] t1 = str.split(" ");
				String[] t2 = t1[0].split(",");
				if (t2[m].equals(key)) {
					d.add((JSONObject) index.get(i));
				}
				
			}
		}
		return d;
		
	}

}