public class CapacityOptimizer {
	private static final int NUM_RUNS = 10;

	private static final double THRESHOLD = 5.0d;
	
	public static int getOptimalNumberOfSpots(int hourlyRate) {
		//intitalize lot size(n) to 1
		int sizeOfLot = 1;
		double average = 0.0;
		int countRun = 1;
		Boolean breakOutOfLoop = false;
		Simulator sim;
		ParkingLot lot = new ParkingLot(sizeOfLot);
		while(!breakOutOfLoop){ 
			System.out.println("==== Setting lot capacity to: "+sizeOfLot+"====");
			while(countRun<NUM_RUNS+1){
				//simulate here
				sim = new Simulator(lot, hourlyRate ,24*3600);
				long startTime = System.nanoTime();
				sim.simulate();
				long endTime   = System.nanoTime();
				long totalTime = endTime - startTime;
				totalTime = totalTime / 1000000; //get runtime in ms
				countRun++;
				average += average + sim.getIncomingQueueSize();
				System.out.println("Simulation run "+ countRun + "("+totalTime+") ;"+"Queue length at the end of simulation run: " + sim.getIncomingQueueSize());
			}
			average = average/NUM_RUNS;
			if(average <= 5.0){
				breakOutOfLoop = true;
			} else {
				sizeOfLot++;
			}
		}
		return sizeOfLot;
	}

	public static void main(String args[]) {
	
		StudentInfo.display();

		long mainStart = System.currentTimeMillis();

		if (args.length < 1) {
			System.out.println("Usage: java CapacityOptimizer <hourly rate of arrival>");
			System.out.println("Example: java CapacityOptimizer 11");
			return;
		}

		if (!args[0].matches("\\d+")) {
			System.out.println("The hourly rate of arrival should be a positive integer!");
			return;
		}

		int hourlyRate = Integer.parseInt(args[0]);

		int lotSize = getOptimalNumberOfSpots(hourlyRate);

		System.out.println();
		System.out.println("SIMULATION IS COMPLETE!");
		System.out.println("The smallest number of parking spots required: " + lotSize);

		long mainEnd = System.currentTimeMillis();

		System.out.println("Total execution time: " + ((mainEnd - mainStart) / 1000f) + " seconds");

	}
}