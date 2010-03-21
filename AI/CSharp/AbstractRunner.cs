using System;
using System.Threading;

namespace Csharp
{
   public abstract class AbstractRunner
   {
      protected int air;
      protected int height;
      protected char[,] map;
      protected char player;
      protected int score;
      protected int width;


      protected AbstractRunner()
      {
         Start();
      }


      protected abstract void Run();

      // Try to read and parse data from stdin

      // Is end of stream?
      private static bool IsEOS()
      {
         String tile = Console.In.ReadLine();
         return (tile.ToCharArray()[0] == ';');
      }


      private bool ReadInput()
      {
         // Player 1 or player 2
         this.player = Console.In.ReadLine().ToCharArray()[0];
         // Number of airbubbels left
         this.air = int.Parse(Console.In.ReadLine());
         //Your current score
         this.score = int.Parse(Console.In.ReadLine());
         // Map width and height)
         this.width = int.Parse(Console.In.ReadLine());
         this.height = int.Parse(Console.In.ReadLine());

         // Read map into map
         if (this.air > 0 && this.width > 0 && this.height > 0)
         {
            this.map = new char[this.height,this.width];
            for (int y = 0; y < this.height; y++)
            {
               String line = Console.In.ReadLine();
               for (int x = 0; x < line.ToCharArray().Length; x++)
               {
                  char c = line.ToCharArray()[x];
                  this.map[y, x] = c;
               }
            }
            return IsEOS();
         }
         return false;
      }


      private void Start()
      {
         try
         {
            while (ReadInput())
            {
               Run();
               Thread.Sleep(10);
            }
         }
         catch
         {
            // Swallow exception
         }
      }
   }
}