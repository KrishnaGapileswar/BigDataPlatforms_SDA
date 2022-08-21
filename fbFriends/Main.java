package fbFriends;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Main extends Configured implements Tool
{
	@Override
	public int run(String[] args) throws Exception
	{
		Job job = Job.getInstance(getConf());
		job.setJobName("fb_friends_mapreduce");
		job.setJarByClass(Main.class);
		
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		job.setMapperClass(FbMapper.class);
		job.setReducerClass(FbReducer.class);
		
		Path inputFilePath = new Path(args[0]);
		Path outputFilePath = new Path(args[1]);
		
		FileInputFormat.addInputPath(job, inputFilePath);
		FileOutputFormat.setOutputPath(job, outputFilePath);
		
		return job.waitForCompletion(true) ? 0 : 1;
	}
	
	public static void main(String[] args) throws Exception
	{
		int exitCode = ToolRunner.run(new Main(), args);
		System.exit(exitCode);
	}

}
