package fbFriends;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FbReducer extends Reducer<Text, Text, Text, Text> 
{
	
	@Override
	public void reduce(Text subjectWithFriend, Iterable<Text> listsOfFriends, Context context) throws IOException, InterruptedException
	{
		String commonFriends = Intersection.getFriendsIntersection(listsOfFriends);
		context.write(new Text(subjectWithFriend), new Text(commonFriends));
		//for(Text f : listsOfFriends)
		//{
		//	context.write(new Text(subjectWithFriend), f);
		//}
	}

}
