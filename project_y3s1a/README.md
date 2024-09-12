# My Personal Project

## A Laundry Tracking App:

My application will track the use of washing machines and dryers in a shared laundry room -- customizable to *your* 
community's laundry. As anyone who has lived on the UBC campus knows, every building in every residence has a unique 
laundry
room for *all* the residents of the building to do their laundry. During my first year I lived on the top floor of an
100-person
building with no elevator and only 2 washers and 4 dryers which were frequently broken.
It would have helped my chore-planning to know which 
machines were being used or if any of our machines were broken. My hope is that my app will help anyone 
living in a UBC 
residence or anyone
living in an apartment/house with community laundry.

## User Stories:
- As a user, I want to be able to add a washer or dryer to my laundry room
- As a user, I want to be able to view my washers and dryers
- As a user, I want to be able to mark a washer/dryer as being used
- As a user, I want to be able to mark a washer/dryer as empty
- As a user, I want to be able to mark a washer/dryer as broken
- As a user, I want to be able to remove a specific washer/dryer
- As a user, I want to be able to see how frequently a machine is being used
- As a user, I want to be able to save a laundry room to a file (if I wish)
- As a user, I want to be able to load my laundry room from a file (if I wish)

## Instructions for Grader:
- You can generate the first required action related to the user story "adding multiple Xs to a Y" 
by clicking either the add washer or add dryer button and then clicking in the panel
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by 
click the remove machine button and then clicking the machine you would like to remove
- You can locate the visual component by adding either a washer or a dryer to the laundry room
- You can save the state of my application by clicking the save button
- You can load the state of my application by clicking the load button

## Phase 4: Task 2
Mon Nov 27 22:09:21 PST 2023  
Washer added to laundry room.  
Mon Nov 27 22:09:21 PST 2023  
Washer added to laundry room.  
Mon Nov 27 22:09:23 PST 2023  
Dryer added to laundry room.  
Mon Nov 27 22:09:23 PST 2023  
Dryer added to laundry room.  
Mon Nov 27 22:09:25 PST 2023  
Dryer removed from laundry room.  
Mon Nov 27 22:09:26 PST 2023  
Washer removed from laundry room.

## Phase 4: Task 3
If I had more time for my project, I would have loved to have changed the relationship between my washer and dryer 
objects. In phase two, I combined them into one class, Machine, however, in the rest of my code, they have almost 
identical function but often in separate methods -- though refactoring these methods would technically increase coupling, 
it would be appropriate in this case as these methods often need to have identical function.
An example of this is in the Laundry class, I have two methods, frequencyWash
and frequencyDry which both return a String that states the amount of times a certain machine has been used. For this 
instance, I could have made a helper method that gives all the text returned except for the name and uses of the machine.
This would have significantly increased cohesion and made refactoring much easier. This could also be seen in my UI -- both my
console based UI and my GUI. Many of the methods would be much more cohesive and much more easily refactored if I had made helper
methods. For example, in my GUI, the AddDryerTool and AddWashingMachineTool have almost identical function, if I had made 
a parent class for them to extend, I would be much more easily able to change their function. 

