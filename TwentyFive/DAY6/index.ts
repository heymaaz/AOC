// give user choice to run part1 or part2
const part = prompt("Which part do you want to run? (1 or 2)");

// give user choice to run example or actual input
const input = prompt("Which input do you want to use? (a for example or b for input)");

if (input === "a") {
  Bun.env.INPUT_FILE = "example.txt";
} else {
  Bun.env.INPUT_FILE = "input.txt";
}

if (part === "1") {
  await import("./part1.ts");
} else if (part === "2") {
  await import("./part2.ts");
} else {
  console.log("Invalid choice. Please enter 1 or 2.");
}
