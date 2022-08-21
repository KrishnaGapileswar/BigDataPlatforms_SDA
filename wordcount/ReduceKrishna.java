package wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

//NOTE: the input key and value types of Reducer should match exactly
// with output key and value types of Mapper

public class ReduceKrishna extends Reducer<Text, IntWritable, Text, LongWritable>
{
	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException
	{
		long count = 0;
		for(IntWritable value : values)
		{
			++count;
		}
		context.write(key, new LongWritable(count));
	}
}
