const foo = Bun.file("input.txt");
// const foo = Bun.file("example.txt");

const lines: string[] = await foo.text().then((data) => data.trim().split("\n"));
console.log(lines);
let sum = 0;

for (const line of lines) {
  console.log(`Processing line: ${line}`);
  const hashmap: Record<string, number[]> = {};
  for (let i = 0; i < line.length; i++) {
    const char = line.charAt(i);
    if (!hashmap[char]) {
      hashmap[char] = [];
    }
    hashmap[char].push(i);
  }
  console.log(`Character positions:`, hashmap);
  // Find the maximum two-digit number we can form
  let maxJoltage = 0;
  
  // Try all pairs of positions (i, j) where i < j
  for (let i = 0; i < line.length; i++) {
    for (let j = i + 1; j < line.length; j++) {
      const num = parseInt(line.charAt(i) + line.charAt(j), 10);
      if (num > maxJoltage) {
        maxJoltage = num;
      }
    }
  }
  
  console.log(`Max joltage for this line: ${maxJoltage}`);
  sum += maxJoltage;
}

console.log(`Sum of all matching numbers: ${sum}`);
