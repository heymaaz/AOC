if (!Bun.env.INPUT_FILE) {
  throw new Error("INPUT_FILE environment variable is not set.");
}
const foo = Bun.file(Bun.env.INPUT_FILE);

const lines: string[] = await foo.text().then((data) => data.trim().split("\n"));

const emptyLineIndex = lines.indexOf("");

const ranges = lines.slice(0, emptyLineIndex);
const ingredients = lines.slice(emptyLineIndex + 1);

const rangesList: { start: number; end: number }[] = [];
for (const range of ranges) {
  const parts = range.split("-");
  const start = Number(parts[0]);
  const end = Number(parts[1]);

  if (isNaN(start) || isNaN(end) || start > end) {
    continue;
  }
  rangesList.push({ start, end });
}

let freshCount = 0;
for (const ingredient of ingredients) {
  const num = Number(ingredient);
  // Check if num is in any of the ranges
  const isInRange = rangesList.some((r) => num >= r.start && num <= r.end);
  if (isInRange) {
    freshCount++;
  }
}

console.log(`Fresh ingredients count: ${freshCount}`);


