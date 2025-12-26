if (!Bun.env.INPUT_FILE) {
  throw new Error("INPUT_FILE environment variable is not set.");
}
const foo = Bun.file(Bun.env.INPUT_FILE);

const lines: string[] = await foo.text().then((data) => data.trim().split("\n"));

const emptyLineIndex = lines.indexOf("");

const ranges = lines.slice(0, emptyLineIndex);

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

// Sort by start time
rangesList.sort((a, b) => a.start - b.start);

const mergedRanges: { start: number; end: number }[] = [];

for (const current of rangesList) {
  if (mergedRanges.length === 0) {
    mergedRanges.push(current);
  } else {
    const last = mergedRanges[mergedRanges.length - 1];
    
    // Check for overlap or adjacency (e.g. 1-2 and 3-4 should merge to 1-4)
    if (last && current.start <= last.end + 1) {
      last.end = Math.max(last.end, current.end);
    } else {
      mergedRanges.push(current);
    }
  }
}

let freshCount = 0;
for (const range of mergedRanges) {
  freshCount += range.end - range.start + 1;
}

console.log(`Fresh ingredients count: ${freshCount}`);