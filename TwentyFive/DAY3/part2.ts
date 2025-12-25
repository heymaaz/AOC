const foo = Bun.file("input.txt");
// const foo = Bun.file("example.txt");

const lines: string[] = await foo.text().then((data) => data.trim().split("\n"));
let sum = 0n; // Use BigInt for large numbers

const DIGITS_TO_SELECT = 12;

for (const line of lines) {
  console.log(`Processing line: ${line}`);
  
  // Greedy approach: for each position, pick the highest digit that leaves
  // enough remaining characters to fill the rest
  let result = "";
  let startPos = 0; // Current position in the line to start searching from
  
  for (let digitIdx = 0; digitIdx < DIGITS_TO_SELECT; digitIdx++) {
    const digitsRemaining = DIGITS_TO_SELECT - digitIdx;
    // We need to leave at least (digitsRemaining - 1) characters after our pick
    const maxPos = line.length - digitsRemaining;
    
    // Find the highest digit in the range [startPos, maxPos]
    let bestDigit = '0';
    let bestPos = startPos;
    
    for (let pos = startPos; pos <= maxPos; pos++) {
      const digit = line.charAt(pos);
      if (digit > bestDigit) {
        bestDigit = digit;
        bestPos = pos;
      }
    }
    
    result += bestDigit;
    startPos = bestPos + 1; // Next digit must come after this one
  }
  
  console.log(`Max joltage for this line: ${result}`);
  sum += BigInt(result);
}

console.log(`Sum of all matching numbers: ${sum}`);
