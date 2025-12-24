const foo = Bun.file("input.txt");
// const foo = Bun.file("example.txt");

const lines: string[] = await foo.text().then((data) => data.trim().split("\n"));
console.log(lines);

let pos: number = 50;
let count: number = 0;
for (const line of lines) {
  const dir = line.substring(0, 1);
  if (pos === 0) {
    count++;
  }
  const num = parseInt(line.substring(1), 10);
  if (dir === "L") pos -= num;
  if (dir === "R") pos += num;
  // pos = pos >= 0 ? pos % 100 : 100 + pos;
  pos = (100 + pos) % 100;
  console.log(pos);
  // if (count++ === 10) break;
}
if (pos === 0) {
  count++;
}
console.log("Password:", count);
