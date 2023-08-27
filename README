# Club simulation

The aim of this project was to correct and extend an existing simulation code of patrons in a club. I had to use synchronization methods to ensure that the existing code adhered to synchronization limits and maintain thread safety and liveness.

## Simulation Design
The simulation is designed as follows: 
* A club is represented as a grid of a specified size.
* There exists an entrance door in middle of the first row of the grid.
* There exists an exit door in the left side of the grid/
* There exists a dance area, represented by a yellow sub-grid.
* There exists a bar area which takes up a whole row in the grid.

### Simulation Behaviour
* The Start button initiates the simulation.
* The Pause button pauses/resumes the simulation.
* The Quit button terminates the simulation (it does).
* Patrons enter through the entrance door and exit through the exit doors.
* The entrance and exit doors are accessed by one patron at a time.
* The maximum number of patrons inside the club must not exceed a specified
* limit.
* Patrons must wait if the club limit is reached or the entrance door is occupied.
* Inside the club, patrons maintain a realistic distance from each other (one per grid block).
* Patrons move block by block and simultaneously to ensure liveness.

#### Concurrency/Behaviour Issues Identified
1. The START button does not start the simulation, simulation starts automatically when the program is ran 
2. Pause button does not pause/resume the simulation (basically has no impact on the simulation)
3. Multiple patrons entering through the door at the same time 
4. Maximum limit is not upheld (more that 5 patrons are entering the club) 
5. No liveliness

##### Solutions 
1. Add a lock, so that the simulation does not start until the START button is pressed.
2. Lock the simulation, when Pause is pressed initially. When Pause is pressed again... resume the simulation.
3. If a patron is at the entrance point, lock the door for other patrons until the patron at the door has successfully entered the club.
4. * Lock the door when the amount of patrons inside are at maximum, and when at least patron leaves, unlock the door.
   * Patrons outside must wait until there is space inside (ergo waiting number should increment as well)
5. Thread must move block by block, ergo they cannot skip blocks
6. A patrons should order on drink and after getting their drink, they should leave (the bar not the club... they can wander)

## Collaborators
[Michelle Kuttel] (mkuttel@gmail.com)
[Fhatuwani Mokwenda] (https://github.com/Fhatuuu)

