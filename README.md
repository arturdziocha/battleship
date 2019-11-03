#TODO
HardCompluterPlayer

#Battleship Command Line Game
The battleship commandline game written in Java
##Installation
Before you start please install gradle.<br>
Clone repository

```bash
git clone git@github.com:arturdziocha/battleship.git
```
```bash
$ cd battleship/
$ gradle run
```
## Start Game
-----


Setting up the game...

```bash
Please select game mode
[0] - Human vs Human
[1] - Human vs Easy Computer
[2] - Human vs Gard Computer
```
```bash
Player 1 - give your name
```
```bash
[name of player] Would you like to set the ships yourself
[0] - Yes
[1] - Set randomly
```
Next, if you will select by yourself, you have to set ships

```bash
   A  B  C  D  E  F  G  H  I  J 
1  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
2  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
3  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
4  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
5  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
6  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
7  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
8  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
9  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
10 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
[name of player] Please select ship to place
[0]Barca - 1 long
[1]Patrol Boat - 2 long
[2]Submarine - 3 long
[3]Destroyer - 3 long
[4]Battleship - 4 long
[5]Carrier - 5 long
>>>
```
Next you setting the position and direction of the ship 

```bash
Please set the position and direction of ship Carrier with length 5
Position should be between A-J and between 1-10
Position should be like A3
>>>
```
```bash
Please set the direction of ship Carrier with length 5
[u]: UP
[r]: RIGHT
[d]: DOWN
[l]: LEFT
>>>
```
When all ships are placed you will see shot board
You cannot shot the same point, and outside board
The size of the grid is traditionally 10x10.<br>
Traditionally the rows are given the numbers 1-10<br>
and the columns are given the letters A-J<br>
eg: fire A10

```bash
[aaaa] This is your shot board
    A  B  C  D  E  F  G  H  I  J 
1  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
2  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
3  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
4  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
5  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
6  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
7  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
8  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
9  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
10 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
[aaaa] Please enter a point to shot like 'B1' Column must be between A-J, row between 1-10
>>>`
```
When you hit opponent board then you will see

```bash
[H]
```
When you miss

```bash
[M]
```
When ship is sunk then

```bash
[S]
```
Occupied neighbors

```bash
[O]
```

```bash
    A  B  C  D  E  F  G  H  I  J 
1  [M][ ][ ][ ][ ][ ][ ][ ][ ][ ]
2  [ ][ ][ ][M][ ][ ][ ][M][ ][ ]
3  [ ][ ][M][ ][O][O][O][ ][ ][ ]
4  [ ][ ][ ][M][O][S][O][M][ ][ ]
5  [M][ ][ ][M][O][S][O][ ][ ][M]
6  [ ][ ][ ][ ][O][S][O][ ][ ][ ]
7  [ ][ ][ ][ ][O][S][O][M][ ][ ]
8  [X][M][ ][ ][O][O][O][ ][ ][ ]
9  [ ][M][ ][ ][ ][ ][ ][ ][ ][ ]
10 [ ][ ][ ][ ][ ][ ][ ][ ][M][ ]
```


