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
    if (str.length % 2 !== 0) continue;
    let left = str.substring(0, Math.floor(str.length / 2));
    let right = str.substring(Math.ceil(str.length / 2));
    if (left === right) {
      console.log(`Found matching number: ${i}`);
      sum += i;
    }
  }
}

console.log(`Sum of all matching numbers: ${sum}`);
