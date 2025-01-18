const dy = [-1, 1, 0, 0];
const dx = [0, 0, -1, 1];
const rotate = [1, -1];

const getNextPosition = ([hy, hx], [ty, tx], board, time) => {
    const arr = [];
    
    for (let i = 0; i < 4; i++) {
        const [nhy, nhx] = [hy + dy[i], hx + dx[i]];
        const [nty, ntx] = [ty + dy[i], tx + dx[i]];

        if (board[nhy][nhx] === 1 || board[nty][ntx] === 1) continue;
            
        arr.push({head: [nhy, nhx], tail: [nty, ntx], time: time + 1});
    }
    
    rotate.forEach((v) => {
        if(hy === ty) { // 가로인 경우
            if (board[hy + v][hx] ===  0 && board[ty + v][tx] === 0) {
                arr.push({head: [hy, hx], tail: [hy + v, hx], time: time + 1});
                arr.push({head: [ty + v, tx], tail: [ty, tx], time: time + 1});
            }
        }else { // 세로인 경우
            if (board[hy][hx + v] ===  0 && board[ty][tx + v] === 0) {
                arr.push({head: [hy, hx], tail: [hy, hx + v], time: time + 1});
                arr.push({head: [ty, tx + v], tail: [ty, tx], time: time + 1});
            }
        }
    })
    
    return arr;
}


function solution(board) {
    const N = board.length;
    const goal = `${N}${N}`;
    
    const newBoard = Array.from({ length: N + 2 }, () => Array(N + 2).fill(1));
    for (let i = 0; i < N; i++) {
        for (let j = 0; j < N; j++) {
            newBoard[i + 1][j + 1] = board[i][j];
        }
    }
    
    const queue = [];
    const visited = new Set();
    
    queue.push({ head: [1, 1], tail: [1, 2], time: 0 });
    const visit = new Set(["1112"]);
    visited.add("1211");
    while (queue.length) {
        const { head, tail, time } = queue.shift();
        
        if (head.join('') === goal || tail.join('') === goal) return time;
        
        const nextPos = getNextPosition(head, tail, newBoard, time);

        for (const next of nextPos) {
            const { head: nextHead, tail: nextTail } = next;
            
            const key = nextHead.join("") + nextTail.join("");
            const reverseKey = nextTail.join("") + nextHead.join("");
            if (!visited.has(key)) {
                visited.add(key);
                visited.add(reverseKey);
                queue.push(next);
            }        
        }
    }
}