#include <stdio.h>
#include <stdlib.h>

/**
	Implement your AI in this function.
	This function is called whenever you are supposed to make a move.
	
	player = which player you are (1 or 2). This does not change over a run of the program
	air = the number of air points you have left
	width = width of the level, in cells. This does not change over a run of the program
	height = height of the level, in cells. This does not change over a run of the program
	level = pointer to an array containting the cells (in row-major order)

	See the compo case for more info.
*/
void RunAI(int player, int air, int score, int width, int height, char* level)
{
	// Just move in a random direction
	switch (rand()&3)
	{
   	case 0: printf("N\n"); break;
   	case 1: printf("S\n"); break;
   	case 2: printf("E\n"); break;
   	case 3: printf("W\n"); break;
	}
}

/**
	You should not change this function (unless you know what you're doing)
*/
int main(int argc, char** argv)
{
	int player, air, score, width, height, x, y;
	char map[100000];
	while (1)
	{
		if (scanf("%d\n%d\n%d\n%d\n%d\n", &player, &air, &score, &width, &height) != 5) exit(1337);
		for (y = 0; y < height; y++) gets(&map[y*width]);
		if (getchar() != ';') break;
		RunAI(player, air, score, width, height, map);
		flushall();
	}
	return 0;
}
