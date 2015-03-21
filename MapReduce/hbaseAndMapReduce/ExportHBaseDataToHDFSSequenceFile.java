//package mapred;

/**
 * Copyright 2009 The Apache Software Foundation
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Export an HBase table.
 * This program has configured test1 table as source hbase table and 'Out' path as destination path in the main() to write the sequence file data to.
 * It scan's 'test1' table by using TableMapReduceUtil.initTableMapperJob , where we pass scan of test1 table to mapper TableMapper
 * There is no reducer and output of mapper directly goes to seqence file.
 *
 * 1)
 * create 'test1', 'cf1'
 * put 'test1', '20130101#1', 'cf1:sales', '100'
 * put 'test1', '20130101#2', 'cf1:sales', '110'
 * put 'test1', '20130102#1', 'cf1:sales', '200'
 * put 'test1', '20130102#2', 'cf1:sales', '210'
 *
 * 2) 
 * abc=`~/current/bigdata-hbase/hbase/hbase/bin/hbase classpath`
 * rm -rf *.class classes/*; mkdir -p classes; ~/current/bigdata-util/tools/Linux/jdk/jdk1.7.0_21_x64/bin/javac -cp $abc -d classes/ ExportHBaseDataToHDFSSequenceFile.java ; ~/current/bigdata-util/tools/Linux/jdk/jdk1.7.0_21_x64/bin/jar -cvf program.jar -C classes/ . ; cp program.jar ~/current/bigdata-hbase/hbase/hbase/lib/ 
 *
 * 3) ~/current/bigdata-hadoop/hadoop/hadoop/bin/hadoop jar program.jar ExportHBaseDataToHDFSSequenceFile
 *
 * 4) -bash-4.1$ ~/current/bigdata-hadoop/hadoop/hadoop/bin/hadoop fs -ls /user/sfdc/Out
 *     Found 2 items
 *     -rw-r--r--   3 sfdc supergroup          0 2014-06-29 08:35 /user/sfdc/Out/_SUCCESS
 *     -rw-r--r--   3 sfdc supergroup        398 2014-06-29 08:35 /user/sfdc/Out/part-m-00000
 *
 * 5) To Rerun : ~/current/bigdata-hadoop/hadoop/hadoop/bin/hadoop fs -rm -r /user/sfdc/Out
 *
 */
public class ExportHBaseDataToHDFSSequenceFile {
	private static final Log LOG = LogFactory.getLog(ExportHBaseDataToHDFSSequenceFile.class);
	final static String NAME = "export";

	/**
	 * Mapper.
	 */
	static class Exporter extends TableMapper<ImmutableBytesWritable, Result> {
		/**
		 * @param row  The current table row key.
		 * @param value  The columns.
		 * @param context  The current context.
		 * @throws IOException When something is broken with the data.
		 * @see org.apache.hadoop.mapreduce.Mapper#map(KEYIN, VALUEIN,
		 *   org.apache.hadoop.mapreduce.Mapper.Context)
		 */
		@Override
			public void map(ImmutableBytesWritable row, Result value,Context context)throws IOException {
				try {
					context.write(row, value);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	}

	/**
	 * Sets up the actual job.
	 * @param conf  The current configuration.
	 * @param args  The command line parameters.
	 * @return The newly created job.
	 * @throws IOException When setting up the job fails.
	 */
	public static Job createSubmittableJob(Configuration conf, String[] args)
		throws IOException {
			String tableName = args[0];
			Path outputDir = new Path(args[1]);
			Job job = new Job(conf, NAME + "_" + tableName);
			job.setJobName(NAME + "_" + tableName);
			job.setJarByClass(Exporter.class);
			// TODO: Allow passing filter and subset of rows/columns.
			Scan s = new Scan();
			// Optional arguments.
			int versions = args.length > 2? Integer.parseInt(args[2]): 1;
			s.setMaxVersions(versions);
			long startTime = args.length > 3? Long.parseLong(args[3]): 0L;
			long endTime = args.length > 4? Long.parseLong(args[4]): Long.MAX_VALUE;
			s.setTimeRange(startTime, endTime);
			s.setCacheBlocks(false);
			if (conf.get(TableInputFormat.SCAN_COLUMN_FAMILY) != null) {
				s.addFamily(Bytes.toBytes(conf.get(TableInputFormat.SCAN_COLUMN_FAMILY)));
			}
			LOG.info("verisons=" + versions + ", starttime=" + startTime +	", endtime=" + endTime);
			TableMapReduceUtil.initTableMapperJob(tableName, s, Exporter.class, null,null, job);
			// No reducers.  Just write straight to output files.
			job.setNumReduceTasks(0);
			job.setOutputFormatClass(SequenceFileOutputFormat.class);
			job.setOutputKeyClass(ImmutableBytesWritable.class);
			job.setOutputValueClass(Result.class);
			FileOutputFormat.setOutputPath(job, outputDir);
			return job;
		}

	/*
	 * @param errorMsg Error message.  Can be null.
	 */
	private static void usage(final String errorMsg) {
		if (errorMsg != null && errorMsg.length() > 0) {
			System.err.println("ERROR: " + errorMsg);
		}
		System.err.println("Usage: ExportHBaseDataToHDFSSequenceFile [-D <property=value>]* <tablename> <outputdir> [<versions> " +
				"[<starttime> [<endtime>]]]\n");
		System.err.println("  Note: -D properties will be applied to the conf used. ");
		System.err.println("  For example: ");
		System.err.println("   -D mapred.output.compress=true");
		System.err.println("   -D mapred.output.compression.codec=org.apache.hadoop.io.compress.GzipCodec");
		System.err.println("   -D mapred.output.compression.type=BLOCK");
		System.err.println("  Additionally, the following SCAN properties can be specified");
		System.err.println("  to control/limit what is exported..");
		System.err.println("   -D " + TableInputFormat.SCAN_COLUMN_FAMILY + "=<familyName>");
	}

	/**
	 * Main entry point.
	 *
	 * @param args  The command line parameters.
	 * @throws Exception When running the job fails.
	 */
	public static void main(String[] args) throws Exception {
		args = new String[]{"test1","Out"};
		Configuration conf = HBaseConfiguration.create();
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		if (otherArgs.length < 2) {
			usage("Wrong number of arguments: " + otherArgs.length);
			System.exit(-1);
		}
		Job job = createSubmittableJob(conf, otherArgs);
		System.exit(job.waitForCompletion(true)? 0 : 1);
	}
}
