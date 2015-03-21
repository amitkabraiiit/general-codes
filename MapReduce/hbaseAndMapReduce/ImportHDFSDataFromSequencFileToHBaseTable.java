import java.io.IOException;
import  org.apache.hadoop.conf.Configuration;
import  org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import  org.apache.hadoop.mapreduce.Job;
import  org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import  org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import  org.apache.hadoop.util.GenericOptionsParser;

/*
 *
 *  This Program needs <tablename> as the hbase table where we will import data to from the <inputdir> where we will import data from
 *  It expects hbase table where we have to import data should already be present with the schema same as the sequence file present in inputDir.
 *  In the below example it takes input from seqence file present in dir '/user/sfdc/Out' and writes data to 'test3' table.
 *  It runs only 1 mapper.
 *
 *  1) rm -rf *.class classes/*; mkdir -p classes; ~/current/bigdata-util/tools/Linux/jdk/jdk1.7.0_21_x64/bin/javac -cp $abc -d classes/ ImportHDFSDataFromSequencFileToHBaseTable.java ; ~/current/bigdata-util/tools/Linux/jdk/jdk1.7.0_21_x64/bin/jar -cvf program.jar -C classes/ . ; cp program.jar ~/current/bigdata-hbase/hbase/hbase/lib/ 
 *
 *  2) create 'test3', 'cf1'
 *
 *  3) Use ExportHBaseDataToHDFSSequenceFile to put data into /user/sfdc/Out
 *
 *  4) ~/current/bigdata-hadoop/hadoop/hadoop/bin/hadoop jar program.jar ImportHDFSDataFromSequencFileToHBaseTable test3 /user/sfdc/Out
 *
 *  5) scan 'test3'
 *
*/

public class ImportHDFSDataFromSequencFileToHBaseTable {

	final static String NAME = "import";
	static class Importer extends TableMapper<ImmutableBytesWritable, Put> {

		@Override
			public void map(ImmutableBytesWritable row, Result value,Context context) throws IOException {

				try {
					context.write(row, resultToPut(row, value));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		private static Put resultToPut(ImmutableBytesWritable key, Result result) throws IOException {
			Put put = new Put(key.get());
			for (KeyValue kv : result.raw()) {
				put.add(kv);
			}
			return put;
		}
	}

	public static Job createSubmittableJob(Configuration conf, String[] args) throws IOException {
		String tableName = args[0];
		Path inputDir = new Path(args[1]);
		Job job = new Job(conf, NAME + "_" + tableName);
		job.setJarByClass(Importer.class);
		FileInputFormat.setInputPaths(job, inputDir);
		job.setInputFormatClass(SequenceFileInputFormat.class);
		job.setMapperClass(Importer.class);

		TableMapReduceUtil.initTableReducerJob(tableName, null, job);
		job.setNumReduceTasks(0);
		return job;
	}

	private static void usage(final String errorMsg) {
		if (errorMsg != null && errorMsg.length() > 0) {
			System.err.println("ERROR: " + errorMsg);
		}
		System.err.println("Usage: ImportHDFSDataFromSequencFileToHBaseTable <tablename> <inputdir>");

	}

	public static void main(String[] args) throws Exception {
		Configuration conf = HBaseConfiguration.create();
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		if (otherArgs.length < 2) {
			usage("Wrong number of arguments: " + otherArgs.length);
			System.exit(-1);
		}
		Job job = createSubmittableJob(conf, otherArgs);
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
