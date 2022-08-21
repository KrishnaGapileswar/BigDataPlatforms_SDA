package fbFriends;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.hadoop.io.Text;

public class Intersection 
{
	
	public static String getFriendsIntersection(Iterable<Text> listsOfFriends)
	{
		HashMap<String, Integer> myMap = new HashMap<>();
		//Collection<String> cs = new ArrayList<String>();
		
		for(Text friendList : listsOfFriends)
		{
			//Collection<String> csInner = new ArrayList<String>();
			String[] friends = friendList.toString().split(",");
			for(String friend : friends)
			{
				//csInner.add(friend);
				if(myMap.containsKey(friend))
				{
					myMap.put(friend, myMap.get(friend) + 1);
				}
				else
				{
					myMap.put(friend, 1);
				}
			}
			//cs.retainAll(csInner);
		}
		
		String commonFriends = "";
		for(Map.Entry<String, Integer> entry : myMap.entrySet())
		{
			if(entry.getValue() > 1)
			{
				commonFriends += entry.getKey();
			}
		}
		/*for(String s : cs)
		{
			commonFriends += s;
		}*/
		return commonFriends;
	}

}
