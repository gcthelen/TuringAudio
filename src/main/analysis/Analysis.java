package main.analysis;

import java.util.ArrayList;
import java.util.TreeMap;

public class Analysis {

	private static TreeMap<Double, ArrayList> freqToData;
	private static double sampleRate = 44100.0;
	private static double alpha = 5.0;
	private static double timeStep = 0.002;
	private static double maxWindow = 0.1;
	private static double minFreq = 27.5;
	private static double maxFreq = 20000.0;
	private static double logBase = 72.0;
	private static double maxBins = 103.37 / 2.0;
	private static int padding = (int) Math.round(maxWindow * sampleRate);
	private TreeMap <Integer, Double> indexToFrequency = new TreeMap<Integer, Double>();
	
	private double[] padSampleData(float[] sampleData) {
		double[] returnVal = new double[sampleData.length + padding];
		for(int index = 0; index < sampleData.length; index++) {
			returnVal[(int) Math.round(maxWindow * sampleRate) / 2 + index] = sampleData[index];
		}
		return returnVal;
	}
	
	private void createFreqToData(float[] inputSamples) {
		freqToData = new TreeMap<Double, ArrayList>();
		double[] sampleData = padSampleData(inputSamples);
		for(double freq = minFreq; freq < maxFreq; freq *= Math.pow(2.0, 1.0 / logBase)) {
			double windowLength = (sampleRate / freq) * maxBins;
			if(windowLength > maxWindow) windowLength = maxWindow;
			int order = (int) Math.round(windowLength * sampleRate);
			double[] BPFilter = Filter.getBPFilter(freq, order, alpha);
			double[] filtered = new double[sampleData.length];
			/*
			 * Filter Data
			*/
			for(int sampleIndex = 0; sampleIndex < sampleData.length; sampleIndex++) {
				for(int filterIndex = 0; filterIndex < BPFilter.length; filterIndex++) {
					filtered[sampleIndex] += sampleData[sampleIndex] * BPFilter[filterIndex];
				}
			}
			/*
			 * Take absolute value of data
			*/
			for(int sampleIndex = 0; sampleIndex < sampleData.length; sampleIndex++) {
				filtered[sampleIndex] = Math.abs(sampleData[sampleIndex]);
			}
			/*
			 * Get average value of absolute value of data
			 */
			int timeStepInSamples = (int) Math.round(timeStep * sampleRate);
			double samplesPerCycle = sampleRate / freq;
			double completeCyclesPerFilter = Math.floor(BPFilter.length / samplesPerCycle);
			// need to make sure number of cycles is an integer in length of averaging window
			// this is because varying fractional cycles at different frequencies will skew average
			int averageRange = (int) Math.round(completeCyclesPerFilter * samplesPerCycle);
			// make sure averaging window is at least one time step
			if (averageRange < timeStepInSamples) {
				averageRange = timeStepInSamples;
			}
			ArrayList<Double> averageArray = new ArrayList<Double>();
			int startIndex = padding - averageRange / 2;
			if(startIndex < 0) startIndex = 0; // shouldn't happen but check for it
			for(int sampleIndex = startIndex; sampleIndex < sampleData.length - padding; sampleIndex += timeStepInSamples) {
				double average = 0.0;
				for(int index = 0; index < averageRange; index++) {
					average =+ filtered[index]; 
				}
				average /= averageRange;
				averageArray.add(average);
			}
			freqToData.put(freq, averageArray);
		}
	}
}
