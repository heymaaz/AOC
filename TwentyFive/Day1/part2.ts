const foo = Bun.file("input.txt");
// const foo = Bun.file("example.txt");

const lines: string[] = await foo.text().then((data) => data.trim().split("\n"));
// console.log(lines);

let pos: number = 50;
let count: number = 0;
for (const line of lines) {
  const startPos = pos;
  const dir = line.substring(0, 1);
  let num = parseInt(line.substring(1), 10);
  if (num >= 100) {
    count += Math.floor(num / 100);
    num = num % 100;
  }
  if (dir === "L") {
    pos -= num;
  }
  if (dir === "R") {
    pos += num;
  }
  const newPos = (100 + pos) % 100;
  if ((newPos < startPos && dir === "R" && newPos !== 0) || (newPos > startPos && dir === "L" && startPos !== 0)) {
    console.log(newPos, startPos, dir);
    console.log("Incrementing count");
    console.log("count before:", count);
    count++;
  }
  pos = newPos;
  if (pos === 0) {
    count++;
  }
}
console.log("Password:", count);
