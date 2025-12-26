if (!Bun.env.INPUT_FILE) {
  throw new Error("INPUT_FILE environment variable is not set.");
}
const foo = Bun.file(Bun.env.INPUT_FILE);

const lines: string[] = await foo.text().then((data) => data.trim().split("\n"));
const arr = lines.map((line) => line.split(""));
if (!arr || !arr[0] || arr.length === 0 || arr[0].length === 0) {
  throw new Error("Input file is empty or improperly formatted.");
}

const hashmap: Record<string, [number, number][]> = {};
// reverse indexing hashmap
const reverseHashmap: Record<string, [number, number][]> = {};

for (let i = 0; i < arr.length; i++) {
  const row = arr[i];
  if (!row) {
    throw new Error(`Row ${i} is undefined.`);
  }
  for (let j = 0; j < row.length; j++) {
    const char = row[j];
    if (char === "@") {
      const key = `${i},${j}`;
      hashmap[key] = [];
      if (j + 1 < row.length && arr[i][j + 1] === "@") {
        hashmap[key].push([i, j + 1]);
        const reverseKey = `${i},${j + 1}`;
        if (!reverseHashmap[reverseKey]) {
          reverseHashmap[reverseKey] = [];
        }
        reverseHashmap[reverseKey].push([i, j]);
      }
      if (j - 1 >= 0 && arr[i][j - 1] === "@") {
        hashmap[key].push([i, j - 1]);
        const reverseKey = `${i},${j - 1}`;
        if (!reverseHashmap[reverseKey]) {
          reverseHashmap[reverseKey] = [];
        }
        reverseHashmap[reverseKey].push([i, j]);
      }
      if (i + 1 < arr.length && arr[i + 1][j] === "@") {
        hashmap[key].push([i + 1, j]);
        const reverseKey = `${i + 1},${j}`;
        if (!reverseHashmap[reverseKey]) {
          reverseHashmap[reverseKey] = [];
        }
        reverseHashmap[reverseKey].push([i, j]);
      }
      if (i - 1 >= 0 && arr[i - 1][j] === "@") {
        hashmap[key].push([i - 1, j]);
        const reverseKey = `${i - 1},${j}`;
        if (!reverseHashmap[reverseKey]) {
          reverseHashmap[reverseKey] = [];
        }
        reverseHashmap[reverseKey].push([i, j]);
      }

      if (i + 1 < arr.length && j + 1 < row.length && arr[i + 1][j + 1] === "@") {
        hashmap[key].push([i + 1, j + 1]);
        const reverseKey = `${i + 1},${j + 1}`;
        if (!reverseHashmap[reverseKey]) {
          reverseHashmap[reverseKey] = [];
        }
        reverseHashmap[reverseKey].push([i, j]);
      }
      if (i - 1 >= 0 && j - 1 >= 0 && arr[i - 1][j - 1] === "@") {
        hashmap[key].push([i - 1, j - 1]);
        const reverseKey = `${i - 1},${j - 1}`;
        if (!reverseHashmap[reverseKey]) {
          reverseHashmap[reverseKey] = [];
        }
        reverseHashmap[reverseKey].push([i, j]);
      }
      if (i + 1 < arr.length && j - 1 >= 0 && arr[i + 1][j - 1] === "@") {
        hashmap[key].push([i + 1, j - 1]);
        const reverseKey = `${i + 1},${j - 1}`;
        if (!reverseHashmap[reverseKey]) {
          reverseHashmap[reverseKey] = [];
        }
        reverseHashmap[reverseKey].push([i, j]);
      }
      if (i - 1 >= 0 && j + 1 < row.length && arr[i - 1][j + 1] === "@") {
        hashmap[key].push([i - 1, j + 1]);
        const reverseKey = `${i - 1},${j + 1}`;
        if (!reverseHashmap[reverseKey]) {
          reverseHashmap[reverseKey] = [];
        }
        reverseHashmap[reverseKey].push([i, j]);
      }
    }
  }
}
let out = 0;
while (true) {
  // find key with only one connection
  const keys = Object.keys(hashmap);
  let foundKey: string | null = null;
  for (const key of keys) {
    if (hashmap[key] && hashmap[key].length < 4) {
      foundKey = key;
      break;
    }
  }
  if (!foundKey) {
    break;
  }
  const connections = hashmap[foundKey];
  delete hashmap[foundKey];
  out++;
  if (!connections) {
    continue;
  }
  // remove reverse connections
  const [foundI, foundJ] = foundKey.split(",").map(Number);
  for (const connection of connections) {
    if (!connection || connection.length < 2) {
      continue;
    }
    const neighborKey = `${connection[0]},${connection[1]}`;
    if (hashmap[neighborKey]) {
      // remove foundKey from the neighbor's connections
      hashmap[neighborKey] = hashmap[neighborKey].filter(
        (conn) => !(conn[0] === foundI && conn[1] === foundJ),
      );
    }
  }

  // repeat until no more keys with less than 4 connections are found
  for (const key in hashmap) {
    if (hashmap[key] && hashmap[key].length < 4) {
      foundKey = key;
      break;
    }
  }
  if (!foundKey) {
    break;
  }
}

console.log(arr);
console.log(out);
