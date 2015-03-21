import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.FirstKeyOnlyFilter;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;

/*
http://rishavrohitblog.blogspot.in/2013/10/mapreduce-on-hbase-table.html
http://sujee.net/tech/articles/hadoop/hbase-map-reduce-freq-counter/

/* This Program scan's hbase table test1 and analyse it and stores the output in hbase table test2. It expects test1 is there with enteries and test2 is already created.
 * test2 can be created with whatever schema we want and reducer can use that schema to write into that table.
 * For the test data given below it spawns only 1 map and 1 reduce. HBase provides mapper and reducer classes called TableMapper and TableReducer.
 * Give source table name to  TableMapReduceUtil.initTableMapperJob and destination table name to  TableMapReduceUtil.initTableReducerJob
 * Mapper will parse by rowkey, etc and reducer will write data in test2.
 *
 *
 * 1)
 * create 'test1', 'cf1'
 * put 'test1', '20130101#1', 'cf1:sales', '100'
 * put 'test1', '20130101#2', 'cf1:sales', '110'
 * put 'test1', '20130102#1', 'cf1:sales', '200'
 * put 'test1', '20130102#2', 'cf1:sales', '210'
 *
 * 2)
 * create 'test2', 'cf1'
 *
 * 3) 
 * abc=`~/current/bigdata-hbase/hbase/hbase/bin/hbase classpath`
 * export HADOOP_CLASSPATH=$abc
 *
 * Compile :
 * rm -rf *.class classes/*; mkdir -p classes; ~/current/bigdata-util/tools/Linux/jdk/jdk1.7.0_21_x64/bin/javac -cp $abc -d classes/ ReadAndWriteToHBaseFromMapReduce.java ; ~/current/bigdata-util/tools/Linux/jdk/jdk1.7.0_21_x64/bin/jar -cvf program.jar -C classes/ . ; cp program.jar ~/current/bigdata-hbase/hbase/hbase/lib/
 *
 * 4) 
 * Run1 : ~/current/bigdata-hbase/hbase/hbase/bin/hbase testDriver
 *
 * OR
 * Run2 : 
 * ~/current/bigdata-hadoop/hadoop/hadoop/bin/hadoop jar program.jar ReadAndWriteToHBaseFromMapReduce
 *
 * 5) 
 * scan 'test2'
 * org.apache.hadoop.hbase.util.Bytes.toInt("\x00\x00\x00\xD2".to_java_bytes)
 *
 */


public  class ReadAndWriteToHBaseFromMapReduce {

	public ReadAndWriteToHBaseFromMapReduce(){}

	public static class testMapper extends TableMapper<ImmutableBytesWritable, IntWritable> {

		@Override
		public void map(ImmutableBytesWritable rowKey, Result columns, Context context)	throws IOException, InterruptedException {
			try {
				// get rowKey and convert it to string
				String inKey = new String(rowKey.get());
				// set new key having only date
				String oKey = inKey.split("#")[0];
				ImmutableBytesWritable userKey = new ImmutableBytesWritable(oKey.getBytes());
				// get sales column in byte format first and then convert it to string (as it is stored as string from hbase shell)
				byte[] bSales = columns.getValue(Bytes.toBytes("cf1"), Bytes.toBytes("sales"));
				String sSales = new String(bSales);
				Integer sales = new Integer(sSales);
				// emit date and sales values
				context.write(userKey, new IntWritable(sales));
			} catch (RuntimeException e){
				e.printStackTrace();
			}
		}
	}

	public static class testReducer extends TableReducer<ImmutableBytesWritable, IntWritable, ImmutableBytesWritable> {

		@Override
		public void reduce(ImmutableBytesWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
			try {
				int sum = 0;
				// loop through different sales vales and add it to sum
				for (IntWritable sales : values) {
					Integer intSales = new Integer(sales.toString());
					sum += intSales;
				} 
				// create hbase put with rowkey as date
				Put insHBase = new Put(key.get());
				// insert sum value to hbase 
				insHBase.add(Bytes.toBytes("cf2"), Bytes.toBytes("sum"), Bytes.toBytes(sum)); // schema for cf2 should already be created
				// write data to Hbase table
				context.write(null, insHBase);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();

		// define scan and define column families to scan
		Scan scan = new Scan();
		scan.addFamily(Bytes.toBytes("cf1"));

		Job job = new Job(conf); 

		job.setJarByClass(ReadAndWriteToHBaseFromMapReduce.class);
		// define input hbase table
		TableMapReduceUtil.initTableMapperJob(
				"test1",
				scan,
				testMapper.class,
				ImmutableBytesWritable.class,
				IntWritable.class,
				job);
		// define output table
		TableMapReduceUtil.initTableReducerJob(
				"test2",
				testReducer.class, 
				job);

		job.waitForCompletion(true);
	}
}

