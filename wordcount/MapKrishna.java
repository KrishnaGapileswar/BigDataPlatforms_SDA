package wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MapKrishna extends Mapper<LongWritable, Text, Text, IntWritable>
{
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
	{
		String line = value.toString();
		String[] words = line.split(" "); //simply separating by space. not using Tokenizer
		for(String word : words)
		{
			context.write(new Text(word), new IntWritable(1));
		}
	}

}
