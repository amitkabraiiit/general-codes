package com.practice.misc;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.*;

public class Histogram {
	public static int total = 0;

	class Range {
		public Range(double l, double h) {
			low = l;
			high = h;
			count = new AtomicLong(0);
		}

		public double low;
		public double high;
		public AtomicLong count;

		public final String toString() {
			if (low == 0) {
				return high + " - " + count.get() + " - " + count.get()*100/total + "%";
			} else if (high == Double.MAX_VALUE) {
				return low + " - " + count.get() + " - " + (double)count.get()*100/(double)total + "%";
			} else
				return high + " - " + count.get() + " - " + (double)count.get()*100.0/(double)total + "%";
		}

		public boolean belongs(double val) {
			return (val >= low && val < high);
		}

	}

	ArrayList<Range> ranges = null;
	String name = null;

	public Histogram(String name, double[] rangeVals) {
		this.name = name;
		Arrays.sort(rangeVals);

		ranges = new ArrayList<Range>();
		double last = 0;
		for (int i = 0; i < rangeVals.length; i++) {

			if (i == 0 && rangeVals[0] != 0) {
				System.out.println(">>> adding < " + rangeVals[0]);
				ranges.add(new Range(0, rangeVals[0]));
				last = rangeVals[0];
			} else {
				System.out.println(">>> adding  " + last + "-" + rangeVals[i]);
				ranges.add(new Range(last, rangeVals[i]));
				last = rangeVals[i];
			}
		}
		ranges.add(new Range(last, Double.MAX_VALUE));
	}

	public void addValue(double value) {
		for (Range range : ranges) {
			if (range.belongs(value)) {
				range.count.incrementAndGet();
			}
		}
	}

	public final String toString() {
		StringBuilder sb = new StringBuilder("Histogram : " + name + "\n");
		for (Range range : ranges) {
			sb.append("\t" + range + "\n");

		}
		return sb.toString();
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//String dataFile = args[0];
		BufferedReader inputStream = null;
		double[] ranges = new double[] { 5,10,20,30,40,50,60,70,80,90,100};
		//double[] ranges = new double[] { 100, 200, 300, 400, 500, 600, 800, 1000, 1500, 2000 };
		//double[] ranges = new double[] { 10000, 15000, 20000, 25000, 30000, 35000, 40000 };
		Histogram test = new Histogram("Test Values", ranges);	    
		/*
		Random random = new Random();
		for (int i = 0; i < 1000; i++) {
			double val = random.nextDouble();
			if (val < 0) {
				val = 0 - val;
			}
			test.addValue(val * 200);
		}
		 */
		try {
			try {
				inputStream = new BufferedReader(new FileReader("/home/abhishek.chouhan/cross_dc_perflogs/latencies_shared_write_smallBatch.out"));
				//inputStream = new BufferedReader(new FileReader("/home/vvishwakarma/vikas/tools/jmeter/perflogs/perf.log.10.204.64.16.csv.4T.500F.1200000S.disk.histo"));
				//inputStream = new BufferedReader(new FileReader("/home/vvishwakarma/vikas/tools/jmeter/perflogs/perf.log.10.204.64.16.csv.4T.500F.1200000S.networlk.histo"));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			String numStr;
			while ((numStr = inputStream.readLine()) != null) {
				try {
					double num = Double.valueOf(numStr.trim()).doubleValue();
					//System.out.println("" + num);
					test.addValue(num);
					total++;

				} catch (NumberFormatException e) {
					System.out.println("NumberFormatException: "
							+ e.getMessage());
				}

			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}

		System.out.println(test);

	}
}
