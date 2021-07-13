# Detect space invaders

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
   rotation in clockwise or anticlockwise direction.
   
 * Result of the matching is echoed out on logs
2. Docker

