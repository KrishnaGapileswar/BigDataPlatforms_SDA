package fbFriends;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;

public class FbMapper extends Mapper<LongWritable, Text, Text, Text>
{
	private String generateKey(String input)
	{
		char[] tempArray = input.toCharArray();
		Arrays.sort(tempArray);
		return new String(tempArray);
	}
	
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
	{
		String friendList = value.toString(); // A-B,C,D which means A is a friend of B,C,D
		String[] splitFriendList = friendList.split("-", 2); 
		String subject = splitFriendList[0]; // A
		String friends = splitFriendList[1]; // B,C,D
		String[] friendsArray = friends.split(",", 0);
		
		for(String friend : friendsArray)
		{
			String toReducekey = generateKey(subject.concat(friend));
			context.write(new Text(toReducekey), new Text(friends)); // (AB) (B,C,D)
		}
	}

}
