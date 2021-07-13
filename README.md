# Detect space invaders

## Logic
The invader images and the radar image is loaded as a matrix in the system. The match between the invader pattern and the 
radar is done by using sliding window technique, where 
based on the invader dimension a sliding window is applied over
the radar image and comparison is applied against the invader pattern.


## Local Run
1. Init.sh
 * Run the init.sh
 * This will build the code and run the application with the `input` folder at root.
   The `input` folder contains two folders `invader` and `radar` which would contain
   files which represent invader pattern and radar image. It also sets the 
   fault tolerance for invader matching(detection) in the radar image.
   So if the fault tolerance is set at 20(percentage) and the match of invader to the radar is
   70 percentage, it will be still a mismatch.
 * Invader pattern is also used to generate diff variations of the invader pattern based on 90 degree(or multiples)
   rotation in clockwise or anticlockwise direction. So one invader image will result in following variations
    * 90 degree clockwise
    * 180 degree clockwise
    * 270 degree clockwise
    * 90 degree anti clockwise
    * 180 degree anti clockwise
    * 270 degree anti clockwise

 * The matching with radar is done based on the sliding window algorithm
   
 * Result of the matching is echoed out on logs
2. Docker
 * The docker-compose file will run the above application.
 * The input directory for radar and invader are part of ENV variables and volume mapping
   * `FAULT_TOLERANCE_PERCENTAGE` is for setting fault tolerance for matching invader to radar image
   *  `INPUT_ROOT` is the folder where the invader and radar images are.
 * The docker run will quit in sometime, the match report will be available im the container logs 

## Improvement
1. Using a dependency injection framework. Currently I have not used any framework as most of the interfaces have
   a single implementation.
2. Re implement the sliding window on Radar matrix in a clean way.
3. Re implement the matching of invader and radar to detect half invaders(cut down in middle).
4. Treat the radar as a continuous stream instead of fixed image.
5. Investigate if the time complexity of matrix operations can be reduced.
6. Output the detection report as a file or event stream(only if radar is a continuous stream).
7. Implement integration tests with stubbed data sources for invader and radar.
8. Clean up the domain.


