using System;

namespace Csharp
{
   public class AI_Csharp : AbstractRunner
   {
      public AI_Csharp() : base() {}


      /// <summary>
      /// Runs this instance every time the program receives input.
      /// It's here you create your AI implementation
      /// </summary>
      protected override void Run()
      {
         Console.Out.WriteLine(GetRandomDirection());
      }


      /// <summary>
      /// Run program
      /// </summary>
      /// <param name="args">The args.</param>
      private static void Main(string[] args)
      {
         new AI_Csharp();
      }


      /// <summary>
      /// Gets a random direction.
      /// </summary>
      /// <returns></returns>
      private static char GetRandomDirection()
      {
         Random random = new Random();
         int direction = random.Next(0, 4);
         switch (direction)
         {
            case 0:
               return 'N';
            case 1:
               return 'S';
            case 2:
               return 'E';
            case 3:
               return 'W';
         }
         return 'N';
      }
   }
}