const foo = Bun.file("input.txt");
// const foo = Bun.file("example.txt");

const ranges: string[] = await foo.text().then((data) => data.trim().split(","));
console.log(ranges);
let sum = 0;

for (const range of ranges) {
  const parts = range.split("-");
  if (!parts[0] || parts[0].startsWith("0")) continue;
  if (!parts[1] || parts[1].startsWith("0")) continue;
  const start = parseInt(parts[0], 10);
  const end = parseInt(parts[1], 10);
  for (let i: number = start; i <= end; i++) {
    let str = i.toString();
      // create hashmap to count characters
      let charCount: { [key: string]: number } = {};
      for (let char of str) {
        charCount[char] = (charCount[char] || 0) + 1;
      }
      let has1count = false;
      let min = Infinity;
      for (let key in charCount) {
        if (!charCount[key] || charCount[key] < 2) {
          has1count = true;
          break;
        }
        if (charCount[key] < min) {
          min = charCount[key];
        }
      }
      if (has1count) continue;

      if (divideAndConquer(str, min)) {
        console.log(`Found matching number: ${i}`);
        sum += i;
        continue;
      }
  }
}

console.log(`Sum of all matching numbers: ${sum}`);

function divideAndConquer(str: string, min: number) {
  // divide string into min parts and check if all parts are equal
  const s: Set<string> = new Set();
  const len = Math.floor(str.length / min);
  for (let i = 0; i < str.length; i += len) {
    s.add(str.substring(i, i + len));
  }
  if (s.size === 1) {
    return true;
  }
  return false;
}

