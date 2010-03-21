<?php
	error_reporting(0);

	abstract class AbstractRunner
	{
		private $exitCount = 0;
		protected $map = array();
        protected $player;
        protected $air;
		protected $score;
        protected $width;
        protected $height;

		public function move($direction)
		{
			switch ($direction)
			{
				case 0: fwrite(STDOUT, "N\n"); break;
				case 1: fwrite(STDOUT, "S\n"); break;
				case 2: fwrite(STDOUT, "E\n"); break;
				case 3: fwrite(STDOUT, "W\n"); break;
			}
		}

		public function start() {
			while($this->exitCount < 20000)
			{
				$this->exitCount++;
				$out = "";
				$line = "";

				while ($line=fgets(STDIN))
				{
					if(strpos($line, ';') !== false)
						break;
					$out.= $line;
				}

				// Parses the input
				$input = split("\n",$out);

				$player = $input[0];
				$air = $input[1];
				$score = $input[2];
				$width = $input[3];
				$height = $input[4];

				if($air == null || $width == null || $height == null)
					break;

				$map = array();
				for ($y = 0; $y < $height; $y++)
					$this->map[$y] = str_split($input[$y+5]);

				$this->run();
			}
		}

		abstract protected function run();

	}

	class AI_PHP extends AbstractRunner
	{
		 protected function run() {
		     $this->move(rand(0, 3));
		 }
	}

	$ai = new AI_PHP;
	$ai->start();


?>