const filePath = process.platform === 'linux' ? '/dev/stdin' : 'input.txt';
const input = require('fs')
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n');

// 체력 => 지금 칸과 다음 칸의 높이 차이
// 체력의 최댓값을 저장 -> minheap 

const N = Number(input[0])
const graph = input.slice(1).map((v) => v.split(' ').map(Number))

const dist = [[1, 0], [-1, 0], [0, 1], [0, -1]]

// 최소 힙
class MinHeap {
  constructor() {
    this.heap = [];
  }

  isEmpty() {
    return this.heap.length === 0;
  }

  swap(idx1, idx2) {
    [this.heap[idx1], this.heap[idx2]] = [this.heap[idx2], this.heap[idx1]];
  }

  add(value) {
    this.heap.push(value);
    this.bubbleUp();
  }

  poll() {
    if (this.heap.length === 1) {
      return this.heap.pop();
    }

    const value = this.heap[0];
    this.heap[0] = this.heap.pop();
    this.bubbleDown();
    return value;
  }

  bubbleUp() {
    let index = this.heap.length - 1;
    let parentIdx = Math.floor((index - 1) / 2);
    while (
      this.heap[parentIdx] &&
      this.heap[index][0] < this.heap[parentIdx][0]
    ) {
      this.swap(index, parentIdx);
      index = parentIdx;
      parentIdx = Math.floor((index - 1) / 2);
    }
  }

  bubbleDown() {
    let index = 0;
    let leftIdx = index * 2 + 1;
    let rightIdx = index * 2 + 2;

    while (
      (this.heap[leftIdx] && this.heap[leftIdx][0] < this.heap[index][0]) ||
      (this.heap[rightIdx] && this.heap[rightIdx][0] < this.heap[index][0])
    ) {
      let smallerIdx = leftIdx;
      if (
        this.heap[rightIdx] &&
        this.heap[rightIdx][0] < this.heap[smallerIdx][0]
      ) {
        smallerIdx = rightIdx;
      }

      this.swap(index, smallerIdx);
      index = smallerIdx;
      leftIdx = index * 2 + 1;
      rightIdx = index * 2 + 2;
    }
  }
}


const pq = new MinHeap()
const visited = Array.from({ length: N }, () => Array(N).fill(Infinity))

visited[0][0] = 0
pq.add([0, 0, 0]) // [체력, x, y]

while (!pq.isEmpty()) {
  const [power, x, y] = pq.poll()

  // 도착 지점
  if (x === N - 1 && y === N - 1) {
    console.log(power)
    break
  }
  if(power > visited[x][y]) continue;

  for (const [dx, dy] of dist) {
    const nx = x + dx
    const ny = y + dy

    if (nx >= 0 && nx < N && ny >= 0 && ny < N) {

      const diff = Math.abs(graph[nx][ny] - graph[x][y])
      const nextPower = Math.max(power, diff)

      if (nextPower < visited[nx][ny]) {
        visited[nx][ny] = nextPower
        pq.add([nextPower, nx, ny])
      }
    }
  }
}