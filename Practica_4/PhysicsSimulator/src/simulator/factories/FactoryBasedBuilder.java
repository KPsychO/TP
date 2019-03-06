package simulator.factories;

import java.util.*;

import org.json.JSONObject;

public class FactoryBasedBuilder<T> implements Factory<T> {

	List<Builder<T>> builder_list;
	
	public FactoryBasedBuilder(List<Builder<T>> builder_list) {
		
		this.builder_list = new ArrayList<Builder<T>>(builder_list);
		
	}
	
	public T createInstance(JSONObject info) {

		T aux = null;
		
		for (Builder<T> builder : builder_list) {
			
			try {
				
				if(builder.typeTag.equals(info.getString("type")))
					aux = (T) builder.createTheInstance(info.getJSONObject("data"));
				
			} catch (IllegalArgumentException e) {
				
				System.out.println(e.getMessage());
				
			}
			
			if(aux != null)
				return aux;
			
		}
		
		return null;
	}

	public List<JSONObject> getInfo() {
		
		List<JSONObject> list = new ArrayList<JSONObject>();
		
		for(Builder<T> builder : this.builder_list)
			list.add(builder.getBuilderInfo());
		
		return list;
	}

}