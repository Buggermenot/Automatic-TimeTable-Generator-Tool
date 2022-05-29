# Automatic-TimeTable-Generator-Tool
Developed as part of the 30 hour Hackaccino Hackathon conducted by the Computer Science Society of India at Bennett University, Greater Noida, India.

The Generator Tool is written purely in Java without using any External Libraries and was made specific to the requirements of Bennett University CSE 1st year Students.
The code may be adjusted to add/change certain aspects as per required.

The approach to the problem was simple:
  Each weekly timetable has 5 days and each day is divided into 8 blocks of 1 hour each.
  Each block is defined as an instance in time that defines the occupancy of all the rooms at the university.
  There are different types of rooms at the unversity that includes Lecture Halls, Tutorial Rooms, etc.
  There are x number of each type of rooms and each room can only be occupated by the same type of subject. i.e. Phy-Lecture can only be taught in a lecture room and not a tutorial room.
  
With this, it is simple to see that a solution can be to simply try to fill the rooms according to the classes that batches need to take.
At each block, the program tries to fill the rooms and hence eventually maps all classes to some block and room.
With this done, at each block, we can now back track to find where each batch would be to get a timetable.

The same logic can be extended to be on the basis of Teachers, Subjects or Student Batches instead of Rooms. We simply found the approach via picking rooms to be the simplest.

The code in its current state is very raw and contains a lot of bugs. There simply wasn't enough time to clean it with regards to the timeframe of the competition.
