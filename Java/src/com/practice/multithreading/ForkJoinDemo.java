package com.practice.multithreading;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo extends RecursiveTask<Integer> {

  private static final int SEQUENTIAL_THRESHOLD = 5;

  private final int[] data;
  private final int start;
  private final int end;

  public ForkJoinDemo(int[] data, int start, int end) {
    this.data = data;
    this.start = start;
    this.end = end;
  }

  public ForkJoinDemo(int[] data) {
    this(data, 0, data.length);
  }

  @Override
  protected Integer compute() {
    final int length = end - start;
    if (length < SEQUENTIAL_THRESHOLD) {
      return computeDirectly();
    }
    final int split = length / 2;
    final ForkJoinDemo left = new ForkJoinDemo(data, start, start + split);
    left.fork();
    final ForkJoinDemo right = new ForkJoinDemo(data, start + split, end);
    return Math.max(right.compute(), left.join());
  }

  private Integer computeDirectly() {
    System.out.println(Thread.currentThread() + " computing: " + start + " to " + end);
    int max = Integer.MIN_VALUE;
    for (int i = start; i < end; i++) {
      if (data[i] > max) {
        max = data[i];
      }
    }
    return max;
  }

  public static void main(String[] args) {
    // create a random data set
    final int[] data = new int[1000];
    final Random random = new Random();
    for (int i = 0; i < data.length; i++) {
      data[i] = random.nextInt(100);
    }
    final ForkJoinDemo finder = new ForkJoinDemo(data);

    // submit the task to the pool
    ForkJoinPool pool = new ForkJoinPool(4);
    System.out.println(pool.invoke(finder));
  }
}
