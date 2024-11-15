const filePath = process.platform === 'linux' ? '/dev/stdin' : 'input.txt'
const input = require('fs')
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')

// 오른쪽, 아래로만 이동
// 도착지점에서 최대 최소 둘 다 구해야함
// (x, y)가 홀수 위치 = 연산자 / 짝수 위치 = 숫자

const n = Number(input[0])
const arr = input.splice(1).map((v) => v.split(' '))

const dx = [1, 0]
const dy = [0, 1]

let maxNum = -Infinity
let minNum = Infinity

const dfs = (x, y, num) => {

  // 도착 지점
  if (x === n-1 && y === n-1) {
    maxNum = Math.max(maxNum, num)
    minNum = Math.min(minNum, num)
    return
  }

  for (let i=0; i<2; i++) {

    const nx = x + dx[i]
    const ny = y + dy[i]

    if (nx >= n || ny >= n) continue

    if ((x+y) % 2 === 0) { // 숫자일 때
      dfs(nx, ny, num)

    } else { // 연산자일 때

      // console.log(typeof(arr[nx][ny]))
      const intNum = parseInt(arr[nx][ny]) // 다음 값 숫자 처리

      if (arr[x][y] === '*') {
        dfs(nx, ny, num * intNum)
      } else if (arr[x][y] === '+') {
        dfs(nx, ny, num + intNum)
      } else if (arr[x][y] === '-') {
        dfs(nx, ny, num - intNum)
      }
    }
  }
}

for (let i=0; i<n; i++) {
  for (let j=0; j<n; j++) {

    if ((i+j) % 2 === 0) {
      arr[i][j] = parseInt(arr[i][j])
    }
  }
}

dfs(0, 0, arr[0][0])
if(maxNum === 0) maxNum = 0
if(minNum === 0) minNum = 0
console.log(maxNum, minNum)
