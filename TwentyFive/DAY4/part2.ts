if (!Bun.env.INPUT_FILE) {
  throw new Error("INPUT_FILE environment variable is not set.");
}
const foo = Bun.file(Bun.env.INPUT_FILE);

const lines: string[] = await foo.text().then((data) => data.trim().split("\n"));
const arr = lines.map((line) => line.split(""));
if (!arr || !arr[0] || arr.length === 0 || arr[0].length === 0) {
  throw new Error("Input file is empty or improperly formatted.");
}

let out = 0;
for (let i = 0; i < arr.length; i++) {
    const row = arr[i];
    if (!row) {
        throw new Error(`Row ${i} is undefined.`);
    }
    for (let j = 0; j < row.length; j++) {
        const char = row[j];
        let c = 0;
        if (char === "@") {
            out++;
        }
    }
}

console.log(arr);
console.log(out);