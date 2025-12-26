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
            if(j+1 < row.length && arr[i][j+1] === "@") {
                c++;
            }
            if(j-1 >= 0 && arr[i][j-1] === "@") {
                c++;
            }
            if(i+1 < arr.length && arr[i+1][j] === "@") {
                c++;
            }
            if(i-1 >= 0 && arr[i-1][j] === "@") {
                c++;
            }

            if(i+1 < arr.length && j+1 < row.length && arr[i+1][j+1] === "@") {
                c++;
            }
            if(i-1 >= 0 && j-1 >= 0 && arr[i-1][j-1] === "@") {
                c++;
            }
            if(i+1 < arr.length && j-1 >= 0 && arr[i+1][j-1] === "@") {
                c++;
            }
            if(i-1 >= 0 && j+1 < row.length && arr[i-1][j+1] === "@") {
                c++;
            }
            if(c < 4) {
                out++;
            }
        }
    }
}

console.log(arr);
console.log(out);